<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
<!-- page label -->    
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<!-- fontAwesome --> 
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous"> 	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/meetup/fontawesome/css/fontawesome.min.css"/>
  	
  	 <!-- Favicon -->
    <link rel="icon" href="modelMaster/img/core-img/favicon.ico">

    <!-- Core Stylesheet -->
    <link href="modelMaster/style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="modelMaster/css/responsive/responsive.css" rel="stylesheet">
		
    <title>聯誼首頁</title>    
    <style>
    *{
    	font-family:微軟正黑體;	
    }
   	
   	.pic{
		width:300px;
		height:auto;
	}
	
   	.itemImg{
		width: 300px;
		height: auto;
		margin: 20px;
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
			<div class="search">
			<form METHOD="POST" action="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
				<input type="radio" name="searchType" value="nam">名稱
				<input type="radio" name="searchType" value="loc">區域
				<input type=hidden name=action value="getSearch">
				<input type="text" class="searchInfo" name="searchInfo" placeholder="Search.." >
				<button type="submit" class="btn btn-info"> 查詢 <i class="fa fa-search"></i></button>
			</form>
	</div></div></div>
	
	<div class="row">
	<c:forEach var="meetupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<%-- c:if test="${meetupVO.meetup_status==1}"//過濾狀態為0的不出現--%>
		<div class="col">	
			<div class="welcome-single-slide">
			<div class="itemImg">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
					<input type=hidden name=meetup_ID value="${meetupVO.meetup_ID}">
					<input type=hidden name=action value="getOne_For_Display">
					<input class='pic' type='image' src='/CA106G5/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}' alt='submit'>					
				</FORM>
			</div>
			
			<div class="project_title" style="font-family:Microsoft JhengHei;">
				<div class="post-date-commnents d-flex">
			     	<a href="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do?meetup_ID=${meetupVO.meetup_ID}&action=getOne_For_Display">
			     		<fmt:formatDate value="${meetupVO.meetup_date}" pattern="yyyy-MM-dd HH:mm"/></a>
			    </div>
                <a href="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do?meetup_ID=${meetupVO.meetup_ID}&action=getOne_For_Display">
                    <h5>${meetupVO.meetup_name}</h5>
                </a> 	
			</div>
<jsp:useBean id="mLikeSvc" scope="page" class="com.meetU.meetup_like.model.MeetupLikeService"/>					
					
<%-- 				<input type='image' class='heart' id="${meetupVO.meetup_ID}"  --%>
<%-- 						src=" ${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID)!= null?'img/heart_red.png':'img/heart_white.png'}"  --%>
<%-- 		 				title="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? '取消收藏' : '加入收藏' }"  --%>
<%-- 		   				alt="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? 'favorite' : 'unfavorite' }" > --%>
<%-- 				<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}"> --%>
<%-- 				<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}"> --%>
					
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