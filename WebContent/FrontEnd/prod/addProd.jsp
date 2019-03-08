<%@page import="com.meetU.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<%
	ProductVO prodVO = (ProductVO) request.getAttribute("prodVO");
%>
<html lang="en">
  <head>
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
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>Hello, world!</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
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
			 value='<%= (prodVO==null)? "Captain Marvel" : prodVO.getProd_name()%>'/></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="prod_price" size="45"
			 value='<%= (prodVO==null)? "699.5" : prodVO.getProd_price()%>'/></td>
	</tr>
	<tr>
		<td>類型:</td>
		<td>
		
		<select name='prod_type'>
		<c:forEach var='prod_type' items='${pt}'>
			<option value='${pt.indexOf(prod_type)}' 
							${prodVO.prod_type==pt.indexOf(prod_type) ? 'selected' : '' }> ${prod_type}		
		</c:forEach>
		</select>
		
		
		</td>
	</tr>
	<tr>
		<td>庫存量:</td>
		<td><input type="TEXT" name='prod_stock' size="45"
			 value='<%= (prodVO==null)? "69" : prodVO.getProd_stock()%>' /></td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="prod_pic" size="45" onchange='readURL(this)' /><br>
		<input type='hidden' name='encodeText' value='${encodeText}'>
		<img class='pic' src='data:img/png;base64,${encodeText}' ${(prodVO.prod_pic==null)? 'style="display:none"' : ''}></td>
	</tr>
	<tr>
		<td>促銷狀態:</td>
		<td>
		<select name='prod_promt_status'>
		<c:forEach var='prod_promt_status' items='${pps}'>
			<option value='${pps.indexOf(prod_promt_status)}' 
							${prodVO.prod_promt_status==pps.indexOf(prod_promt_status) ? 'selected' : '' }> ${prod_promt_status}		
		</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td>上架狀態:</td>
		<td>
		<select name='prod_status'>
		<c:forEach var='prod_status' items='${ps}'>
			<option value='${ps.indexOf(prod_status)}' 
							${prodVO.prod_status==ps.indexOf(prod_status) ? 'selected' : '' }> ${prod_status}		
		</c:forEach>
		</select>
		</td> 	
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
    
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>