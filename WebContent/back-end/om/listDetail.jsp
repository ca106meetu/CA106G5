<%@page import="com.meetU.orderDetail.model.OrderDetailVO"%>
<%@page import="com.meetU.orderDetail.model.OrderDetailService"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	OrderDetailService odSvc = new OrderDetailService();
	OrderMasterService omSvc = new OrderMasterService();
	String order_ID = request.getParameter("order_ID");
	List<OrderDetailVO> list = odSvc.findOdByOm(order_ID);
	pageContext.setAttribute("list", list);

	
	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	String order_date = df.format(omSvc.getOneOm(order_ID).getOrder_date());
%>

<!doctype html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
	.pic{
		width:86.25px;
		height:115px;
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

    <title>訂單明細</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    <div class='container'>
 <table id = 'table-1'>
	<tr>
		<td>
			<h3>訂單明細【<%=order_ID%>】</h3>
			<h4>訂單成立時間: <%=order_date%></h4>
		</td>
	</tr>
</table>


<table>
	<tr>
		<th>商品名稱</th>
		<th>類型</th>
		<th>商品單價</th>
		<th>數量</th>
		<th>圖片</th>
<!-- 		<th>刪除</th> -->
	</tr>
	
	<jsp:useBean id='prodSvc' scope='page' class='com.meetU.product.model.ProductService'/>
	<c:forEach var="odVO" items= "${list}" begin="0" end="${list.size()-1}" >
		
		<tr>
			<c:set scope="page" var="prod_type">
    				<c:out value="${prodSvc.getOneProd(odVO.prod_ID).prod_type}"/> 
			</c:set>
		
			<td>${prodSvc.getOneProd(odVO.prod_ID).prod_name}</td>
			<td>${pt[prod_type]}</td>
			<td>${odVO.price}</td>
			<td>${odVO.quantity}</td>
			<td><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodSvc.getOneProd(odVO.prod_ID).prod_ID}'></td>
<!-- 			<td>	 -->
<!-- 				<form method='post' action='od.do' style="margin-bottom: 0px;"> -->
<!-- 					<input type='submit' value='刪除'> -->
<%-- 					<input type='hidden' name='order_ID' value='<%=order_ID%>'> --%>
<%-- 					<input type='hidden' name='order_ID' value='${prodSvc.getOneProd(odVO.prod_ID).prod_ID}'> --%>
<!-- 					<input type='hidden' name='action' value='delete'>				 -->
<!-- 				</form> -->
			
<!-- 			</td> -->
			
		</tr>
 	
	</c:forEach>
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