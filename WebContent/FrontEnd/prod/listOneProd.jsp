<%@page import="com.meetU.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductVO prodVO =  (ProductVO) request.getAttribute("prodVO");
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
    
    
    <h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>商品資料 - ListOneProd.jsp</h3>
		 <h4><a href="selectPage.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>類型</th>
		<th>庫存量</th>
		<th>圖片</th>
		<th>促銷狀態</th>
		<th>上架狀態</th>
		<th>商品資訊</th>
		<th>推播</th>
		
	</tr>
	<tr>
		<td>${prodVO.prod_ID}</td>
		<td>${prodVO.prod_name}</td>
		<td>${prodVO.prod_price}</td>
		<td>${pt[prodVO.prod_type]}</td>
		<td>${prodVO.prod_stock}</td>
		<td><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></td>
		<td>${pt[prodVO.prod_promt_status]}</td>
		<td>${pt[prodVO.prod_status]}</td>
		<td>${prodVO.prod_info}</td>
		<td><button type='button' class='btn btn-primary' id='me' onclick="connect();">推播</button></td>
	</tr>
</table>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
  
  
  
  
  <script>
    
    var MyPoint = "/MyEchoServer/loren/205";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			var prod_name = '${prodVO.prod_name}';
			var jsonObj = {"prodName" : prod_name , "message" : "上架囉~~快去搶購!"};
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