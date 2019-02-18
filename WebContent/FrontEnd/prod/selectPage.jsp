<%@ page contentType="text/html; charsetUTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
 
</style>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>meetU Prod: Home</title>
</head>
<body bgcolor='gray'>

<div class="container">
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
		<form method='post' action="">
			
			<div class="input-group">
			  <select class="custom-select" name='prod_ID' id="inputGroupSelect04" aria-label="Example select with button addon">
			    <option selected>選擇商品編號...</option>
			    <c:forEach var='prodVO' items='${prodSvc.all}'>
					<option value='${prodVO.prod_ID}'>${prodVO.prod_ID}
				</c:forEach>
			  </select>
			  <div class="input-group-append">
			    <button class="btn btn-info" type="button">送出</button>
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
		<form method='post' action="">
		
		
		
			<div class="input-group">
			  <select class="custom-select" name='prod_ID' id="inputGroupSelect04" aria-label="Example select with button addon">
			    <option selected>選擇商品名稱...</option>
			    <c:forEach var='prodVO' items='${prodSvc.all}'>
					<option value='${prodVO.prod_ID}'>${prodVO.prod_name}
				</c:forEach>
			  </select>
			  <div class="input-group-append">
			    <button class="btn btn-info" type="button">送出</button>
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
	<li><a href='#'>Add</a> a new Prod.</li>
	</ul>
	</div>
	<div class="col-6"></div>
</div>
</div>
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>