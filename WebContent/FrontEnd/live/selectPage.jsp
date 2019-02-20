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

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllLive.jsp'>�������</a> �Ҧ������D�C��.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="live.do" >
        <b>��J�����DID�s�� (�pM000005):</b>
        <input type="text" name="host_ID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="liveSvc" scope="page" class="com.meetU.live.model.LiveService" />
   
  <li>
     <FORM METHOD="post" ACTION="live.do" >
       <b>��ܪ����DID:</b>
       <select size="1" name="host_ID">
         <c:forEach var="liveVO" items="${liveSvc.all}" > 
          <option value="${liveVO.host_ID}">${liveVO.host_ID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  
</ul>


<h3>�����D�޲z</h3>

<ul>
  <li><a href='addLive.jsp'>�W�[</a>�@�쪽���D</li>
</ul>

</body>
</html>