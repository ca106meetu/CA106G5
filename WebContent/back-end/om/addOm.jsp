<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<%
	OrderMasterVO omVO = (OrderMasterVO) request.getAttribute("omVO");
%>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
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

    <title>新增訂單</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    <table id="table-1">
	<tr><td>
		 <h3>訂單資料新增 - addOm.jsp</h3></td><td>
		 <h4><a href="selectPageOm.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
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
<jsp:useBean id='memSvc' scope='page' class='com.meetU.mem.model.MemService'/>
<form method='post' action='om.do'>

<table>
	<tr>
		<td>會員名稱:</td>
		<td>
			<div class="input-group">
			  <select class="custom-select" name='mem_ID' id="inputGroupSelect04" aria-label="Example select with button addon">
			    <c:forEach var='memVO' items='${memSvc.all}'>
					<option value='${memVO.mem_ID}' 
						${memVO.mem_ID == omVO.mem_ID ? 'selected' : ''}>【${memVO.mem_ID}】${memVO.mem_name}
				</c:forEach>
			  </select>
			</div>
		</td>
	</tr>
	<tr>
		<td>訂單金額:</td>
		<td><input type="TEXT" name="price" size="45" class='form-control'
			 value='<%= (omVO==null)? "699.5" : omVO.getPrice()%>'/></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>類型:</td> -->
<!-- 		<td> -->
		
<!-- 		<select name='prod_type'> -->
<%-- 		<c:forEach var='prod_type' items='${pt}'> --%>
<%-- 			<option value='${pt.indexOf(prod_type)}'  --%>
<%-- 							${omVO.prod_type==pt.indexOf(prod_type) ? 'selected' : '' }> ${prod_type}		 --%>
<%-- 		</c:forEach> --%>
<!-- 		</select> -->
		
		
<!-- 		</td> -->
<!-- 	</tr> -->
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

<input type='hidden' name='action' value='insert'>
<button type='submit' class="btn btn-outline-success">送出新增</button>
</form>
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>