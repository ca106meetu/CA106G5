<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.post.model.*"%>

<%
  PostVO postVO = (PostVO) request.getAttribute("postVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�峹���e�ק� - updatePost.jsp</title>

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
		 <h3>�峹���e�ק� - updatePost.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back.png" width="60" height="45" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

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
		<td>�峹�s��:</td>
		<td><%=postVO.getPost_ID()%></td>
	</tr>
	<tr>
		<td>�o��̽s��:</td>
		<td><%=postVO.getPoster_ID()%></td>
	</tr>
	<tr>
		<td>��~��|���s��:</td>
		<td><%=postVO.getMem_ID()%></td>
	</tr>
	<tr>
		<td>�峹���e:</td>
		<td><textarea name="post_content" rows=3 cols=45><%=postVO.getPost_content()%></textarea></td>
	</tr>

	<tr>
		<td>�ਣ��:</td>
		<td><select size="1" name="post_visb">
				<option value="0" ${(postVO.post_visb==0)?'selected':'' } >�u�����H
				<option value="1" ${(postVO.post_visb==1)?'selected':'' } >�B��
				<option value="2" ${(postVO.post_visb==2)?'selected':'' } >���}
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="post_ID" value="<%=postVO.getPost_ID()%>">
<input type="hidden" name="poster_ID" value="<%=postVO.getPoster_ID()%>">
<input type="hidden" name="mem_ID" value="<%=postVO.getMem_ID()%>">
<input type="hidden" name="check_in_ID" value="<%=postVO.getCheck_in_ID()%>">
<input type="hidden" name="publish_time" value="<%=postVO.getPublish_time()%>">
<input type="hidden" name="post_like" value="<%=postVO.getPost_like()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>

</html>