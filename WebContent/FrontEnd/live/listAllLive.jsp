<%@page import="java.util.*"%>
<%@page import="com.meetU.live.model.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	LiveService liveSvc = new LiveService();
	List<LiveVO> list = liveSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<title>所有直播主資料-listAllLive.jsp</title>



<style>
.pic {
	width: 172.5px;
	height: 230px;
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
<body bgcolor='green'>
	<table id='table-1'>
		<tr>
			<td>
				<h3>所有直播資料-listAllLive.jsp</h3>
				<h4>
					<a href='selectPage.jsp'><img src="images/back1.gif"
						width="100" height="32">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>



	<table>
		<tr>
			<th>直播主ID</th>
			<th>直播間名稱</th>
			<th>累積瀏覽數</th>
			<th>直播間封面</th>
			<th>直播間創立時間</th>
			<th>直播間狀態</th>
			<th>查看房間</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		<%
		if(request.getAttribute("lastPage") != null &&(boolean)request.getAttribute("lastPage")){
			pageIndex = pageIndexArray[pageNumber-1];
		}
	%>

		<c:forEach var="liveVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr  ${(liveVO.host_ID==param.host_ID) ? 'bgcolor=	#66FFFF':''}>
				<td>${liveVO.host_ID}</td>
				<td>${liveVO.live_name}</td>
				<td>${liveVO.live_acc}</td>
				<td><c:if test="${liveVO.live_pic != null}">
						<img class='pic'
							src='/CA106G5/ShowPic?HOST_ID=${liveVO.host_ID}'>
					</c:if></td>


				<td><fmt:formatDate value="${liveVO.live_date}"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${liveVO.live_status}</td>

				<td>
					<form method='post'
						action='<%=request.getContextPath()%>/FrontEnd/fileRec/listOneFileRec.jsp'
						style="margin-bottom: 0px;">
						<input type='submit' value='進入直播主房間'> 
						<input type='hidden' name='host_ID' value='${liveVO.host_ID}'>
						<input type='hidden' name='action' value='go_to_fileRec_back'>
							
					</form>
				</td>

				<td>
					<form method='post' action='live.do' style="margin-bottom: 0px;">
						<input type='submit' value='修改'>
						<input type='hidden' name='host_ID' value='${liveVO.host_ID}'>
					    <input type='hidden' name='action' value='getOne_For_Update'>
					    <input type='hidden' name='whichPage' value='${param.whichPage}'>				
					    <input type='hidden' name='requestURL' value='<%=request.getServletPath()%>'>			
					</form>
				</td>
				<td>
					<form method='post' action='live.do' style="margin-bottom: 0px;">
						<input type='submit' value='刪除'> <input type='hidden'
							name='host_ID' value='${liveVO.host_ID}'> <input
							type='hidden' name='action' value='delete'>
					</form>
				</td>
			</tr>

		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>