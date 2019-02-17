<%@page import="java.util.*"%>
<%@page import="com.meetU.product.model.*"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	ProductService prodSvc = new ProductService(); 
	List<ProductVO> list = prodSvc.getAll();
	pageContext.setAttribute("list", list);

%>

<html>
<head>
<title>所有商品資料-listAllProd.jsp</title>



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
</head>
<body bgcolor = 'gray'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
 <table id = 'table-1'>
	<tr><td>
		<h3>所有商品資料-listAllProd.jsp</h3>
		<h4><a href='#'><img src="images/back1.gif" width="100" height="32">回首頁</a></h4>
	
	
	</td>
	
	
	</tr>




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
		<th>修改</th>
		<th>刪除</th>		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="prodVO" items= "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
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
			<td>
				<form method='post' action='' style="margin-bottom: 0px;">
					<input type='submit' value='修改'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID }'>
					<input type='hidden' name='action' value='getOne_For_Update'>				
				</form></td>
			<td>	
				<form method='post' action='' style="margin-bottom: 0px;">
					<input type='submit' value='刪除'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID }'>
					<input type='hidden' name='action' value='delete'>				
				</form>
			
			</td>
		
		
		</tr>
 	
	</c:forEach>
</table>
<%@ include file="page2.file" %> 



</body>
</html>