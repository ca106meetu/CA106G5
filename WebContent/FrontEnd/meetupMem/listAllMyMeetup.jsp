<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_mem.model.*, com.meetU.meetup.model.* "%>
<%@ page import="com.meetU.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<% 
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	MeetupMemService meetupMemSvc = new MeetupMemService();
	List<MeetupMemVO> listAll = meetupMemSvc.getMyAllMeetup(memVO.getMem_ID());
	pageContext.setAttribute("listAll", listAll);
	
	MeetupService meetupSvc = new MeetupService();
	pageContext.setAttribute("meetupSvc", meetupSvc);
%>
<% request.setAttribute("currentTime", new Date()); %>


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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous"> 	
<link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/meetup/fontawesome/css/fontawesome.min.css"/>
		
<title>我參加的聯誼</title>
<style>
	
	.pic{
		width:150px;
		height:auto;
	}
    
     .itemImg{ 
 		width: 150px; 
 		height: 150px;
 		align:center; 
 	} 
    
    *{
    	font-family:微軟正黑體;	
    }
    
    .title{
    	font-weight: bold;
    	text-align:center; 
    }
    .cart-item {
		position: relative;
		margin-bottom: 30px;
		padding: 0 50px 0 50px;
		background-color: #fff;
		box-shadow: 0 12px 20px 1px rgba(64, 64, 64, .09);
	}
	
 	.itemImg, .itemTitle, .itemEdit{ 
     	height: 150px; 
     	line-height:150px; 
     	text-align:center; 
     } 
    
</style>
</head>

<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<%@ include file="page1.file" %>
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
		<div class="col-2 cart-item title">Cover</div>
		<div class="col-3 cart-item title">Name</div>
		<div class="col-2 cart-item title">Status</div>
		<div class="col-2 cart-item title">Rating</div>
		<div class="col-2 cart-item title">Delete</div>
		<div class="col-1 cart-item"></div>
	</div>
	<c:forEach var="meetupMemVO" items="${listAll}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
	<div class="row">
		<div class="col-2 cart-item">
		    <div class="itemImg">
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
                    <input type=hidden name=meetup_ID value="${meetupMemVO.meetup_ID}">
                    <input type=hidden name=action value="getOne_For_Display">
                    <input class='pic' type='image' src='/CA106G5/ShowPic?MEETUP_ID=${meetupMemVO.meetup_ID}' alt='submit'>                    
                 </FORM>
            </div>
         </div>  
         <div class="col-3 cart-item"> 
            <div class="itemTitle">
            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
                    <button type="submit" class="btn btn-light">${meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).meetup_name}</button>
                    <input type="hidden" name="meetup_ID"  value="${meetupMemVO.meetup_ID}">
                    <input type="hidden" name="action"  value="getOne_For_Display">
            	</FORM>
      		</div>
          </div>
          <div class="col-2 cart-item">		
      		<div class="itemEdit">
	      			<c:choose>
						<c:when test="${meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).meetup_date.compareTo(currentTime)<0}">
							<B><font style="color:#FF1493">活動已結束</font></B>
						</c:when>					
	  					<c:otherwise>
	  						<B><font style="color:#6495ED">未來的活動</font></B>
	  					</c:otherwise>	
					</c:choose>  								      				
			</div>
		   </div>
		   <div class="col-2 cart-item">			
			<div class="itemEdit">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupMem/meetupMem.do" >
				<c:if test="${meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).meetup_date.compareTo(currentTime)<0 and meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).mem_ID!=memVO.mem_ID}">
					<input type="hidden" name="meetup_ID"  value="${meetupMemVO.meetup_ID}">
					<input type="hidden" name="mem_ID"  value="${memVO.mem_ID}">
					<input type="hidden" name="action"	value="getOne_For_Update">
					<input type="submit" class="btn btn-danger" value="前往評價" ${meetupMemVO.meetup_rate=='0'?'':'disabled'}>
				</c:if>
				</form>	
			
				<c:if test="${meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).meetup_date.compareTo(currentTime)>0}">
	  					<input type="submit" class="btn btn-warning" disabled value="尚未開放評價">
				</c:if>
			</div>
		</div>
		<div class="col-2 cart-item">		    
			<div class="itemEdit">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupMem/meetupMem.do" >
			  	<c:if test="${meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).mem_ID!=memVO.mem_ID}">
			     <button type="submit" class="btn" class="btn btn-warning" ${meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).meetup_date.compareTo(currentTime)>0?'':'disabled'}>
			     	<i class="fa fa-trash"></i> 退出</button>
			    </c:if>
			     <input type="hidden" name="meetup_ID"  value="${meetupMemVO.meetup_ID}">
			     <input type="hidden" name="mem_ID"  value="${memVO.mem_ID}">
			     <input type="hidden" name="action" value="delete">
			  </FORM>
			</div>
	    </div>
	    <div class="col-1 cart-item"></div>
	   </div>
	</c:forEach>
</div>
   

<%@ include file="page2.file" %>

    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>