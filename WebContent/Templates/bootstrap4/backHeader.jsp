<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<script src="Templates/sweetalert-master/sweetalert.min.js"></script>
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
          <a class="dropdown-item" id="AUTH00010" href="http//www.google.com">帳號管理</a>
          
        </div>
      </li>

      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          商城管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00020" href="#">商品上架/下架</a>
          <a class="dropdown-item" id="AUTH00030" href="#">訂單管理</a>
          <a class="dropdown-item" id="AUTH00040" href="#">點數管理</a>
          
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          廣告管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00050" href="#">審核廣告</a>
          <a class="dropdown-item" id="AUTH00060" href="#">廣告排程</a>
        </div>
      </li>
      
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        檢舉管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00070" href="#">直播檢舉管理</a>
          <a class="dropdown-item" id="AUTH00080" href="#">聯誼檢舉管理</a>

        </div>
             <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
       員工管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" id="AUTH00090" href="#">員工權限管理</a>
          <a class="dropdown-item" id="AUTH00100" href="#">員工資料管理</a>
        </div>   
      </li>
    </ul>
    
    <form class="form-inline my-2 my-lg-0">
<!--     	<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#login"> -->
<!-- 		  	登入 -->
<!-- 		</button> -->
<%-- 	  <jsp:include page="/backLogin.jsp" /> --%>
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
  
    <script>
    var AUTHS = new Array();
    var AUTH00010 = document.getElementById("AUTH00010");
    var AUTH00020 = document.getElementById("AUTH00020");
    var AUTH00030 = document.getElementById("AUTH00030");
    var AUTH00040 = document.getElementById("AUTH00040");
    var AUTH00050 = document.getElementById("AUTH00050");
    var AUTH00060 = document.getElementById("AUTH00060");
    var AUTH00070 = document.getElementById("AUTH00070");
    var AUTH00080 = document.getElementById("AUTH00080");
    var AUTH00090 = document.getElementById("AUTH00090");
    var AUTH00100 = document.getElementById("AUTH00100");
    AUTHS.push(AUTH00010,AUTH00020,AUTH00030,AUTH00040,AUTH00050,
    		   AUTH00060,AUTH00070,AUTH00080,AUTH00090,AUTH00100);
    
    var len = AUTHS.length;
    for(var i = 0; i < len; i++){
    	AUTHS[i].classList.add("disabled");
    	//AUTHS[i].removeAttribute("href");
    }
    //alert(len);
    
    //AUTH00010.classList.add("disabled");
    //AUTH00020.classList.remove("disabled");
    
    </script>

    
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  </body>
</html>