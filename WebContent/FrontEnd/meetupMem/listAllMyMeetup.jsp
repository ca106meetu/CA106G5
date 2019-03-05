<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_mem.model.*"%>
<%@ page import="com.meetU.meetup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MeetupMemService meetupMemSvc = new MeetupMemService();
	String mem_ID = request.getParameter("mem_ID");
	List<MeetupMemVO> listAll = meetupMemSvc.getMyAllMeetup(mem_ID);
	pageContext.setAttribute("listAll", listAll);
%>

<html>
<head>
<title>listAllMyMeetup.jsp</title>
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
		 <h3>所有員工資料 - listAllMyMeetup.jsp</h3>
		 <h4><a href="select_page_mem.jsp">回首頁</a></h4>
	</td></tr>
</table>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="msg" items="${errorMsgs}">
			<li style="color:red">${msg}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>聯誼編號</th>
<%--		<th>圖片</th>--%>
		<th>聯誼評價</th>
		<th>評價內容</th>
		<th>修改</th>
		<th>退出</th>
	</tr>
	
	<%@ include file="page1.file" %>
	
	<c:forEach var="meetupMemVO" items="${listAll}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
		<tr>
			  <FORM METHOD="post" ACTION="meetupMem.do" style="margin-bottom: 0px;">
<%-- 			<td>${meetupMemVO.meetup_ID}</td> --%>
			<td><img src='/CA106G52/ShowPic?MEETUP_ID=${meetupMemVO.meetup_ID}'></td>
			<td><input type="text" name="meetup_rate"  value="${meetupMemVO.meetup_rate}"></td> <%-- 想做預設--%>
			<td><input type="text" name="meetup_comment"  value="${meetupMemVO.meetup_comment}"></td>
			<td>
			     <input type="submit" value="送出評價">
			     <input type="hidden" name="meetup_ID"  value="${meetupMemVO.meetup_ID}">
			     <input type="hidden" name="mem_ID"  value="${meetupMemVO.mem_ID}">
			     <input type="hidden" name="action"	value="update">
			</td>
			     </FORM>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/meetupMem/meetupMem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="退出">
			     <input type="hidden" name="meetup_ID"  value="${meetupMemVO.meetup_ID}">
			     <input type="hidden" name="mem_ID"  value="${meetupMemVO.mem_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>	
<%@ include file="page2.file" %>
</body>
</html>
