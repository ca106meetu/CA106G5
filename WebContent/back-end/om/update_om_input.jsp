<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	OrderMasterVO omVO = (OrderMasterVO) request.getAttribute("omVO");
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
<!--     Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!--     Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>Hello, world!</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    
    <table id="table-1">
	<tr><td>
		 <h3>訂單資料修改- update_prod_input.jsp</h3>
		 <h4><a href="selectPageOm.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="om.do" name="form1" >
<table>

	<tr>
		<td>訂單編號: </td>
		<td><span class="input-group-text" id="inputGroup-sizing-sm">【${omVO.order_ID}】</span></td>
	
	
	</tr>
	<jsp:useBean id='memSvc' scope='page' class='com.meetU.mem.model.MemService'/>
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
		<td><input type="TEXT" class='form-control' name="price" size="45"
			 value='${omVO.price}'/></td>
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
		<td><input type="TEXT" class='form-control' name='out_add' size="45"
			 value='${omVO.out_add}' /></td>
	</tr>
	
	<tr>
		<td>收件人:</td>
		<td><input type="TEXT"  class='form-control' name="recipient" size="45"
			 value='${omVO.recipient}'/></td>
	</tr>
	
	<tr>
		<td>收件人電話:</td>
		<td><input type="TEXT" class='form-control' name="phone" size="45"
			 value='${omVO.phone}'/></td>
	</tr>
	
	<tr>
		<td>出貨狀態:</td>
		<td><select name='out_status' class='form-control'>
		<c:forEach var='out_status' items='${outs}'>
			<option  value='${outs.indexOf(out_status)}' 
							${omVO.out_status==outs.indexOf(out_status) ? 'selected' : '' }> ${out_status}		
		</c:forEach>
		</select></td>
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
			    rows="3">${omVO.tip}</textarea>
		  	</div>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="order_ID" value="<%=omVO.getOrder_ID()%>">
<button type='submit' class="btn btn-outline-success">送出修改</button>
</FORM>
</body>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>