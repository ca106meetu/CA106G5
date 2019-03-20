<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

    <title>Hello, world!</title>  
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    
    
    <button type="button" class="btn btn-outline-success test" >
		  test
		</button> 
    
	
	<script>
		
	$(document).ready(function(){
		  
		 //$('.test').click(function(){
			 if(!allowUser()){ 
				 <%session.setAttribute("location", request.getRequestURI());%>
				 $('#btn_login').trigger('click');
				 //$('#login').modal('show');
				 return;
			 }else{
				 //swal('你已經登入了','你已經登入了','success');
				 return;
				 //alert('good');
			 }
//			 $.ajax({
//				 type: "POST",
//				 url: "ShoppingServlet",
//				 data: {"prod_ID":$(this).next().attr('value'), "action":"add", "quantity":$(this).parent().prev().val()},
//				 dataType: "json",
//				 success: function(){
					 
//					 $('#myModal').modal('show');
////	 					 alert("555");
//					},
			     
//	             error: function(){alert("AJAX-grade發生錯誤囉!")}
//	         });
	 //});
   })
	
	
	</script>    
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>