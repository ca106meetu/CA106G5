<%@page import="java.util.Vector"%>
<%@page import="com.meetU.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List<ProductVO> buyList = (Vector<ProductVO>) session.getAttribute("shoppingCart");

		double total = 0;
	if(buyList != null){
		for (int i = 0; i < buyList.size(); i++) {
			ProductVO ProdVO = buyList.get(i);
			Double Price = ProdVO.getProd_price();
			Integer Quantity = ProdVO.getQuantity();
			total += (Price * Quantity);
		}
	}
	
%>
<!doctype html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
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
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

    <title>${memVO.mem_name}購物車內容</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    <hr>
    
    
    
    <div class="container">
      
      <%if(buyList != null && !buyList.isEmpty()){ %>
      <div class="row bg-info">
      	<div class="col-5"></div>
      	<div class="col-2"><h2 class="text-dark">購物車內容</h2></div>
      	<div class="col-5"></div>
      </div>
      
      <br>
      <br>
      <div class="row">
        <div class="col-5">商品</div>
        <div class="col-3">單價</div>
        <div class="col-1">數量</div>
        <div class="col-1">總計</div>
        <div class="col-1">操作</div>
      </div>
      <br>
      
      <c:forEach var="prodVO" items= "${shoppingCart}">
      <div class="row">
        <div class="col-1"><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></div>
        <div class="col-2">${prodVO.prod_name}</div>
        <div class="col-2"></div>
        <div class="col-3">$${prodVO.prod_price}</div>
        <div class="col-1">
        	<input class="form-control quantity" type="number" min="1" max="${prodVO.prod_stock}" value="${prodVO.quantity}" id="example-number-input">
        	<input type="hidden" value="${prodVO.prod_ID}">
        </div>
        <div class="col-1" id='${prodVO.prod_ID}'>${prodVO.prod_price*prodVO.quantity}</div>
        <div class="col-1">
        		<form method='post' action='ShoppingServlet' style="margin-bottom: 0px;" >
					<input type='submit' value='刪除'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID}'>
					<input type='hidden' name='action' value='del'>				
				</form>
        </div>
      </div>
      </c:forEach>
      
      
      <div class='row'>
      	<div class="col-8"></div>
        <div class="col-1" style="font-size:20px">總金額: </div>
        <div class="col-1 amount" style="color:green;font-size:20px"><%=total%></div>
        <div class="col-1" style="font-size:20px">元</div>
        <div class="col-1"></div>
      </div>
      
      
      
      <div class='row'>
      	<div class="col-8"></div>
        <div class="col-3">
        	<form method='post' action='ShoppingServlet' onsubmit='return allowUser();'>
        		<input type='hidden' name='action' value='checkOut'>
        		<button type="submit" class="btn btn-warning checkOut">前往結帳</button>
        	</form>
        </div>
        <div class="col-1"></div>
      </div>

    
     <%}else {%>
    	<div class='row justify-content-center'>
    	<div class='col-3'></div>
    	<div class='col-6' ><h2 style="text-align:center;">購物車是空的</h2></div>
    	<div class='col-3'></div>
    	
    	
    	</div>
    	<div class='row justify-content-center'>
    	<div class='col-3'></div>
    	<div class='col-6'><img src='images/out.jpg'></div>
    	<div class='col-3'></div> 

    	</div>    
    	<hr> 
    	<div class='row justify-content-center'>
    	<div class='col-5'></div>
    	<div class='col-2'><a class="btn btn-primary" href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp" role="button">前往購物</a></div>
    	<div class='col-5'></div>

    	</div>
    	</div>
    
    	
    
    
    
    <%}%>
    
    
    
    
    

   
    
    <script>
    
    
	 $('.checkOut').click(function(){
			 if(!allowUser()){ 
				 <%session.setAttribute("location", request.getRequestURI());%>
				 $('#login').modal('show');
				 return;
			 }else{
				window.location.href=('http://www.ncu.edu.tw');
			 } 
	});
	 
	 
	 $('.quantity').change(function(){
		 var prod_ID = $(this).next().val();
		 $.ajax({
			 type: "POST",
			 url: "ShoppingServlet",
			 data: {"quantity":$(this).val(), "prod_ID":prod_ID, "action":"changeQ"},
			 dataType: "json",
			 success: function(data){
					$(".amount").text(data.amount);
					$(".amount").attr("style", "font-size:20px;color:red;"); 
					document.getElementById(data.prod_ID).innerText = data.total;
					
			 },
		     
             error: function(){alert("AJAX-grade發生錯誤囉!")}
         });
 });
    
    
    
    </script>
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>