<%@page import="java.util.Vector"%>
<%@page import="com.meetU.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List<ProductVO> butList = (Vector<ProductVO>) session.getAttribute("shoppingCart");
	
%>
<!doctype html>
<html lang="en">
  <head>
	  <style type="text/css">
	  	.pic{
		width:86.25px;
		height:115px;
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
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    
    
    <div class="container">
      
      <div class="row bg-info">
      	<div class="col-5"></div>
      	<div class="col-2"><h2 class="text-dark">購物車內容</h2></div>
      	<div class="col-5"></div>
      </div>
      <br>
      <br>
      <div class="row">
      	<div class="col-1"><input type='checkbox'></div>
        <div class="col-5">商品</div>
        <div class="col-3">單價</div>
        <div class="col-1">數量</div>
        <div class="col-1">總計</div>
        <div class="col-1">操作</div>
      </div>
      <br>
      
      <c:forEach var="prodVO" items= "${shoppingCart}">
      <div class="row">
      	<div class="col-1"><input type='checkbox'></div>
        <div class="col-1"><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></div>
        <div class="col-2">${prodVO.prod_name}</div>
        <div class="col-2"></div>
        <div class="col-3">$${prodVO.prod_price}</div>
        <div class="col-1">${prodVO.quantity}</div>
        <div class="col-1">${prodVO.prod_price*prodVO.quantity}</div>
        <div class="col-1">
        		<form method='post' action='ShoppingServlet' style="margin-bottom: 0px;">
					<input type='submit' value='刪除'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID}'>
					<input type='hidden' name='action' value='del'>				
				</form>
        </div>
      </div>
      </c:forEach>
      
      <div class='row'>
      	<div class="col-8"></div>
        <div class="col-3">
        	<form method='post' action='ShoppingServlet'>
        		<input type='hidden' name='action' value='checkOut'>
        		<button type="submit" class="btn btn-warning">前往結帳</button>
        	</form>
        </div>
        <div class="col-1"></div>
      </div>

    </div>
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>