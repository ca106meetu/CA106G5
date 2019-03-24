<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.meetU.live.model.LiveVO"%>
<%@page import="com.meetU.mem.model.MemService"%>
<!doctype html>
<html lang="en">
<head>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>Live-selectPage.jsp</title>

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
	<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />



	<table id="table-1">
		<tr>
			<td><h3>Live-selectPage.jsp</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>This is the Home page for Live-selectPage.jsp</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllLive.jsp'>按我顯示</a> 所有直播主列表. <br> <br></li>


		<li>
			<FORM METHOD="post" ACTION="live.do">
				<b>輸入直播主ID編號 (如M000005):</b> <input type="text" name="host_ID">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="liveSvc" scope="page"
			class="com.meetU.live.model.LiveService" />

		<li>
			<FORM METHOD="post" ACTION="live.do">
				<b>選擇直播主ID:</b> <select size="1" name="host_ID">
					<c:forEach var="liveVO" items="${liveSvc.all}">
						<option value="${liveVO.host_ID}">${liveVO.host_ID}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li><jsp:useBean id="memSvc" scope="page"
				class="com.meetU.mem.model.MemService" />

			<FORM METHOD="post" ACTION="live.do">
				<b>選擇直播主姓名:</b> <select size="1" name="host_ID">
					<c:forEach var="liveVO" items="${liveSvc.all}">
						<option value="${liveVO.host_ID}">
<%-- 							<c:set var="host_ID" value="${liveVO.host_ID}" /> --%>
							${memSvc.getOneMem(liveVO.host_ID).mem_name}
<%-- 							<%=memSvc.getOneMem((String) pageContext.getAttribute("host_ID")).getMem_name()%> --%>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM></li>
	</ul>
	<h3>直播主管理</h3>
	<ul>
		<li><a href='addLive.jsp'>增加</a>一位直播主</li>
	</ul>


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