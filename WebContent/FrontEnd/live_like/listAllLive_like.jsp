<%@page import="com.meetU.mem.model.MemVO"%>
<%@page import="java.util.*"%>
<%@page import="com.meetU.live_like.model.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String mem_ID =(String) request.getAttribute("mem_ID");
	Live_likeService live_likeSvc =new Live_likeService();
	List<Live_likeVO> list = live_likeSvc.getOneLive_like(mem_ID);
	pageContext.setAttribute("list", list);
	MemVO memVO = (MemVO)session.getAttribute("memVO");
%>
<!doctype html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>收藏直播間-listAllLive_like.jsp</title>
<style>
.btn-primary {
    color: #fff;
    background-color: 	#FFBB00;
    border-color: 	#FFBB00;
}
#live_like {
	position: fixed;
	right: 0;
	top: 50%;
	width: 8em;
	margin-top: -2.5em;
}
#live_rep {
	position: fixed;
	right: 0;
	top: 57%;
	width: 8em;
	margin-top: -2.5em;
}

.pic {
	width: 172.5px;
	height: 230px;
}

table {
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #FFBB00;
}

th {
	padding: 2px;
	text-align: center;
}

td {
	width: 150PX;
	height: 200PX;
	padding: 2px;
	text-align: center;
}
</style>
</head>
<body>
	<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

	<table id='table-1'>
		<tr>
			<td>
				<h3>收藏直播間-listAllLive_like.jsp</h3>
				<h4>
					<a href='<%=request.getContextPath()%>/FrontEnd/live/liveHome.jsp'>
						<img
						src="<%=request.getContextPath()%>/FrontEnd/live/images/back1.gif"
						width="100" height="32">
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>直播主姓名</th>
			<th>直播間照片</th>
			<th>進入直播間</th>
			<th>取消收藏</th>
		</tr>
	

		<jsp:useBean id="memSvc"  scope="page" class="com.meetU.mem.model.MemService" />
		<jsp:useBean id="liveSvc" scope="page" class="com.meetU.live.model.LiveService"/>




		<c:forEach var="live_likeVO" items="${list}">
			<tr>
				<td>${memSvc.getOneMem(live_likeVO.host_ID).mem_name}</td>
				<td><c:if test="${liveSvc.getOneLive(live_likeVO.host_ID).live_pic != null}">
					<img class='pic'src='/CA106G5/ShowPic?HOST_ID=${live_likeVO.host_ID}'>
					</c:if></td>
					<td>
					<form method='post'
						action='<%=request.getContextPath()%>/FrontEnd/fileRec/fileRec.do'
						style="margin-bottom: 0px;">
						<input type='submit' value='進入直播主房間'> 
						<input type='hidden' name='host_ID' value='${live_likeVO.host_ID}'> 
						<input type='hidden' name='action' value='go_to_fileRec_front'>
					</form>
					</td>
		<td>
						<form action="<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do" method='post'>
						<input class="btn btn-danger "  type="submit" value="取消收藏">
		    			<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
		    			<input type="hidden" name="host_ID"	value="${live_likeVO.host_ID}">
		    			<input type='hidden' name='action' value='delete2'>
						</form>
		</td>
			</tr>

		</c:forEach>
	</table>
	
	
	
	

	<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>

