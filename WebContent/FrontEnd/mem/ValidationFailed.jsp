<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <!-- Required meta tags -->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/sweetalert2.all.js"></script> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/sweetalert2.css"> 
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title></title>
</head>
<body>

	
<%-- 	<h1>${errorMsg}</h1> --%>


<script type="text/javascript">
$(document).ready(function(){ 
	swal({title:"驗證信錯誤訊息", html:"<h4>${errorMsg}</h4>", type:"warning", confirmButtonText:"確定"});
}); 

</script>

<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
    
</body>
</html>