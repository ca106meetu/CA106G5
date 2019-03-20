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
		width:20px;
		height:20px;
		border-radius:50%;
		opacity:0.8;
		margin: 0px auto;
		background-color:red;
	}
	
	.greenBall{
		width:20px;
		height:20px;
		border-radius:50%;
		opacity:0.8;
		margin: 0px auto;
		background-color:green;
	}
	
	#repAns{
		width:70%;
		height:auto;
		margin:20px;
	}
	
</style>
</head>

<body>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<div class="container">
	<div class="row">

<jsp:useBean id="meetupSvc" scope="page" class="com.meetU.meetup.model.MeetupService"/>

<%@ include file="page1.file"%>  

<form method="post" action="meetupRep.do">
<table class="table1">
				<tr>
					<th>檢舉狀態</th>
					<th>檢舉編號</th>
					<th>聯誼名稱</th>
					<th>檢舉者</th>
					<th>檢舉時間</th>
					<th>檢舉內容</th>	
					<th>聯誼處理</th>		
				</tr>

<c:forEach var="meetupRepVO" items="${list}" varStatus="varstatus" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td><div id="${meetupRepVO.meetup_rep_ID}" Class="${meetupRepVO.rep_status ==0?'redBall':'greenBall' }"></div></td>
					<td id="meetupRepId">${meetupRepVO.meetup_rep_ID}</td>
					<td><A href="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do?meetup_ID=${meetupRepVO.meetup_ID}&action=getOne_For_Display">
					${meetupSvc.getOneMeetup(meetupRepVO.meetup_ID).meetup_name}</a></td>
					<td>${meetupRepVO.mem_ID}</td>
					<td>${meetupRepVO.rep_date}</td>
					<td>${meetupRepVO.rep_content}</td>	
					<td>
						<select class="repSelect" name="rep_Ans">
							<option>--聯誼內容審核--</option>
							<option value="1">內容無不妥</option>
							<option value="0">聯誼被隱形</option>
						</select>
						<input type="submit" value="確定送出" class="btn btn-info btn-sm btnSaveAns" id="${meetupRepVO.meetup_rep_ID}">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="rep_status" value="1">
						<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}">
					</td>	
				</tr>
</c:forEach>
</table>
</form>

<%@ include file="page2.file" %>
	</div>
</div>
		

<jsp:include page="/Templates/bootstrap4/backFooter.jsp" />
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>