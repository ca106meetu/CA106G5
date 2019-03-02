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
          <a class="dropdown-item" href="#">帳號管理</a>
          <a class="dropdown-item" href="#">權限管理</a>
        </div>
      </li>

      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          商城管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">商品上架/下架</a>
          <a class="dropdown-item" href="#">限時活動管理</a>
          <a class="dropdown-item" href="#">訂單管理</a>
          <a class="dropdown-item" href="#">點數管理</a>
          <a class="dropdown-item" href="#">退貨管理</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          廣告管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">審核廣告</a>
          <a class="dropdown-item" href="#">廣告排程</a>
        </div>
      </li>
      
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        檢舉管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">直播檢舉管理</a>
          <a class="dropdown-item" href="#">聯誼檢舉管理</a>
          <a class="dropdown-item" href="#">塗鴉牆檢舉管理</a>
          <a class="dropdown-item" href="#">社團檢舉管理</a>
        </div>
             <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
       員工管理
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">員工登入/出</a>
          <a class="dropdown-item" href="#">員工權限管理</a>
          <a class="dropdown-item" href="#">員工資料管理</a>
        </div>   
      </li>
    </ul>
    
    <form class="form-inline my-2 my-lg-0">
    	<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#login">
		  	登入
		</button>
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<div class="modal fade" id="login" tabindex="-1" action="loginhandler" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="loginLabel">登入</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form  METHOD="post" ACTION="backLogin.do" name="form1">
  	      <div class="form-group">
   		     <label for="InputEmp_ID">員工ID</label>
   		     <input type="text" name="emp_ID" class="form-control" id="InputEmp_ID" aria-describedby="ACCHelp" placeholder="輸入您的帳號">
   		     <!-- <small id="ACCHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
 		  </div>
	     <div class="form-group">
	         <label for="InputEmp_pw">員工密碼</label>
	         <input type="password" name="emp_pw" class="form-control" id="InputEmp_pw" placeholder="輸入您的密碼">
	     </div>
	   <button type="submit" class="btn btn-success">登入</button>
	 </form>
      </div>
      <div class="modal-footer">
       <!--  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-success">Save changes</button> -->
      </div>
    </div>
  </div>
</div>   
    
    
    
    
    
    
    
    
    
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  </body>
</html>