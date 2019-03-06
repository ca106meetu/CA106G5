<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_report.model.*"%>
<%@ page import="com.meetU.meetup.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>
<%
	MeetupRepVO meetupRepVO = (MeetupRepVO) request.getAttribute("meetupRepVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	MeetupService meetupSvc = new MeetupService(); 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>聯誼資料 - listOneMeetupRep.jsp</title>
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
<body>
<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneMeetup.jsp</h3>
		 <h4><a href="listAllRep.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>檢舉狀態</th>
		<th>檢舉編號</th>
		<th>聯誼名稱</th>
		<th>檢舉者</th>
		<th>檢舉時間</th>
		<th>檢舉內容</th>		
	</tr>	
	<tr>
			<td><%=meetupRepVO.getRep_status()%></td>
			<td><%=meetupRepVO.getMeetup_rep_ID()%></td>
			<td><%=meetupSvc.getOneMeetup(meetupRepVO.getMeetup_ID()).getMeetup_name()%></td>
			<td><%=meetupRepVO.getMem_ID()%></td>
			<td><%=meetupRepVO.getRep_date()%></td> 
			<td><%=meetupRepVO.getRep_content()%></td> 
	</tr>
</table>
</body>
</html>