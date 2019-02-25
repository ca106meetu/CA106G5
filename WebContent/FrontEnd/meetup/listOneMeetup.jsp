<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.meetU.meetup.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MeetupVO meetupVO = (MeetupVO) request.getAttribute("meetupVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>聯誼資料 - listOneMeetup.jsp</title>

<style>
	 table#table-1 {
		background-color: #CCCCFF;
	    border: 2px solid black;
	    text-align: center;
	  }
	  table#table-1 h4 {
	    color: red;
	    display: block;
	    margin-bottom: 1px;
	  }
	  h4 {
	    color: blue;
	    display: inline;
	  }	
	  
	  table {
		width: 600px;
		background-color: white;
		margin-top: 5px;
		margin-bottom: 5px;
	  }
	  table, th, td {
	    border: 1px solid #CCCCFF;
	  }
	  th, td {
	    padding: 5px;
	    text-align: center;
	  }
	  img{
		width:150;
		height:auto;
	  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneMeetup.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>聯誼編號</th>
		<th>聯誼名稱</th>
		<th>聯誼日期</th>
		<th>聯誼地址</th>
		<th>聯誼狀態</th>
		<th>聯誼圖片</th>
		<th>聯誼資訊</th>		
	</tr>
	<tr>
			<td><%=meetupVO.getMeetup_ID()%></td>
			<td><%=meetupVO.getMeetup_name()%></td>
			<td><%=meetupVO.getMeetup_date()%></td>
			<td><%=meetupVO.getMeetup_loc()%></td>
			<td><%=meetupVO.getMeetup_status()%></td> 
			<td><img class='pic' src='/CA106G52/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}'></td>
			<td><%=meetupVO.getMeetup_info()%></td>
	</tr>
</table>
</body>
</html>	