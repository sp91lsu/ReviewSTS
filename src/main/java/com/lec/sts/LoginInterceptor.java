package com.lec.sts;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// session 객체 가져오기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		
		// 컨트롤러 실행전
		System.out.println("[preHandle] " + id);
		
		if(id == null) {
			// 직전 요청 url 을 세션에 기록
			String urlPrior = 
					request.getRequestURL().toString() + "?" + request.getQueryString();
			session.setAttribute("url_prior_login", urlPrior);
			
			
			// 만약 로그인이 되어 있지 않다면, 로그인 페이지로 redirect
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;  // ★ 더 이상 컨트롤러 핸들러 진행하지 않도록 false 리턴
		}
		
		return true; // ★ 컨트롤러 계속 진행!
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 실행후, 뷰 직전
		System.out.println("[postHandle]");
		
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰 실행후 (response 후)
		System.out.println("[afterCompletion]");
		super.afterCompletion(request, response, handler, ex);
	}

}
