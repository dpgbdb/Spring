package bookstore.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookstore.vo.BookVO;

@Repository
public class BookDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public List<BookVO> selectbooks() {
		return mybatis.selectList("bookstore.selectbooks");
	}
	public void insertbook(BookVO vo) {
		mybatis.insert("bookstore.insertbook", vo);
	}
	
	public BookVO selectbook(int bookId) {
		return mybatis.selectOne("bookstore.selectbook", bookId);
	}
	public void updatebook(BookVO vo) {
		mybatis.update("bookstore.updatebook", vo);
	}
	public void deletebook(int bookId) {
		mybatis.delete("bookstore.deletebook", bookId);
	}
	
}
