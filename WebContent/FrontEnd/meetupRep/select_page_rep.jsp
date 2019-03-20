<%@ page contentType="text/html ;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<p>This is the Home page for Meetup Home</p>

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
	<li><a href='listAllRep.jsp'>List</a>all Meetup</li><br><br>
	
	<%--另一種寫法 --%>
	<button type="submit" onclick="location='listAllRep.jsp';" value="submit">all Meetup</button>
	
	<li>
		<FORM METHOD="post" ACTION="meetupRep.do">
			<b>輸入meetup聯誼檢舉編號(如MPREP000013)</b>
			<input type="text" name="meetup_rep_ID" value="MPREP000001">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="查詢">
		</FORM>
	</li>
	
	<li>
		<FORM METHOD="post" ACTION="meetupRep.do">
			<b>輸入meetup聯誼檢舉編號(如MPREP000013)</b>
			<input type="text" name="meetup_rep_ID" value="MPREP000001">
			<input type="hidden" name="action" value="getOne_For_Update">
			<input type="submit" value="查詢">
		</FORM>
	</li>
	
</ul>
<h3>員工管理</h3>

<ul>
  <li><a href='addMeetupRep.jsp'>Add</a> a new Meetup.</li>
</ul>

</body>
</html>
