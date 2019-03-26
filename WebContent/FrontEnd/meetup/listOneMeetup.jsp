<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.meetU.meetup.model.*, com.meetU.meetup_like.model.*"%>
<%@ page import="java.util.*" %>
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

<%
	MeetupMemService mtRateSvc = new MeetupMemService();
	Set <MeetupMemVO> hset = mtRateSvc.getHostRate(meetupVO.getMem_ID());
	pageContext.setAttribute("hset", hset);
%>

<% request.setAttribute("currentTime", new Date()); %> 

<html>
<head>
<script src="http://maps.google.com/maps/api/js?key=AIzaSyBbAAPKAKdERmjzz1pWIZVULGozcKOY6Y8&sensor=false"></script>
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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous"> 	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/meetup/fontawesome/css/fontawesome.min.css"/>
<title>顯示聯誼詳情</title>

<style>
	 
	 img{
	 	width:400px;
	 	height:400px;
	 }
	 
	  .headIntro{
        height: 400px;
        height:400px;
        margin-bottom: 20px;
        margin-top: 50px;
      }
      
      #rep{
      	display:none;
      	margin:50px 0px 50px 0px; 
      	margin-left:15%;     
      }
      
      *{
      	font-family:微軟正黑體;
      }
      
      .heart{
   		margin: 16px 0px 0px 16px;
   	  }
   	  #btnRep{
   		margin-right:50px;
   		float:right;
   	  }
   
      .map{
      	margin:20px;
      	margin-left:15%;
      }
      
      .checked{
      	color:orange;
      }
      
      .hostP{
      	text-align:center;
      }
      
      .hostDiv{
      	margin:20px;
      }
      .commentP{
      	display: inline;
      	margin-left:20px;
      }
      
      .btnAjax{
      	display: inline;
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
          		<h1><%=meetupVO.getMeetup_name()%></h1>
          		<p>聯誼日期 : <fmt:formatDate value="${meetupVO.meetup_date}" pattern="yyyy-MM-dd HH:mm"/></p>
          		<p>報名截止 : <fmt:formatDate value="${meetupVO.meetup_joindate}" pattern="yyyy-MM-dd"/></p>
          		<p>聯誼地點 : <%=meetupVO.getMeetup_loc()%></p>
          		<p>聯誼主揪 : <%=memSvc.getOneMem(meetupVO.getMem_ID()).getMem_nickname()%></p>
				<p>至少 <%=meetupVO.getMeetup_minppl()%> 人成團</p>
				<p>至多 <font style="color:red" > <%=meetupVO.getMeetup_maxppl()%> </font>人滿團</p>
		
		  	<div class="btnAjax">        			
	          	<c:choose>
					<c:when test="${meetupVO.meetup_date.compareTo(currentTime)>0 and mtMemSvc.getAll(meetupVO.meetup_ID).size()< meetupVO.meetup_maxppl}">
			          	<input type="button" id="btnJoin" value="${mtMemSvc.getOneMeetupMem(meetupVO.meetup_ID, memVO.mem_ID)!=null?'已報名':'報名'}" 
			          		class="btn btn-warning btnJoin" ${mtMemSvc.getOneMeetupMem(meetupVO.meetup_ID, memVO.mem_ID)!=null?'disabled': ''} >
			          	<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
						<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}"> 
			          		 <i class="fa fa-user"></i> <%=mtMemSvc.getAll(meetupVO.getMeetup_ID()).size()%> 位
		          	</c:when>
		          	<c:otherwise>
		          		<input type="button" class="btn btn-dark btnJoin" disabled id="btnJoin" value="${mtMemSvc.getOneMeetupMem(meetupVO.meetup_ID, memVO.mem_ID)!=null?'已報名':'報名'}" >
			          	<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
						<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}"> 
			          		 <i class="fa fa-user"></i> <%=mtMemSvc.getAll(meetupVO.getMeetup_ID()).size()%> 位
		          	</c:otherwise>	 
	          	</c:choose>    
				
				<input type='image' src="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID)!= null?'img/heart_red.png':'img/heart_white.png'}" 
				   class='heart' title="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? '取消收藏' : '加入收藏' }" 
				   alt="${mLikeSvc.getOneMeetupLike(meetupVO.meetup_ID, memVO.mem_ID) != null ? 'favorite' : 'unfavorite' }" >
				<input type="hidden" name="meetup_ID" value="${meetupVO.meetup_ID}">
				<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
	 				<%=mLikeSvc.LikeByWho(meetupVO.getMeetup_ID()).size()%> 位			
				<button class="btn btn-info" type="submit" id="btnRep"><i class="fa fa-bullhorn"></i> 檢舉</button>
			</div>
        
        </div>
       </div>
      </div><!-- 來自ROW-->	
      
      <div class="row">
      	<div class="col-1"></div>
      	<div class="col-11"><p><%=meetupVO.getMeetup_info()%></p></div>
      	
	  </div>

      <div class="row">
      	<div class="map">
      		<iframe width="800" height="600" frameborder="0" style="border:0" 
      			src="https://www.google.com/maps/embed/v1/place?key=AIzaSyDCEmXxcPeO_-ka9O1sp8EItwFMq5mFTYM&q=<%=meetupVO.getMeetup_loc()%>" allowfullscreen="">
			</iframe>
      	</div>
      </div>
      
      <hr>
      
      <div class="row">
      	<div class="col-2"></div>
      	<div class="col-8">
	      	<h5 class="hostP">主揪的過去評價</h5>
		      <c:forEach var="hostRate" items="${hset}">
			      <div class="hostDiv">
			      		<c:if test="${hostRate.meetup_rate==5}">
			      			<%for(int i=1;i<=5;i++){%>
			      				<span class="fa fa-star checked"></span>
			      				<% }%>
								<p class="commentP">${hostRate.meetup_comment}</p>
			      		</c:if>
			      		
			      		<c:if test="${hostRate.meetup_rate==4}">
			      			<%for(int i=1;i<=4;i++){%>
			      				<span class="fa fa-star checked"></span>
			      				<% }%>
			      			<span class="fa fa-star"></span>
							<p class="commentP">${hostRate.meetup_comment}</p>
			      		</c:if>
			      		
			      		<c:if test="${hostRate.meetup_rate==3}">
			      			<%for(int i=1;i<=3;i++){%>
			      				<span class="fa fa-star checked"></span>
			      				<% }%>
							<%for(int i=1;i<=2;i++){%>
			      				<span class="fa fa-star"></span>
			      				<% }%>	      				
							<p class="commentP">${hostRate.meetup_comment}</p>
			      		</c:if>
			      		
			      		<c:if test="${hostRate.meetup_rate==2}">
			      			<%for(int i=1;i<=2;i++){%>
			      				<span class="fa fa-star checked"></span>
			      				<% }%>
							<%for(int i=1;i<=3;i++){%>
			      				<span class="fa fa-star"></span>
			      				<% }%>	      				
							<p class="commentP">${hostRate.meetup_comment}</p>
			      		</c:if>
			      		
			      		<c:if test="${hostRate.meetup_rate==1}">
			      			<span class="fa fa-star checked"></span>
			      			<%for(int i=1;i<=4;i++){%>
			      				<span class="fa fa-star checked"></span>
			      				<% }%>
							<p class="commentP">${hostRate.meetup_comment}</p>
			      		</c:if>
		     		 </div>
	      	   </c:forEach>
		  </div>
		  <div class="col-2"></div>
	  </div><%-- 來自ROW --%>
	  
      <div class="row">
      	<div class="col-12">
      		<div id="rep">
				<%-- 檢舉內容 --%>
					<textarea rows="5" cols="95%" name="rep_content" placeholder="請輸入檢舉原因" id="repText"></textarea>		
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
				 $("#btnJoin").prop("disabled",true);
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
	         error: function(){alert("請確認輸入文字後送出")}
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