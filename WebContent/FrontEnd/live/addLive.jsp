<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.meetU.live.model.*"%>
<%@page import="com.meetU.mem.model.*"%>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<!doctype html>
<%
	LiveVO liveVO = (LiveVO) request.getAttribute("liveVO");
%>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>直播間新增 - addLive.jsp</title>


<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

.pic {
	width: 200px;
	height: 200px;
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

<style>
table {
	width: 500px;
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
<body>
	<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />


	<table id="table-1">
		<tr>
			<td>
				<h3>直播間新增 - addLive.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="selectPage.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

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
				<td><select size="1" name="host_ID">
						<c:forEach var="memVO" items="${memSvc.all}">
							<option value="${memVO.mem_ID}"
								${(memVO.mem_ID==liveVO.host_ID)? 'selected': '' }>${memVO.mem_name}
						</c:forEach>
				</select></td>
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
					<img class='pic' src='data:img/png;base64,${encodeText}'
					${(liveVO.live_pic==null) ? 'style="display:none"' : ''}></td>
			</tr>
			<tr>
				<td>直播間狀態:</td>
				<td><input type="NUMBER" name="live_status"
					value="<%=(liveVO == null) ? " " : liveVO.getLive_status()%>" /></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
	<script>
		function readURL(input) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$(".pic").attr('src', e.target.result).css("display", "");

			}
			reader.readAsDataURL(input.files[0]);
		}
	</script>
	<jsp:include page="/Templates/bootstrap4/backFooter.jsp" />
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>