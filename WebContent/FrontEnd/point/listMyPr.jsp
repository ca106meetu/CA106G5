<%@page import="com.meetU.pointRec.model.PointRecVO"%>
<%@page import="com.meetU.pointRec.model.PrService"%>
<%@page import="com.meetU.mem.model.MemVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PrService prSvc = new PrService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	List<PointRecVO> list = prSvc.getPrByMem(memVO.getMem_ID());
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
  <head>
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

    <title>Hello, world!</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    
 <table id = 'table-1'>
	<tr><td>
		<h3>${memVO.mem_name}的儲值紀錄-listMyPr.jsp</h3>
	
	
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

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員名稱</th>
		<th>訂單金額</th>

	</tr>
	<%@ include file="page1.file" %> 
	
	<jsp:useBean id='memSvc' scope='page' class='com.meetU.mem.model.MemService'/>
	<c:forEach var="prVO" items= "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${prVO.rec_ID}</td>
			<td>${prVO.amount}</td>
			<td>${prVO.rec_date}</td>
			
			<td>
				<form method='post' action='listOdByOm.jsp' style="margin-bottom: 0px;">
					<input type='submit' value='查看明細'>
					<input type='hidden' name='order_ID' value='${omVO.order_ID}'>
				</form>
			</td>
		</tr>
 	
	</c:forEach>
</table>
<%@ include file="page2.file" %> 
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>