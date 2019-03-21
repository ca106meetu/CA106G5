<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.emp.model.*"%>
<%@ page import="com.meetU.empAuth.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
    
%>
<% 
    java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formatDate = df.format(new java.util.Date());
    out.println(formatDate);
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

<title>所有員工資料 - listAllEmp.jsp</title>

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
	width: 800px;
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
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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
		
		<th>修改</th>
		<th>修改權限</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${empVO.emp_ID}</td>
			<td>${empVO.emp_pw}</td>
			<td>${empVO.emp_name}</td>
			<td><fmt:formatDate value="${empVO.emp_bday}" pattern="yyyy-MM-dd"/></td>
			<td>${empVO.emp_email}</td>
			<td>${empVO.emp_pho}</td>
			<td>${empVO.emp_gend}</td>
			<td>
			
			<img class='pic' src='<%=request.getContextPath() %>/ShowPic?emp_ID=${empVO.emp_ID}'>
			</td>
			<td>${empVO.emp_state}</td>
			<td><fmt:formatDate value="${empVO.emp_hday}" pattern="yyyy-MM-dd"/></td>
			<td>${empVO.emp_address}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="emp_ID"  value="${empVO.emp_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/empAuth/empAuth.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改權限">
			     <input type="hidden" name="emp_ID"  value="${empVO.emp_ID}">
<%-- 			     <input type="hidden" name="empVO"  value="${empVO}"> --%>
			     <input type="hidden" name="action"	value="getAuths_For_Update">
			  </FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="emp_ID"  value="${empVO.emp_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
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
    <script src="<%=request.getContextPath()%>Templates/bootstrap4/js/jquery-1.12.4.min.js"></script>
    
    
  
</body>
</html>