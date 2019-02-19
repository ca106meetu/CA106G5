<%@page import="com.meetU.product.model.ProductVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	ProductVO prodVO = (ProductVO) request.getAttribute("prodVO");
%>

<html>
<head>
<meta charset="BIG5">

<title>Insert title here</title>

</head>

<body>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method='post' action='prod.do'>

商品名稱:<input type='text' name='prod_name' value='<%= (prodVO==null)? "廖大叔" : prodVO.getProd_name()%>'>
商品價格:<input type='text' name='prod_price' value='<%= (prodVO==null)? "699.5" : prodVO.getProd_price()%>'>
類型:<input type='text' name='prod_type' value='<%= (prodVO==null)? "1" : prodVO.getProd_type()%>'>
庫存量:<input type='text' name='prod_stock' value='<%= (prodVO==null)? "69" : prodVO.getProd_stock()%>'>
圖片:<input type='file' name='prod_pic' >
促銷狀態:<input type='text' name='prod_promt_status' value='<%= (prodVO==null)? "0" : prodVO.getProd_promt_status()%>'>
上架狀態:<input type='text' name='prod_status' value='<%= (prodVO==null)? "0" : prodVO.getProd_status()%>'>
商品資訊:<input type='text' name='prod_info' value='<%= (prodVO==null)? "大叔問問題前先自己看錯誤訊息" : prodVO.getProd_info()%>'>

<input type='hidden' name='action' value='insert'>
<button type='submit'>送出新增</button>
</form>


</body>
</html>