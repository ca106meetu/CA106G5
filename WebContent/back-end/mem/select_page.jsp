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

    
<title>meetU Mem: Home</title>

<style>
  table#table-1 {

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

<p>This is the Home page for meetU Mem: Home</p>

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
  <li>
    <h4><a href='listAllMem.jsp'>List all Mems.</a></h4>
  </li>
  <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="mem.do" >
    <div class="input-group">
       <div class="input-group-prepend">
     	 <label class="input-group-text">輸入會員ID :</label>
       </div>
         <input type="text" name="mem_ID" class="form-control">
       <div class="input-group-append">
         <input type="hidden" name="action" value="getOne_For_Display">
         <button class="btn btn-outline-info" type="submit"><b>送出</b></button>
       </div>
    </div>
    </FORM>
  </li>
  <br>

  <jsp:useBean id="memSvc" scope="page" class="com.meetU.mem.model.MemService" />
   
  <li>
     <FORM METHOD="post" ACTION="mem.do" >
     <div class="input-group">
     	<div class="input-group-prepend">
     	  <label class="input-group-text">選擇會員ID:</label>     
        </div>
        <select size="1" name="mem_ID" class="custom-select">
          <c:forEach var="memVO" items="${memSvc.all}" > 
            <option value="${memVO.mem_ID}">${memVO.mem_ID}
          </c:forEach>   
        </select>
        <div class="input-group-append">
       	<input type="hidden" name="action" value="getOne_For_Display">
       	<button class="btn btn-outline-info" type="submit"><b>送出</b></button>
       </div>
    </div>    
    </FORM>
  </li>
<br>
  
  <li>
     <FORM METHOD="post" ACTION="mem.do" >
   	 <div class="input-group">
	     <div class="input-group-prepend">
	     	<label class="input-group-text">選擇會員帳號 :</label>
	     </div>	
       <select size="1" name="mem_ID" class="custom-select">
         <c:forEach var="memVO" items="${memSvc.all}" > 
          <option value="${memVO.mem_ID}">${memVO.mem_acc}
         </c:forEach>   
       </select>
       <div class="input-group-append">
       <input type="hidden" name="action" value="getOne_For_Display">
       <button class="btn btn-outline-info" type="submit"><b>送出</b></button>
       </div>
     </div>
     </FORM>
  </li>
</ul>

<br>
<ul>
  <li><a href='addMem.jsp'>Add a new Mem.</a></li>
</ul>
</div>
</div>
</div>
<br><br>
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