<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@page import="com.meetU.live_like.model.*"%>
<%@page import="com.meetU.mem.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String host_ID = request.getParameter("host_ID");
	pageContext.setAttribute("host_ID", host_ID);
	
   
    MemService memSvc = new MemService();
    String host_name = memSvc.getOneMem(host_ID).getMem_name();
    pageContext.setAttribute("host_name",host_name);
    MemVO memVO = (MemVO)session.getAttribute("memVO");
%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

    <title>直播影片間</title>  
    
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
 #fileRec {
	position: fixed;
	right: 0;
	top: 30%;
	width: 8em;
	margin-top: -2.5em;
}
#live_rep {
	position: fixed;
	right: 0;
	top: 35%;
	width: 8em;
	margin-top: -2.5em;
	
}

.button {
    border: none;
    padding: 5px 5px;
    border-radius: 5px;
    width: 60px;
    background: orange;
    box-shadow: inset 0 0 10px #000000;
    font-weight: bold;
}
.button:hover {
    background: #77DDFF;
}
#userName {
	    background: white;
		box-shadow: inset 0 0 10px #00568c;
		margin: 0.5em 0em 0.5em 0.5em;
   		width: 15%;
   		height:35PX;
}
#message {
    min-width: 50%;
    max-width: 60%;
}
.statusOutput{
	background: #0078ae;
    text-align:center;
    color: #ffffff;
    border: 1px solid grey;
    padding: 0.2em;
    box-shadow: 0 0 5px #000000;
    
}
html,body {	
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;   
	 
}

.panel {
    border: 2px solid #0078ae;
    border-radius: 5px;
    width:100%;
}
.message-area {
    height: 460px;
    resize: none;
    box-sizing: border-box;
    overflow: auto;
}
.input-area {
    background: #0078ae;
    box-shadow: inset 0 0 10px #00568c;
}

.input-area input {
    margin: 0.5em 0em 0.5em 0.5em;
}
.text-field {
    border: 1px solid grey;
    padding: 0.2em;
    box-shadow: 0 0 5px #000000;
}

</style>
    
  </head>
  <body onload="connect();" onunload="disconnect();">
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
   
			
		
    

    <div class="container-fluid">
			<div class="row">
				<div class="col-8">
				
				<div class="jumbotron" style="margin-bottom: 0rem";>
                <h1 class="display-3"><%= host_name%>的直播間
 <button style="${(memVO.mem_ID eq host_ID) ? '': 'display:none' }"  class='btn btn-success' onclick='connect2();'>直播推播</button>
               		<a href='<%=request.getContextPath()%>/FrontEnd/live/liveHome.jsp'>
					<img src="<%=request.getContextPath()%>/FrontEnd/live/images/back1.gif"	width="100" height="32">
					</a>
                </h1>
  				<hr class="my-4">
  				<iframe width="95%" height="600" src="https://www.youtube.com/embed/60ZHM9nPQps" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				</div>
				</div>
				
				<div class="col-4">	
				
				
				 <div class="jumbotron" style="margin-bottom: 0rem";>
  				<h1 class="display-3"> 聊天室</h1>
  				<hr class="my-4">
  				 <h3 id="statusOutput" class="statusOutput"></h3>
        <textarea id="messagesArea" class="panel message-area" readonly ></textarea>
        <div class="panel input-area">
            <div id="userName" class="text-field" style='display:none'>${memVO.mem_name}</div><br><br>
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
	        </div>
  			</div>
			</div>
			</div>
			</div>
			
	
	<jsp:useBean id="live_likeSvc" scope="page" class="com.meetU.live_like.model.Live_likeService"/>	
		
	<div id='live_like'>
		<input class="${live_likeSvc.getOneLive_like2(memVO.mem_ID,param.host_ID ) != null ? 'btn btn-danger ' : 'btn btn-primary ' } live_like"  type="submit" value="${live_likeSvc.getOneLive_like2(memVO.mem_ID,param.host_ID ) != null ? '退訂直播主' : '收藏直播主' }">
		<input type="hidden" name="host_ID" value="${param.host_ID}">
		<input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
	</div>
	
	<div id='live_like2'>
		<form action="<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do" method='post' onsubmit='return allowUser();'>
			<input class="btn btn-primary live2CheckOut"  type="submit" value="看我的收藏" >
		    <input type="hidden" name="mem_ID"	value="${memVO.mem_ID}">
		    <input type='hidden' name='action' value='getOne_For_Display'>
		</form>
	</div>
	
	<div id='fileRec'>
		<form action="<%=request.getContextPath()%>/FrontEnd/fileRec/fileRecHome.jsp" method='post'>
			<input type='hidden' name='host_ID' value='<%=host_ID%>'>
			<input type='submit' class="btn btn-primary" value='看其他影片'>
		</form>
	</div>
	
	<!-- 檢舉跳出 -->
	   <!-- Button trigger modal -->
	   <div id='live_rep'>
<input  type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" value="檢舉直播主"></div>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
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
  
 <script>
 
 
 
//  $(document).ready(function(){
// 		$(".live_like").click(function(){
			 
// 			 if($(this).val() == "收藏直播主"){
				 				 
// 				 $.ajax({
// 					 type: "POST",
<%-- 					 url: "<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do", --%>
// 					 data: {"host_ID":$(this).next().attr('value'), 
// 						 	"action":"insert", 
// 						 	"mem_ID":$(this).next().next().attr('value')},
// 					 dataType: "json",
// 					 success: function(){
						 
// 						 $(".live_like").val("退訂直播主");
// 						 $(".live_like").attr("class","btn btn-danger live_like");
						
						
// 						 alert("成功加入收藏");
// 						},
						
// 		             error: function(){alert("愛你唷,不過錯了")}
// 			         });
				 
				 
// 			 }else if($(this).val() == "退訂直播主"){
				 
// 				 $.ajax({
// 					 type: "POST",
<%-- 					 url: "<%=request.getContextPath()%>/FrontEnd/live_like/live_like.do", --%>
// 					 data: {"host_ID":$(this).next().attr('value'), 
// 						 	"action":"delete", 
// 						 	"mem_ID":$(this).next().next().attr('value')},
// 							 dataType: "json",
// 					 success: function(){
						 
// 						 $(".live_like").val("收藏直播主");
// 						 $(".live_like").attr("class","btn btn-primary live_like");
													
// 						 alert("成功取消收藏");
// 						},
// 		             error: function(){alert("愛你唷,不過錯了2")}
// 			         });				
// 			 }
// 		 });
// 	})
 
 

    //以下聊天室
    var MyPoint = "/live_chatHome/<%=host_ID%>";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + "/SmokeChen" + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			var userName = inputUserName.innerText.trim();
			 if (userName === ""){
				 updateStatus("尚未登入聊天室");
			    }else{
			updateStatus("${memVO.mem_name}"+"進入聊天室");
			}
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        messagesArea.value = messagesArea.value + message;
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	var inputUserName = document.getElementById("userName");

	
	function sendMessage() {
	    var userName = inputUserName.innerText.trim();
	    if (userName === ""){
	    	$('#login').modal('show');
			 return;
	    }
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}

	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
	//以上聊天室
	
   //以下推播
    var MyPoint2 = "/MyEchoServerlive/smoke/205";
    var host2 = window.location.host;
    var path2 = window.location.pathname;
    
    var endPointURL2 = "ws://" + window.location.host + "/SmokeChen" + MyPoint2;
	var webSocket2;
	
	function connect2() {
		// 建立 websocket 物件
		webSocket2 = new WebSocket(endPointURL2);
		
		webSocket2.onopen = function(event) {
			
			var host_ID = "${host_ID}";
			var host_name ="${host_name}";
			var jsonObj2 = {"host_ID" :host_ID ,"host_name" :host_name, "message" : "開台囉!快來觀看!!" };
	        webSocket2.send(JSON.stringify(jsonObj2));
		};
		
		webSocket2.onmessage = function(event) {
			
			
			
		};
		
		webSocket2.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
		
	
	}
	//以上推播
	
	//以下登入
	$('.live2CheckOut').click(function(){
		 if(!allowUser()){ 
			 <%session.setAttribute("location", request.getRequestURI());%>
			 $('#login').modal('show');
			 return;
		 }else{
			
		 } 
 });
	
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
	//以上登入	
	
    
</script> 
  
  
</html>