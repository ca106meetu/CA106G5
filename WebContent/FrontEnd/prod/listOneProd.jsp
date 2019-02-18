<%@page import="com.meetU.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProductVO prodVO =  (ProductVO) request.getAttribute("prodVO");
%>
<!DOCTYPE html>
<html>
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
<title>Insert title here</title>
</head>
<body>
<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneProd.jsp</h3>
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
	</tr>
	<tr>
		<td>${prodVO.prod_ID}</td>
			<td>${prodVO.prod_name}</td>
			<td>${prodVO.prod_price}</td>
			<td>${prodVO.prod_type}</td>
			<td>${prodVO.prod_stock}</td>
			<td><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></td>
			<td>${prodVO.prod_promt_status}</td>
			<td>${prodVO.prod_status}</td>
			<td>${prodVO.prod_info}</td>
	</tr>
</table>


</body>
</html>