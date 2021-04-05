package com.lec.sts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	// 예제 테스트 계정
	public static final String ADMIN_ID = "admin";
	public static final String ADMIN_PW = "1234";
	
	@RequestMapping("/login")
	public void login() {}   //   /user/login.jsp
	
	@RequestMapping(value = "/loginOk", method=RequestMethod.POST)
	public String loginOk(String id, String pw, HttpSession session) {
		String returnURL = "user/logfail";
		
		if(session.getAttribute("id") != null) {
			// 로그인 세션 정보가 있었다면.
			session.removeAttribute("id");  // 일단 기존의 로그인 세션 정보는 삭제
		}
		
		if(ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)) {
			// 로그인 성공
			session.setAttribute("id", id);  // 세션에 추가!
			
			// 원래 가고자 했던 url 이 있었다면
			String priorUrl = (String)session.getAttribute("url_prior_login");
			if(priorUrl != null) {
				returnURL = "redirect:" + priorUrl;
				session.removeAttribute("url_prior_login");
			} else {
				returnURL = "redirect:/board/list.do";
			}
		}
		
		return returnURL;
	}
	
	// 로그아웃
	@RequestMapping("/logout")
	public void logOut(HttpSession session) {
		session.removeAttribute("id");
	}
	
}











