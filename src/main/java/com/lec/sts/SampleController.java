package com.lec.sts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/sample/*")
public class SampleController {
	
	@GetMapping("/all")    // url : /sample/all
	public void doAll() {  // jsp : /sample/all.jsp
		System.out.println("doAll() : access 개나소나");
	}
	
	@GetMapping("/member")
	public void doMember(HttpSession session, HttpServletRequest request) {
		System.out.println("doMember() : access 회원만");
	}
	
	@GetMapping("/admin")
	public void doAdmin(HttpSession session, HttpServletRequest request) {
		System.out.println("doAdmin() : access 관리자 only");
	}

}















