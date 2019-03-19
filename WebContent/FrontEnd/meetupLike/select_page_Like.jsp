<%@ page contentType="text/html ;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup.model.*"%>
<html>
<head>
<title>MeetupHome</title>
	<style>
	table#table-1 {
		width: 450px;
		background-color: #CCCCFF;
		margin-top: 5px;
		margin-bottom: 10px;
	    border: 3px ridge Gray;
	    height: 80px;
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
  </style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Meetup Home Like</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for MeetupLike Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='<%=request.getContextPath()%>/FrontEnd/meetup/meetupHomePg.jsp'>List</a>back to home page (all Meetup)</li><br><br>
	
	
	<li> <%-- 此為直接進入meetup 活動詳情 --%>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
			<b>輸入 meetup 聯誼編號(如MP000001)</b>
			<input type="text" name="meetup_ID" value="MP000001">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="查詢">
		</FORM>
	</li>
	
	<li> <%-- 此為直接進入meetup 活動詳情 --%>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do">
			<b>輸入 meetup 聯誼編號(如MP000001)</b>
			<input type="text" name="meetup_ID" value="">
			<input type="text" name="mem_ID" value="">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="查詢">
		</FORM>
	</li>
	
	<li>
		<FORM METHOD="post" ACTION="listAllMyLikeMeetup.jsp">
			<input type="text" name="mem_ID" value="M000001">的所有 like 聯誼
			<input type="submit" value="查詢" >
		</FORM>
	</li>
	
	
</ul>
<h3>員工管理</h3>

<ul>
  <li><a href='addMeetupLike.jsp'>Add</a> a new Meetup.</li>
</ul>

</body>
</html>