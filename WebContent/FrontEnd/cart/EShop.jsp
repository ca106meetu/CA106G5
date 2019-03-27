<%@page import="java.util.ArrayList"%>
<%@page import="com.meetU.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.meetU.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductService prodSvc = new ProductService(); 
	List<ProductVO> list = prodSvc.getSome();
	pageContext.setAttribute("list", list);
	List<String> ps = (List<String>)application.getAttribute("ps");

%>

<!doctype html>
<html lang="en">
  <head>
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
	  	
	  #menu {
	  position: fixed;
	  right: 0;
	  top: 50%;
	  width: 8em;
	  margin-top: -2.5em;
	}
	
  	.card-img-top {
    width:auto; 
 	height:auto; 
    border-top-left-radius: calc(.25rem - 1px);
    border-top-right-radius: calc(.25rem - 1px);
     border-radius: 5%;
	}
	.card-deck .card {
    
    margin: 15px;
}

.card {
   
    
    border-radius: 3%;
    border: 2px solid rgba(0, 0, 0, 0.23);
    color: black;
    box-shadow: 0 0 3px black;
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
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>meetU商城&lt;3</title> 
  </head>
  <body onload="connect();">
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
	<hr>

<div class='container'>
<%for(int j =0; j<=(list.size())/3; j++){%>
<div class="card-deck">
<%for(int i =0; i<=2; i++){
	if(3*j+i <= list.size()-1){
	ProductVO prodVO = list.get(3*j+i);
	Integer	max = prodVO.getProd_stock();
		
	%>	
  <div class="card ">
  	<a href='<%=request.getContextPath()%>/FrontEnd/cart/prodDetail.jsp?prod_ID=<%=prodVO.getProd_ID()%>'><img src="/CA106G5/ShowPic?PROD_ID=<%=prodVO.getProd_ID()%>" id='pic' class="card-img-top"></a>
    <div class="card-body ">
      <h5 class="card-title">
      			 <a href='<%=request.getContextPath()%>/FrontEnd/cart/prodDetail.jsp?prod_ID=${prodVO.prod_ID}' class="card-link">	
	        		<%=prodVO.getProd_name()%>
	        	</a>
	  </h5>
      <p class="card-text text-warning"><%=prodVO.getProd_info()%></p>
      <p class="card-text text-success">商品庫存量:  <%=prodVO.getProd_stock()%></p>
    </div>
    <div class="card-footer">
      <h3 class="text-muted" >$ <%=prodVO.getProd_price().intValue()%> TWD</h3>
     
     
	  	<div class="input-group mb-3">
	  		<input class="form-control" type="number" min="1" max="<%=max%>" value="1" id="example-number-input" name='quantity'>
		  	
		  	<div class="input-group-append">
		    	<input class='shopping-cart cart' type='image' src='images/shopping-cart.png'>
				<input type='hidden' name='prod_ID' value=<%=prodVO.getProd_ID()%>>
		  	</div>
		</div>
	  	
	  	<input type='hidden' name='action' value='add'>
    </div>
  </div>
<%}else{%> 
  <div class="card">
  </div>
 <%}};%> 
</div><br>	
 <%};%>	
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


<div class="modal" tabindex="-1" role="dialog" id='myModal2'>
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body text-center">
      
      <div class='row'>
      	<div class='col-6'><img  id='img1' width='200px' height='200px' src='images/excited.gif'></div>
      	<div class='col-6'>        
      		<h3>新商品上市囉!!</h3>
      		<hr>
      		<h2 id='info1' style="color:red;"></h2>
      		<h4 id='info2' style="color:blue;"></h4>
        </div>
      </div>
      <div class='row'>
      	<div class='col-3'></div>
        <div class='col-6'>
        	<a class="btn btn-primary" role="button" id='prodDetail'>前往搶購</a>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">繼續選購</button></div>
        <div class='col-3'></div>
      </div>
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
    
    <script>
    
    $(document).ready(function(){
		 $('.cart').click(function(){
			 
			 if( $(this).parent().prev().val() <= 0 || $(this).parent().prev().val() > parseInt($(this).parent().prev().attr('max'))){
				 
				 $("#alert").modal('show');
			 }else{
				 
				 $.ajax({
					 type: "POST",
					 url: "ShoppingServlet",
					 data: {"prod_ID":$(this).next().attr('value'), "action":"add", "quantity":$(this).parent().prev().val()},
					 dataType: "json",
					 success: function(){
						 
						 $('#myModal').modal('show');
						},
				     
		             error: function(){alert("AJAX-grade發生錯誤囉!")}
		         });
				 
			 }
			 
	 });
    })
    
    
    
    </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
  
  
  <script>
    
    var MyPoint = "/prodPush/loren/205";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
		};

		webSocket.onmessage = function(event) {
	        var jsonObj = JSON.parse(event.data);
	        var message1 = jsonObj.prodName
	        var message2 = jsonObj.message
	        var message3 = jsonObj.prod_ID
	        var img1 = jsonObj.img1
	        if(img1 != null && img1 != '')
	        $('#img1').attr('src', img1);
	        $('#info1').text(message1);
	        $('#info2').text(message2);
	        $('#prodDetail').attr('href','prodDetail.jsp?prod_ID='+message3);
	        $('#myModal2').modal('show');
// 	        alert(message);
		};

		webSocket.onclose = function(event) {
		};
	}	
    
</script>
  
  
</html>