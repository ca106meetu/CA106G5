<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.meetU.mem.model.*"%>
<%
  String mem_ID = (String) session.getAttribute("mem_ID");
  MemVO memVO = new MemService().getOneMem(mem_ID);
%>
<!DOCTYPE html>
<html>
<head><meta charset="utf-8">
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
<title>Insert title here</title>
</head>
<body>

<!-- 	<h1>驗證信已送出</h1> -->
<!-- 	<p>提醒您驗證信只有最新的一封有效</p> -->
<script type="text/javascript">
$(document).ready(function(){ 
	//swal({title:"驗證信已送出", html:"<h4>提醒您驗證信只有最新的一封有效</h4>", type:"success", confirmButtonText:"確定"});
// 	swal({title:"驗證信已送出", 
// 	      html:"<h4><a id='link' href='mailto:who@yahoo.com.tw'>提醒您驗證信只有最新的一封有效</a></h4>",
// 		  type:"success",
// 		  confirmButtonText:"確定"}).then(
// 	        function (result) {
// 				if (result.value) {
// 	                //使用者按下「確定」要做的事
// 	                //swal("完成!", "資料已經刪除", "success");
// 					$('#link').trigger('click');
// 	            } 
// 	        });//end then 
// 	});

	swal({title:"驗證信已送出", html:"<h4>提醒您驗證信只有最新的一封有效</h4>", type:"success"}).then(function (result) {
        //導頁寫在此
        //window.location.href = "mailto:bell0920tw@gmail.com";
        window.location.href = "<%=request.getContextPath()%>/FrontEnd/lorenTest/test.jsp";
    }); 

}); 


</script>

<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>