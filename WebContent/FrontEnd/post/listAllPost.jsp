<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.meetU.post.model.*" %>
<%@ page import="com.meetU.checkIn.model.*" %>

<% 
	PostService postSvc = new PostService();
	List<PostVO> list = postSvc.getAll();
	pageContext.setAttribute("list",list);
	CheckInService checkInSvc = new CheckInService();
	List<CheckInVO> ck_list = checkInSvc.getAll();
	pageContext.setAttribute("ck_list",ck_list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>�Ҧ��峹��� - listAllPost.jsp</title>

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
	width: 1200px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 3px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back.png" width="60" height="45" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�峹�s��</th>
		<th>�o��|��ID</th>
		<th>��~��|��ID</th>
		<th>���d�a�IID</th>
		<th>�峹���e</th>
		<th>�峹�o���ɶ�</th>
		<th>�峹���g�ƶq</th>
		<th>�峹�ਣ��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	
	<%@ include file="page1.file" %> 
	<c:forEach var="postVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${postVO.post_ID}</td>
			<td>${postVO.poster_ID}</td>
			<td>${postVO.mem_ID}</td>
			<td>
				<c:forEach var="checkInVO" items="${ck_list}">
				 <c:if test="${(checkInVO.check_in_ID == postVO.check_in_ID)}">
				 	${checkInVO.check_in_name}
				 </c:if>  
				</c:forEach>
			</td>
			<td>${postVO.post_content}</td>
			<td>${postVO.publish_time}</td> 
			<td>${postVO.post_like}</td>
			<td>
				<c:if test="${postVO.post_visb == 0}">
					�u�����H
				</c:if>
				<c:if test="${postVO.post_visb == 1}">
					�B��
				</c:if>
				<c:if test="${postVO.post_visb == 2}">
					���}
				</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/post.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="post_ID"  value="${postVO.post_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/post/post.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="post_ID"  value="${postVO.post_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>