<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_mem.model.*, com.meetU.meetup.model.* "%>
<%@ page import="com.meetU.mem.model.*"%>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<% MeetupMemVO meetupMemVO = (MeetupMemVO) request.getAttribute("meetupMemVO");%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
<!-- page label -->    
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<!-- fontAwesome --> 
<%-- 	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous"> 	 -->
<-- 	<link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/meetup/fontawesome/css/fontawesome.min.css"/> --%>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">	
<title>聯誼評價</title>

<style>
  img{
  	width : 150px;
  	height: auto;
  }	
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>  
<style>
  	.star-rating{
		font-size: 0;
	}
	.star-rating__wrap{
		display: inline-block;
		font-size: 1rem;
	}
	.star-rating__wrap:after{
		content: "";
		display: table;
		clear: both;
	}
	.star-rating__ico{
		float: right;
		padding-left: 2px;
		cursor: pointer;
		color: #FFB300;
	}
	.star-rating__ico:last-child{
		padding-left: 0;
	}
	.star-rating__input{
		display: none;
	}
	.star-rating__ico:hover:before,
	.star-rating__ico:hover ~ .star-rating__ico:before,
	.star-rating__input:checked ~ .star-rating__ico:before
	{
		content: "\f005";
	}
</style>

</head>
<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="msg" items="${errorMsgs}">
			<li style="color:red">${msg}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="container">
	<div class="row">
	<FORM METHOD="POST" ACTION="meetupMem.do" name="form1" enctype='multipart/form-data'>
	<table>
		<tr>
			<th>聯誼名稱</th>
			<td><input type="text" name="meetup_ID" size="45" value="<%=meetupMemVO.getMeetup_ID()%>"/></td>
		</tr>
		
		<tr>
			<th>聯誼成員</th>
			<td><input type="text" name="mem_ID" size="45" value="<%=meetupMemVO.getMem_ID()%>"/></td>
		</tr>
		<tr>
			<th>聯誼評價</th>
			<td>
				<div class="star-rating">
			      <div class="star-rating__wrap">
			        <input class="star-rating__input" id="star-rating-5" type="radio" name="meetup_rate" value="5">
			        	<label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5" title="5 out of 5 stars"></label>
			        <input class="star-rating__input" id="star-rating-4" type="radio" name="meetup_rate" value="4">
			        	<label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4" title="4 out of 5 stars"></label>
			        <input class="star-rating__input" id="star-rating-3" type="radio" name="meetup_rate" value="3">
			        	<label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3" title="3 out of 5 stars"></label>
			        <input class="star-rating__input" id="star-rating-2" type="radio" name="meetup_rate" value="2">
			        	<label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2" title="2 out of 5 stars"></label>
			        <input class="star-rating__input" id="star-rating-1" type="radio" name="meetup_rate" value="1">
			        	<label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1" title="1 out of 5 stars"></label>
			      </div>
			    </div>
			</td>
		</tr>	
		<tr>
			<th>評價內容</th>
			<td><input type="text" name="meetup_comment" size="45" value="<%=(meetupMemVO==null)? "很棒":meetupMemVO.getMeetup_comment()%>"/></td>
		</tr>	
	</table>
	<br>
	<input type="hidden" name="action" value="update">
	<button type="submit" >送出新增</button>
	</FORM>
	</div>
</div>
<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>

</body>
</html>