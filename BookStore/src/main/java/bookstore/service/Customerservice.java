package bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bookstore.dao.CustomerDAO;
import bookstore.vo.BookVO;
import bookstore.vo.CustomerVO;

@Service
public class Customerservice {
		
		@Autowired
		private CustomerDAO dao;
		
		public void insertcustomer(CustomerVO vo) {
			dao.insertcustomer(vo);
		}
		public List<CustomerVO> selectcustomers() {
			return dao.selectcustomers();
		}
		
		public CustomerVO selectcustomer(int custId) {
			return dao.selectcustomer(custId);
		}
		public void updatecustomer(CustomerVO vo) {
			dao.updatecustomer(vo);
		}
		public void deletecustomer(int custId) {
			dao.deletecustomer(custId);
		}
}
