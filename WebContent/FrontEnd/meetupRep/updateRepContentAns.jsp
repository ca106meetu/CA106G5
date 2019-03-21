<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.meetU.meetup_report.*, com.meetU.meetup.model.*" %>


<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/jquery-1.12.4.min.js"></script>
	
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/sweetalert2.all.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/sweetalert2.css">

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
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
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
<FORM METHOD="post" ACTION="meetupRep.do">
<table>
	<tr>
		<th>檢舉者</th>
		<th>檢舉內容</th>
		<th>回饋處理</th>
	</tr>
	<tr>
		<td>${meetupRepVO.mem_ID}</td>
		<td>${meetupRepVO.meetup_rep_ID}:${meetupRepVO.rep_content}</td>
		<td>
			<select class="rep_ans" name="rep_ans">
				<option value="1">內容無不妥</option>
				<option value="0">聯誼被隱形</option>
			</select>
		</td>
	</tr>
</table>
	<input type="submit" class="btn btn-info" value="完成回覆" id="btnSaveAns">
	<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}"/>
	<input type="hidden" name="meetup_ID" value="${meetupRepVO.meetup_ID}"/>
	<input type="hidden" name="rep_status" value="1">
	<input type="hidden" name="action" value="update">
</FORM>  

<%-- include listOneMeetup --%>
<jsp:include page="/Templates/bootstrap4/backFooter.jsp" />
<%-- include listOneMeetup --%>

</body>
</html>