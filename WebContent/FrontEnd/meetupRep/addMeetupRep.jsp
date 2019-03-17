<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.meetU.meetup_report.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>This is the Home page for 檢舉聯誼內容</p>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupRep/meetupRep.do" name="form1">
	<input type="text" name="meetup_ID" value="MP000001"/><br>
	<input type="text" name="mem_ID" value="M000006" /><br>
	<b>檢舉原因</b><br>
	<input type="text" name="rep_content" placeholder="請輸入檢舉原因" /><br>
	<input type="hidden" name="rep_status" value="0">
	<input type="hidden" name="action" value="insert">
	<input type="submit" value="送出檢舉">
</FORM> 

</body>
</html>