<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.meetU.mem.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java(Concroller), 存入req的memVO物件
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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>會員資料 - listOneMem.jsp</title>

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
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<div class="container justify-content-center">
<div class="row">
<div class="col-6">    
<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>會員資料 - ListOneMem.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

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
	</tr>
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
			<td>${memVO.mem_hobby}</td>
			<td>
			  <img class='pic' src='<%=request.getContextPath() %>/ShowPic?mem_ID=${memVO.mem_ID}&photoNo=2'>
			</td>
			
			<td>${memVO.mem_get_point}</td>
	</tr>
</table>
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