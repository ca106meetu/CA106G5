<%@ page contentType="text/html; charsetUTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap4/css/bootstrap.min.css">
<title>meetU Prod: Home</title>
</head>
<body bgcolor='gray'>

<div class="container justify-content-center">
<div class="row">
<div class="col-6">

<table id='table-1'>
<tr><td><h3>meetU Prod: Home</h3></td></tr>
</table>
<p>This is the Home page for meetU Prod: Home</p>
<%-- 錯誤列表 --%>
<c:if test='${not empty errorMsgs }'>
	<font style='color:red'>請修正以下錯誤</font>
	<ul>
		<c:forEach var='message' items='${errorMsgs}'>
			<li style='color:red'>${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
	<li><a href='listAllProd.jsp' class="btn btn-link" role="button">List all Prods.</a></li>
	<br></br>
	<li>
		<form method='post' action="prod.do">
		<div class="input-group mb-3">
	  		<input type="text" class="form-control" name='prod_ID' placeholder="請輸入商品編號(如P000001)" aria-label="Recipient's username" aria-describedby="button-addon2">
	  	<div class="input-group-append">
	    	<button class="btn btn-info" type="submit" id="button-addon2">送出</button>
	  	</div>
		</div>
			<input type='hidden' name='action' value='getOne_For_Display'>
		</form>
		<%-- 
			<b>輸入商品編號(如P000001):</b>
			<input type='text' name='empno'>
			<input type='submit' value='送出'>
		--%>
	</li>
	<jsp:useBean id='prodSvc' scope='page' class='com.meetU.product.model.ProductService'/>
	
	<li>
		<form method='post' action="prod.do">
			
			<div class="input-group">
			  <select class="custom-select" name='prod_ID' id="inputGroupSelect04" aria-label="Example select with button addon">
			    <option selected>選擇商品編號...</option>
			    <c:forEach var='prodVO' items='${prodSvc.all}'>
					<option value='${prodVO.prod_ID}'>${prodVO.prod_ID}
				</c:forEach>
			  </select>
			  <div class="input-group-append">
			    <button class="btn btn-info" type="submit">送出</button>
			  </div>
			</div>
			<input type='hidden' name='action' value='getOne_For_Display'>
			<%--
			<b>選擇商品編號:</b>
			<select size='1' name='prod_ID'>
				<c:forEach var='prodVO' items='${prodSvc.all}'>
					<option value='${prodVO.prod_ID}'>${prodVO.prod_ID}
				</c:forEach>
			
			</select>
			<input type='submit' value='送出'>
			--%>
		</form>
	
	</li>
	<li>
		<form method='post' action="prod.do">
		
		
		
			<div class="input-group">
			  <select class="custom-select" name='prod_ID' id="inputGroupSelect04" aria-label="Example select with button addon">
			    <option selected>選擇商品名稱...</option>
			    <c:forEach var='prodVO' items='${prodSvc.all}'>
					<option value='${prodVO.prod_ID}'>${prodVO.prod_name}
				</c:forEach>
			  </select>
			  <div class="input-group-append">
			    <button class="btn btn-info" type="submit">送出</button>
			  </div>
			</div>
			<input type='hidden' name='action' value='getOne_For_Display'>
			
			<%--<b>選擇商品名稱:</b>
			<select size='1' name='prod_ID'>
				<c:forEach var='prodVO' items='${prodSvc.all}'>
					<option value='${prodVO.prod_ID}'>${prodVO.prod_name}
				</c:forEach>
			
			</select>
			<input type='hidden' name='action' value='getOne_For_Display'>
			<input type='submit' value='送出'>--%>
		</form>
	
	</li>
</ul>

	<h3>商品管理</h3>
	
	<ul>
	<li><a href='addProd.jsp'>Add</a> a new Prod.</li>
	</ul>
	</div>
</div>
</div>
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>