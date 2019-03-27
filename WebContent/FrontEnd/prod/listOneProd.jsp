<%@page import="com.meetU.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductVO prodVO =  (ProductVO) request.getAttribute("prodVO");
%>
<!doctype html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
  
  	.card-text{
  		color:blue;
  	}
  
	.pic{
		width:172.5px;
		height:230px;
	}
	
	table {
	width: 75%;
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

    <title>商品推播</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    <div class='container'>
    
<table id="table-1">
	<tr><td>
		<h4>【${prodVO.prod_ID}】</h4>
		 <h4><a href="selectPage.jsp"><img src="images/back1.png" width="60"  border="0">回商品管理</a></h4>
	</td></tr>
</table>


	<div class="row justify-content-center">

	<div class="col-3">
		<a href='<%=request.getContextPath()%>/FrontEnd/cart/prodDetail.jsp?prod_ID=${prodVO.prod_ID}'>
        		<img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'>
        </a>
		<div class='card' style='border:0px;'>
			<p class="card-text">
				<button type='button' class='btn btn-outline-danger' id='me' onclick="connect();">商品推播</button>
				<div class="custom-file">
				<input type='file'  onchange='getBase64(this);' class="custom-file-input">
				<label class="custom-file-label" for="validatedCustomFile">挑一張圖吧!</label>
				</div>
				<img id='img1' src="">
			</p>
		</div>
	
	</div>
		<div class="col-6">
          <div class="card text-black" >
            <div class="card-header">
            	<a href='<%=request.getContextPath()%>/FrontEnd/cart/prodDetail.jsp?prod_ID=${prodVO.prod_ID}'>	
	        		<h3>${prodVO.prod_name}</h3>
	        	</a>
            	
            </div>
            	 <div class="card-body">
				    <h4 class="card-title">商品價格</h4>
				    <p class="card-text">$ ${prodVO.prod_price} TWD</p>
				    <h4 class="card-title">類型</h4>
				    <p class="card-text">${pt[prodVO.prod_type]}</p>
				  	<h4 class="card-title">庫存量</h4>
				    <p class="card-text">${prodVO.prod_stock}</p>
				    <h4 class="card-title">促銷狀態</h4>
				    <p class="card-text">${pps[prodVO.prod_promt_status]}</p>
					<h4 class="card-title">上架狀態</h4>
				    <p class="card-text">${ps[prodVO.prod_status]}/p>
				    <h4 class="card-title">商品資訊</h4>
				    <p class="card-text">${prodVO.prod_info}</p>
				    
				  </div>
          </div>
         </div>
	<div class="col-3"></div>

      </div>

<!-- 	<tr> -->
<!-- 		<th>商品名稱</th> -->
<!-- 		<th>商品價格</th> -->
<!-- 		<th>類型</th> -->
<!-- 		<th>庫存量</th> -->
<!-- 		<th>圖片</th> -->
<!-- 		<th>促銷狀態</th> -->
<!-- 		<th>上架狀態</th> -->
<!-- 		<th>商品資訊</th> -->
<!-- 		<th>推播</th> -->
		
<!-- 	</tr> -->
<!-- 	<tr> -->
<%-- 		<td>${prodVO.prod_name}</td> --%>
<%-- 		<td>${prodVO.prod_price}</td> --%>
<%-- 		<td>${pt[prodVO.prod_type]}</td> --%>
<%-- 		<td>${prodVO.prod_stock}</td> --%>
<%-- 		<td><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></td> --%>
<%-- 		<td>${pps[prodVO.prod_promt_status]}</td> --%>
<%-- 		<td>${ps[prodVO.prod_status]}</td> --%>
<%-- 		<td>${prodVO.prod_info}</td> --%>
<!-- 		<td> -->
<!-- 			<button type='button' class='btn btn-primary' id='me' onclick="connect();">推播</button> -->
<!-- 			<input type='file'  onchange='getBase64(this);'> -->
<!-- 			<img id='img1' src=""> -->
<!-- 		</td> -->
<!-- 	</tr> -->
    
    <br>
    <br> 
    <br>
    <br>
    </div>
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
  
  
  
  
  <script>
  
  
  	function getBase64(imgDOM){
  		var file = imgDOM.files[0];
  		var reader = new FileReader();
  		
  		reader.onload = (function(event){
  			$('#img1').attr('src', event.target.result);
  			$('#img1').attr('style', 'width:172.5px;height:230px');
  		})
  		
  		reader.readAsDataURL(file);
  	}
  
  
  
  
    
    var MyPoint = "/prodPush/loren/205";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;
    
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			
			var prod_name = '${prodVO.prod_name}';
			var prod_ID = '${prodVO.prod_ID}'
 			var jsonObj = {"prodName" : prod_name , "message" : "上架囉~~快去搶購!", "img1" : $('#img1').attr('src'), "prod_ID": prod_ID};
	        webSocket.send(JSON.stringify(jsonObj));
		};
		
		webSocket.onmessage = function(event) {
		};
		
		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
		
	
	}	
	
	
    
</script>
  
  
  
  
  
  
  
  
  
  
  
  
</html>