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

<title>meetU giftbox: Home</title>

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
   <tr><td><h3>meetU giftbox: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for meetU giftbox: Home</p>

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
  <li><a href='listAllGiftbox.jsp'>List</a> all giftbox.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="giftbox.do" >
        <b>輸入會員ID :</b>
        <input type="text" name="mem_ID">
        <input type="hidden" name="action" value="getSome_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="giftboxSvc" scope="page" class="com.meetU.giftbox.model.GiftboxService" />
   
  <li>
     <FORM METHOD="post" ACTION="giftbox.do" >
       <b>選擇會員ID:</b>
       <select size="1" name="mem_ID">
         <c:forEach var="giftboxVO" items="${giftboxSvc.all}" > 
          <option value="${giftboxVO.getMem_ID()}">${giftboxVO.getMem_ID()}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getSome_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="giftbox.do" > -->
<!--        <b>選擇禮物ID:</b> -->
<!--        <select size="1" name="prod_ID"> -->
<%--          <c:forEach var="giftboxVO" items="${giftboxSvc.all}" >  --%>
<%--           <option value="${giftboxVO.prod_ID}">${giftboxVO.prod_ID} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getSome_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>禮物管理</h3>

<ul>
  <li><a href='addGiftbox.jsp'>Add</a> a new giftbox.</li>
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