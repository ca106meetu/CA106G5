<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%-- 
	MeetupService meetupSvc = new MeetupService();
	List<MeetupVO> list = meetupSvc.getAll();
	pageContext.setAttribute("list", list);
	--%>	
	<%
	List<MeetupVO> list =(List) request.getAttribute("list");
%>

<html>
<head>
<title>listAllMeetup.jsp</title>
<style>
	img{
		width:150;
		height:auto;
	}
	
	table#table-1 {
		background-color: #CCCCFF;
	    border: 2px solid black;
	    text-align: center;
	  }
	table#table-1 h4 {
	    color: red;
	    display: block;
	    margin-bottom: 1px;
	  }
	h4 {
	    color: blue;
	    display: inline;
	  }
	
	table {
		width: 800px;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	  }
	  table, th, td {
	    border: 1px solid #CCCCFF;
	  }
	  th, td {
	    padding: 5px;
	    text-align: center;
	  }

</style>
</head>

<body>
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllMeetup.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>聯誼編號</th>
		<th>聯誼名稱</th>
		<th>聯誼日期</th>
		<th>聯誼地址</th>
		<th>聯誼狀態</th>
		<th>聯誼圖片</th>
		<th>聯誼資訊</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="meetupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${meetupVO.meetup_ID}</td>
			<td>${meetupVO.meetup_name}</td>
			<td>${meetupVO.meetup_date}</td>
			<td>${meetupVO.meetup_loc}</td>
			<td>${meetupVO.meetup_status}</td> 
			<td><img class='pic' src='/CA106G52/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}'></td>
			<td>${meetupVO.meetup_info}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="meetup_ID"  value="${meetupVO.meetup_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="meetup_ID"  value="${meetupVO.meetup_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<%--呼叫VO的public getter/setter方法 不過省略get/set寫法 --%>
</body>
</html>