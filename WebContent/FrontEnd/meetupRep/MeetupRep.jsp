<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.meetup_report.model.*, com.meetU.meetup.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<% 
	MeetupRepService meetupRepSvc = new MeetupRepService();
	List<MeetupRepVO> list = meetupRepSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>

<!DOCTYPE html>
<html>
<head>
<meta>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

<title>Meetup Report</title>
<style>
	*{
		font-family:微軟正黑體;
	}
	
	img{
		width:150;
		height:auto;
	}
	
	table{
		border:1px solid black;
		margin:3em;	
	}
	
	th, td {
	    padding: 15px;
	    text-align: center;
	    border:1px solid black;
	  }

</style>

</head>

<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<div class="container">
	<div class="row">
    	
<jsp:useBean id="meetupSvc" scope="page" class="com.meetU.meetup.model.MeetupService"/>
			<table class="table1">
				<tr>
					<th>檢舉狀態</th>
					<th>檢舉編號</th>
					<th>聯誼名稱</th>
					<th>檢舉者</th>
					<th>檢舉時間</th>
					<th>檢舉內容</th>		
				</tr>
<%@ include file="page1.file"%>
<c:forEach var="meetupRepVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${meetupRepVO.rep_status}</td>
					<td>${meetupRepVO.meetup_rep_ID}</td>
					<td>${meetupSvc.getOneMeetup(meetupRepVO.meetup_ID).meetup_name}</td>
					<td>${meetupRepVO.mem_ID}</td>
					<td>${meetupRepVO.rep_date}</td>
					<td>
						<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/FrontEnd/meetupRep/meetupRep.do" >	
							<input type="hidden" name="meetup_rep_ID" value="${meetupRepVO.meetup_rep_ID}">
							<input type="hidden" name="action" value="getOne_For_Display">
							<input type="button" class="btn btn-info" value="查看檢舉原因">	
						</FORM>
					</td>	
				</tr>
</c:forEach>
</table>
<%@ include file="page2.file" %>
		
<div class="modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    
      <div class="modal-header">
        <h5 class="modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
               <jsp:include page="updateRepContentAns.jsp" />
<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
		
		
		
	</div>
</div>

    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>