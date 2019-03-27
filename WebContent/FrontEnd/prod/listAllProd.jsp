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
  
  .t {
  	color:black;
  }
  .d {
  	color:blue;
  }
  
  .card-body {
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 1.25rem;
    width: 800px;
}
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
    
    
 	<div class='container'>
 		
<table id = 'table-1'>
	<tr><td>
		<h3>所有商品資料</h3>
		<h4><a href='selectPage.jsp'><img src="images/back1.png" width="60" >回商品管理</a></h4>
	
	
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

	<%@ include file="page1.file" %> 

		<div class='row'>
	 	<div class='col-9'><hr></div>
	 	</div>
		<div class="row">
		<div class="col-3" style="color:#0a0ac3"><h3>商品編號 </h3></div>
        <div class="col-3" style="color:#0a0ac3"><h3>商品名稱 </h3></div>
        <div class="col-3" style="color:#0a0ac3"><h3>上架狀態 </h3></div>
        </div>
        <div class='row'>
	 	<div class='col-9'><hr></div>
	 	</div>
	
	<%
		if(request.getAttribute("lastPage") != null &&(boolean)request.getAttribute("lastPage")){
			pageIndex = pageIndexArray[pageNumber-1];
		}
	%>
	<c:forEach var="prodVO" items= "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus='status'>



		<div class="row" ${(prodVO.prod_ID==param.prod_ID) ?  'style="background-color:#AFEEEE;"':''}>
        <div class="col-3">
          
        <p>
          <button class="btn btn-info" type="button" data-toggle="collapse" data-target="#${prodVO.prod_ID}" aria-expanded="false" aria-controls="collapseExample">
            	<h4>【${prodVO.prod_ID}】</h4>
          </button>
        </p>
        

        </div>
        <div class="col-3"><h4>${prodVO.prod_name}</h4></div>
        <div class="col-3"><h4>${ps[prodVO.prod_status]}</h4></div>
      </div>

	<div class="row">
        <div class="collapse" id="${prodVO.prod_ID}">
          <div class="card card-body" style='border:0px;'>
	<div class="row">
	<div class='col-6'>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">商品編號</div>
      <div class="col-6 d">${prodVO.prod_ID}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">商品名稱</div>
      <div class="col-6 d">${prodVO.prod_name}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">商品價格</div>
      <div class="col-6 d">${prodVO.prod_price}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">類型</div>
      <div class="col-6 d">${pt[prodVO.prod_type]}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">庫存量</div>
      <div class="col-6 d">${prodVO.prod_stock}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">促銷狀態</div>
      <div class="col-6 d">${pps[prodVO.prod_promt_status]}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">上架狀態</div>
      <div class="col-6 d">${ps[prodVO.prod_status]}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">商品資訊</div>
      <div class="col-6 d">${prodVO.prod_info}</div>
    </div>
    <div class="row inner border border-info rounded">
      <div class="col-6 t">修改</div>
      <div class="col-6 d">
      			<form method='post' action='prod.do' style="margin-bottom: 0px;">
					<input type='submit' value='修改' class='btn btn-primary'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID}'>
					<input type='hidden' name='action' value='getOne_For_Update'>
					<input type='hidden' name='whichPage' value='${param.whichPage}'>				
					<input type='hidden' name='requestURL' value='<%=request.getServletPath()%>'>				
				</form>
	   </div> 
    
    
          </div>

    </div>
<div class='col-6'>

<img class='pic' src='/CA106G5/ShowPic?PROD_ID=${prodVO.prod_ID}'>
</div>
</div>
   
    
    
        </div>

      </div>
      
      </div>
		<div class='row'>
	 	<div class='col-9'><hr></div>
	 	</div>
 	
	</c:forEach>
<%@ include file="page2.file" %> 
 	</div>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>