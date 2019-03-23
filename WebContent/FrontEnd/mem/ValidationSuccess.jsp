<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.meetU.mem.model.*"%>
<%
  String mem_ID = (String) session.getAttribute("mem_ID");
  MemVO memVO = new MemService().getOneMem(mem_ID);
%>
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

<title>Insert title here</title>
</head>
<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<h1>${memVO.mem_acc}你好! 您的信箱${memVO.mem_email}已經通過驗證!</h1>

<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
<script> 

$(document).ready(function(){
	var url = "<%=request.getContextPath()%>/FrontEnd/mem/reg_mem_input.jsp";
	var s_url = new String(url);
	
	//alert(s_url);
	setTimeout(function(){location.href = s_url;
	//document.getElementById("clickme").click();
	},2500);
})

</script>
</body>
</html>