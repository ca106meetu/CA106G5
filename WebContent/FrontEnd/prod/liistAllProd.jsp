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
	img{
		width:345px;
		height:460px;
	}
	
</style>
</head>
<body bgcolor = 'gray'>

<%-- <table id = 'table-1'>
	<tr><td>
	
	
	
	</td>
	
	
	</tr>




</table>
--%>


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
	<c:forEach var="prodVO" items= "${list }">
		<tr>
			<td>${prodVO.prod_ID}</td>
			<td>${prodVO.prod_name}</td>
			<td>${prodVO.prod_price}</td>
			<td>${prodVO.prod_type}</td>
			<td>${prodVO.prod_stock}</td>
			<td><img src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></td>
			<td>${prodVO.prod_promt_status}</td>
			<td>${prodVO.prod_status}</td>
			<td>${prodVO.prod_info}</td>
		
		
		</tr>
 	
	</c:forEach>
</table>












</body>
</html>