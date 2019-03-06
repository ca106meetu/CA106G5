<%@page import="java.util.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String host_ID =request.getParameter("host_ID");
	FileRecService fileRecSvc = new FileRecService();
	List<FileRecVO> list = fileRecSvc.getOneFileRec(host_ID);
	pageContext.setAttribute("list", list);
%>
<!doctype html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>直播主影片(前端)-fileRecHome.jsp</title>
<style>
.btn-primary {
    color: #fff;
    background-color: 	#FFBB00;
    border-color: 	#FFBB00;
}
#live_like {
	position: fixed;
	right: 0;
	top: 50%;
	width: 8em;
	margin-top: -2.5em;
}
#live_rep {
	position: fixed;
	right: 0;
	top: 57%;
	width: 8em;
	margin-top: -2.5em;
}

.pic {
	width: 172.5px;
	height: 230px;
}

table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #FFBB00;
}

th {
	padding: 2px;
	text-align: center;
}

td {
	width: 150PX;
	height: 200PX;
	padding: 2px;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

	<table id='table-1'>
		<tr>
			<td>
				<h3>直播主影片(前端)-fileRecHome.jsp</h3>
				<h4>
					<a href='<%=request.getContextPath()%>/FrontEnd/live/liveHome2.jsp?host_ID=<%=host_ID%>'>
						<img
						src="<%=request.getContextPath()%>/FrontEnd/live/images/back1.gif"
						width="100" height="32">
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>直播主姓名</th>
			<th>影片名稱</th>
			<th>影片描述</th>
			<th>影片內容</th>
			<th>影片上架時間</th>
			<th>影片觀看人數</th>

		</tr>
		<%@ include file="page1.file"%>

		<jsp:useBean id="memSvc" scope="page"
			class="com.meetU.mem.model.MemService" />




		<c:forEach var="fileRecVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${memSvc.getOneMem(fileRecVO.host_ID).mem_name}</td>
				<td>${fileRecVO.file_name}</td>
				<td>${fileRecVO.live_des}</td>
				<td><iframe height="100%" src="${fileRecVO.file_cont}"
						allowfullscreen></iframe></td>
				<td><fmt:formatDate value="${fileRecVO.file_date}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${fileRecVO.file_pop}</td>
			</tr>

		</c:forEach>
	</table>
	<div id='live_like'>
		<a class="btn btn-primary" href="" role="button">收藏直播主</a>
	</div>
	<div id='live_rep'>
		<a class="btn btn-primary" href="" role="button">檢舉直播主</a>
	</div>
	
	
	<%@ include file="page2.file"%>

	<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>

