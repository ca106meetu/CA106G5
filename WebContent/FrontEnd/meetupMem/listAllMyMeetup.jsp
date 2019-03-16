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

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
		
<title>我參加的聯誼</title>
<style>
	
	.pic{
		width:200px;
		height:auto;
	}
    
    .itemImg{
		width: 200px;
		height: 200px;
		<%--margin: 20px 0px 10px 0px;
		<%--padding: 10px 25px 0px 25px;
		flex-grow: 1;
		background-color:black;--%>
		align:center;
	}
    
    .itemTitle{
    	width:200px;
    	text-align:center;
    	margin:0px;
	 	
  		font-weight: bold;
  	}
    
    *{
    	font-family:微軟正黑體;	
    }
    
    .cart-item {
    	 
		  position: relative;
		  margin-bottom: 30px;
		  padding: 0 50px 0 50px;
		  background-color: #fff;
		  box-shadow: 0 12px 20px 1px rgba(64, 64, 64, .09);
	}
	
	.itemImg, .itemTitle, .itemEdit{
    	float:left;
		margin:20px;
    	height: 200px;
    	line-height:200px;
    	text-align:center;
    	
    }
    
    .addMeetup{
    	float:right;    
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

<c:forEach var="meetupMemVO" items="${listAll}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">	
	<div class="row">
		<div class="col cart-item">
		    <div class="itemImg">
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
                    <input type=hidden name=meetup_ID value="${meetupMemVO.meetup_ID}">
                    <input type=hidden name=action value="getOne_For_Display">
                    <input class='pic' type='image' src='/CA106G5/ShowPic?MEETUP_ID=${meetupMemVO.meetup_ID}' alt='submit'>                    
                 </FORM>
            </div>
            <div class="itemTitle">
            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
                    <button type="submit" class="btn btn-light">${meetupSvc.getOneMeetup(meetupMemVO.meetup_ID).meetup_name}</button>
                    <input type="hidden" name="meetup_ID"  value="${meetupMemVO.meetup_ID}">
                    <input type="hidden" name="action"  value="getOne_For_Display">
            	</FORM>
      		</div>
          		
      		<div class="itemEdit">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupMem/meetupMem.do">
				    <input type="submit" value="送出評價" class="btn btn-warning">
				    <input type="hidden" name="meetup_ID"  value="${{meetupMemVO.meetup_ID}}">
				    <input type="hidden" name="mem_ID"  value="${meetupMemVO.mem_ID}">
				    <input type="hidden" name="action"	value="update">
			    </FORM>
			</div>
			
			<div class="itemEdit">
			  <%-- FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupMem/meetupMem.do" > --%>
			     <input type="submit" value="退出" class="btn btn-warning quit">
			     <input type="hidden" name="meetup_ID"  value="${meetupMemVO.meetup_ID}">
			     <input type="hidden" name="mem_ID"  value="${meetupMemVO.mem_ID}">
			     <input type="hidden" name="action" value="delete">
			  <%--</FORM>--%>
			</div>
			
	    </div>
   </div>    
</c:forEach>
</div>

<%@ include file="page2.file" %>

<script>
$(document).ready(function(){
	
	$(".quit").click(function(){
		$.ajax({
		 type: "POST",
		 url: "<%=request.getContextPath()%>/FrontEnd/meetupMem/meetupMem.do",
		 data: {"meetup_ID":$(this).next().attr('value'), 
			 	"action":"delete", 
			 	"mem_ID":$(this).next().next().attr('value')},
		 dataType: "json",
		 success: function(){
			 <%--
			 alert("成功退出");
			 $(".quit").addClass("disabled");
			 --%>
			 window.location.reload();
			},
         error: function(){alert("AJAX-grade發生錯誤囉!")}
    });	
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