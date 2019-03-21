<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.mem.model.*"%>
<%@ page import="com.meetU.memHobby.model.*"%>
<%@ page import="com.meetU.hobby.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemService memSvc = new MemService();
    List<MemVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<% 
    java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formatDate = df.format(new java.util.Date());
    out.println(formatDate);
 %>

<!doctype html>
<html lang="zh-Hant-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
      <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>所有會員資料 - listAllEmp.jsp</title>

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
	width: 800px;
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
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<div class="container justify-content-center">
<div class="row">
<div class="col-6">
<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料 - listAllMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>會員ID</th>
		<th>會員密碼</th>
		<th>會員姓名</th>
		<th>會員帳號</th>
		<th>會員暱稱</th>
		
		<th>會員生日</th>
		<th>會員電子郵件信箱</th>
		<th>會員手機</th>
		<th>會員性別</th>
		<th>會員大頭照</th>
		
		<th>會員自我介紹</th>
		<th>會員驗證碼</th>
		<th>會員帳號狀態</th>
		<th>會員註冊日期</th>
		<th>會員最後簽到時間</th>
		
		<th>會員登入狀態</th>
		<th>會員居住地</th>
		<th>上次配對時間</th>
		<th>會員興趣</th>
		<th>會員QRCODE</th>
		<th>會員點數</th>
		
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memVO.mem_ID}</td>
			<td>${memVO.mem_pw}</td>
			<td>${memVO.mem_name}</td>
			<td>${memVO.mem_acc}</td>
			<td>${memVO.mem_nickname}</td>
			
			<td><fmt:formatDate value="${memVO.mem_bday}" pattern="yyyy-MM-dd"/></td>
			<td>${memVO.mem_email}</td>
			<td>${memVO.mem_pho}</td>
			<td>${memVO.mem_gend}</td>
			<td>
			  <img class='pic' src='<%=request.getContextPath() %>/ShowPic?mem_ID=${memVO.mem_ID}&photoNo=1'>
			</td>
			
			<td>${memVO.mem_intro}</td>
			<td>${memVO.mem_code}</td>
			<td>${memVO.mem_state}</td>
			<td><fmt:formatDate value="${memVO.mem_date}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${memVO.mem_sign_day}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			
			<td>${memVO.mem_login_state}</td>
			<td>${memVO.mem_address}</td>
			<td><fmt:formatDate value="${memVO.last_pair}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<jsp:useBean id="hobbySvc" scope="page" class="com.meetU.hobby.model.HobbyService" />
			<jsp:useBean id="memhobbySvc" scope="page" class="com.meetU.memHobby.model.MemHobbyService" />
			
			<td>
			<c:forEach var="MemHobbyVO" items="${memhobbySvc.getPartOfOneMemHobby(memVO.mem_ID)}">
			
				${hobbySvc.getOneHobby(MemHobbyVO.hobby_ID).hobby_name}
			</c:forEach>
			
			</td>
			

			<td>
			  <img class='pic' src='<%=request.getContextPath() %>/ShowPic?mem_ID=${memVO.mem_ID}&photoNo=2'>
			</td>
			
			<td>${memVO.mem_get_point}</td>
						
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_ID"  value="${memVO.mem_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mem/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="mem_ID"  value="${memVO.mem_ID}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</div>
</div>
</div>
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/js/bootstrap.min.js"></script>

    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  
</body>
</html>