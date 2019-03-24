<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.emp.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<!doctype html>
<html lang="zh-Hant-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
     <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>員工資料 - listOneEmp.jsp</title>

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
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<div class="container justify-content-center">
<div class="row">
<div class="col">    

		<center>
			<h3>員工資料 - ListOneEmp.jsp</h3>
		</center>
		<center>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
		</center>
<center>		
<table>
	<tr>
		<th>員工ID</th>
		<th>員工密碼</th>
		<th>員工姓名</th>
		<th>員工生日</th>
		<th>員工電子郵件信箱</th>
		<th>員工手機</th>
		<th>員工性別</th>
		<th>員工大頭照</th>
		<th>員工帳號狀態</th>
		<th>員工就職時間</th>
		<th>員工居住地</th>
	</tr>
	<tr>
		<td><%=empVO.getEmp_ID()%></td>
		<td><%=empVO.getEmp_pw()%></td>
		<td><%=empVO.getEmp_name()%></td>
		<td><%=empVO.getEmp_bday()%></td>
		<td><%=empVO.getEmp_email()%></td>
		<td><%=empVO.getEmp_pho()%></td>
		<td><%=empVO.getEmp_gend()%></td>
		<td>
		<img class='pic' src='<%=request.getContextPath() %>/ShowPic?emp_ID=${empVO.emp_ID}'>
		</td>
		<td>${eS[empVO.emp_state]}</td>
		<td><%=empVO.getEmp_hday()%></td>
		<td><%=empVO.getEmp_address()%></td>
	</tr>
</table>
</center>
</div>
</div>
</div>
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/js/bootstrap.min.js"></script>

    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  

</body>
</html>