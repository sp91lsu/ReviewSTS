<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
uid: ${w.uid}<br>
작성자: ${w.name}<br>
제목: ${w.subject }<br>
<button onclick="history.back()">돌아가기</button>