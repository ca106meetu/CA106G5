<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="com.meetU.live.model.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	LiveService liveSvc = new LiveService();
	List<LiveVO> list = liveSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!doctype html>
<html lang="en">
<head>
<style>
div {
	font-family: DFKai-sb;
}

.card-img-top {
	width: auto;
	height: auto;
	border-top-left-radius: calc(.25rem - 1px);
	border-top-right-radius: calc(.25rem - 1px);
}

table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 2px;
	text-align: center;
}

.shopping-cart {
	width: 40px;
	height: 40px;
	float: right;
}
#live_like {
	position: fixed;
	right: 0;
	top: 20%;
	width: 8em;
	margin-top: -2.5em;
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;   
}
#gointo{
    border: none;
    padding: 5px 5px;
    border-radius: 5px;
    width: 300px;
    background: orange;
    box-shadow: inset 0 0 10px #000000;
    font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei; 
    font-weight: bold;
	
}
#gointo:hover{
    border: none;
    padding: 5px 5px;
    border-radius: 5px;
    width: 300px;
    background: red;
    box-shadow: inset 0 0 10px #000000;
    font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei; 
    font-weight: bold;
	
}
.btn-primary {
    color: #fff;
    background-color:#0078ae;
    border-color:#0078ae;
} 
small{
font: 100px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei; 
}

</style>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>直播間列表</title>
</head>
<body>
	<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
	
	
	
	<div id='live_like'>
		<form action="<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do" method='post'>
			<input class="btn btn-primary "  type="submit" value="看我的收藏">
		    <input type="hidden" name="mem_ID"	value="M000005">
		    <input type='hidden' name='action' value='getOne_For_Display'>
		</form>
	</div>
	
	
	
	

  <div class="jumbotron" style="margin-bottom: 0rem";>
  
	<div class='container'>

		<%
			for (int j = 0; j <= (list.size()) / 3; j++) {
		%>

		<div class="card-deck">
			<%
				for (int i = 0; i <= 2; i++) {
						if (3 * j + i <= list.size() - 1) {
							LiveVO liveVO = list.get(3 * j + i);
							pageContext.setAttribute("liveVO", liveVO);
			%>
					
			<div class="card border-danger mb-3">
			<c:if test="${liveVO.live_pic != null}">
				<img src="/CA106G5/ShowPic?HOST_ID=<%=liveVO.getHost_ID()%>"
					id='pic' class="card-img-top"></c:if>
				<div class="card-body ">
					<h5 class="card-title"><%=liveVO.getLive_name()%></h5>
					直播創立時間<br>
					<p class="card-text text-warning">
						<fmt:formatDate value="${liveVO.live_date}"
							pattern="yyyy-MM-dd HH:mm" />
					</p>
				</div>
				<div class="card-footer">
					<small class="text-muted">累積瀏覽數: <%=liveVO.getLive_acc()%>
						人
					</small>
					<form method='post'
						action='<%=request.getContextPath()%>/FrontEnd/fileRec/fileRec.do'
						style="margin-bottom: 0px;">
						<input type='submit' value='進入直播主房間' id='gointo'> 
						<input type='hidden' name='host_ID' value='<%=liveVO.getHost_ID()%>'> 
						<input type='hidden' name='action' value='go_to_fileRec_front'>
					</form>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="card"></div>


			<%
				}
					}
					;
			%>
		</div>
		<br>
		<%
			}
			;
		%>
	</div>

</div>

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