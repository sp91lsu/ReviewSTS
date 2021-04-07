<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Access Denied Page</title>
</head>
<body>
<h1>Access Denied 페이지</h1>
<h2><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage() }"/></h2>
<h2><c:out value="${msg }"/></h2>


</body>
</html>













