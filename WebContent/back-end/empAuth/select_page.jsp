<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>meetU empAuth: Home</title>

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
   <tr><td><h3>meetU empAuth: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for meetU empAuth: Home</p>

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
  <li><a href='listAllEmpAuth.jsp'>List</a> all empAuth.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="empAuth.do" >
        <b>輸入員工ID :</b>
        <input type="text" name="emp_ID">
        <input type="hidden" name="action" value="getSome_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="empAuthSvc" scope="page" class="com.meetU.empAuth.model.EmpAuthService" />
   
  <li>
     <FORM METHOD="post" ACTION="empAuth.do" >
       <b>選擇員工ID:</b>
       <select size="1" name="emp_ID">
         <c:forEach var="empAuthVO" items="${empAuthSvc.all}" > 
          <option value="${empAuthVO.getEmp_ID()}">${empAuthVO.getEmp_ID()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <jsp:useBean id="AuthSvc" scope="page" class="com.meetU.auth.model.AuthService" />
  <li>
     <FORM METHOD="post" ACTION="auth.do" >
       <b>選擇權限:</b>
       <select size="1" name="auth_ID">
         <c:forEach var="AuthVO" items="${AuthSvc.all}" > 
          <option value="${AuthVO.auth_ID}">${AuthVO.auth_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>   
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="empAuth.do" > -->
<!--        <b>選擇員工權限ID:</b> -->
<!--        <select size="1" name="prod_ID"> -->
<%--          <c:forEach var="empAuthVO" items="${empAuthSvc.all}" >  --%>
<%--           <option value="${empAuthVO.prod_ID}">${empAuthVO.prod_ID} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getSome_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>員工權限管理</h3>

<ul>
  <li><a href='addEmpAuth.jsp'>Add</a> a new empAuth.</li>
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