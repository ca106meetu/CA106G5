<%@page import="com.meetU.orderDetail.model.OrderDetailVO"%>
<%@page import="java.util.List"%>
<%@page import="com.meetU.orderDetail.model.OrderDetailService"%>
<%@page import="com.meetU.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	OrderDetailService odSvc = new OrderDetailService();
	String order_ID = request.getParameter("order_ID");
	List<OrderDetailVO> list = odSvc.findOdByOm(order_ID);
%>
<!doctype html>
<html lang="en">
  <head>
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

    <title>Hello, world!</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneProd.jsp</h3>
		 <h4><a href="selectPage.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品名稱</th>
		<th>類型</th>
		<th>商品單價</th>
		<th>數量</th>
		<th>圖片</th>
	</tr>
	
	<jsp:useBean id='prodSvc' scope='page' class='com.meetU.product.model.ProductService'/>
	<c:forEach var="odVO" items= "${list}">">
		
		<tr>
			<td>${prodSvc.getOneProd(odVO.prod_ID).prod_name}</td>
<%-- 			<td>${pt[prodSvc.getOneProd(odVO.prod_ID).prod_ID]}</td> --%>
			<td>${odVO.price}</td>
			<td>${odVO.quantity}</td>
			<td><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></td>
		</tr>
 	
	</c:forEach>
</table>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>