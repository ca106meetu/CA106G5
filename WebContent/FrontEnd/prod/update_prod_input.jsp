<%@page import="com.meetU.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductVO prodVO = (ProductVO) request.getAttribute("prodVO");
%>

<!doctype html>
<html lang="en">
  <head>
  <style>
.pic{
	width:172.5px;
	height:230px;
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
		 <h3>商品資料修改- update_prod_input.jsp</h3>
		 <h4><a href="selectPage.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="prod.do" name="form1" enctype='multipart/form-data'>
<table>
	<tr>
		<td>商品編號:<font color=red><b>*</b></font></td>
		<td><%=prodVO.getProd_ID()%></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" name="prod_name" size="45" value="<%=prodVO.getProd_name()%>" /></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" name="prod_price" size="45" value="<%=prodVO.getProd_price()%>" /></td>
	</tr>
	<tr>
		<td>商品種類:</td>
		<td><input type="TEXT" name="prod_type" size="45" value="<%=prodVO.getProd_type()%>" /></td>
	</tr>
	<tr>
		<td>庫存量:</td>
		<td><input name="prod_stock" type="text" value="<%=prodVO.getProd_stock()%>"></td>
	</tr>
	
	<tr>
		<td>促銷狀態:</td>
		<td><input type="TEXT" name="prod_promt_status" size="45" value="<%=prodVO.getProd_promt_status()%>" /></td>
	</tr>
	<tr>
		<td>上架狀態:</td>
		<td><input type="TEXT" name="prod_status" size="45" value="<%=prodVO.getProd_status()%>" /></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" name="prod_info" size="45" value="<%=prodVO.getProd_info()%>" /></td>
	</tr>

	<tr>
		<td>圖片:</td>
		<td><input type="file" name="prod_pic" size="45" onchange='readURL(this)'/><br>
		<img class='pic' src="data:img/png;base64,${encodeText}" ></td>
		
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ID" value="<%=prodVO.getProd_ID()%>">
<input type="submit" value="送出修改"></FORM>
<script>
			
			
			function readURL(input){
				var reader = new FileReader();
	    		reader.onload = function (e) {
	       			
	       					
	       					$(".pic").attr('src', e.target.result);
	       					
	       			
	    		}
	    		reader.readAsDataURL(input.files[0]);
			}


		</script>
</body>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>