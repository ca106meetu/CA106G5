<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
  
  
  	
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>訂單管理</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
    
    
    <body bgcolor='gray'>

<div class="container justify-content-center">
<div class="row">
<div class="col-6">

<table id='table-1'>
<tr><td><h3>訂單管理</h3></td></tr>
</table>

<ul>
	<li><a href='listAllOm.jsp' class="btn btn-link" role="button">所有訂單資料</a></li>
	<br>
	
	<jsp:useBean id='omSvc' scope='page' class='com.meetU.orderMaster.model.OrderMasterService'/>
	
	<li>
		<form method='post' action="om.do">
			<div class="input-group">
			  <select class="custom-select" name='order_ID' id="inputGroupSelect04" aria-label="Example select with button addon">
			    <option selected>選擇訂單編號...</option>
			    <c:forEach var='omVO' items='${omSvc.all}'>
					<option value='${omVO.order_ID}'>${omVO.order_ID}
				</c:forEach>
			  </select>
			  <div class="input-group-append">
			    <button class="btn btn-info" type="submit">送出</button>
			  </div>
			</div>
			<input type='hidden' name='action' value='getOne_For_Display'>
		</form>
	
	</li>
	<jsp:useBean id='memSvc' scope='page' class='com.meetU.mem.model.MemService'/>
	<li>
		<form method='post' action="getOmByMem.jsp">
			<div class="input-group">
			  <select class="custom-select" name='mem_ID' id="inputGroupSelect04" aria-label="Example select with button addon">
			    <option selected>選擇會員名稱...</option>
			    <c:forEach var='memVO' items='${memSvc.all}'>
					<option value='${memVO.mem_ID}'>${memVO.mem_name}
				</c:forEach>
			  </select>
			  <div class="input-group-append">
			    <button class="btn btn-info" type="submit">送出</button>
			  </div>
			</div>
		</form>
	
	</li>
</ul>
 
	</div>
</div>
</div>

    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>