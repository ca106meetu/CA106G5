<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!doctype html>
<html lang="en">
  <head>
  <style>
  	.meetULogo{
  		width:200px;
  		height:200px;
  	}
  </style>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/sweetalert2.all.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/sweetalert2.css">
  </head>
  <body>
    
    
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#"><img class='meetULogo' alt="" src="<%=request.getContextPath()%>/Templates/meetULogo.png"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          會員管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00010" href="<%=request.getContextPath()%>/back-end/mem/select_page.jsp">帳號管理</a>
          
        </div>
      </li>

      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          商城管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00020" href="<%=request.getContextPath()%>/FrontEnd/prod/selectPage.jsp">商品管理</a>
          <a class="dropdown-item" id="AUTH00030" href="<%=request.getContextPath()%>/back-end/om/selectPageOm.jsp">訂單管理</a>
          
          
        </div>
      </li>
      
         
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        檢舉管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
         
          <a class="dropdown-item mtPg" id="AUTH00040"  href="<%=request.getContextPath()%>/FrontEnd/meetupRep/MeetupRep.jsp">聯誼檢舉管理</a>

        </div>
             <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
       員工管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00050" href="<%=request.getContextPath()%>/back-end/emp/select_page.jsp">員工資料管理</a>
        </div>   
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
       直播管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00060" href="<%=request.getContextPath()%>/FrontEnd/live/selectPage.jsp">直播主管理</a>
          <a class="dropdown-item" id="AUTH00070" href="#">貼圖管理</a>
        </div>   
      </li>
    </ul>
    <div ${empVO != null ? "style='display:inline-block'" : "style='display:none'"} ><h3>${empVO.emp_name} 您好</h3></div>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/BackLoginHandler" >
    	<input type="hidden" name="action" value="back_logout">
    	<button id="loginOut" type="submit" class="btn btn-outline-success btn-lg" ${empVO != null ? "style='display:inline'" : "style='display:none'"}>登出</button>
    </FORM>
  </div>
</nav>
  
<script>

setInterval("setAuthsFunc();", 1000);


    var AUTHS = new Array();
    var AUTH00010 = document.getElementById("AUTH00010");
    var AUTH00020 = document.getElementById("AUTH00020");
    var AUTH00030 = document.getElementById("AUTH00030");
    var AUTH00040 = document.getElementById("AUTH00040");
    var AUTH00050 = document.getElementById("AUTH00050");
    var AUTH00060 = document.getElementById("AUTH00060");
    var AUTH00070 = document.getElementById("AUTH00070");

    
    //var loginOut = document.getElementById('loginOut').style.display = 'none';
    
    AUTHS.push(AUTH00010,AUTH00020,AUTH00030,AUTH00040,AUTH00050,
 		   AUTH00060,AUTH00070); 
 function setAuthsFunc(){    
    var len = AUTHS.length;
    for(var i = 0; i < len; i++){
    	AUTHS[i].style.display="none";
    	AUTHS[i].classList.add("disabled");
    }
    
    //loginOut.style.display = "none";
    
<% 
	List<String> auth_IDs = (List<String>)session.getAttribute("auth_IDs");
	if(auth_IDs != null){
		for(String auth: auth_IDs){
 %> 
 		<%=auth%>.style.display="inline";
 		<%=auth%>.classList.remove("disabled"); 
 <% 
 		}
 	}
 %> 
}

 </script>
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  </body> 
</html>