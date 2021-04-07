package com.lec.sts;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	// 로그인 성공 하면 호출되는 메소드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("[로그인 성공!] onAuthenticationSuccess() 호출");
		
		// Authentication 객체를 이용해서 사용자가 가진 모든 권한을 문자열로 체크 가능.
		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(auth -> {
			roleNames.add(auth.getAuthority());
		});
		
		System.out.println("ROLE NAMES: " + roleNames);
		
		// 만약 사용자가 ROLE_ADMIN 권한을 가졌다면, 로그인 직후 곧바로 /sample/admin 으로 이동
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect(request.getContextPath() + "/sample/admin");
			return;
		}
		
		if(roleNames.contains("ROLE_MEMBER")) {
			response.sendRedirect(request.getContextPath() + "/sample/member");
			return;
		}
		
		response.sendRedirect(request.getContextPath());
	}
	
	

}








