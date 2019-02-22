<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.post.model.*"%>

<%
  PostVO postVO = (PostVO) request.getAttribute("postVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�s�W�峹 - addPost.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  

</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>�s�W�峹 - addPost.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/back.png" width="60" height="45" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="post.do" name="form1">
<table>
	<tr>
		<td>�o��̽s��:</td>
		<td><input type="TEXT" name="poster_ID" size="45" 
			 value="<%= (postVO==null)? "M000001" : postVO.getPoster_ID()%>" /></td>
	</tr>
	<tr>
		<td>��~��|���s��:</td>
		<td><input type="TEXT" name="mem_ID" size="45"
			 value="<%= (postVO==null)? "M000001" : postVO.getMem_ID()%>" /></td>
	</tr>
	<jsp:useBean id="checkInSvc" scope="page" class = "com.meetU.checkIn.model.CheckInService"/>
	<tr>
		<td>���d�a�I���:</td>
		<td><select size="1" name="check_in_ID">
		<option value="" ${(postVO.check_in_ID==null)?'selected':'' }>�п��
			<c:forEach var="checkInVO" items="${checkInSvc.all}">
				<option value="${checkInVO.check_in_ID}" ${(postVO.check_in_ID==checkInVO.check_in_ID)? 'selected':'' } >${checkInVO.check_in_name}
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><textarea name="post_content" rows=3 cols=45><%= (postVO==null)? "" : postVO.getPost_content()%></textarea></td>
	</tr>
	<tr>
		<td>�ਣ��:</td>
		<td><select size="1" name="post_visb">
				<option value=-1 ${(postVO.post_visb==null)?'selected':'' }>�п��
				<option value="0" ${(postVO.post_visb==0)?'selected':'' } >�u�����H
				<option value="1" ${(postVO.post_visb==1)?'selected':'' } >�B��
				<option value="2" ${(postVO.post_visb==2)?'selected':'' } >���}
		</select></td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>


</html>