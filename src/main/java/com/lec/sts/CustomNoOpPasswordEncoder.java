package com.lec.sts;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomNoOpPasswordEncoder implements PasswordEncoder {

	// 주어진 문자열 rawPassword 를 인코딩 하여 리턴, 일반적으로 SHA-1 혹은 그 이상의 알고리즘 활용
	@Override
	public String encode(CharSequence rawPassword) {
		System.out.println("encode 점 : " + rawPassword);
		return rawPassword.toString();
	}

	// 주어진 rawPassword 가 인코딩 된 비번(encodedPassword)과 동일한지 판정.
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		System.out.println("matches 수행 : " + rawPassword + "::" + encodedPassword);
		return rawPassword.toString().equals(encodedPassword);
	}

}

















