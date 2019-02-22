<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.meetU.post.model.*"%>
<%@ page import="com.meetU.checkIn.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  PostVO postVO = (PostVO) request.getAttribute("postVO"); //PostServlet.java(Concroller), 存入req的postVO物件
  CheckInService checkInSvc = new CheckInService();
  List<CheckInVO> ck_list = checkInSvc.getAll();
  pageContext.setAttribute("ck_list",ck_list);
%>

<html>
<head>
<title>文章資料listOnePost.jsp</title>

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
		 <h3>文章資料 - ListOnePost.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back.png" width="60" height="45" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>文章編號</th>
		<th>發文會員ID</th>
		<th>塗鴉牆會員ID</th>
		<th>打卡地點ID</th>
		<th>文章內容</th>
		<th>文章發布時間</th>
		<th>文章按讚數量</th>
		<th>文章能見度</th>
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
					只限本人
				</c:if>
				<c:if test="${postVO.post_visb == 1}">
					朋友
				</c:if>
				<c:if test="${postVO.post_visb == 2}">
					公開
				</c:if>
		</td>
	</tr>
</table>

</body>
</html>