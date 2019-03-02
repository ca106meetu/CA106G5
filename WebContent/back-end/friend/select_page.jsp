<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="zh-Hant-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>meetU friend: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<div class="container justify-content-center">
<div class="row">
<div class="col-6">
<table id="table-1">
   <tr><td><h3>meetU friend: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for meetU friend: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllFriend.jsp'>List</a> all friend.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="friend.do" >
        <b>輸入會員ID :</b>
        <input type="text" name="mem_ID">
        <input type="hidden" name="action" value="getSome_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="friendSvc" scope="page" class="com.meetU.friend.model.FriendService" />
   
  <li>
     <FORM METHOD="post" ACTION="friend.do" >
       <b>選擇會員ID:</b>
       <select size="1" name="mem_ID">
         <c:forEach var="friendVO" items="${friendSvc.all}" > 
          <option value="${friendVO.getMem_ID()}">${friendVO.getMem_ID()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="friend.do" > -->
<!--        <b>選擇禮物ID:</b> -->
<!--        <select size="1" name="prod_ID"> -->
<%--          <c:forEach var="friendVO" items="${friendSvc.all}" >  --%>
<%--           <option value="${friendVO.prod_ID}">${friendVO.prod_ID} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>禮物管理</h3>

<ul>
  <li><a href='addFriend.jsp'>Add</a> a new friend.</li>
</ul>
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