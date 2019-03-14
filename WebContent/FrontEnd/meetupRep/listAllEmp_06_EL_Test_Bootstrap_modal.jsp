<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_report.model.*, com.meetU.meetup.model.*"%>

<%-- 此測試頁，練習採用 EL 的寫法取值 --%>
<jsp:useBean id="meetupRepSvc"  scope="page" class="com.meetU.meetup_report.model.MeetupRepService" />
<jsp:useBean id="meetupSvc" scope="page" class="com.meetU.meetup.model.MeetupService" />

<%--
	MeetupRepService meetupRepSvc = new MeetupRepService();
	List<MeetupRepVO> list = meetupRepSvc.getAll();
	pageContext.setAttribute("list", list);	
--%>

<html>
<head>
<title>所有員工資料</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h3 {
    font: 20px Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
    font-weight:bold;
    color: black;
    display: block;
    margin: 5px;
  }
  h4 {
    font: 18px Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
    font-weight:bold;
    color: blue;
    display: inline;
    margin: 5px;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
</style>

<style>
.myTable{
  width: 100%;
}
.myTable *{
  text-align: center;
}
</style>

</head>
<body bgcolor='white'>

<h4>此測試頁，練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料<br><font color=red>listAllEmp_06_EL_Test_<font color=blue>Bootstrap_modal</font>.jsp</font></h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>檢舉狀態</th>
					<th>檢舉編號</th>
					<th>聯誼名稱</th>
					<th>檢舉者</th>
					<th>檢舉時間</th>
					<th>檢舉內容</th>
	</tr>
 
	<c:forEach var="meetupRepVO" items="${meetupRepSvc.all}" varStatus="varstatus" >
		<tr>
			<td>${meetupRepVO.rep_status}</td>
					<td><A href="meetupRep.do?meetup_rep_ID=${meetupRepVO.meetup_rep_ID}&action=getOne_For_Update">${meetupRepVO.meetup_rep_ID}</a></td>
					<td><A href="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do?meetup_ID=${meetupRepVO.meetup_ID}&action=getOne_For_Display">
					${meetupSvc.getOneMeetup(meetupRepVO.meetup_ID).meetup_name}</a></td>
					<td>${meetupRepVO.mem_ID}</td>
					<td>${meetupRepVO.rep_date}</td>
					<td>
						<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupRep/meetupRep.do" >	
							<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}">
							<input type="hidden" name="action" value="getOne_For_Update">
							<input type="submit" class="btn btn-info btnShowReason" value="查看/ 回覆檢舉原因">	
						</FORM>
					</td>
		</tr>
	</c:forEach>
</table> 

<c:if test="${openModal!=null}">

<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
				
			<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h3 class="modal-title" id="myModalLabel">The Bootstrap modal-header</h3>
            </div>
			
			<div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="updateRepContentAns.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
			</div>
			
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
		
		</div>
	</div>
</div>

        <script>
    		 $("#basicModal").modal({show: true});
        </script>
 </c:if>
</body>
</html>