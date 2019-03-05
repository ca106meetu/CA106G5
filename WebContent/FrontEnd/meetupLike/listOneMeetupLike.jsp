<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.meetU.meetup_like.model.*"%>

<%
	MeetupLikeVO meetupLikeVO = (MeetupLikeVO) request.getAttribute("meetupLikeVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>只展現一支</title>
</head>
<body>
<h4><a href="select_page_Like.jsp">回首頁</a></h4>
<table>
	<tr>
		<th>聯誼編號</th>
		<th>成員名稱</th>
	</tr>
	<tr>
			<td><%=meetupLikeVO.getMeetup_ID()%></td>
			<td><%=meetupLikeVO.getMem_ID()%></td>
	</tr>
</table>
</body>
</html>