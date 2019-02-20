<%@page import="com.meetU.live.model.*"%>
<%@page import="com.meetU.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	  LiveVO liveVO =  (LiveVO) request.getAttribute("liveVO");
%>
<html>
<head>
<title>直播間新增 - addLive.jsp</title>

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
  table {
	width: 400px;
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
		 <h3>直播間新增 - addLive.jsp</h3></td><td>
		 <h4><a href="selectPage.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>




<FORM METHOD="post" ACTION="live.do" name="form1">
<table>
<jsp:useBean id="memSvc" scope="page" class="com.meetU.mem.model.MemService"/>
	<tr>
		<td>會員姓名:</td>
		<td><select size="1" name="host_ID">
			<c:forEach var="memVO" items="${memSvc.all}">			
			<option value="${memVO.mem_ID}" ${(memVO.mem_ID==liveVO.host_ID)? 'selected': '' } >${memVO.mem_name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>直播間名稱:</td>
		<td><input type="TEXT" name="live_name" size="20" 
			 value="<%= (liveVO==null)? " " : liveVO.getLive_name()%>" /></td>
	</tr>
	<tr>
		<td>累積瀏覽人數:</td>
		<td><input type="NUMBER" min="0" name="live_acc" size="20" 
			 value="<%= (liveVO==null)? " " : liveVO.getLive_acc()%>" /></td>
	</tr>
	<tr>
		<td>直播間封面:</td>
		<td><input type="TEXT" name="live_pic" 
			 value="<%= (liveVO==null)? " " : liveVO.getLive_pic()%>" /></td>
	</tr>
	<tr>
		<td>直播間狀態:</td>
		<td><input type="NUMBER" name="live_status" 
			 value="<%= (liveVO==null)? " " : liveVO.getLive_status()%>" /></td>
	</tr>	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>
</html>