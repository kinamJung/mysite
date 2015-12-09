<%@page import="com.hanains.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	UserVo authUser= (UserVo)session.getAttribute("authUser");
	
%>

<div id="header">
	<h1>MyHome</h1>
	<ul>
		<%
			if(authUser == null){
				
			
		%>
		<li><a href="/mysite/user?a=loginForm">로그인</a>
		<li>
		<li><a href="/mysite/user?a=joinform">회원가입</a>
		<li>
		<%
			} else{
		%>
	
	
		
		<li><a href="">회원정보수정</a>
		<li>
		<li><a href="/mysite/user?a=logout">로그아웃</a>
		<li>
		<li><%=authUser.getName() %>님 안녕하세요 ^^;</li>
		<%
		}
		%>
	
	</ul>
</div>
