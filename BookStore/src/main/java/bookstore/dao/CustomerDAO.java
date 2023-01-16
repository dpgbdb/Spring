package bookstore.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bookstore.vo.CustomerVO;


@Repository
public class CustomerDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public List<CustomerVO> selectcustomers() {
		return mybatis.selectList("bookstore.selectcustomers");
	}
	public void insertcustomer(CustomerVO vo) {
		mybatis.insert("bookstore.insertcustomer", vo);
	}
	
	public CustomerVO selectcustomer(int custId) {
		return mybatis.selectOne("bookstore.selectcustomer", custId);
	}
	public void updatecustomer(CustomerVO vo) {
		mybatis.update("bookstore.updatecustomer", vo);
	}
	public void deletecustomer(int custId) {
		mybatis.delete("bookstore.deletecustomer", custId);
	}
	
}
