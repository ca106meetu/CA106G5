<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.empAuth.model.*"%>
<%@ page import="com.meetU.emp.model.*"%>
<%@ page import="java.util.*"%>

<%

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

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工權限資料修改 - update_empAuth_input.jsp</title>

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
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<div class="container justify-content-center">
<div class="row justify-content-center">
		<div class="col-6">   
		  <center>
		 	<h3>員工權限資料修改 - update_empAuth_input.jsp</h3>
		  </center>
		</div>
	 </div>
	 <div class="row justify-content-center">
		<div class="col-6">   
		  <center>
		 <h4><a href="../emp/select_page.jsp">回首頁</a></h4>
		  </center>
		</div>
	 </div>
	 <div class="row justify-content-center">
		<div class="col-6">   
		  <center>
			<h3>員工權限資料修改:</h3>
		  </center>
		</div>
	 </div>
	 <div class="row justify-content-center">
		<div class="col-6">   
		  <center>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
		  </center>
		</div>
	 </div>
<div class="row justify-content-center">
	<div class="col">
		<center> 
<FORM METHOD="post" ACTION="empAuth.do" name="form1">
<table>
	<jsp:useBean id="empSvc" scope="page" class="com.meetU.emp.model.EmpService" />
	<tr>
		<td>員工名稱:<font color=red><b>*</b></font></td>
		<td>
			 ${empVO.emp_name}
		</td>
	</tr>
		<jsp:useBean id="authSvc" scope="page" class="com.meetU.auth.model.AuthService" />

	<tr>
		<td>權限名稱</td>
		<td>
		<c:forEach var="authVO" items="${authSvc.all}" varStatus="s" >
		${(s.index%3==0)? '<br>' : ''}
			<input type="checkbox"  name="auth_ID" value="${authVO.auth_ID}" ${listAuth_ID.contains(authVO.auth_ID)?'checked':''}> ${authVO.auth_name}
		</c:forEach>
		
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="emp_ID" value="${empVO.emp_ID}">
<button class="btn btn-outline-info" type="submit">送出修改</button>
</FORM>
</center>
</div>
</div>
</div>
<br><br><br><br><br><br>

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



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
</html>