<%@page import="com.meetU.mem.model.MemVO"%>
<%@page import="com.meetU.mem.model.MemService"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterVO"%>
<%@page import="com.meetU.orderMaster.model.OrderMasterService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	OrderMasterService omSvc = new OrderMasterService();
	String mem_ID = request.getParameter("mem_ID");
	List<OrderMasterVO> list = omSvc.getOmByMem(mem_ID);
	pageContext.setAttribute("list", list);
	MemVO memVO = new MemService().getOneMem(mem_ID);
%>

<!doctype html>
<html lang="en">
  <head>
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <style>
  
   .t {
  	color:black;
  }
  .d {
  	color:blue;
  }
  
  .card-body {
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 1.25rem;
    width: 500px;
}
  
	.pic{
		width:172.5px;
		height:230px;
	}
	table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 2px;
    text-align: center;
  }
	
</style>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

    <title>會員訂單資料</title> 
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    <div class='container'>
    
 <table id = 'table-1'>
	<tr><td>
		<h3>【<%=memVO.getMem_acc()%>】的訂單資料</h3>
		<h4><a href='selectPageOm.jsp'><img src="images/back1.png" width="60">回訂單管理</a></h4>
	
	
	</td>
	
	
	</tr>




</table>

	<br>
	<%@ include file="page1.file" %> 

		
		<div class='row'>
	 	<div class='col-12'><hr></div>
	 	</div>
		<div class="row">
		<div class="col-3" style="color:#0a0ac3"><h3>訂單編號 </h3></div>
        <div class="col-2" style="color:#0a0ac3"><h3>訂單金額 </h3></div>
        <div class="col-2" style="color:#0a0ac3"><h3>修改 </h3></div>
        <div class="col-2" style="color:#0a0ac3"><h3>出貨 </h3></div>
        <div class="col-3" style="color:#0a0ac3"><h3>訂單時間 </h3></div>
        </div>
        <div class='row'>
	 	<div class='col-12'><hr></div>
	 	</div>

	<%
		if(request.getAttribute("lastPage") != null &&(boolean)request.getAttribute("lastPage")){
			pageIndex = pageIndexArray[pageNumber-1];
		}
	%>
	<jsp:useBean id='memSvc' scope='page' class='com.meetU.mem.model.MemService'/>
	<c:forEach var="omVO" items= "${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		
		
		 <div class="row align-items-center" ${(omVO.order_ID == param.order_ID) ?  'style="background-color:#AFEEEE;"':''}>
        <div class="col-3">
          
          <button class="btn btn-info" type="button" data-toggle="collapse" data-target="#${omVO.order_ID}" aria-expanded="false" aria-controls="collapseExample">
            	<h4>【${omVO.order_ID}】</h4>
          </button>
        

        </div>
        <div class="col-2"><h4>${omVO.price}</h4></div>
        <div class="col-2">
        	<form method='post' action='om.do' style="margin-bottom: 0px;">
					<button class="btn btn-outline-primary" type="submit">
			            	<h4>修改訂單</h4>
					</button>
					<input type='hidden' name='order_ID' value='${omVO.order_ID}'>
					<input type='hidden' name='action' value='getOne_For_Update'>				
					<input type='hidden' name='location' value='<%=request.getServletPath()%>'>
					<input type='hidden' name='mem_ID' value='<%=memVO.getMem_ID()%>'>
			</form>
		</div>
        <div class="col-2">
        
        	<form method='post' action='om.do' style="margin-bottom: 0px;">

					<button class="btn btn-${omVO.order_status != 0 ? 'success' : 'primary'}" type="submit" ${omVO.order_status != 0 ? 'disabled="disabled"' : ''}>
			            	<h4>${omVO.order_status != 0 ? "已出貨" : "出貨"}</h4>
					</button>

			<input type='hidden' name='order_ID' value='${omVO.order_ID}'>
			<input type='hidden' name='whichPage' value='${param.whichPage}'>
			<input type='hidden' name='location' value='<%=request.getServletPath()%>'>
			<input type='hidden' name='action' value='out'>				
			<input type='hidden' name='mem_ID' value='<%=memVO.getMem_ID()%>'>				
			</form>
        
        </div>
        <div class="col-3"><h4><fmt:formatDate value="${omVO.order_date}" pattern="yyyy-MM-dd HH:mm" /></h4></div>
      </div>
      
      <div class="row">
        <div class="collapse" id="${omVO.order_ID}">
          <div class="card card-body" style='border:0px;'>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">訂單編號</div>
      <div class="col-6 d">${omVO.order_ID}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">會員名稱</div>
      <div class="col-6 d">${memSvc.getOneMem(omVO.mem_ID).mem_name}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">訂單金額</div>
      <div class="col-6 d">${omVO.price}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">訂單日期</div>
      <div class="col-6 d"><fmt:formatDate value="${omVO.order_date}" pattern="yyyy-MM-dd HH:mm" /></div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">出貨地址</div>
      <div class="col-6 d">${omVO.out_add}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">收件人</div>
      <div class="col-6 d">${omVO.recipient}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">收件人電話</div>
      <div class="col-6 d">${omVO.phone}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">出貨日期</div>
      <div class="col-6 d">
      	${omVO.out_date != null ? '' : '<font style="color:red;">待出貨</font>'}
      	<fmt:formatDate value="${omVO.out_date}" pattern="yyyy-MM-dd HH:mm" />
      </div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">訂單狀態</div>
      <div class="col-6 d" style='color:${ords[omVO.order_status] == "待出貨" ? "#dc3545" :  "#28a745"};'>${ords[omVO.order_status]}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">備註</div>
      <div class="col-6 d">${omVO.tip}</div>
    </div>
    <div class="row inner border-bottom border-info ">
      <div class="col-6 t">查看明細</div>
      <div class="col-6 d">
      			<form method='post' action='listOdByOm.jsp' style="margin-bottom: 0px;">
					<input type='submit' value='查看明細' class='btn btn-outline-success'>
					<input type='hidden' name='order_ID' value='${omVO.order_ID}'>
				</form></div>
    </div>
    
    
          </div>
        </div>

      </div>
		
		
		
		
		
		
	<hr>
	</c:forEach>
<%@ include file="page2.file" %>
<br> 
    </div>
    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
</html>