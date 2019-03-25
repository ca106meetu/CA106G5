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
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous"> 	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/meetup/fontawesome/css/fontawesome.min.css"/>
<title>顯示聯誼詳情</title>

<style>
	 
	 img{
	 	width:300px;
	 	height:auto;
	 }
	 
	  .headIntro{
        height: 400px;
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
   		margin: 16px 10px 0px 16px;
   	  }
   	  #btnRep{
   		margin-right:50px;
   		float:right;
   	  }
   
      .HeartnRep{
   	  	margin-top:5px;
      }
      
      .map{
      	margin:20px;
      	margin-left:15%;
      }
      
</style>

</head>
<body>

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
       	 </div>
       </div>
   </div><!-- 來自ROW-->	
      
   <div class="item"><!-- 假文假圖-->
  	  <h3><%=meetupVO.getMeetup_info()%></h3>
   </div>

</div><!-- 來自CONTAINER-->   

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>