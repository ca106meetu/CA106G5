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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
 <table id = 'table-1'>
	<tr><td>
		<h3>�Ҧ��ӫ~���-listAllProd.jsp</h3>
		<h4><a href='#'><img src="images/back1.gif" width="100" height="32">�^����</a></h4>
	
	
	</td>
	
	
	</tr>




</table>



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
		<th>�ק�</th>
		<th>�R��</th>		
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
					<input type='submit' value='�ק�'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID }'>
					<input type='hidden' name='action' value='getOne_For_Update'>				
				</form></td>
			<td>	
				<form method='post' action='' style="margin-bottom: 0px;">
					<input type='submit' value='�R��'>
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