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
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<h1>${memVO.mem_acc}你好! 您的信箱${memVO.mem_email}已經通過驗證!</h1>

<script> 

$(document).ready(function(){
	var url = "<%=request.getContextPath()%>/FrontEnd/mem/reg_mem_input.jsp";
	var s_url = new String(url);
	
	//alert(s_url);
	setTimeout(function(){location.href = s_url;
	//document.getElementById("clickme").click();
	},5000);
})

</script>
</body>
</html>