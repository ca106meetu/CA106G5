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
        <div class="col-5"><h3>商品</h3></div>
        <div class="col-3"><h3>單價</h3></div>
        <div class="col-2"><h3>數量</h3></div>
        <div class="col-2"><h3>總計</h3></div>
      </div>
      <br><br>
      
      <c:forEach var="prodVO" items= "${shoppingCart}">
      <div class="row align-items-center">
        <div class="col-1"><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></div>
        <div class="col-2">${prodVO.prod_name}</div>
        <div class="col-2"></div>
        <div class="col-3">$${prodVO.prod_price}</div>
        <div class="col-2">${prodVO.quantity}</div>
        <div class="col-2">${prodVO.prod_price*prodVO.quantity}</div>
      </div>
      </c:forEach>
      <hr>
      
      
      <div class="row">
        <div class="col-8"></div>
        <div class="col-2">您的帳戶: </div>
        <div class="col-1 text-success justify-content-end">${memVO.mem_get_point}</div>
        <div class="col-1">元</div>
      </div>      
      <div class="row">
        <div class="col-8"></div>
        <div class="col-2">訂單金額: </div>
        <div class="col-1 text-danger justify-content-end">${amount}</div>
        <div class="col-1">元</div>
      </div>
            
      <div class="row">
        <div class="col-8"></div>
        <div class="col-2">訂單金額: </div>
        <div class="col-1 text-${(memVO.mem_get_point-amount) < 0 ? 'danger' : 'success'} justify-content-end">${(memVO.mem_get_point-amount) < 0 ? '餘額不足' :  (memVO.mem_get_point-amount)}</div>
        <div class="col-1">${(memVO.mem_get_point-amount) < 0 ? '' : '元'}</div>
      </div>      
      
      <hr>
      
      <div class='row'>
      
      <div class='col-3'></div>
      <div class='col-6'>
      
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
						<span class="input-group-text" id="inputGroup-sizing-sm">【${memVO.mem_name}】</span>
						<input type='hidden' name='mem_ID' value='${memVO.mem_ID}'>
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
			<input type='hidden' name='out_status' value='0'>
			<input type='hidden' name='order_status' value='0'>
			<button type='submit' class="btn btn-outline-success"  >確認付款</button>
			</form>
      
      
      </div>
      <div class='col-3'></div>
      
      
      </div>


      
      
      

    </div>
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>