<%@page import="com.meetU.product.model.ProductVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductVO prodVO = (ProductVO) request.getAttribute("prodVO");
%>

<!doctype html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
.pic{
	width:172.5px;
	height:230px;
}
 </style>
<!--     Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!--     Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>商品修改</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    <div class='container'>
    
    <table id="table-1">
	<tr><td>
		 <h4><a href="selectPage.jsp"><img src="images/back1.png" width="60" border="0">回商品管理</a></h4>
	</td></tr>
</table>

<h3>商品資料修改:</h3>
    <div class='row'>

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
		
		<td><span class="input-group-text" id="inputGroup-sizing-sm">【<%=prodVO.getProd_ID()%>】</span></td>
	</tr>
	<tr>
		<td>商品名稱:</td>
		<td><input type="TEXT" class='form-control' name="prod_name" size="45" value="<%=prodVO.getProd_name()%>" /></td>
	</tr>
	<tr>
		<td>商品價格:</td>
		<td><input type="TEXT" class='form-control' name="prod_price" size="45" value="<%=prodVO.getProd_price()%>" /></td>
	</tr>
	<tr>
		<td>商品種類:</td>
		<td><select name='prod_type' class='form-control'>
		<c:forEach var='prod_type' items='${pt}'>
			<option value='${pt.indexOf(prod_type)}' 
							${prodVO.prod_type==pt.indexOf(prod_type) ? 'selected' : '' }> ${prod_type}		
		</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>庫存量:</td>
		<td><input name="prod_stock" class='form-control' type="text" value="<%=prodVO.getProd_stock()%>"></td>
	</tr>
	
	<tr>
		<td>促銷狀態:</td>
		<td><select name='prod_promt_status' class='form-control'>
		<c:forEach var='prod_promt_status' items='${pps}'>
			<option value='${pps.indexOf(prod_promt_status)}' 
							${prodVO.prod_promt_status==pps.indexOf(prod_promt_status) ? 'selected' : '' }> ${prod_promt_status}		
		</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>上架狀態:</td>
		<td><select name='prod_status' class='form-control'>
		<c:forEach var='prod_status' items='${ps}'>
			<option value='${ps.indexOf(prod_status)}' 
							${prodVO.prod_status==ps.indexOf(prod_status) ? 'selected' : '' }> ${prod_status}		
		</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>商品資訊:</td>
		<td><input type="TEXT" class='form-control' name="prod_info" size="45"  value="<%=prodVO.getProd_info()%>" /></td>
	</tr>

	<tr>
		<td>圖片:</td>
		<td>
		
		<div class="custom-file">
		    <input type="file" class="custom-file-input" id="validatedCustomFile" required onchange='readURL(this)'><br>
		    <input type='hidden' name='encodeText' value='${encodeText}'>
		    <label class="custom-file-label" for="validatedCustomFile">換一張圖吧!</label>
		    <div class="invalid-feedback">Example invalid custom file feedback</div>
	  	</div>
		    <img class='pic' src='data:img/png;base64,${encodeText}' ${(prodVO.prod_pic==null)? 'style="display:none"' : ''}>
		
		
		
<!-- 		<input type="file" name="prod_pic" size="45" onchange='readURL(this)'/><br> -->
<%-- 		<img class='pic' src="data:img/png;base64,${encodeText}" ${(prodVO.prod_pic==null)? 'style="display:none"' : ''}></td> --%>
		
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prod_ID" value="<%=prodVO.getProd_ID()%>">
<input type='hidden' name='whichPage' value='${param.whichPage}'>				
<input type='hidden' name='requestURL' value='<%=request.getServletPath()%>'>
<input type="submit" class="btn btn-outline-success" value="送出修改"></FORM>
</div>
</div>
<script>
			
			
			function readURL(input){
				var reader = new FileReader();
	    		reader.onload = function (e) {
	       			
	       					
	       					$(".pic").attr('src', e.target.result).css('display', '');
	       					
	       			
	    		}
	    		reader.readAsDataURL(input.files[0]);
			}


		</script>
</body>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>