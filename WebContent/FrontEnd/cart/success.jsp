<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
  	body {
		  font-family: '微軟正黑體';
		}
  	
  </style>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>購物成功!!水喔</title>  
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    
    <div class='container'>
    
    	<div class='row'>
    		<div class='col-4'></div>
    		<div class='col-4 justify-center'><h1 class='text-info'>訂單成立!</h1></div>
    		<div class='col-4'></div>
    	
    	</div>
    	
    	<div class='row'>
    		<div class='col-4'><img src='images/allforu.JPG'></div>
    		<div class='col-4'></div>
    		<div class='col-4'></div>
    	</div>
    	
    	<div class='row'>
    		<div class='col-4'></div>
    		<div class='col-4 justify-center'><a href='EShop.jsp' class="btn btn-primary">繼續購物</a></div>
    		<div class='col-4'></div>
    	
    	</div>
    </div>
     
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>