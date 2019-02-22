<%@page import="com.meetU.live.model.*"%>
<%@page import="com.meetU.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-2.1.4.min.js">
	
</script>
<!DOCTYPE html>

<%
	LiveVO liveVO = (LiveVO) request.getAttribute("liveVO");
%>
<html>
<head>
<title>直播間資料修改 - update_live_input.jsp</title>

<style>
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

.pic {
	width: 200px;
	height: 200px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>直播間資料修改 - update_live_input.jsp</h3>
				<h4>
					<a href="selectPage.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="live.do" name="form1"
		enctype='multipart/form-data'>
		<table>
			<jsp:useBean id="memSvc" scope="page"
				class="com.meetU.mem.model.MemService" />
			<tr>
				<td>會員姓名:</td>
				<td><%=memSvc.getOneMem(liveVO.getHost_ID()).getMem_name()%></td>
			</tr>
			<tr>
				<td>直播主ID:</td>
				<td><%=(liveVO == null) ? " " : liveVO.getHost_ID()%></td>
			</tr>
			<tr>
				<td>直播間名稱:</td>
				<td><input type="TEXT" name="live_name" size="20"
					value="<%=(liveVO == null) ? " " : liveVO.getLive_name()%>" /></td>
			</tr>
			<tr>
				<td>累積瀏覽人數:</td>
				<td><input type="NUMBER" min="0" name="live_acc" size="20"
					value="<%=(liveVO == null) ? " " : liveVO.getLive_acc()%>" /></td>
			</tr>
			<tr>
				<td>直播間封面:</td>
				<td><input type="file" name="live_pic" onchange='readURL(this)' /><br>
					<img class='pic' src='data:img/png;base64,${encodeText}'></td>
			</tr>
			<tr>
				<td>直播間狀態:</td>
				<td><input type="NUMBER" name="live_status" min="0" max="1"
					value="<%=(liveVO == null) ? " " : liveVO.getLive_status()%>" />0:停權
					1:正常</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="host_ID" value="<%=liveVO.getHost_ID()%>">
		<input type="submit" value="送出修改">
	</FORM>
	<script>
		function readURL(input) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$(".pic").attr('src', e.target.result);

			}
			reader.readAsDataURL(input.files[0]);
		}
	</script>
</body>
</html>