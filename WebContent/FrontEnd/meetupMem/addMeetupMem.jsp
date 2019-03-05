<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup_mem.model.*"%>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<% MeetupMemVO meetupMemVO = (MeetupMemVO) request.getAttribute("meetupMemVO");%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>加入meetup  - addMeetupMem.jsp</title>

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
</style>

<style>
  img{
  	width : 150px;
  	height: auto;
  }	
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<table id="table-1">
	<tr><td>
		 <h3>加入meetup - addMeetupMem.jsp</h3></td><td>
		 <h4><a href="select_page_mem.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>加入meetup :</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="msg" items="${errorMsgs}">
			<li style="color:red">${msg}</li>
		</c:forEach>
	</ul>
</c:if>


<FORM METHOD="POST" ACTION="meetupMem.do" name="form1" enctype='multipart/form-data'>
<table>

	<tr>
		<th>聯誼名稱</th>
		<td><input type="text" name="meetup_ID" size="45" value="<%=(meetupMemVO==null)? "MP000001":meetupMemVO.getMeetup_ID()%>"/></td>
	</tr>
	
	<tr>
		<th>成員名稱</th>
		<td><input type="text" name="mem_ID" size="45" value="<%=(meetupMemVO==null)? "M000001":meetupMemVO.getMem_ID()%>"/></td>
	</tr>
	<%--<tr>
		<th>聯誼評價</th>
		<td><input type="text" name="meetup_rate" size="45" value="<%=(meetupMemVO==null)? "3":meetupMemVO.getMeetup_rate()%>"/></td>
	</tr>	
	<tr>
		<th>評價內容</th>
		<td><input type="text" name="mem_comment" size="45" value="<%=(meetupMemVO==null)? "很棒":meetupMemVO.getMeetup_comment()%>"/></td>
	</tr>  --%>	
	
</table>
<br>
<input type="hidden" name="action" value="insert">
<button type="submit" >送出新增</button>
</FORM>


</body>
</html>

