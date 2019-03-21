<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*, com.meetU.meetup_mem.model.* "%>
<%@ page import="com.meetU.meetup.model.*, com.meetU.mem.model.* "%>

<% 	
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	MeetupService meetupSvc = new MeetupService();
	List<MeetupVO> list = meetupSvc.getAllByHost(memVO.getMem_ID());
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
		
<title>我的主持聯誼</title>    
    <style>
    .pic{
		width:200px;
		height:auto;
	}
    
    .itemImg{
		width: 200px;
		height: 200px;
		<%--margin: 20px 0px 10px 0px;
		<%--padding: 10px 25px 0px 25px;
		flex-grow: 1;
		background-color:black;--%>
		align:center;
	}
    
    .itemTitle{
    	width:200px;
    	text-align:center;
    	margin:0px;
	 	
  		font-weight: bold;
  	}
    
    *{
    	font-family:微軟正黑體;	
    }
    
    .cart-item {
    	 
		  position: relative;
		  margin-bottom: 30px;
		  padding: 0 50px 0 50px;
		  background-color: #fff;
		  box-shadow: 0 12px 20px 1px rgba(64, 64, 64, .09);
	}
	
	.itemImg, .itemTitle, .itemEdit{
    	float:left;
		margin:20px;
    	height: 200px;
    	line-height:200px;
    	text-align:center;
    	
    }
    
    .addMeetup{
    	float:right;    
    }
    
    </style> 
   
  </head>
  <body>
 
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<%@ include file="page1.file" %>	

<div class="container">
	<div class="row">
		<div class="col">
		<div class="addMeetup">
			 <FORM METHOD="post" ACTION="addMeetup.jsp">
                 <input type=hidden name=mem_ID value="${mem.mem_ID}">
                 <input type='submit' value="主辦一個新的聯誼" class="btn btn-info">                    
             </FORM>
        </div>
		</div>
	</div>

<c:forEach var="meetupVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<div class="row">
		
		<div class="col cart-item">
		    <div class="itemImg">
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
                        <input type=hidden name=meetup_ID value="${meetupVO.meetup_ID}">
                        <input type=hidden name=action value="getOne_For_Display">
                        <input class='pic' type='image' src='/CA106G5/ShowPic?MEETUP_ID=${meetupVO.meetup_ID}' alt='submit'>                    
                 </FORM>
            </div>
            <div class="itemTitle">
            	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do">
                        <button type="submit" class="btn btn-light">${meetupVO.meetup_name}</button>
                        <input type="hidden" name="meetup_ID"  value="${meetupVO.meetup_ID}">
                        <input type="hidden" name="action"  value="getOne_For_Display">
            	</FORM>
      		</div>
      		
      		<div class="itemEdit"> 
				共 X 位參與者
			</div>
      		
      		<div class="itemEdit">
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-warning">
			     <input type="hidden" name="meetup_ID"  value="${meetupVO.meetup_ID}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			    </FORM>
			</div>
			<div class="itemEdit">
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-warning">
			     <input type="hidden" name="meetup_ID"  value="${meetupVO.meetup_ID}">
			     <input type="hidden" name="action" value="delete">
			    </FORM>
			</div>
	    </div>
   </div>    
</c:forEach>
</div>

<%@ include file="page2.file" %>

<script>


</script>

    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
    </body>
</html>