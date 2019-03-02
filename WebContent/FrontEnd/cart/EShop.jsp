<%@page import="java.util.ArrayList"%>
<%@page import="com.meetU.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.meetU.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductService prodSvc = new ProductService(); 
	List<ProductVO> list = prodSvc.getAll();
	pageContext.setAttribute("list", list);

%>

<!doctype html>
<html lang="en">
  <head>
  <style>
  	.card-img-top {
    width:auto; 
 	height:auto; 
    border-top-left-radius: calc(.25rem - 1px);
    border-top-right-radius: calc(.25rem - 1px);
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
  .shopping-cart{
  	width:40px;
  	height:40px;
  	float:right;
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
    
	

<div class='container'>
<%for(int j =0; j<=(list.size())/3; j++){%>
<div class="card-deck">
<%for(int i =0; i<=2; i++){
	if(3*j+i < list.size()-1){
	ProductVO prodVO = list.get(3*j+i);%>	
  <div class="card border-danger mb-3">
    <img src="/CA106G5/ShowPic?PROD_ID=<%=prodVO.getProd_ID()%>" id='pic' class="card-img-top">
    <div class="card-body ">
      <h5 class="card-title"><%=prodVO.getProd_name()%></h5>
      <p class="card-text text-warning"><%=prodVO.getProd_info()%></p>
    </div>
    <div class="card-footer">
      <small class="text-muted">價錢: <%=prodVO.getProd_price()%> 元</small>
     
     
	  <form method='post' action="ShoppingServlet">
	  	<input type='hidden' name='prod_ID' value=<%=prodVO.getProd_ID()%>>
	  	<div class="input-group mb-3">
	  		<input class="form-control" type="number" min="1" max="5" value="1" id="example-number-input" name='quantity'>
	  	<div class="input-group-append">
	    	<input class='shopping-cart' type='image' src='images/shopping-cart.png' alt='submit'>
	  	</div>
		</div>
	  	
	  	<input type='hidden' name='action' value='add'>
	  </form>     
    </div>
  </div>
 <%}else{%> 
  <div class="card">
     
     
  </div>
  
  
 <%}};%> 
</div><br>	
 <%};%>	
</div>	
	<a class="btn btn-primary" href="cart.jsp" role="button">查看購物車</a>

<%-- 錯誤列表 --%>
<%-- <c:if test='${not empty errorMsgs }'> --%>
<!-- 	<font style='color:red'>請修正以下錯誤</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var='message' items='${errorMsgs}'> --%>
<%-- 			<li style='color:red'>${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>