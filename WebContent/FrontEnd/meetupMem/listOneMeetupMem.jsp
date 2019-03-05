<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.meetU.meetup_mem.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>


<%
	MeetupMemVO meetupMemVO = (MeetupMemVO) request.getAttribute("meetupMemVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>只展現一支</title>

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
		 <h3>員工資料 - ListOneMeetupBy A MEMBER.jsp</h3>
		 <h4><a href="select_page_mem.jsp">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>聯誼編號</th>
		<th>成員名稱</th>
		<th>聯誼評價</th>
		<th>評價內容</th>
		
	</tr>
	<tr>
			<td><%=meetupMemVO.getMeetup_ID()%></td>
			<td><%=meetupMemVO.getMem_ID()%></td>
			<td><%=meetupMemVO.getMeetup_rate()%></td>
			<td><%=meetupMemVO.getMeetup_comment()%></td> 
			
	</tr>
</table>
</body>
</html>