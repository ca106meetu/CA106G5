<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup.model.*, com.meetU.meetup_like.model.*"%>
<%@ page import="java.util.* , com.meetU.meetup_mem.model.*" %>
<%@page import="com.meetU.mem.model.*"%>

<%
	MeetupLikeService meetupLikeSvc = new MeetupLikeService();
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	List<MeetupLikeVO> list = meetupLikeSvc.getAll(memVO.getMem_ID());
	pageContext.setAttribute("list", list);
	
	MeetupService meetupSvc = new MeetupService();
	pageContext.setAttribute("meetupSvc", meetupSvc);
%>
<!DOCTYPE html>
<html>
<head>
<meta>
<title>我的收藏聯誼</title>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
<style>
    .pic{
		width:200px;
		height:auto;
	}
    
    .itemImg{
		width: 250px;
		height: 200px;
		margin: 20px 0px 10px 0px;
		padding: 10px 25px 0px 25px;
		flex-grow: 1;
		align:center;
	
	}
    
    .itemTitle{
    	width:200px;
    	font-family:微軟正黑體;	
    	text-align:center;
    	float:left;
	 	margin:0px;
	 	dispay:inline;
	 	
    }
   
   .heart{
   		margin-top:10px;
   }
</style>

</head>
<body>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<%@ include file="page1.file" %>

<div class="container">
		<div class="row">
		<c:forEach var="meetupLikeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<div class="col">	
				<div class="itemImg">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
						<input type=hidden name=meetup_ID value="${meetupLikeVO.meetup_ID}">
						<input type=hidden name=action value="getOne_For_Display">
						<input class='pic' type='image' src='/CA106G5/ShowPic?MEETUP_ID=${meetupLikeVO.meetup_ID}' alt='submit'>					
					</FORM>
				</div>
				<div> 
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" class="itemTitle">
						<button type="submit" class="btn btn-light">${meetupSvc.getOneMeetup(meetupLikeVO.meetup_ID).meetup_name}</button> 
						<input type="hidden" name="meetup_ID"  value="${meetupLikeVO.meetup_ID}">
			     		<input type="hidden" name="action"	value="getOne_For_Display">
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do">
						<input type='image' src="<%=request.getContextPath()%>/FrontEnd/meetup/img/heart_red.png" class='heart' title='取消收藏' alt="favorite">
						<input type="hidden" name="action"	value="delete">
						<input type="hidden" name="meetup_ID"	value="${meetupLikeVO.meetup_ID}">
						<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">   
					</FORM>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>

<%@ include file="page2.file" %>

<script>
$(document).ready(function(){
	
})

</script>


<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>