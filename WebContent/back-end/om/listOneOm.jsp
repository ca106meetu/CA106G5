<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    
    <div class='container'>
<table id="table-1">
	<tr><td>
		 <h3>訂單資料</h3>
		 <h4><a href="selectPageOm.jsp"><img src="images/back1.png" width="60" border="0">回訂單管理</a></h4>
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
		<th>查看明細</th>
		<th>出貨</th>
	</tr>
	<tr>
		<td>${omVO.order_ID}</td>
		<td>${omVO.mem_ID}</td>
		<td>${omVO.price}</td>
		<td>
			<fmt:formatDate value="${omVO.order_date}" pattern="yyyy-MM-dd HH:mm" />
		</td>
		<td>${omVO.out_add}</td>
		<td>${omVO.recipient}</td>
		<td>${omVO.phone}</td>
		<td>${omVO.out_date}</td>
		<td>${outs[omVO.out_status]}</td>
		<td>${ords[omVO.order_status]}</td>
		<td>${omVO.tip}</td>
		<td>
			<form method='post' action='listDetail.jsp' style="margin-bottom: 0px;">
				<input type='submit' value='查看明細' class='btn btn-warning'>
				<input type='hidden' name='order_ID' value='${omVO.order_ID}'>
			</form>
		</td>
		<td>
			<form method='post' action='om.do' style="margin-bottom: 0px;">
				<input type='submit' class='btn btn-${omVO.order_status != 0 ? "success" : "primary"}' value='${omVO.order_status != 0 ? "已出貨" : "出貨"}' ${omVO.order_status != 0 ? 'disabled="disabled"' : ''}>
				<input type='hidden' name='order_ID' value='${omVO.order_ID}'>
				<input type='hidden' name='location' value='<%=request.getServletPath()%>'>
				<input type='hidden' name='action' value='out'>				
			</form>
		</td>
	</tr>
</table>
    
    
    </div>
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>