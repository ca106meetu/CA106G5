<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup.model.*"%>
<%@ page import="com.meetU.meetup_like.model.*"%>
<% 
	MeetupService meetupSvc = new MeetupService();
	List<MeetupVO> list = meetupSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html>
  <head>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>Meetup HomePage</title>    
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
 
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<%@ include file="page1.file" %>
	
	<div class="container">
		<div class="row">
		<c:forEach var="meetupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<div class="col">	
				<div class="itemImg">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
						<input type=hidden name=meetup_ID value="${meetupVO.meetup_ID}">
						<input type=hidden name=action value="getOne_For_Display">
						<input class='pic' type='image' src='/CA106G52/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}' alt='submit'>					
					</FORM>
				</div>
				<div>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" class="itemTitle">
						<button type="submit" class="btn btn-light">${meetupVO.meetup_name}</button>
						<input type="hidden" name="meetup_ID"  value="${meetupVO.meetup_ID}">
			     		<input type="hidden" name="action"	value="getOne_For_Display">
					</FORM>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do">
						<input type='image' src="<%=request.getContextPath()%>/FrontEnd/meetup/img/heart_white.png" class='heart' title='加入收藏' alt="unfavorite" >
						<input type="hidden" name="action"	value="insert">
						<input type="hidden" name="meetup_ID"	value="${meetupVO.meetup_ID}">
						<input type="hidden" name="mem_ID"	value="M000006">
					</FORM>
					
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do">
						<input type='image' src="<%=request.getContextPath()%>/FrontEnd/meetup/img/heart_red.png" class='heart2' title='取消收藏' alt="favorite" style="display:none;">
						<input type="hidden" name="action"	value="delete">
						<input type="hidden" name="meetup_ID"	value="${meetupVO.meetup_ID}">
						<input type="hidden" name="mem_ID"	value="M000006">
					</FORM>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>

<%@ include file="page2.file" %>

<script>




$(function(){
	
	$('.heart').click(function(){
		$('.heart').style("display", "none")
		$('.heart2').style("display", "")
		})

	$('.heart2').click(function(){
		$('this').style("display", "none")
		$('this').next().style("display", "")
		})
})



function init(){
	var heart = document.getElementsByClassName("heart");
	
	for(var i = 0; i<heart.length; i++){
		
		heart[i].onclick=function(e){
				
				e.target.;
				
			}		
		}
	}
}
window.onload=init;
</script>

    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
    </body>
</html>