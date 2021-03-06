<%@page import="com.meetU.orderDetail.model.OrderDetailVO"%>
<%@page import="com.meetU.orderDetail.model.OrderDetailService"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	OrderDetailService odSvc = new OrderDetailService();
	OrderMasterService omSvc = new OrderMasterService();
	String order_ID = request.getParameter("order_ID");
	List<OrderDetailVO> list = odSvc.findOdByOm(order_ID); 
	pageContext.setAttribute("list", list);

	
	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
	String order_date = df.format(omSvc.getOneOm(order_ID).getOrder_date());
%>

<!doctype html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
	.pic{
		width:86.25px;
		height:115px;
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
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>訂單明細</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    <div class='container'>
 <table id = 'table-1'>
	<tr>
		<td>
			<h3>訂單明細【<%=order_ID%>】</h3>
			<h4>訂單成立時間: <%=order_date%></h4>
		</td>
	</tr>
</table>

<%-- 錯誤列表 --%>
<c:if test='${not empty errorMsgs }'>
	<font style='color:red'>請修正以下錯誤</font>
	<ul>
		<c:forEach var='message' items='${errorMsgs}'>
			<li style='color:red'>${message}</li>
		</c:forEach>
	</ul>
</c:if>


	<div class='row'>
	<div class="col-3" style="color:#0a0ac3"><h3>商品名稱 </h3></div>
    <div class="col-2" style="color:#0a0ac3"><h3>商品單價 </h3></div>
    <div class="col-1" style="color:#0a0ac3"><h3>數量 </h3></div>
    <div class="col-6" style="color:#0a0ac3"><h3>圖片 </h3></div>
	</div>
	<div class='row'>
 	<div class='col-9'><hr></div>
 	</div>
	<jsp:useBean id='prodSvc' scope='page' class='com.meetU.product.model.ProductService'/>
	<c:forEach var="odVO" items= "${list}" begin="0" end="${list.size()-1}" >
		
		
		
			<c:set scope="page" var="prod_type">
    				<c:out value="${prodSvc.getOneProd(odVO.prod_ID).prod_type}"/>  
			</c:set>
			<div class='row align-items-center'>		
			<div class="col-3">
				<a href='<%=request.getContextPath()%>/FrontEnd/cart/prodDetail.jsp?prod_ID=${odVO.prod_ID}'>
					<h4>${prodSvc.getOneProd(odVO.prod_ID).prod_name}</h4>
				</a>
			</div>
		    <div class="col-2"><h4>${odVO.price}</h4></div>
		    <div class="col-1"><h4>${odVO.quantity}</h4></div>
		    <div class="col-6">
		    	<a href='<%=request.getContextPath()%>/FrontEnd/cart/prodDetail.jsp?prod_ID=${odVO.prod_ID}'>
		    		<img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodSvc.getOneProd(odVO.prod_ID).prod_ID}'>
		    	</a>
		    </div>
			</div>
 	<div class='row'>
 	<div class='col-9'><hr></div>
 	</div>
	</c:forEach>

    
    </div> 
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>