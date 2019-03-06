<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String host_ID = (String) request.getAttribute("host_ID");
	
%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>直播影片間</title>  
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
    
    <div class="container-fluid">
			<div class="row">
				<div class="col-8"><iframe width="95%" height="650" src="https://www.youtube.com/embed/60ZHM9nPQps" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></div>
				<div class="col-4">這邊以後是聊天室</div>
			</div>
			</div>
			
	<div id='live_like'>
	
	
		<form action="<%=request.getContextPath()%>/FrontEnd/fileRec/fileRecHome.jsp" method='post'>
			<input type='hidden' name='host_ID' value='${host_ID}'>
			<input type='submit' class="btn btn-primary" value='觀看直播主影片'>
		</form>

	</div>
	
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>