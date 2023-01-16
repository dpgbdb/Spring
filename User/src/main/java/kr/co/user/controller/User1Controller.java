package kr.co.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user1")
public class User1Controller {

		@GetMapping("/list")
		public String list() {
			return "/user1/list";
		}
		
		@GetMapping("/register")
		public String register() {
			return "/user1/register";
		}
		
		@GetMapping("/modify")
		public String modify() {
			return "/user1/modify";
		}
}
