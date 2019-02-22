<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.meetU.post.model.*"%>
<%@ page import="com.meetU.checkIn.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  PostVO postVO = (PostVO) request.getAttribute("postVO"); //PostServlet.java(Concroller), �s�Jreq��postVO����
  CheckInService checkInSvc = new CheckInService();
  List<CheckInVO> ck_list = checkInSvc.getAll();
  pageContext.setAttribute("ck_list",ck_list);
%>

<html>
<head>
<title>�峹���listOnePost.jsp</title>

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
	width: 1000px;
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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�峹��� - ListOnePost.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back.png" width="60" height="45" border="0">�^����</a></h4>
	</td></tr>
</table>

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
	</tr>
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
		<td>${postVO.getPost_content()}</td>
		<td>${postVO.getPublish_time()}</td>
		<td>${postVO.getPost_like()}</td>
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
	</tr>
</table>

</body>
</html>