<%@page import="java.util.*"%>
<%@page import="com.meetU.live.model.*"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	LiveService liveSvc = new LiveService(); 
    List<LiveVO> list = liveSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有直播資料-listAllLive.jsp</title>



<style>
	.pic{
		width:172.5px;
		height:230px;
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
	
</style>
</head>
<body bgcolor = 'gray'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
 <table id = 'table-1'>
	<tr><td>
		<h3>所有直播資料-listAllLive.jsp</h3>
		<h4><a href='selectPage.jsp'><img src="images/back1.gif" width="100" height="32">回首頁</a></h4>
	
	
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
		<th>修改</th>
		<th>刪除</th>		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="liveVO" items= "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>		
			<td>${liveVO.host_ID}</td>
			<td>${liveVO.live_name}</td>
			<td>${liveVO.live_acc}</td>
			<td><img class='pic' src='/CA106G5/ShowPic?HOST_ID=${liveVO.host_ID}'></td>
			<td>${liveVO.live_date}</td>
			<td>${liveVO.live_status}</td>			
			
			<td>
				<form method='post' action='' style="margin-bottom: 0px;">
					<input type='submit' value='修改'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID }'>
					<input type='hidden' name='action' value='getOne_For_Update'>				
				</form></td>
			<td>	
				<form method='post' action='' style="margin-bottom: 0px;">
					<input type='submit' value='刪除'>
					<input type='hidden' name='prod_ID' value='${prodVO.prod_ID }'>
					<input type='hidden' name='action' value='delete'>				
				</form></td>		
		</tr>
 	
	</c:forEach>
</table>
<%@ include file="page2.file" %> 



</body>
</html>