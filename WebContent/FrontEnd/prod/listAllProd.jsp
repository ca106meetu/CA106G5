<%@page import="com.meetU.product.model.ProductVO"%>
<%@page import="java.util.List"%>
<%@page import="com.meetU.product.model.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	ProductService prodSvc = new ProductService(); 
	List<ProductVO> list = prodSvc.getAll();
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

    <title>所有商品資料</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    
    <h4>此頁練習採用 EL 的寫法取值:</h4>
 <table id = 'table-1'>
	<tr><td>
		<h3>所有商品資料-listAllProd.jsp</h3>
		<h4><a href='selectPage.jsp'><img src="images/back1.gif" width="100" height="32">回首頁</a></h4>
	
	
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
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>類型</th>
		<th>庫存量</th>
		<th>圖片</th>
		<th>促銷狀態</th>
		<th>上架狀態</th>
		<th>商品資訊</th>
		<th>修改</th>
		<th>刪除</th>		
	</tr>
	<%@ include file="page1.file" %> 
	<%
		if(request.getAttribute("lastPage") != null &&(boolean)request.getAttribute("lastPage")){
			pageIndex = pageIndexArray[pageNumber-1];
		}
	%>
	<c:forEach var="prodVO" items= "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(prodVO.prod_ID==param.prod_ID) ? 'bgcolor=#CCCCFF':''}>
			<td>${prodVO.prod_ID}</td>
			<td>${prodVO.prod_name}</td>
			<td>${prodVO.prod_price}</td>
			<td>${pt[prodVO.prod_type]}</td>
			<td>${prodVO.prod_stock}</td>
			<td><img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'></td>
			<td>${pps[prodVO.prod_promt_status]}</td>
			<td>${ps[prodVO.prod_status]}</td>
			<td>${prodVO.prod_info}</td>
			<td>
				<form method='post' action='prod.do' style="margin-bottom: 0px;">
					<input type='submit' value='修改'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID}'>
					<input type='hidden' name='action' value='getOne_For_Update'>
					<input type='hidden' name='whichPage' value='${param.whichPage}'>				
					<input type='hidden' name='requestURL' value='<%=request.getServletPath()%>'>				
				</form></td>
			<td>	
				<form method='post' action='prod.do' style="margin-bottom: 0px;">
					<input type='submit' value='刪除'>
					<input type='hidden' name='action' value='delete'>				
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID}'>
					<input type='hidden' name='whichPage' value='${param.whichPage}'>				
					<input type='hidden' name='requestURL' value='<%=request.getServletPath()%>'>				
				</form>
			
			</td>
			
		
		
		</tr>
 	
	</c:forEach>
</table>
<%@ include file="page2.file" %> 
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>