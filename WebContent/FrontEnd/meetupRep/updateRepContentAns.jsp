<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.meetU.meetup_report.*, com.meetU.meetup.model.*" %>

<%--
MeetupService meetupSvc = new MeetupService();
String meetup_ID = request.getParameter("meetup_ID");
MeetupVO meetupVO = meetupSvc.getOneMeetup(meetup_ID);
--%>

<!DOCTYPE html>
<html>
<head>
<meta>
<title>repContent.jsp</title>
<style>
img{
	 	width:150px;
	 	height:auto;
	 }
*{
	font-family:微軟正黑體;
}

table{
	border:1px solid black;
} 

th, td{
	padding: 5px;
	text-align: center;
	border:1px solid black;
}

</style>

</head>
<body>

<%-- 錯誤表列 --%>
<jsp:useBean id="meetupSvc" scope="request" class="com.meetU.meetup.model.MeetupService"/>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>聯誼名稱</th>
		<th>聯誼封面照</th>
		<th>檢舉者</th>
		<th>檢舉內容</th>
	</tr>
	<tr>
		<td>${meetupSvc.getOneMeetup(meetupRepVO.meetup_ID).meetup_name}</td>
		<td><img class='pic' src='/CA106G5/ShowPic?MEETUP_ID=${meetupRepVO.meetup_ID}'></td>
		<td>${meetupRepVO.mem_ID}</td>
		<td>${meetupRepVO.rep_content}</td>
	</tr>
</table>
	
<FORM METHOD="post" ACTION="meetupRep.do" name="form1">
  <fieldset>
    <legend>管理員針對檢舉回覆</legend>
   		<textarea name="rep_ans" rows="5" cols="100%" placeholder="針對檢舉回覆"></textarea>
		<input type="hidden" name="rep_status" value="0">
		<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}">
		<input type="hidden" name="action" value="update">
		<input type="submit" value="完成回覆">
  </fieldset>
</form>			


</body>
</html>