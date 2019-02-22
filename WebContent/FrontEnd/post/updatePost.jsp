<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.post.model.*"%>

<%
  PostVO postVO = (PostVO) request.getAttribute("postVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>文章內容修改 - updatePost.jsp</title>

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
		 <h3>文章內容修改 - updatePost.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back.png" width="60" height="45" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="post.do" name="form1">
<table>
	<tr>
		<td>文章編號:</td>
		<td><%=postVO.getPost_ID()%></td>
	</tr>
	<tr>
		<td>發文者編號:</td>
		<td><%=postVO.getPoster_ID()%></td>
	</tr>
	<tr>
		<td>塗鴉牆會員編號:</td>
		<td><%=postVO.getMem_ID()%></td>
	</tr>
	<tr>
		<td>文章內容:</td>
		<td><textarea name="post_content" rows=3 cols=45><%=postVO.getPost_content()%></textarea></td>
	</tr>

	<tr>
		<td>能見度:</td>
		<td><select size="1" name="post_visb">
				<option value="0" ${(postVO.post_visb==0)?'selected':'' } >只限本人
				<option value="1" ${(postVO.post_visb==1)?'selected':'' } >朋友
				<option value="2" ${(postVO.post_visb==2)?'selected':'' } >公開
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
<input type="submit" value="送出修改"></FORM>
</body>

</html>