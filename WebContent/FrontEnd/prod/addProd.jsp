<%@page import="com.meetU.product.model.ProductVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-2.1.4.min.js">
</script>

<%
	ProductVO prodVO = (ProductVO) request.getAttribute("prodVO");
%>

<html>
<head>
<meta charset="BIG5">

<title>商品資料新增 - addEmp.jsp</title>

<style>
	.pic{
		width:172.5px;
		height:230px;
	}
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>

<body bgcolor='gray'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增 - addEmp.jsp</h3></td><td>
		 <h4><a href="selectPage.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method='post' action='prod.do' enctype='multipart/form-data'>

<table>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name='prod_name' size="45" 
			 value='<%= (prodVO==null)? "廖大叔" : prodVO.getProd_name()%>'/></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="prod_price" size="45"
			 value='<%= (prodVO==null)? "699.5" : prodVO.getProd_price()%>'/></td>
	</tr>
	<tr>
		<td>類型:</td>
		<td><input name="prod_type" type="text" value='<%= (prodVO==null)? "1" : prodVO.getProd_type()%>'></td>
	</tr>
	<tr>
		<td>庫存量:</td>
		<td><input type="TEXT" name='prod_stock' size="45"
			 value='<%= (prodVO==null)? "69" : prodVO.getProd_stock()%>' /></td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="prod_pic" size="45" onchange='readURL(this)' /><br>
		<img class='pic' src='data:img/png;base64,${encodeText}' ${(prodVO.prod_pic==null)? 'style="display:none"' : ''}></td>
	</tr>
	<tr>
		<td>促銷狀態:</td>
		<td><input type="TEXT" name="prod_promt_status" size="45"
			 value='<%= (prodVO==null)? "0" : prodVO.getProd_promt_status()%>'/></td>
	</tr>
	<tr>
		<td>上架狀態:</td>
		<td><input type="TEXT" name="prod_status" size="45"
			 value='<%= (prodVO==null)? "0" : prodVO.getProd_status()%>'/></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" name="prod_info" size="45"
			 value='<%= (prodVO==null)? "asdasdijaojuewhildhi" : prodVO.getProd_info()%>'/></td>
	</tr>

</table>

<input type='hidden' name='action' value='insert'>
<button type='submit'>送出新增</button>
</form>
<script>
	function readURL(input){
		var reader = new FileReader();
	  		reader.onload = function (e) {
     					$(".pic").attr('src', e.target.result).css("display","");
     			
  		}
  		reader.readAsDataURL(input.files[0]);
	}
</script>

</body>
</html>