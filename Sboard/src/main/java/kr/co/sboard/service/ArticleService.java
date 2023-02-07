package kr.co.sboard.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import kr.co.sboard.utils.PageHandler;
import kr.co.sboard.vo.SearchCondition;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.repository.ArticleRepo;
import kr.co.sboard.vo.ArticleVO;
import kr.co.sboard.vo.FileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleService {

	@Autowired
	private ArticleDAO dao;
	
	@Autowired
	private ArticleRepo repo;

	public void getArticles(Model m, SearchCondition sc){
		log.info("ArticleService getArticles...");

		int totalCnt = dao.countAll(sc);

		/** 검색 페이지 > 전체 페이수일 경우 실행 **/
		// 전체 페이지수
		int totalPage = (int)Math.ceil(totalCnt/(double)sc.getPageSize());

		// 전체 페이지수가 현재 페이지수 보다 크면 전체 페이지수로 값 저장
		if(sc.getPage() > totalPage) sc.setPage(totalPage);
		/**                                 **/

		PageHandler pageHandler = new PageHandler(totalCnt, sc);  // 페이징 처리
		List<ArticleVO> articles =  dao.selectAll(sc); 			  // 게시물 조회

		log.info(articles.toString());

		m.addAttribute("ph", pageHandler);
		m.addAttribute("articles", articles);
	};

	public ArticleVO getArticle(int no) {
		log.info("ArticleService getArticle...");
		return dao.select(no);
	};



	public int countAll(SearchCondition sc) {
		return dao.countAll(sc);
	}


	@Transactional
	public int insertArticle(ArticleVO vo) {
		// 글 등록
		int result = dao.insertArticle(vo);
		// 파일 업로드
		FileVO fvo = fileUpload(vo);
		// 파일 등록
		if(fvo != null) {
			dao.insertFile(fvo);
		}
		
		return result;	
	}
	
	@Transactional
	public FileVO selectFile(int fno) {
		FileVO vo = dao.selectFile(fno);
		dao.updateDownload(fno);
		return vo;
	}
	
//	public ArticleVO selectArticle(int no) {
//		return dao.selectArticle(no);
//	}
//	public List<ArticleVO> selectArticles(int start) {
//		return dao.selectArticles(start);
//	}
	public int updateArticle(ArticleVO vo) {
		return dao.updateArticle(vo);
	}
	public int deleteArticle(int no) {
		return dao.deleteArticle(no);
	}

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	public FileVO fileUpload(ArticleVO vo) {
		// 첨부 파일
		MultipartFile file = vo.getFname();
		FileVO fvo = null;
		
		if(!file.isEmpty()) {
			// 시스템 경로
			String path = new File(uploadPath).getAbsolutePath();
			
			// 새 파일명 생성
			String oName = file.getOriginalFilename();
			String ext = oName.substring(oName.lastIndexOf("."));
			String nName = UUID.randomUUID().toString()+ext;
			
			// 파일 저장
			try {
				file.transferTo(new File(path, nName));
			} catch (IllegalStateException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
			
			fvo = FileVO.builder()
					.parent(vo.getNo())
					.oriName(oName)
					.newName(nName)
					.build();
		}
		
		return fvo;
	}
	
	public ResponseEntity<Resource> fileDownload(FileVO vo) throws IOException {
		
		Path path = Paths.get(uploadPath+vo.getNewName());
		String contentType = Files.probeContentType(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition
										.builder("attachment")
										.filename(vo.getOriName(), StandardCharsets.UTF_8)
										.build());
		
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);		
	}
	/*
	// 페이지 시작값
	public int getLimitStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	// 현재 페이지 
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	// 전체 게시물 갯수
	public long getTotalCount() {
		return dao.selectCountTotal();
	}
	
	// 마지막 페이지 번호
	public int getLastPageNum(long total) {
		
		int lastPage = 0;
		
		if(total % 10 == 0) {
			lastPage = (int) (total / 10);
		}else {
			lastPage = (int) (total / 10) + 1;
		}
		
		return lastPage;
	}
	
	// 페이지 시작 번호
	public int getPageStartNum(long total, int start) {
		return (int) (total - start);
	}
		
	// 페이지 그룹
	public int[] getPageGroup(int currentPage, int lastPage) {
		
		int groupCurrent = (int) Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPage) {
			groupEnd = lastPage;
		}
		
		int[] groups = {groupStart, groupEnd};
		
		return groups;
	}
	 */
	
}