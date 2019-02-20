<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Live-selectPage.jsp</title>

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

<table id="table-1">
   <tr><td><h3>Live-selectPage.jsp</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Live-selectPage.jsp</p>

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
  <li><a href='listAllLive.jsp'>按我顯示</a> 所有直播主列表.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="live.do" >
        <b>輸入直播主ID編號 (如M000005):</b>
        <input type="text" name="host_ID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="liveSvc" scope="page" class="com.meetU.live.model.LiveService" />
   
  <li>
     <FORM METHOD="post" ACTION="live.do" >
       <b>選擇直播主ID:</b>
       <select size="1" name="host_ID">
         <c:forEach var="liveVO" items="${liveSvc.all}" > 
          <option value="${liveVO.host_ID}">${liveVO.host_ID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
</ul>


<h3>直播主管理</h3>

<ul>
  <li><a href='addLive.jsp'>增加</a>一位直播主</li>
</ul>

</body>
</html>