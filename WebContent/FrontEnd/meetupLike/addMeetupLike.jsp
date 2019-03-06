<%@ page contentType="text/html ;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup_like.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add one more meetup to like List</title>
</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="msg" items="${errorMsgs}">
			<li style="color:red">${msg}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do" enctype='multipart/form-data'>
<table>
	<tr>
		<th>聯誼名稱</th>
		<td><input type="text" name="meetup_ID" size="45" value="MP000001"/></td>
	</tr>
	
	<tr>
		<th>成員名稱</th>
		<td><input type="text" name="mem_ID" size="45" value="M000001"/></td>
	</tr>	
</table>
<br>
<input type="hidden" name="action" value="insert">
<button type="submit" >送出新增</button>
</FORM>

</body>
</html>