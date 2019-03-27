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
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

    <title>直播主播放間</title>  
    
 <style>  
 
/*  ----------------------------松松加----------------------- */
 .docker{
 	width:100%;
 	margin:0;
 	padding:0;
 	color: white;
 }
 
 .sticker{
 	width:100%;
 }
/*  ----------------------------------------------------- */
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
    	background-color: white;
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



<style>
            .videoContainer {
                position: relative;
                width:  800px;
                height: 600px;
                margin:auto;
                
            }
            .videoContainer video {
                position: absolute;
                width: 100%;
                height: 100%;
            }
            .volume_bar {
                position: absolute;
                width: 5px;
                height: 0px;
                right: 0px;
                bottom: 0px;
                background-color: cyan;
            }
        </style>

    
  </head>
  <body onload="connect();" onunload="disconnect();">
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
    
   
			
		
    

    <div class="container-fluid">
			<div class="row">
				<div class="col-8">
				
				<div class="jumbotron" style="margin-bottom: 0rem">
                <h1 class="display-3"><%= host_name%>的播放間
 <button style="${(memVO.mem_ID eq host_ID) ? '': 'display:none' }"  class='btn btn-success' onclick='connect2();'>直播推播</button>
               		<a href='<%=request.getContextPath()%>/FrontEnd/live/liveHome.jsp'>
					<img src="<%=request.getContextPath()%>/FrontEnd/live/images/back1.gif"	width="100" height="32">
					</a>
                </h1>
  				<hr class="my-4">
  				<div class="videoContainer">
           			<video id="localVideo" style="height: 600px;" oncontextmenu="return false;"></video>
            		<div id="localVolume" class="volume_bar"></div>
        		</div>
					
				</div>
				</div>
				
<div class="col-4">	
				
<!-- ------------------------------------------松松改--------------------------------------				 -->
				
<jsp:useBean id="sSvc" scope="page" class="com.meetU.stick.model.StickService"/>
				 <div class="jumbotron" style="margin-bottom: 0rem">
  				<h1 class="display-3"> 聊天室</h1>
  				<hr class="my-4">
  				 <h3 id="statusOutput" class="statusOutput"></h3>
        <div id="messagesArea" class="panel message-area"  ></div>
        <div class="panel input-area">
            <div class='container-fluid'>
            <div id='userName' style='display:none;'>${memVO.mem_name}</div>
            <div id='mem_ID' style='display:none;'>${memVO.mem_ID}</div>
            <div id='host_ID' style='display:none;'>${host_ID}</div>
            <div id="userNa" class="row" >
            
            	<c:forEach var="sVO" items="${sSvc.all}" begin="0" end="${sSvc.all.size()}">
            		<div class='col-1 docker'>
            			<img class='sticker' src='<%=request.getContextPath()%>/ShowPic?STICK_ID=${sVO.stick_ID}' onclick='sendStick("${sVO.stick_ID}");'>
            		</div>
				</c:forEach>
			
            </div>
            </div>	
            
            
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
	        </div>
  			</div>

<!-- --------------------------------------------------------------------------------------- -->
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
<!-- 	   Button trigger modal -->
<!-- 	   <div id='live_rep'> -->
<!-- <input  type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" value="檢舉直播主"></div> -->



 <!-- Modal --> 
<!-- <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!--   <div class="modal-dialog" role="document"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h5 class="modal-title" id="exampleModalLabel">Modal title</h5> -->
<!--         <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<!--         ... -->
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!--         <button type="button" class="btn btn-primary">Save changes</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->
	
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>

  
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
    var MyPoint = "<%=request.getContextPath()%>/live_chatHome/<%=host_ID%>";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "wss://" + window.location.host + MyPoint;
    
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
	        
	        if("stick" == jsonObj.action){
				var message = "<font size='4px' color='green'>"+jsonObj.userName + ": </font>" +
						"<img style='width:30px;' src='data:img/png;base64,"+ jsonObj.stick + "'>"+"<br>"+"<font color='#EE7700'>"+jsonObj.chat_date+"</font>" +"<br>";       
	        	
	        }else{
		        var message ="<font size='4px' color='green'>"+ jsonObj.userName + ": </font>" + "<font size='5px'>"+jsonObj.message + "</font>"+"<br>"+"<font color='#EE7700'>"+jsonObj.chat_date+"</font>" +"<br>";
	        }
	        messagesArea.innerHTML = messagesArea.innerHTML + message;
	        
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	

	var inputUserName = document.getElementById("userName");
	var inputmem_ID = document.getElementById("mem_ID");
	var inputhost_ID = document.getElementById("host_ID");
// --------------------------------松松加---------------------------------------
	function sendStick(stick_ID){
		var userName = inputUserName.innerText.trim();
		var mem_ID = inputmem_ID.innerText.trim();
		var host_ID = inputhost_ID.innerText.trim();
		if(allowUser()){
		var jsonObj = {"action" : "stick", "stick_ID" : stick_ID,"userName" :userName,"mem_ID" :mem_ID,"host_ID" :host_ID};
		webSocket.send(JSON.stringify(jsonObj));
		} else{
			$('#login').modal('show');
			 return;
		}
		
	}
// 	------------------------------------------------------------------------
	function sendMessage() {
	    var userName = inputUserName.innerText.trim();
	    var mem_ID = inputmem_ID.innerText.trim();
	    var host_ID = inputhost_ID.innerText.trim();
	
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
	        var jsonObj = {"mem_ID" :mem_ID,"userName" : userName, "message" : message, "action":"text","host_ID":host_ID};
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
    var MyPoint2 = "<%=request.getContextPath()%>/MyEchoServerlive/smoke/205";
    var host2 = window.location.host;
    var path2 = window.location.pathname;
    
    var endPointURL2 = "wss://" + window.location.host + MyPoint2;
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





        
        <script src="<%=request.getContextPath() %>/SimpleWebRTC-master/simplewebrtc.bundle.js"></script>
        <script>
            // grab the room from the URL
            var room = location.search && location.search.split('?')[1];

            // create our webrtc connection
            var webrtc = new SimpleWebRTC({
            	// the id/element dom element that will hold "our" video
                localVideoEl: 'localVideo',
                // the id/element dom element that will hold remote videos
                remoteVideosEl: '',
                // immediately ask for camera access
                autoRequestMedia: true,
                debug: false,
                detectSpeakingEvents: true,
            });

            // when it's ready, join if we got a room from the URL
            webrtc.on('readyToCall', function () {
                // you can name it anything
                if (room) webrtc.joinRoom(room);
            });

            function showVolume(el, volume) {
                if (!el) return;
                if (volume < -45) { // vary between -45 and -20
                    el.style.height = '0px';
                } else if (volume > -20) {
                    el.style.height = '100%';
                } else {
                    el.style.height = '' + Math.floor((volume + 100) * 100 / 25 - 220) + '%';
                }
            }
            webrtc.on('channelMessage', function (peer, label, data) {
                if (data.type == 'volume') {
                    showVolume(document.getElementById('volume_' + peer.id), data.volume);
                }
            });
            webrtc.on('videoAdded', function (video, peer) {
                console.log('video added', peer);
                var remotes = document.getElementById('remotes');
                if (remotes) {
                    var d = document.createElement('div');
                    d.className = 'videoContainer';
                    d.id = 'container_' + webrtc.getDomId(peer);
                    d.appendChild(video);
                    var vol = document.createElement('div');
                    vol.id = 'volume_' + peer.id;
                    vol.className = 'volume_bar';
                    video.onclick = function () {
                        video.style.width = video.videoWidth + 'px';
                        video.style.height = video.videoHeight + 'px';
                    };
                    d.appendChild(vol);
                    remotes.appendChild(d);
                }
            });
            webrtc.on('videoRemoved', function (video, peer) {
                console.log('video removed ', peer);
                var remotes = document.getElementById('remotes');
                var el = document.getElementById('container_' + webrtc.getDomId(peer));
                if (remotes && el) {
                    remotes.removeChild(el);
                }
            });
            webrtc.on('volumeChange', function (volume, treshold) {
                //console.log('own volume', volume);
                showVolume(document.getElementById('localVolume'), volume);
            });

            // Since we use this twice we put it here
//             function setRoom(name) {
//                 $('form').remove();
//                 $('h1').text(name);
//                 $('#subTitle').text('Link to join: ' + location.href);
//                 $('body').addClass('active');
//             }

            if (room) {
                setRoom(room);
            } else {
                $('form').submit(function () {
                    var val = $('#sessionInput').val().toLowerCase().replace(/\s/g, '-').replace(/[^A-Za-z0-9_\-]/g, '');
                    webrtc.createRoom(val, function (err, name) {
                        console.log(' create room cb', arguments);
                    
                        var newUrl = location.pathname + '?' + name;
                        if (!err) {
                            history.replaceState({foo: 'bar'}, null, newUrl);
                            setRoom(name);
                        } else {
                            console.log(err);
                        }
                    });
                    return false;          
                });
            }

            var button = $('#screenShareButton'),
                setButton = function (bool) {
                    button.text(bool ? 'share screen' : 'stop sharing');
                };
            webrtc.on('localScreenStopped', function () {
                setButton(true);
            });

            setButton(true);

            button.click(function () {
                if (webrtc.getLocalScreen()) {
                    webrtc.stopScreenShare();
                    setButton(true);
                } else {
                    webrtc.shareScreen(function (err) {
                        if (err) {
                            setButton(true);
                        } else {
                            setButton(false);
                        }
                    });
                    
                }
            });
        </script>
        
</body>
</html>