<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	OrderMasterVO omVO =  (OrderMasterVO) request.getAttribute("omVO");
%>
<!doctype html>
<html lang="en">
  <head>
   <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
	.pic{
		width:172.5px;
		height:230px;
	}
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
    padding: 2px;
    text-align: center;
  }
	
</style>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>會員訂單資料</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    
    <h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneOm.jsp</h3>
		 <h4><a href="selectPageOm.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員名稱</th>
		<th>訂單金額</th>
		<th>訂單日期</th>
		<th>出貨地址</th>
		<th>收件人</th>
		<th>收件人電話</th>
		<th>出貨日期</th>
		<th>出貨狀態</th>
		<th>訂單狀態</th>
		<th>備註</th>
	</tr>
	<tr>
		<td>${omVO.order_ID}</td>
		<td>${omVO.mem_ID}</td>
		<td>${omVO.price}</td>
		<td>${omVO.order_date}</td>
		<td>${omVO.out_add}</td>
		<td>${omVO.recipient}</td>
		<td>${omVO.phone}</td>
		<td>${omVO.out_date}</td>
		<td>${outs[omVO.out_status]}</td>
		<td>${ords[omVO.order_status]}</td>
		<td>${omVO.tip}</td>
	</tr>
</table>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>