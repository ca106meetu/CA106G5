<%@page import="java.util.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String host_ID = (String) request.getAttribute("host_ID");
	FileRecService fileRecSvc = new FileRecService();
	List<FileRecVO> list = fileRecSvc.getOneFileRec(host_ID);
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<title>直播主影片-listOneFileRec.jsp</title>



<style>
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
<body bgcolor='gray'>
	<table id='table-1'>
		<tr>
			<td>
				<h3>直播主影片-listOneFileRec.jsp</h3>
				<h4>
					<a href='<%=request.getContextPath()%>/FrontEnd/live/listAllLive.jsp'>
					<img src="<%=request.getContextPath()%>/FrontEnd/live/images/back1.gif" width="100" height="32">回上頁
					</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>直播主姓名</th>
			<th>影片名稱</th>
			<th>影片描述</th>
			<th>影片內容</th>
			<th>影片上架時間</th>
			<th>影片觀看人數</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<%@ include file="page1.file"%>
		
        <jsp:useBean id="memSvc" scope="page" class="com.meetU.mem.model.MemService" />
        
		<c:forEach var="fileRecVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
			    <td>${memSvc.getOneMem(fileRecVO.host_ID).mem_name}</td>
				<td>${fileRecVO.file_name}</td>
				<td>${fileRecVO.live_des}</td>
				<td><iframe src="${fileRecVO.file_cont}" allowfullscreen></iframe></td>
				<td><fmt:formatDate value="${fileRecVO.file_date}" pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${fileRecVO.file_pop}</td>
				
				<td>
					<form method='post' action='' style="margin-bottom: 0px;">
						<input type='submit' value='修改'> <input type='hidden'
							name='host_ID' value='${liveVO.host_ID}'> <input
							type='hidden' name='action' value='getOne_For_Update'>
					</form>
				</td>
				<td>
					<form method='post' action='' style="margin-bottom: 0px;">
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