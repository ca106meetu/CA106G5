<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MeetupMemHome</title>

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
<body>
<table id="table-1">
   <tr><td><h3>Meetup Comment Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Meetup Comment Home</p>

<h3>資料查詢:</h3>
<%-- 錯誤表列 --%>

<c:if test = "${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="msg" items="${errorMsgs}">
			<li style="color:red">${msg}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<%-- 該聯誼的所有成員 --%>
	<li>
		<FORM METHOD="post" ACTION="meetupMem.do">
			聯誼<input type="text" name="meetup_ID" value="MP000001">的所有成員
			<input type="hidden" name="action" value="list_AllMeetup_Mem">
			<button type="submit" onclick="location='listAllMeetupMem.jsp';">查詢</button>
		</FORM>
	</li>
	<%-- 該聯誼的所有成員 --%>
	
	<%-- 該成員的所有聯誼 --%>
	<li>
		<FORM METHOD="post" ACTION="listAllMyMeetup.jsp">
			<b>成員</b><input type="text" name="mem_ID" value="M000001">的所有聯誼
			<input type="hidden" name="action" value="list_All_MyMeetup">
			<input type="submit" value="查詢">
		</FORM>
	</li>
	<%-- 該成員的所有聯誼 --%>
	
	<%-- 該成員的單一聯誼寫評價; 用到連動式下拉是選單 --%>
	<jsp:useBean id="meetupSvc" scope="page" class="com.meetU.meetup.model.MeetupService"/>
	
	<li>
		<FORM METHOD="POST" action="meetupMem.do">
			<b>選擇聯誼編號</b>
			<select size="1" name="meetup_ID">
				<c:forEach var="meetupVO" items="${meetupSvc.all}">
					<option value="${meetupVO.meetup_ID}">${meetupVO.meetup_name}
				</c:forEach>
			</select>
			<b>選擇成員編號</b>
			<input type="text" name="mem_ID" value="M000001">
			<input type="hidden" name="action" value="getOne_For_Display">
			<input type="submit" value="查詢">
		</FORM>	
	</li>
	<%-- 該成員的單一聯誼寫評價 --%>
	
	
	<li>
		<FORM METHOD="POST" action="meetupMem.do">
			<b>選擇聯誼編號</b>
			<select size="1" name="meetup_ID">
				<c:forEach var="meetupVO" items="${meetupSvc.all}">
					<option value="${meetupVO.meetup_ID}">${meetupVO.meetup_name}
				</c:forEach>
			</select>
			<input type="hidden" name="action" value="list_AllMeetup_Mem">
       		<input type="submit" value="查詢">
		</FORM>
	</li>
</ul>
<h3>員工管理</h3>

<ul>
  <li><a href='addMeetupMem.jsp'>Add</a> a new Meetup rate & comment.</li>
</ul>

</body>
</html>