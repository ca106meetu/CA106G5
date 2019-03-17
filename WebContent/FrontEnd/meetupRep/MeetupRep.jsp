<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_report.model.*, com.meetU.meetup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<% 
	MeetupRepService meetupRepSvc = new MeetupRepService();
	List<MeetupRepVO> list = meetupRepSvc.getAll();
	pageContext.setAttribute("list", list);	
%>

<!DOCTYPE html>
<html>
<head>
<meta>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
<!-- for modal dialog -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<title>Meetup Report</title>
<style>
	*{
		font-family:微軟正黑體;
	}
	
	img{
		width:150;
		height:auto;
	}
	
	table{
		border:1px solid black;
		margin:3em;	
	}
	
	th, td {
	    padding: 15px;
	    text-align: center;
	    border:1px solid black;
	}

	
	.redBall{
		background-color:red;
	}
	
	.greenBall{
		background-color:green;
	}
	
	#statusBall{
		width:20px;
		height:20px;
		border-radius:50%;
		opacity:0.8;
		margin: 0px auto;
	}
	
	#repAns{
		width:70%;
		height:auto;
		margin:20px;
	}
	
</style>
</head>

<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<div class="container">
	<div class="row">

<jsp:useBean id="meetupSvc" scope="page" class="com.meetU.meetup.model.MeetupService"/>

<%@ include file="page1.file"%>  
			<table class="table1">
				<tr>
					<th>檢舉狀態</th>
					<th>檢舉編號</th>
					<th>聯誼名稱</th>
					<th>檢舉者</th>
					<th>檢舉時間</th>
					<th>檢舉內容</th>		
				</tr>

<c:forEach var="meetupRepVO" items="${list}" varStatus="varstatus" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td><div id="statusBall" Class="${meetupRepVO.rep_status ==0?'redBall':'greenBall' }"></div></td>
					<td id="meetupRepId">${meetupRepVO.meetup_rep_ID}</td>
					<td><A href="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do?meetup_ID=${meetupRepVO.meetup_ID}&action=getOne_For_Display">
					${meetupSvc.getOneMeetup(meetupRepVO.meetup_ID).meetup_name}</a></td>
					<td>${meetupRepVO.mem_ID}</td>
					<td>${meetupRepVO.rep_date}</td>
					<td>
						<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupRep/meetupRep.do" >	
							<input type="submit" class="btn btn-info btnSaveAns" data-toggle="modal" data-target="#basicModal" value="查看/回覆檢舉原因">
							<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}">
							<input type="hidden" name="action" value="getOne_For_Update">
								
						</FORM>
					</td>	
				</tr>
</c:forEach>
</table>
<%@ include file="page2.file" %>

	</div>
</div>

<c:if test="${openModal!=null}">	

	
<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
               <h3 class="modal-title" id="myModalLabel">聯誼名稱 : ${meetupRepVO.meetup_ID}-${meetupSvc.getOneMeetup(meetupRepVO.meetup_ID).meetup_name}</h3>
               <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            </div>
			
	  <div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
 <jsp:include page="updateRepContentAns.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
<input type="submit" class="btn btn-info" data-dismiss="modal" value="關閉視窗" id="btnCloseModal" >
      </div>
    </div>
  </div>
</div>
		<script>
		$("#basicModal").modal({show: true});
        </script>
</c:if>
		
<script>

$(document).ready(function(){
	$(".btnSaveAns").click(function(){
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/FrontEnd/meetupRep/meetupRep.do",
			 data: {"meetup_rep_ID":$(this).next().attr('value'), 
				 	"rep_status":$(this).next().next().attr('value'),
				 	"action":"update", 
				 	"rep_ans":$("#repAns").val()},
			 dataType: "json",
			 success: function(){
				 window.location.reload();
				 
				},
	         error: function(){alert("請回覆檢舉 或是 選擇離開視窗")}
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