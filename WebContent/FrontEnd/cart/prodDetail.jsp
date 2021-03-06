<%@page import="com.meetU.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.meetU.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	String prod_ID = request.getParameter("prod_ID");
	ProductService prodSvc = new ProductService(); 
	ProductVO prodVO = prodSvc.getOneProd(prod_ID);
	pageContext.setAttribute("prodVO", prodVO);

%> 
<!doctype html>
<html lang="en">
  <head>
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
  	.pic{
  		width:100%;
  	}
  	 #menu {
	  position: fixed;
	  right: 0;
	  top: 50%;
	  width: 8em;
	  margin-top: -2.5em;
	}
  
  </style>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

    <title>${prodVO.prod_name}</title>  
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp"/> 
    
    <hr>
<div class="container">
            	<form method='post' action='ShoppingServlet' onsubmit='return allowUser();'>
      <div class="row">
        <div class="col-5"><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></div>
        <div class="col-7">
          <div class='row' style='height:100px;'>
            <div class="col-3" >商品名稱</div>
            <div class="col-9">${prodVO.prod_name}</div>
          </div>
          <div class='row' style='height:50px;'>
            <div class="col-3">商品價格</div>
            <div class="col-9">${prodVO.prod_price.intValue()}元</div>
          </div>
          <div class='row' style='height:50px;'>
            <div class="col-3">商品庫存</div>
            <div class="col-9">${prodVO.prod_stock}</div>
          </div>
          <div class='row' style='height:50px;width:60%;'>
            <div class="col-3">數量</div>
            <div class="col-9">
            	<input id='quantity' class="form-control qq" type="number" min="1" max="${prodVO.prod_stock}" value="1" id="example-number-input" name='quantity'>
            	<input type="hidden" value="${prodVO.prod_price.intValue()}">
            
            </div>
          </div>
          <div class='row' style='height:150px;'> 
            <div class="col-3">商品詳情</div>
            <div class="col-9">${prodVO.prod_info}</div>
          </div>
          <hr>
          
          <div class='row' style='height:50px;'>  
            <div class="col-3" style="font-size:20px">總金額: </div>
            <div class="col-9 total" style="color:green;font-size:20px">${prodVO.prod_price.intValue()}元</div>
          </div>
          
          <div class='row'>
            <div class="col-3">
            	<button type="button" class="btn btn-outline-danger cart"> 加 入 購 物 車 </button>
            	<input type='hidden' name='prod_ID' value='${prodVO.prod_ID}'>
            </div>
            
            <div class="col-3">
            		<input type='hidden' name='prod_ID' value='${prodVO.prod_ID}'>
            		<input type='hidden' name='action' value='DcheckOut'>
            		<button type="submit" class="btn btn-danger dCheckOut"> 直 接 購 買 </button>
            </div>
         	<div class="col-6"></div>
          </div>
        </div>
      </div>
            	</form>
            	<br>
</div>
<div id='menu'>
<a class="btn btn-primary" href="cart.jsp" role="button">查看購物車</a>
</div>	
    
    
    <div class="modal" tabindex="-1" role="dialog" id='myModal'>
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header text-center">
	        <h5 class="modal-title text-center">成功加入購物車</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body text-center">
	      	<img  width='200px' height='200px' src='images/check2.gif'>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">繼續選購</button>
	        <a class="btn btn-primary" href="cart.jsp" role="button">查看購物車</a>
	      </div>
	    </div>
	  </div> 
	</div>
    
    <div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id='alert' aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    	<div class="modal-header">
    		<h3 class="modal-title" style='color:red;'>提示</h3>
      		
 	   	</div>
 	   	
 	   	<div class="modal-body" style='color:blue;'>
        	<h5>數量輸入錯誤</h5>
      	</div>
    </div>
  </div> 
</div>
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
    
    
    <script>
    
    $(document).ready(function(){
    	
    	
    	$('.qq').change(function(){
    		
    		var total = $(this).val() * parseInt($(this).next().val());
			$('.total').text(total);    	
    	
    	})
    	
    	
			 $('.cart').click(function(){
				 var quantity = parseInt(document.getElementById("quantity").value);
				 var max = document.getElementById("quantity").getAttribute("max");
	    	 if( quantity <= 0 || quantity > max ){
				 
				 $("#alert").modal('show');
			 }else{
				 $.ajax({
					 type: "POST",
					 url: "ShoppingServlet",
					 data: {"prod_ID":$(this).next().val(), "action":"add", "quantity":$('.qq').val()},
					 dataType: "json",
					 success: function(){
						 
						 $('#myModal').modal('show');
	// 					 alert("555");
						},
				     
		             error: function(){alert("AJAX-grade發生錯誤囉!")}
		         });
			 }
		 	});
		 
		  
		 $('.dCheckOut').click(function(){
			 if(!allowUser()){ 
				 <%session.setAttribute("location", request.getRequestURI());%>
				 $('#login').modal('show');
				 return;
			 }
	 });
    })
    
    
    
    </script>
    
    
  </body>
</html>