<%@page import="com.meetU.mem.model.MemVO"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	OrderMasterService omSvc = new OrderMasterService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	List<OrderMasterVO> list = omSvc.getOmByMem(memVO.getMem_ID());
	pageContext.setAttribute("list", list);
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

    <title>${memVO.mem_name}的訂單</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    <hr>
    <div class='container'>
    
 <table id = 'table-1'>
	<tr><td>
		<h3>所有【${memVO.mem_name}】的訂單</h3> 
	
	
	</td>
	
	
	</tr>




</table>
	<br>
<table>
	<tr>
		<th>訂單編號</th>
		<th>會員名稱</th>
		<th>訂單金額</th>
		<th>訂單日期</th>
		<th>出貨地址</th>
		<th>收件人</th>
		<th>收件人電話</th>
		<th>出貨狀態</th>
		<th>出貨日期</th>
		<th>訂單狀態</th>
		<th>備註</th>
		<th>查看明細</th>

	</tr>
	<%@ include file="page1.file" %> 
	
	<jsp:useBean id='memSvc' scope='page' class='com.meetU.mem.model.MemService'/>
	<c:forEach var="omVO" items= "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${omVO.order_ID}</td>
			<td>${memSvc.getOneMem(omVO.mem_ID).mem_name}</td>
			<td>${omVO.price}</td>
			<td>
			<fmt:formatDate value="${omVO.order_date}" pattern="yyyy-MM-dd HH:mm" />
			</td>
			<td>${omVO.out_add}</td>
			<td>${omVO.recipient}</td>
			<td>${omVO.phone}</td>
			<td>${outs[omVO.out_status]}</td>
			<td>
			<fmt:formatDate value="${omVO.out_date}" pattern="yyyy-MM-dd HH:mm" />
			</td>
			<td>${ords[omVO.order_status]}</td>
			<td>${omVO.tip}</td>
			
			<td>
				<form method='post' action='listOdByOm.jsp' style="margin-bottom: 0px;">
					<input type='submit' value='查看明細' class='btn btn-primary'>
					<input type='hidden' name='order_ID' value='${omVO.order_ID}'>
				</form>
			</td>
		</tr>
 	
	</c:forEach>
</table>
<%@ include file="page2.file" %> 
</div>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>