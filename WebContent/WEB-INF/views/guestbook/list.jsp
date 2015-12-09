<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="com.hanains.mysite.dao.GuestBookDAO" %>
<%@page import="com.hanains.mysite.vo.GuestBookVo" %>
<%@page import="com.hanains.mysite.vo.UserVo" %>
<% 	
	List<GuestBookVo> list = (List<GuestBookVo>)request.getAttribute("list");
	UserVo authUser= (UserVo)session.getAttribute("authUser");

%>
<!doctype html>
<html>
<head>
<title>MySite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<div id="content">
			<div id="guestbook">
				<form action="/mysite/gs" method="post">
					<input type="hidden" name="a" value="add">
					<table>
						<tr>
							<td>이름</td>				
							<td> 
							<%
								if( authUser != null )
								{
									%>
									
									<label> <%=authUser.getName() %> </label>
									<input type="hidden" name="name" value="<%=authUser.getName() %>" >
									<%
								}else
								{
									%>
									<input type="text" name="name">
									<%
								}
							%>
							
							</td>
							<td>비밀번호</td><td><input type="password" name="pass"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
				</form>
				<ul>
					<li>
						<%
		if( list != null )
		{
			for(int i = list.size() ; i > 0 ; i--){
				GuestBookVo vo = list.get(i-1);
				
				String message = vo.getMessage();
				message = message.replaceAll("\r\n", "<br>");				
	%>
	
	<table width=510 border=1>
		<tr>
			<td><%=i %></td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getDate() %></td>
			<td><a href="/mysite/gs?no=<%=vo.getNo()%>&a=deleteForm">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4 ><%=message%></td>
		</tr>
	</table>
		<br>
	<%
			}
		}
	%>
						<br>
					</li>
				</ul>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navigation.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
	</div>
</body>
</html>