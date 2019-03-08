<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
  <style>
  	.meetULogo{
  		width:200px;
  		height:200px;
  	}
  	
  	*{
  		font-family:微軟正黑體;
  	
  	}
  </style>
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->

  </head>
  <body>
    
    
<nav class="navbar navbar-expand-lg navbar-light bg-information">
  <a class="navbar-brand" href="#"><img class='meetULogo' alt="" src="<%=request.getContextPath()%>/Templates/meetULogoback.png"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          購物商城
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp">瀏覽商品</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/cart/cart.jsp">查看購物車</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/od/listMyOm.jsp">我的訂單</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          聯誼活動
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/meetup/meetupHomePg.jsp">瀏覽聯誼</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/meetup/runMeetup.jsp">主持聯誼</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/meetup/AllMyMeetup.jsp">我已參加</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/meetup/AllMyLikeMeetup.jsp">我的收藏</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/meetup/MyMeetupRep.jsp">我的檢舉</a>
        </div>
      </li>
      
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      	<!-- Button trigger modal -->
		<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#login">
		  登入
		</button>
		<button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#register">
		  註冊
		</button>
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<!-- Modal -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="loginLabel">登入</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form METHOD="post" ACTION="frontLogin.do" >
  	      <div class="form-group">
   		     <label for="exampleInputACC">帳號</label>
   		     <input type="email" class="form-control" id="exampleInputACC" aria-describedby="ACCHelp" placeholder="輸入您的帳號">
   		     <!-- <small id="ACCHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
 		  </div>
	     <div class="form-group">
	         <label for="exampleInputPassword1">密碼</label>
	         <input type="password" class="form-control" id="exampleInputPassword1" placeholder="輸入您的密碼">
	     </div>
	     <!-- <div class="form-check">
	         <input type="checkbox" class="form-check-input" id="exampleCheck1">
	         <label class="form-check-label" for="exampleCheck1">Check me out</label>
	     </div> -->
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

<div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="registerLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="registerLabel">註冊</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form METHOD="post" ACTION="registerLogin.do">
  	      <div class="form-group">
   		     <label for="exampleInputACC">會員帳號</label>
   		     <input type="email" class="form-control" id="exampleInputACC" aria-describedby="ACCHelp" placeholder="輸入您的帳號">
   		     <!-- <small id="ACCHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
 		  </div>
	     <div class="form-group">
	         <label for="InputPassword">會員密碼</label>
	         <input type="password" class="form-control" id="InputPassword" placeholder="輸入您的密碼">
	     </div>
	     <div class="form-group">
	         <label for="InputEmail">E-mail</label>
	         <input type="password" class="form-control" id="InputEmail" placeholder="輸入您的E-mail">
	     </div>
	     <div class="form-group">
	         <label for="InputAddress">居住地</label>
	         <input type="password" class="form-control" id="InputAddress" placeholder="輸入您的居住地">
	     </div>
	     
	     <!-- <div class="form-check">
	         <input type="checkbox" class="form-check-input" id="exampleCheck1">
	         <label class="form-check-label" for="exampleCheck1">Check me out</label>
	     </div> -->
	   <button type="submit" class="btn btn-success">註冊</button>
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