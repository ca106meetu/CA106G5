<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!-- page label -->    
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<!-- fontAwesome --> 
<%-- 	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous"> 	 -->
<-- 	<link rel="stylesheet" href="<%=request.getContextPath()%>/FrontEnd/meetup/fontawesome/css/fontawesome.min.css"/> --%>

<title>聯誼檢舉</title>
<style>
	*{
		font-family:微軟正黑體;
	}
	
	img{
		width:150;
		height:auto;
	}
	
	table{
		border:1px solid #ddd;
		margin:3em;	
	}
	
	th, td {
	    padding: 15px;
	    text-align: center;
	    border:1px solid #ddd;
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
	
	tr:nth-child(even) {
  		background-color: #f2f2f2;
	}
	
</style>
</head>

<body>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="container">
	<div class="row">
		<div class="col-12">
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
					<th>聯誼處理</th>		
				</tr>
			
			<c:forEach var="meetupRepVO" items="${list}" varStatus="varstatus" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td><div id="${meetupRepVO.meetup_rep_ID}" Class="${meetupRepVO.rep_status ==0?'redBall':'greenBall' }"></div></td>
					<td id="meetupRepId">${meetupRepVO.meetup_rep_ID}</td>
					<td><A href="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do?meetup_ID=${meetupRepVO.meetup_ID}&action=getOne_For_Display">
						${meetupSvc.getOneMeetup(meetupRepVO.meetup_ID).meetup_name}</a></td>
					<td>${meetupRepVO.mem_ID}</td>
					<td><fmt:formatDate value="${meetupRepVO.rep_date}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${meetupRepVO.rep_content}</td>	
					<td>
						<FORM METHOD="post" action="meetupRep.do">
							<select class="rep_ans" name="rep_ans">
								<option value="1">內容無不妥</option>
								<option value="0">聯誼被隱形</option>
							</select>
							<button type="submit" class="btn btn-outline-dark" class="btnSaveAns">確認</button>
							<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}"/>
							<input type="hidden" name="meetup_ID" value="${meetupRepVO.meetup_ID}"/>
							<input type="hidden" name="rep_status" value="1">
							<input type="hidden" name="action" value="update">
						</Form>
					</td>
				</tr>
			</c:forEach>
			</table>

<%@ include file="page2.file" %>
		</div>
	</div>
</div>
<script>
		
$(document).ready(function(){
		
	
		 
});		
</script>
<jsp:include page="/Templates/bootstrap4/backFooter.jsp" />
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>