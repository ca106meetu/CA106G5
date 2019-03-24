<%@page import="com.meetU.filerec.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<%


	FileRecVO filerecVO = (FileRecVO) request.getAttribute("fileRecVO");
  	String host_ID =request.getParameter("host_ID");
  	pageContext.setAttribute("host_ID", host_ID);



	
%>
<!DOCTYPE html>
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

<title>新增影片</title>


<style>
table#table-1 {
width:auto;
	background-color:#FFCC22;
	border: 2px solid black;
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



table {
	width:300px;
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
.bg-warning {
    
    margin-top: 150px;
}
</style>

</head>
<body>
	<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<div class="container-fluid">
			<div class="row">
				
				<div class="col-4"><div class="xxx"></div></div>
				<div class="col-4">
				
				
	
				
				<table id="table-1">
		<tr>
			<td>
				<h3>新增影片</h3>
			</td>
			<td>
				<h4>
					<a href='fileRecHome.jsp?host_ID=<%=host_ID%>'><img src="<%=request.getContextPath()%>/FrontEnd/live/images/back1.gif"	width="100" height="32">回影片區</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>填寫影片資料:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>



	<FORM METHOD="post" ACTION="fileRec.do" name="form1"
		enctype='multipart/form-data'>
		<table>
			<jsp:useBean id="memSvc" scope="page"
				class="com.meetU.mem.model.MemService" />
			<tr>
				<td>會員姓名:</td>
				<td >${memSvc.getOneMem(host_ID).getMem_name()}
				<input type='hidden' name='host_ID' value='${host_ID}'> 
				</td>
			</tr>
			<tr>
				<td>影片名稱:</td>
				<td><input type="TEXT" name="file_name" size="20"
					value="<%=(filerecVO == null) ? " " : filerecVO.getFile_name()%>" /></td>
			</tr>
			<tr>
				<td>影片描述:</td>
				<td><input type="TEXT" name="live_des" size="20"
					value="<%=(filerecVO == null) ? " " : filerecVO.getLive_des()%>" /></td>
			</tr>
			<tr>
				<td>影片網址:</td>
				<td><input type="TEXT" name="file_cont" size="20"
					value="<%=(filerecVO == null) ? " " : filerecVO.getFile_cont()%>" /></td>
			</tr>
		
		

		</table>
		<br> <input type="hidden" name="action" value="insertfile"> <input
			type="submit" value="送出新增">
	</FORM></div>
				<div class="col-4"><div class="xxx"></div></div>
				
			</div>
		</div>







	
	<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
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