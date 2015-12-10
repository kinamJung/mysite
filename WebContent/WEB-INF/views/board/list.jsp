<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page import="com.hanains.mysite.vo.UserVo"%>
<% 	
	UserVo authUser= (UserVo)session.getAttribute("authUser");
%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
				
					<c:set value="${fn:length(list) }" var="count"></c:set>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<tr>
							<td> ${vo.articleSequence } </td>
							<td> <a href="/mysite/bs?a=boardView&no=${vo.no}&memberNo=${vo.memberNo}">${vo.title}</a>   </td>
							<td>${vo.name}</td> 
							<td>${vo.viewCount}</td>
							<td>${vo.date}</td>
							<c:if test="${ vo.memberNo eq authUser.no }">
								<td><a href="/mysite/bs?a=delete&no=${vo.no}" class="del"><font color="black">삭제</font></a></td>
							</c:if>
							
						</tr>
					</c:forEach>
				
					
				</table>
				<div class="pager">
					<ul>
						<li class="pg-prev"><a href="#">◀ 이전</a></li>
						
						<c:forEach begin="1" end="5" varStatus="status">
							<c:choose>
								<c:when test="${size >= status.count}">
									<li><a href='/mysite/bs?a=displayPaging&index=${status.index}&size=${size}'>${status.index}</a></li>
								</c:when>
								<c:otherwise>
									<li class="disable">${status.index }</li>
								</c:otherwise>
							</c:choose>
		
						</c:forEach>
					
						<li class="pg-next"><a href="#">다음 ▶</a></li>
					</ul>
				</div>
				<c:if test="${ not empty authUser }">
					<div class="bottom">
							<a href="/mysite/bs?a=writeForm&no=${authUser.no}" id="new-book">글쓰기</a>
						</div>
				</c:if>
				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>