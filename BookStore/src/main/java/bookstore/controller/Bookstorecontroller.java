package bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bookstore.service.Bookservice;
import bookstore.service.Customerservice;
import bookstore.vo.BookVO;
import bookstore.vo.CustomerVO;

@Controller
public class Bookstorecontroller {
	
	@GetMapping(value = {"/", "/index"})
		public String index() {
			return "/index";
		}
	@Autowired
	private Bookservice service;
	@Autowired
	private Customerservice cservice;
	
	@GetMapping("/book/list")
	public String list(Model model) {
		List<BookVO> books = service.selectbooks();
		model.addAttribute("books", books);
		return "/book/list";
	}
	@GetMapping("/book/register")
	public String register() {
		return "/book/register";
	}
	@PostMapping("/book/register")
	public String register(BookVO vo) {
		service.insertbook(vo);
		return "redirect:/book/list";
	}
	
	@GetMapping("/book/modify")
	public String modify(int bookId, Model model) {
		BookVO book = service.selectbook(bookId);
		model.addAttribute("book",book);
		return "/book/modify";
	}
	
	@PostMapping("/book/modify")
	public String modify(BookVO vo) {
		service.updatebook(vo);
		return "redirect:/book/list";
	}
	@GetMapping("/book/delete")
	public String delete(int bookId) {
		service.deletebook(bookId);
		return "redirect:/book/list";
	}
	
	@GetMapping("/customer/list")
	public String list2(Model model) {
		List<CustomerVO> customers = cservice.selectcustomers();
		model.addAttribute("customers", customers);
		return "/customer/list";
	}
	@GetMapping("/customer/register")
	public String register2() {
		return "/customer/register";
	}
	@PostMapping("/customer/register")
	public String register2(CustomerVO vo) {
		cservice.insertcustomer(vo);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/customer/modify")
	public String modify2(int custId, Model model) {
		CustomerVO customer = cservice.selectcustomer(custId);
		model.addAttribute("customer",customer);
		return "/customer/modify";
	}
	
	@PostMapping("/customer/modify")
	public String modify2(CustomerVO vo) {
		cservice.updatecustomer(vo);
		return "redirect:/customer/list";
	}
	@GetMapping("/customer/delete")
	public String delete2(int custId) {
		cservice.deletecustomer(custId);
		return "redirect:/customer/list";
	}
}
