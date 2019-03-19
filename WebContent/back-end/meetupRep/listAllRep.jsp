<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_report.model.*, com.meetU.meetup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<% 
	MeetupRepService meetupRepSvc = new MeetupRepService();
	List<MeetupRepVO> list = meetupRepSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta>
<title>Meetup Report HomePage</title>

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
		 <h3>所有員工資料 - listAllMeetupRep.jsp</h3>
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
		<th>檢舉狀態</th>
		<th>檢舉編號</th>
		<th>聯誼名稱</th>
		<th>檢舉者</th>
		<th>檢舉時間</th>
		<th>檢舉內容</th>		
	</tr>	
	<%@ include file="page1.file"%>
<c:forEach var="meetupRepVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<td>${meetupRepVO.rep_status}</td>
		<td>${meetupRepVO.meetup_rep_ID}</td>
		<td>${meetupRepVO.meetup_ID}</td>
		<td>${meetupRepVO.mem_ID}</td>
		<td>${meetupRepVO.rep_date}</td>
		<td>
			<FORM METHOD="POST" ACTION="back-end/meetupRep/meetupRep.do" >	
				<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="button" value="查看檢舉原因">	
			</FORM>
		</td>		
	</tr>
</c:forEach>
</table>	
<%@ include file="page2.file" %>	

<%--<%@ jsp:include page="/repContent.jsp" flush="true"%>--%>

</body>
</html>