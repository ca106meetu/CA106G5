<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup.model.*, com.meetU.mem.model.*"%>
<%@ page import="com.meetU.meetup_like.model.*"%>
<% 
	MeetupService meetupSvc = new MeetupService();
	List<MeetupVO> list = meetupSvc.getVisibleAll();
	pageContext.setAttribute("list", list);
%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	MeetupVO meetupVO = (MeetupVO)session.getAttribute("meetupVO");
%>

<!doctype html>
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
		
    <title>聯誼首頁</title>    
    <style>
    *{
    	font-family:微軟正黑體;	
    }
    
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
    	text-align:center;
    	float:left;
	 	margin:0px;
	 	dispay:inline;
    }
   
   	.heart{
   		margin-top:10px;
   	}
   
      
   	.search input[type=search] { 
	  padding: 6px;
	  border-radius:10px;
	  margin: 0px 0px 0px 20px;
	  font-size: 17px;
	  width: 60%;
	}
   
  	.search input[type=radio] {
	  margin: 10px;
	}
	
   	.searchDiv{
   		margin:50px;
   	}
   
</style> 
    
</head>
<body>
 
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<%@ include file="page1.file" %>
	
<div class="container">
	<div class="row">
		<div class="col searchDiv">
			<div class="search"> <span class="glyphicon glyphicon-filter"></span>
			<form METHOD="POST" action="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
				<input type="radio" name="searchType" value="nam">名稱
				<input type="radio" name="searchType" value="loc">區域
				<input type=hidden name=action value="getSearch">
				<input type="text" class="searchInfo" name="searchInfo" placeholder="Search.." >
				<input type="submit" class="btn btn-info" value="查詢" >
			</form>
	</div></div></div>
	
	<div class="row">
	<c:forEach var="meetupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<%-- c:if test="${meetupVO.meetup_status==1}"//過濾狀態為0的不出現--%>
		<div class="col">	
			<div class="itemImg">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
					<input type=hidden name=meetup_ID value="${meetupVO.meetup_ID}">
					<input type=hidden name=action value="getOne_For_Display">
					<input class='pic' type='image' src='/CA106G5/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}' alt='submit'>					
				</FORM>
			</div>
			<div>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" class="itemTitle">
					<button type="submit" class="btn btn-light">${meetupVO.meetup_name}</button>
					<input type="hidden" name="meetup_ID"  value="${meetupVO.meetup_ID}">
			     	<input type="hidden" name="action"	value="getOne_For_Display">
				</FORM>

<jsp:useBean id="mLikeSvc" scope="page" class="com.meetU.meetup_like.model.MeetupLikeService"/>					
					
				<input type='image' class='heart' id="${meetupVO.meetup_ID}" 
						src=" ${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID)!= null?'img/heart_red.png':'img/heart_white.png'}" 
		 				title="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? '取消收藏' : '加入收藏' }" 
		   				alt="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? 'favorite' : 'unfavorite' }" >
				<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
				<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
					
			</div>
		</div>
		</c:forEach>
	</div>
</div>

<%@ include file="page2.file" %>

<script>
$(document).ready(function(){
	
	$(".heart").click(function(){

		if(!allowUser()){ 
			 <%session.setAttribute("location", request.getRequestURI());%>
			 $('#login').modal('show');
			 return;
		}
		
		var element = $(this);
		 
		 if($(this).attr("alt") == "unfavorite"){
			 				 
			 $.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do",
				 data: {"meetup_ID":$(this).next().attr('value'), 
					 	"action":"insert", 
					 	"mem_ID":$(this).next().next().attr('value')},
				 dataType: "json",
				 success: function(){
					 
					 var heartId = element.attr('id');
					 $('input[id='+heartId+']').attr({"src":"img/heart_red.png",
						 "title": "取消收藏",
						 "alt": "favorite"
						});
					
					 alert("成功加入收藏");
					},
					
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
		         });
			 
			 
		 }else if($(this).attr("alt") == "favorite"){
			 
			 $.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do",
				 data: {"meetup_ID":$(this).next().attr('value'), 
					 	"action":"deleteAjax", 
					 	"mem_ID":$(this).next().next().attr('value')},
				 dataType: "json",
				 success: function(){
					 
					 var heartId = element.attr('id');
					 
					 $('input[id='+heartId+']').attr({"src":"img/heart_white.png",
						 "title": "加入收藏",
			 			 "alt": "unfavorite"
						});	
					 
					 alert("成功取消收藏");
					},
	             error: function(){alert("AJAX-grade發生錯誤囉!")}
		         });				
		 }
	 });
})

</script>

<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>