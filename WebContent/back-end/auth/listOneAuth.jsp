<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.meetU.auth.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  AuthVO authVO = (AuthVO) request.getAttribute("authVO"); //AuthServlet.java(Concroller), 存入req的authVO物件
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

<title>員工資料 - listOneAuth.jsp</title>

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
	width: 600px;
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
<div class="col-6">
<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>權限資料 - ListOneAuth.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>權限ID</th>
		<th>權限名稱</th>
	</tr>
	<tr>
		<td><%=authVO.getAuth_ID()%></td>
		<td><%=authVO.getAuth_name()%></td>
	</tr>
</table>
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