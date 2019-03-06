<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@page import="com.meetU.mem.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String host_ID = request.getParameter("host_ID");
    MemService memSvc = new MemService();
    String host_name = memSvc.getOneMem(host_ID).getMem_name();
%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

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
	top: 10%;
	width: 8em;
	margin-top: -2.5em;
}
 #fileRec {
	position: fixed;
	right: 0;
	top: 15%;
	width: 8em;
	margin-top: -2.5em;
}
#live_rep {
	position: fixed;
	right: 0;
	top: 20%;
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
    width: 20%;
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
            <input id="userName" class="text-field" type="text" placeholder="使用者名稱"/><br>
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
	        </div>
  				
  				</div>

	   
			</div>
			</div>
			</div>
			
	
	
	<div id='live_like'>
		<a class="btn btn-primary" href="" role="button">收藏直播主</a>
	</div>
	<div id='fileRec'>
		
		<form action="<%=request.getContextPath()%>/FrontEnd/fileRec/fileRecHome.jsp" method='post'>
			<input type='hidden' name='host_ID' value='<%=host_ID%>'>
			<input type='submit' class="btn btn-primary" value='看其他影片'>
		</form>

	</div>
	<div id='live_rep'>
		<a class="btn btn-primary" href="" role="button">檢舉直播主</a>
	</div>
	
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  </body>
  
 <script>
    
    var MyPoint = "/live_chatHome/<%=host_ID%>/205";
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
			updateStatus("WebSocket 成功連線");
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
	inputUserName.focus();
	
	function sendMessage() {
	    var userName = inputUserName.value.trim();
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
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
    
</script> 
  
  
</html>