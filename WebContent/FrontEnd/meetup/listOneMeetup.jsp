<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup.model.*, com.meetU.meetup_like.model.*"%>
<%@ page import="java.util.* " %>
<%@ page import="com.meetU.meetup_mem.model.*, com.meetU.meetup_report.*" %>
<%@ page import="com.meetU.mem.model.*"%>

<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	MeetupVO meetupVO = (MeetupVO)session.getAttribute("meetupVO");	
%>

<%
	MemService memSvc = new MemService();
%>

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
<title>顯示聯誼詳情</title>

<style>
	 
	 img{
	 	width:300px;
	 	height:auto;
	 }
	 
	  .headIntro{
        height: 400px;
        margin-bottom: 20px;
        
      }
      
      #rep{
      	display:none;      
      }
      
      *{
      	font-family:微軟正黑體;
      }
      
      .heart{
   		margin: 16px 16px 0px 16px;
   	  }
   	  #btnRep{
   		margin-bottom:6px;
   	  }
   
      .HeartnRep{
   	  	margin-top:5px;
      }
      
</style>

</head>
<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<jsp:useBean id="mtMemSvc" scope="page" class="com.meetU.meetup_mem.model.MeetupMemService"/>
<jsp:useBean id="mLikeSvc" scope="page" class="com.meetU.meetup_like.model.MeetupLikeService"/>
	
	<div class="container">
      <div class="row">
       <div class="col-6">
          <div class="headIntro introPic">
          	<img class='pic' src='/CA106G5/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}'>
          </div>
        </div> 
        
        <div class="col-6">
          <div class="headIntro">
          	<ul>
          		<li><%=meetupVO.getMeetup_name()%></li>
          		<li>聯誼日期 : <%=meetupVO.getMeetup_date()%></li>
          		<li>聯誼地點 : <%=meetupVO.getMeetup_loc()%></li>
          		<li>聯誼主揪 : <%=memSvc.getOneMem(meetupVO.getMem_ID()).getMem_nickname()%></li>
          		<li>共 <%=mtMemSvc.getAll(meetupVO.getMeetup_ID()).size()%> 位參與</li>
          		<li>共 <%=mLikeSvc.LikeByWho(meetupVO.getMeetup_ID()).size()%> 位收藏</li>
          	</ul>
          	
          <div class="join"> 
          	<input type="button" class="${mtMemSvc.getOneMeetupMem(meetupVO.meetup_ID, memVO.mem_ID)!=null?'btn btn-warning disabled': 'btn btn-warning btnJoin'}" 
          		id="btnJoin" value="${mtMemSvc.getOneMeetupMem(meetupVO.meetup_ID, memVO.mem_ID)!=null?'已報名':'報名'}"  >
          	<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
			<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">     
		  </div>
			
					
		  <div class="HeartnRep"> 
			<input type='image' src=" ${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID)!= null?'img/heart_red.png':'img/heart_white.png'}" 
			   class='heart' title="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? '取消收藏' : '加入收藏' }" 
			   alt="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? 'favorite' : 'unfavorite' }" >
			<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
			<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
			
       		<button type="submit" class="btn btn-info btn-sm" id="btnRep"> 
       			<span class="glyphicon glyphicon-alert">檢舉</span>
       		</button>
		  </div>		
        </div>
       </div>
      </div><!-- 來自ROW-->	
      
      <div class="item"><!-- 假文假圖-->
      	<p><%=meetupVO.getMeetup_info()%></p>
      </div>
      
      <div class="item"><!-- 假文假圖-->	
			<img src="https://api.fnkr.net/testimg/650x800/00CED1/FFF/?text=img+placeholder">
			<h3>title</h3>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem, cupiditate.</p>
	  </div>
      
      <div class="item"><!-- 假文假圖-->	
			<img src="https://api.fnkr.net/testimg/650x800/00CED1/FFF/?text=img+placeholder">
			<h3>title</h3>
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem, cupiditate.</p>
	  </div>
      
      <div class="row">
      	<div class="col-12">
      		<div id="rep">
				<%-- 檢舉內容 --%>
					<textarea rows="5" cols="100%" name="rep_content" placeholder="請輸入檢舉原因" id="repText"></textarea>		
					<br><input type="reset" class="btn btn-info btn-sm" value="取消">
					<input type="submit" class="btn btn-info btn-sm" value="送出檢舉" id="btnRepSubmit">
					<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
					<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
					<input type="hidden" name="rep_status" value="0">
			</div>
      	</div>
      </div>
     </div><!-- 來自CONTAINER-->   
   
<script>
		
$(document).ready(function(){
	
	$(".heart").click(function(){
		 
		if(!allowUser()){ 
			 <%session.setAttribute("location", request.getRequestURI());%>
			 $('#login').modal('show');
			 return;
		}
		
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
					 	"action":"deleteAjax", 
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
	
	$(".btnJoin").click(function(){
		
		if(!allowUser()){ 
			 <%session.setAttribute("location", request.getRequestURI());%>
			 $('#login').modal('show');
			 return;
		}
		
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/FrontEnd/meetupMem/meetupMem.do",
			 data: {"meetup_ID":$(this).next().attr('value'), 
				 	"action":"insert", 
				 	"mem_ID":$(this).next().next().attr('value')},
			 dataType: "json",
			 success: function(){
				 alert("成功報名");
				 $(".btnJoin").val("已報名")
				 $(".btnJoin").addClass("disabled");
				},
	         error: function(){alert("AJAX-grade發生錯誤囉!")}
	    });	
	});
	
	
	$("#btnRepSubmit").click(function(){
		
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/FrontEnd/meetupRep/meetupRep.do",
			 data: {"meetup_ID":$(this).next().attr('value'), 
				 	"mem_ID":$(this).next().next().attr('value'),
				 	"action":"insert", 
				 	"rep_content":$("#repText").val(),
				 	"rep_status":$(this).next().next().next().attr('value')},
			 dataType: "json",
			 success: function(){
				 alert("謝謝您的回饋，管理員會盡快處理");
				},
	         error: function(){alert("AJAX-grade發生錯誤囉!")}
	    });	
	});
	
	
	$("#btnRep").click(function(){
		if(!allowUser()){ 
			 <%session.setAttribute("location", request.getRequestURI());%>
			 $('#login').modal('show');
			 return;
		}
		$("#rep").show();
		$("#repText").focus();
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