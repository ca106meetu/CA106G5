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
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>點數儲值</title>  
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    <hr>
    
    <div class="container">
    		

			<div class="row justify-content-center">
				<h2>點數儲值</h2>
			</div>	


			<div class="row">
				
				<form action='pr.do' method='post' onsubmit='return allowUser();'>
					<div class="input-group">
					  <select name='amount' class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon">
					    <option selected value='0'>請輸入要儲值的金額</option>
					    <option value="5000">5000</option>
					    <option value="10000">10000</option>
					    <option value="20000">20000</option>
					  </select>
					  <div class="input-group-append">
					    <button class="btn btn-outline-secondary store" type="submit">Button</button>
					  	<input type='hidden' name='action' value='insert'>
					  </div>
					</div>
				</form>
				
			</div>

    	</div>
    
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
    
    
   <script>
   $(document).ready(function(){
	    $('.store').click(function(){
				 if(!allowUser()){ 
					 <%session.setAttribute("location", request.getRequestURI());%>
					 $('#login').modal('show');
					 return;
				 }else{
					window.location.href=('http://www.ncu.edu.tw');
				 } 
		 });
   })
    </script>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>