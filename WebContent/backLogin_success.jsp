<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="Templates/bootstrap4/js/jquery-1.12.4.min.js"></script>
	
    <script src="Templates/bootstrap4/js/sweetalert2.all.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="Templates/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="Templates/bootstrap4/css/sweetalert2.css">

<title>後端登入成功首頁</title>
</head>
<body bgcolor='white'>
	<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
	<br><br><br><br><br><br>
<div class="container">
	<div class="row justify-content-center">
	  	<div class="col-6">
	  	  <center>
 		     <h3> 歡迎:<font color=red> ${empVO.emp_name} </font>您好</h3>
		  </center>
		</div>
	</div>
</div>
<br><br><br><br><br><br><br><br><br><br><br><br>
<jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

<script src="Templates/bootstrap4/popper.min.js" ></script>
    <script src="Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>