<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup.model.*, com.meetU.meetup_like.model.*"%>
<%@ page import="java.util.* " %>
<%@ page import="com.meetU.meetup_mem.model.*, com.meetU.meetup_report.*" %>
<%@page import="com.meetU.mem.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	MeetupVO meetupVO = (MeetupVO)session.getAttribute("meetupVO");
	String meetup_ID = meetupVO.getMeetup_ID();
	pageContext.setAttribute("meetup_ID", meetup_ID);
	out.print(meetup_ID);
%>

<html>
<head>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    
<title>顯示聯誼詳情</title>

<style>
	 FORM{
	 	float:left;
	 	margin:0px;
	 	dispay:inline; 
	 }
	 img{
	 	width:300px;
	 	height:auto;
	 }
	 
	 .headIntro{
        height: 400px;
        margin-bottom: 20px;
      }
      
      .introPic{
      	
      }
      
      #rep{
      	display:none;      
      }
      
      *{
      	font-family:微軟正黑體;
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
	
	<div class="container">
      <div class="row">
       <div class="col">
          <div class="headIntro introPic">
          	<img class='pic' src='/CA106G5/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}'>
          </div>
        </div> 
        
        <div class="col">
          <div class="headIntro">
          	<ul>
          		<li><%=meetupVO.getMeetup_name()%></li>
          		<li><%=meetupVO.getMeetup_date()%></li>
          		<li><%=meetupVO.getMeetup_loc()%></li>
          	</ul>
          	
          	<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupMem/meetupMem.do" >
          		<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
				<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
          		<input type="hidden" name="action" value="insert">
				<button type="submit" class="btn btn-light">報名</button>
			</FORM>
		
<jsp:useBean id="mLikeSvc" scope="page" class="com.meetU.meetup_like.model.MeetupLikeService"/>		
			
			
			<input type='image' src=" ${mLikeSvc.getOneMeetupLike(meetup_ID, memVO.mem_ID)!= null?'img/heart_red.png':'img/heart_white.png'}" 
				   class='heart' title="${mLikeSvc.getOneMeetupLike(meetup_ID, memVO.mem_ID) != null ? '取消收藏' : '加入收藏' }" 
				   alt="${mLikeSvc.getOneMeetupLike(meetup_ID, memVO.mem_ID) != null ? 'favorite' : 'unfavorite' }" >
			<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
			<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
			
          	<button type="submit" class="btn btn-light" onclick="report()"> 檢舉</button>
          	
          </div>
        </div>	
      </div><!-- 來自ROW-->
      
      <div class="row">
      	<div class="col">
      		<div id="rep">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupRep/meetupRep.do">
					<textarea rows="5" cols="80%" name="rep_content" placeholder="請輸入檢舉原因"></textarea>
					<input type="hidden" name="rep_status" value="1">
					<input type="hidden" name="action" value="insert">
					<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
					<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
					<br><input type="reset" value="取消">
					<input type="submit" value="送出檢舉">
				</FORM>
				
			</div>
      	</div>
      </div>
      
    </div><!-- 來自CONTAINER--> 
<script>

function report(){
	$("#rep").show();
}
		
$(document).ready(function(){
	$(".heart").click(function(){
		 
		 if($(this).attr("alt") == "unfavorite"){
			 				 
			 $.ajax({
				 type: "POST",
				 url: "<%=request.getContextPath()%>/FrontEnd/meetupLike/meetupLike.do",
				 data: {"meetup_ID":$(this).next().attr('value'), 
					 	"action":"insert", 
					 	"mem_ID":$(this).next().next().attr('value')},
				 dataType: "json",
				 success: function(){
					 
					 $(".heart").attr({"src":"img/heart_red.png",
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
					 	"action":"delete", 
					 	"mem_ID":$(this).next().next().attr('value')},
				 dataType: "json",
				 success: function(){
					 
					 $(".heart").attr({"src":"img/heart_white.png",
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