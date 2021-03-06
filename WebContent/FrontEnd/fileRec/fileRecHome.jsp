<%@page import="com.meetU.mem.model.MemVO"%>
<%@page import="java.util.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String host_ID =request.getParameter("host_ID");
    pageContext.setAttribute("host_ID", host_ID);
    
	FileRecService fileRecSvc = new FileRecService();
	List<FileRecVO> list = fileRecSvc.getOneFileRec(host_ID);
	pageContext.setAttribute("list", list);
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	
	
%>
<!doctype html>
<html>

<head>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<title>直播主影片</title>
<style>
.btn-primary {
    color: #fff;
    background-color:#0078ae;
    border-color:#0078ae;
} 
#live_like {
	position: fixed;
	right: 0;
	top: 20%;
	width: 8em;
	margin-top: -2.5em;
}
#live_like2 {
	position: fixed;
	right: 0;
	top: 25%;
	width: 8em;
	margin-top: -2.5em;
}

html,body {	
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;   
	 
}

.pic {
	width: 172.5px;
	height: 230px;
}
.table {
   
    text-align: center;
}
.btn-warning {
   
    margin: 5px;
}



.canclebutton {
	display: none;
}
</style>
</head>
<body>
<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
	<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
	
	
	
	
	
		<div class="container-fluid">
			<div class="row">
				<!-- 第一行 -->
				<div class="col-1"><div class="xxx"></div></div>
				<div class="col-10">
				<table class="table">
 				 <thead class="table-active">
		<tr>
			<th scope="col">
				<h3>直播主影片</h3>
				<h4>
					<a href='<%=request.getContextPath()%>/FrontEnd/live/liveHome2.jsp?host_ID=<%=host_ID%>'>
						<img
						src="<%=request.getContextPath()%>/FrontEnd/live/images/back1.gif"
						width="100" height="32">
					</a>

<button style="${(memVO.mem_ID eq host_ID) ? '': 'display:none' }"  class='btn btn-success' onclick='openbtn();'>編輯影片</button>
<button style="${(memVO.mem_ID eq host_ID) ? '': 'display:none' }" type="submit" form="addfileRec" value="Submit" class='btn btn-info canclebutton' >新增影片</button>

<form action="<%=request.getContextPath()%>/FrontEnd/fileRec/addfileRecHome.jsp" method='post' id="addfileRec">
		    <input type="hidden" name="host_ID"	value="${host_ID}">
		    <input type='hidden' name='action' value='insert'>
</form>


				</h4>
			</th>
		</tr>
	</table>
				
				
				
				</div></div>
				<div class="col-1"><div class="xxx"></div></div>
			
		
		</div>
	
	
	
	<div class="container-fluid">
			<div class="row">
				<!-- 第一行 -->
				<div class="col-1"><div class="xxx"></div></div>
				<div class="col-10">
				<table class="table">
	<%@ include file="page1.file"%>
  <thead class="table-active">
    <tr>
      <th scope="col">直播主姓名</th>
      <th scope="col">影片名稱</th>
      <th scope="col">影片描述</th>
      <th scope="col">影片內容</th>
      <th scope="col">影片上架時間</th>
      <th class='canclebutton'>修改影片</th>
    </tr>
  </thead>
  
  

		<jsp:useBean id="memSvc" scope="page"
			class="com.meetU.mem.model.MemService" />




		<c:forEach var="fileRecVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
		

		
  
  
  
  <tbody>
    <tr>
      <th scope="row">${memSvc.getOneMem(fileRecVO.host_ID).mem_name}</th>
      <td>${fileRecVO.file_name}</td>
      <td>${fileRecVO.live_des}</td>
      <td><iframe height="100%" src="${fileRecVO.file_cont}" allowfullscreen></iframe></td>
      <td><fmt:formatDate value="${fileRecVO.file_date}" pattern="yyyy-MM-dd HH:mm" /></td>
      <td  class='canclebutton'>
            <button style="${(memVO.mem_ID eq host_ID) ? '': 'display:none' }" type="submit" form="${fileRecVO.file_ID}" value="Submit"  class='btn btn-warning canclebutton' >修改影片</button>
				<form action="fileRec.do" method='post' id="${fileRecVO.file_ID}">
					 <input type="hidden" name="file_ID"	value="${fileRecVO.file_ID}">
		   			 <input type="hidden" name="host_ID"	value="${host_ID}">
		  			 <input type='hidden' name='action' value='getOne_For_Update'>
				</form>
				
				<button style="${(memVO.mem_ID eq host_ID) ? '': 'display:none' }" type="submit" form="${fileRecVO.file_ID}delet" value="Submit"  class='btn btn-danger canclebutton' >刪除影片</button>
				<form action="fileRec.do" method='post' id="${fileRecVO.file_ID}delet">
					 <input type="hidden" name="file_ID"	value="${fileRecVO.file_ID}">
					 <input type="hidden" name="host_ID"	value="${host_ID}">
		  			 <input type='hidden' name='action' value='delete'>
				</form>
	  </td>
     
    </tr>
  
  </tbody>
  </c:forEach>
  <%@ include file="page2.file"%>
</table>
				</div></div>
				<div class="col-1"><div class="xxx"></div></div>
				
			</div>
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	

	
	
	<jsp:useBean id="live_likeSvc" scope="page" class="com.meetU.live_like.model.Live_likeService"/>
	<div id='live_like'>
		<input class="${live_likeSvc.getOneLive_like2(memVO.mem_ID,param.host_ID ) != null ? 'btn btn-danger ' : 'btn btn-primary ' } live_like"  type="submit" value="${live_likeSvc.getOneLive_like2(memVO.mem_ID,param.host_ID ) != null ? '退訂直播主' : '收藏直播主' }">
		<input type="hidden" name="host_ID" value="${param.host_ID}">
		<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
	</div>
	
<!-- 	<div id='live_rep'> -->
<!-- 		<a class="btn btn-primary" href="" role="button">檢舉直播主</a> -->
<!-- 	</div> -->
	
	<div id='live_like2'>
		<form action="<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do" method='post' onsubmit='return allowUser();'>
			<input class="btn btn-primary  live3CheckOut"  type="submit" value="看我的收藏" >
		    <input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
		    <input type='hidden' name='action' value='getOne_For_Display'>
		</form>
	</div>
	
	


	<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
<script>

function openbtn(){
	
	$(".canclebutton" ).show();
	} 



//以下登入
$('.live_like').click(function(){
		 if(!allowUser()){ 
			 <%session.setAttribute("location", request.getRequestURI());%>
			 $('#login').modal('show');
			 return;
		 }else{
			 if($(this).val() == "收藏直播主"){
 				 
				 $.ajax({
					 type: "POST",
					 url: "<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do",
					 data: {"host_ID":$(this).next().attr('value'), 
						 	"action":"insert", 
						 	"mem_ID":$(this).next().next().attr('value')},
					 dataType: "json",
					 success: function(){
						 
						 $(".live_like").val("退訂直播主");
						 $(".live_like").attr("class","btn btn-danger live_like");
						
						
						 alert("成功加入收藏");
						},
						
		             error: function(){alert("愛你唷,不過錯了")}
			         });
				 
				 
			 }else if($(this).val() == "退訂直播主"){
				 
				 $.ajax({
					 type: "POST",
					 url: "<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do",
					 data: {"host_ID":$(this).next().attr('value'), 
						 	"action":"delete", 
						 	"mem_ID":$(this).next().next().attr('value')},
							 dataType: "json",
					 success: function(){
						 
						 $(".live_like").val("收藏直播主");
						 $(".live_like").attr("class","btn btn-primary live_like");
													
						 alert("成功取消收藏");
						},
		             error: function(){alert("愛你唷,不過錯了2")}
			         });				
			 }
			
		 } 
});

$('.live3CheckOut').click(function(){
	 if(!allowUser()){ 
		 <%session.setAttribute("location", request.getRequestURI());%>
		 $('#login').modal('show');
		 return;
	 }else{
		
	 } 
});
</script>
</html>

