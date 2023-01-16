package bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookstore.dao.BookDAO;
import bookstore.vo.BookVO;

@Service
public class Bookservice {
		
		@Autowired
		private BookDAO dao;
		
		public void insertbook(BookVO vo) {
			dao.insertbook(vo);
		}
		public List<BookVO> selectbooks() {
			return dao.selectbooks();
		}
		
		public BookVO selectbook(int bookId) {
			return dao.selectbook(bookId);
		}
		public void updatebook(BookVO vo) {
			dao.updatebook(vo);
		}
		public void deletebook(int bookId) {
			dao.deletebook(bookId);
		}
}
