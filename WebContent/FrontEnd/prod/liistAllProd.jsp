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
<title>�Ҧ��ӫ~���-listAllProd.jsp</title>



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
		<th>�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>����</th>
		<th>�w�s�q</th>
		<th>�Ϥ�</th>
		<th>�P�P���A</th>
		<th>�W�[���A</th>
		<th>�ӫ~��T</th>
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