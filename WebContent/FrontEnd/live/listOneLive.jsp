<%@page import="com.meetU.live.model.LiveVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	LiveVO liveVO =  (LiveVO) request.getAttribute("liveVO");
%>
<!DOCTYPE html>
<html>
<head>
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
<title>Insert title here</title>
</head>
<body>
<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>直播主資料 - ListOneList.jsp</h3>
		 <h4><a href="selectPage.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>直播主ID</th>
		<th>直播間名稱</th>
		<th>累積瀏覽數</th>
		<th>直播間封面</th>
		<th>直播間創立時間</th>
		<th>直播間狀態</th>
		
	</tr>
	<tr>
		    <td>${liveVO.host_ID}</td>
			<td>${liveVO.live_name}</td>
			<td>${liveVO.live_acc}</td>
			<td><img class='pic' src='/SmokeChen/ShowPic?HOST_ID=${liveVO.host_ID}'></td>
			<td>${liveVO.live_date}</td>
			<td>${liveVO.live_status}</td>	
	</tr>
</table>


</body>
</html>