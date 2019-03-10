<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.meetU.meetup_report.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>repContent.jsp</title>
</head>
<body>

<p>This is the Home page for 檢舉聯誼內容的回覆</p>
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

<FORM METHOD="post" ACTION="meetupRep.do" name="form1">
	<input type="text" name="meetup_rep_ID" value="MPREP000001"/><br>
	<b>管理員針對檢舉回覆</b><br>
	<input type="text" name="rep_ans" placeholder="針對檢舉回覆">
	<input type="hidden" name="rep_status" value="0">
	<input type="hidden" name="action" value="update">
	<input type="submit" value="完成回覆">
</FORM> 

</body>
</html>