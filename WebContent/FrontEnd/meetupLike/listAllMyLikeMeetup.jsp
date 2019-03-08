<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup_like.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	MeetupLikeService meetupLikeSvc = new MeetupLikeService();
	//String mem_ID = request.getParameter("mem_ID");
	List<MeetupLikeVO> list = meetupLikeSvc.getAll("M000001");
	pageContext.setAttribute("list", list);
%>


<title>all my like</title>
</head>
<body>
<%@ include file="page1.file" %>
<table>
	<tr>
		<th>聯誼編號</th>
		<th>成員編號</th>
		<th>取消like</th>
	</tr>	
	<c:forEach var="meetupLikeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
		<tr>
			<td>${meetupLikeVO.meetup_ID}</td>
			<td>${meetupLikeVO.mem_ID}</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="meetup_ID"  value="${meetupLikeVO.meetup_ID}">
			     <input type="hidden" name="mem_ID"  value="${meetupLikeVO.mem_ID}">
			     <input type="hidden" name="action" value="delete">
			    </FORM>
			 </td>   
		</tr>
	</c:forEach>
</table>	
<%@ include file="page2.file" %>

</body>
</html>