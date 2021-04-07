package com.lec.sts;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * 스프링에서는 자동으로 클래스 간 여러 의존주입이 발생하기 때문에
 * 다른 클래스에서 작성한 코드들을 사용할 수 있지만, JUnit 은 테스트 케이스 부분만 실행하기 때문에
 * 빈 자동 등록이나 의존주입이 일어나지 않습니다.
 * 따라서! @Service 나 @Mapper 가 붙은 클래스나 인터페이스도 쓰지 못하게 됩니다.
 * 이럴때는
 * @RunWith와 @ContextConfiguration을 이용합니다
 * 
 * 위와 같이 하면 JUnit 테스트를 실행할때 
 * @RunWith의 SpringJUnit4ClassRunner 클래스가 
 * @ContextConfiguration에 적어 놓은 파일들을 같이 실행시킵니다. 
 * root-context.xml과 security-context.xml을 실행시켜 빈 등록과 의존 주입을 실행시키는 것입니다
 */

// 참조 https://webcoding.tistory.com/entry/Spring-%EC%8A%A4%ED%94%84%EB%A7%81-JUnit%EC%9D%84-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%BD%94%EB%93%9C-%ED%85%8C%EC%8A%A4%ED%8A%B8%ED%95%98%EA%B8%B0

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
  "file:src/main/webapp/WEB-INF/spring/root-context.xml",
  "file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"
  })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)  // JUnit 의 실행 순서를 메소드 이름 순으로   @FixMethodOrder 는 JUnit5에선 지원하지 않는다.
public class MemberTests {

	// 자동 주입 받을 PasswordEncoder 와 DataSource 객체
	private PasswordEncoder pwencoder;
	private DataSource ds;
  
	public PasswordEncoder getPwencoder() {return pwencoder;}
	@Autowired
	public void setPwencoder(PasswordEncoder pwencoder) {this.pwencoder = pwencoder;}
	public DataSource getDs() {return ds;}
	@Autowired
	public void setDs(DataSource ds) {this.ds = ds;}


	Connection conn = null;
	PreparedStatement pstmt = null;
	final String SQL_INSERT_MEMBER = "INSERT INTO tbl_member(userid, userpw, username) VALUES (?,?,?)";
	final String SQL_INSERT_AUTH = "INSERT INTO tbl_member_auth (userid, auth) VALUES (?,?)";
 
	@Before // org.junit.Before
	public void initialize() {
		System.out.println("MemberTests 시작");
		try {
			conn = ds.getConnection();   // DataSource 에서 Connection 받아옴.
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} // end initialize()
  
	@Test
	public void testA_InsertMember() {
		System.out.println("testA_InsertMember() 실행");
		
		if(conn == null) return;
		
		int cnt = 0;
		
		String userid = "", userpw = "", username = "";
	
		try {
			pstmt = conn.prepareStatement(SQL_INSERT_MEMBER);
			// 100명의 테스트용 데이터 생성
			for(int i = 0; i < 100; i++) {
				userpw = "pw" + i;   // 패스워드 pw0, pw1, ... 생성
				
				// 일반사용자: 운영자: 관리자  80:10:10 명 생성
				if(i < 80) {
					userid = "user" + i;
					username = "일반사용자" + i;
				} else if (i < 90) {
					userid = "member" + i;
					username = "회원" + i;
				} else {
					userid = "admin" + i;
					username = "관리자" + i;
				}
				
				cnt = 0;
				try {
					pstmt.setString(1, userid);
					pstmt.setString(2, pwencoder.encode(userpw));  // 패스워드 인코딩 
					pstmt.setString(3, username);
					cnt = pstmt.executeUpdate();
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				if(cnt > 0) {
					System.out.println("INSERT_MEMBER 성공]" + userid + ":" + userpw + ":" + username);
				} else {
					System.out.println("INSERT_MEMBER 실패]" + userid + ":" + userpw + ":" + username);
				}
				
			} // end for
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
    
  } // end testInsertMember()
  
  @Test
  public void testB_InsertAuth() {
    System.out.println("testB_InsertAuth() 실행");
    
    if(conn == null) return;
	
	int cnt = 0;
	String userid = "", auth = "";
	
	try {
		pstmt = conn.prepareStatement(SQL_INSERT_AUTH);

		for(int i = 0; i < 100; i++) {
			if(i < 80) {
				userid = "user" + i;
				auth = "ROLE_USER";
			} else if(i < 90) {
				userid = "member" + i;
				auth = "ROLE_MEMBER";
			} else {
				userid = "admin" + i;
				auth = "ROLE_ADMIN";
			}
			cnt = 0;
			
			try {
				pstmt.setString(1, userid);
				pstmt.setString(2, auth);
				cnt = pstmt.executeUpdate();	
				
				if(cnt > 0) {
					System.out.println("INSERT_AUTH 성공] " + userid + ":" + auth);
				} else {
					System.out.println("INSERT_AUTH 실패] " + userid + ":" + auth);
				}
				
				// admin 의 경우 ROLE_MEMBER 도 추가
				if(userid.startsWith("admin")) {
					auth = "ROLE_MEMBER"; 
					pstmt.setString(1, userid);
					pstmt.setString(2, auth);
					cnt = pstmt.executeUpdate();
					
					if(cnt > 0) {
						System.out.println("INSERT_AUTH 성공] " + userid + ":" + auth);
					} else {
						System.out.println("INSERT_AUTH 실패] " + userid + ":" + auth);
					}
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}

		} // end for
		
		
	} catch(Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
  } // end testInsertAuth()
  
  @After  //org.junit.After;
  public void finalize() {
	  System.out.println("MemberTests 종료");
		try {
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
  }
  
  
} // end class



