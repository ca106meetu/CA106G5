<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@page import="java.util.Vector"%>
<%@page import="com.meetU.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	List<ProductVO> butList = (Vector<ProductVO>) session.getAttribute("shoppingCart");
	OrderMasterVO omVO = (OrderMasterVO) request.getAttribute("omVO");
%>
<!doctype html>
<html lang="en">
  <head>
	  <style type="text/css">
	  	.pic{
		width:86.25px;
		height:115px;
		}
	  </style>
	  <style>
	
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
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    
    
    <div class="container">
      <div class="row">
        <div class="col-5">商品</div>
        <div class="col-3">單價</div>
        <div class="col-2">數量</div>
        <div class="col-2">總計</div>
      </div>
      <br>
      
      <c:forEach var="prodVO" items= "${shoppingCart}">
      <div class="row">
        <div class="col-1"><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></div>
        <div class="col-2">${prodVO.prod_name}</div>
        <div class="col-2"></div>
        <div class="col-3">$${prodVO.prod_price}</div>
        <div class="col-2">${prodVO.quantity}</div>
        <div class="col-2">${prodVO.prod_price*prodVO.quantity}</div>
      </div>
      </c:forEach>
      
      <div class="row">
        <div class="col-9"></div>
        <div class="col-1">
        	<form method='post' action='ShoppingServlet'>
        		<input type='hidden' name='action' value='pay'>
        		<button type="submit" class="btn btn-warning">付款</button>
        	</form>
        </div>
        <div class="col-2">共${amount}元</div>
      </div><br>
      

<h3>請填寫訂單資料:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<jsp:useBean id='memSvc' scope='page' class='com.meetU.mem.model.MemService'/>
<form method='post' action='ShoppingServlet'>

<table>
	<tr>
		<td>會員名稱:</td>
		<td>
			${memVO.mem_name}
<!-- 			<div class="input-group"> -->
<!-- 			  <select class="custom-select" name='mem_ID' id="inputGroupSelect04" aria-label="Example select with button addon"> -->
<%-- 			    <c:forEach var='memVO' items='${memSvc.all}'> --%>
<%-- 					<option value='${memVO.mem_ID}'  --%>
<%-- 						${memVO.mem_ID == omVO.mem_ID ? 'selected' : ''}>【${memVO.mem_ID}】${memVO.mem_name} --%>
<%-- 				</c:forEach> --%>
<!-- 			  </select> -->
<!-- 			</div> -->
		</td>
	</tr>
	<tr>
		<td>出貨地址:</td>
		<td><input type="TEXT" name='out_add' size="45" class='form-control'
			 value='<%= (omVO==null)? "中央大學" : omVO.getOut_add()%>' /></td>
	</tr>
	
	<tr>
		<td>收件人:</td>
		<td><input type="TEXT" name="recipient" size="45" class='form-control'
			 value='<%= (omVO==null)? "松松" : omVO.getRecipient()%>'/></td>
	</tr>
	
	<tr>
		<td>收件人電話:</td>
		<td><input type="TEXT" name="phone" size="45" class='form-control'
			 value='<%= (omVO==null)? "0800092000" : omVO.getPhone()%>'/></td>
	</tr>
	
	<tr>
		<td>出貨狀態:</td>
		<td>
		<select name='out_status' class='form-control'>
		<c:forEach var='out_status' items='${outs}'>
			<option  value='${outs.indexOf(out_status)}' 
							${omVO.out_status==outs.indexOf(out_status) ? 'selected' : '' }> ${out_status}		
		</c:forEach>
		</select>
		</td>
	</tr>
	
	<tr>
		<td>訂單狀態:</td>
		<td>
		<select name='order_status' class='form-control'>
		<c:forEach var='order_status' items='${ords}'>
			<option  value='${ords.indexOf(order_status)}' 
							${omVO.order_status==ords.indexOf(order_status) ? 'selected' : '' }> ${order_status}		
		</c:forEach>
		</select>
		</td>
	</tr>
	
	<tr>
		<td>備註:</td>
		<td>
			<div class="form-group">
			    <textarea class="form-control" name='tip' id="exampleFormControlTextarea1" 
			    rows="3"><%= (omVO==null)? "松松愛柔柔" : omVO.getRecipient()%></textarea>
		  	</div>
		</td>
	</tr>

</table>
<input type='hidden' name='price' value='${amount}'>
<input type='hidden' name='action' value='insert'>
<button type='submit' class="btn btn-outline-success">送出新增</button>
</form>
      
      
      

    </div>
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>