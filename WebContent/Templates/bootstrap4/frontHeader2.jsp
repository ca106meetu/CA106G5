<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.mem.model.*"%>
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
	<script src='<%=request.getContextPath()%>/Templates/bootstrap4/js/jquery-3.2.1.min.js'></script>
    <script src='<%=request.getContextPath()%>/Templates/bootstrap4/js/sweetalert2.all.js'></script>
    <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/Templates/bootstrap4/css/sweetalert2.css'>
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
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/meetupLike/AllMyLikeMeetup.jsp">我的收藏</a>
          <a class="dropdown-item" href="<%=request.getContextPath()%>/FrontEnd/meetup/MyMeetupRep.jsp">我的檢舉</a>
        </div>
      </li>
      
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
    
      	<!-- Button trigger modal -->
		<button type="button" id="btn_login" class="btn btn-outline-success" data-toggle="modal" data-target="#login" ${memVO != null ? "style='display:none'" : ''}>
		  登入
		</button>
		<button type="button" id="btn_register" class="btn btn-outline-success" data-toggle="modal" data-target="#register"  ${memVO != null ? "style='display:none'" : ''} >
		  註冊
		</button>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lorenTest" >
    		<input type="hidden" name="action" value="front_logout">
    		<button id="btn_logOut" type="submit" class="btn btn-outline-success" ${memVO != null ? "style='display:inline'" : "style='display:none'"} >登出</button>
    	</FORM>
<!--       <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search"> -->
<!--       <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button> -->
    

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
<%--        <form METHOD="post" ACTION="<%=request.getContextPath()%>/lorenTest" > --%>
  	      <div class="form-group">
   		     <label for="login_text_mem_acc">帳號</label>
   		     <input type="text" name='mem_acc' class="form-control mem_acc" id="login_text_mem_acc" aria-describedby="ACCHelp" placeholder="輸入您的帳號">
   		     <small id="login_text_mem_acc_answer" class="form-text text-muted">請輸入您的帳號</small>
 		  </div>
	     <div class="form-group">
	         <label for="login_text_mem_pw">密碼</label>
	         <input type="password" name='mem_pw' class="form-control mem_pw" id="login_text_mem_pw" placeholder="輸入您的密碼">
	         <small id="login_text_mem_pw_answer" class="form-text text-muted">請輸入您的密碼</small>
	     </div>
	     
	   <button type="button" class="btn btn-success login" >登入</button>
<!-- 	 </form> -->
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
       <form METHOD="post" ACTION="<%=request.getContextPath()%>/lorenTest">
  	      <div class="form-group">
   		     <label for="register_text_mem_acc">會員帳號</label>
   		     <input type="text" name="register_text_mem_acc" class="form-control" id="register_text_mem_acc" aria-describedby="ACCHelp" placeholder="輸入您的帳號">
   		     <small id="register_text_mem_acc_answer" style="color:red;">*您的帳號名稱是什麼?</small>
 		  </div>
	     <div class="form-group">
	         <label for="register_text_mem_pw">會員密碼</label>
	         <input type="password" name="register_text_mem_pw"class="form-control" id="register_text_mem_pw" placeholder="輸入您的密碼">
	         <small id="register_text_mem_pw_answer" style="color:red;">*請輸入4個以上的英文或數字</small>
	     </div>
	     <div class="form-group">
	         <label for="register_text_mem_email">E-mail</label>
	         <input type="email" name="register_text_mem_email" class="form-control" id="register_text_mem_email" placeholder="輸入您的E-mail">
	         <small id="register_text_mem_email_answer" style="color:red;">*請輸入有效的電子郵件。</small>
	     </div>
	     <div class="form-group">
	         <label for="register_text_mem_addr">居住地</label>
    		 <select class="form-control" name="register_text_mem_addr" id="register_text_mem_addr">
        	 </select>
	     </div>
	    
	     <input type="hidden" name="action" value="register">
	   <button type="submit" class="btn btn-success" id="btnRegister" disabled>註冊</button>
	 </form>
	 </div>
      <div class="modal-footer">
       <!--  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-success">Save changes</button> -->
      </div>
    </div>
  </div>
</div>    
    
    
    <script>

    //*==============================================================*/
    var timer;
    var register_text_mem_acc_flag_G = false;
    var register_text_mem_pw_flag_G = false;
    var register_text_mem_email_flag_G = false;
    
	$(function(){
		
	    	$('.login').click(function(){
				 $.ajax({
					 type: "POST",
					 url: "<%=request.getContextPath()%>/lorenTest",
					 data: {"mem_acc":$(".mem_acc").val(),
						    "mem_pw":$(".mem_pw").val()},
					 dataType: "json",
					 success: function(data){
						 if(data.access == 'true'){
							$('#login').modal('hide');
							swal({
								title: "登入成功!",
								text: "歡迎您~",
								type:"success"}).then(function(){ 
							   		location.reload();
							   	}
							);
														
						}else{
							swal('你的帳號,密碼無效!','請您重新輸入帳號密碼','error');

						}
					 },
		             error: function(){
		             
		             }
		         });
			});
			
			
			$('#register_text_mem_acc').on('keyup', function(){
				if( $('#register_text_mem_acc').val().trim().length == 0){
					$('#register_text_mem_acc_answer').text('會員帳號: 請勿空白.');
					//register_text_mem_acc_flag = false;
				}else{
					$('#register_text_mem_acc_answer').text('');
					var register_text_mem_acc = $(this).val();
				}
				var register_text_mem_acc = $(this).val();
 				
					_debounce(function(){
						
						return getRegister_text_mem_acc(register_text_mem_acc, register_text_mem_acc_flag_G); 
					}, 500);
					

			});
		
			
			$('#register_text_mem_pw').on('keyup', function(){
				if($('#register_text_mem_pw').val().trim().length == 0){
					$('#register_text_mem_pw_answer').text('會員密碼: 請勿空白');

				}else{
					$('#register_text_mem_pw_answer').text('');
					
				}
				var register_text_mem_pw = $(this).val();
				_debounce(function(){
					
					return getRegister_text_mem_pw(register_text_mem_pw, register_text_mem_pw_flag_G); 
				}, 500);
			});
			
			
			$('#register_text_mem_email').on('keyup', function(){
				if($('#register_text_mem_email').val().trim().length == 0 ){
					$('#register_text_mem_email_answer').text('會員電子信箱: 請勿空白');
					
				}else{
					$('#register_text_mem_email_answer').text('');
					
				}
				var register_text_mem_email = $(this).val();
				_debounce(function(){ 
					
					return getRegister_text_mem_email(register_text_mem_email, register_text_mem_email_flag_G); 
				}, 500);
			});
			datas.map(function(data){
		        $('#register_text_mem_addr').append('<option value="'+ data.value +'">' + data.title + '</option>');
		    });
			 
		});
	
	function getRegister_text_mem_pw(register_text_mem_pw,register_text_mem_pw_flag){
		$('#register_text_mem_pw_answer').text('資料驗證中');
		$.ajax({
			url:'<%=request.getContextPath()%>/lorenTest',
			type:"POST",
			data:{ action:'askPw',
				register_text_mem_pw: $('#register_text_mem_pw').val().trim(),
				register_text_mem_pw_flag: register_text_mem_pw_flag },
			dataType: 'json',
			success: function(data){
				//alert(data.register_text_mem_pw_flag);
				register_text_mem_pw_flag_G = data.register_text_mem_pw_flag;
				$('#register_text_mem_pw_answer').text(data.register_text_mem_pw_answer);
				checkRegisterBtn();
// 				console.log(register_text_mem_pw_flag_G);
			},
			error: function(){
	           	 
            }
		});
	}
	
	function getRegister_text_mem_email(register_text_mem_email, register_text_mem_email_flag){
		$('#register_text_mem_email_answer').text('資料驗證中');
		$.ajax({
			url:'<%=request.getContextPath()%>/lorenTest',
			type:"POST",
			data:{ action:'askEmail',
				register_text_mem_email: $('#register_text_mem_email').val().trim(),
				register_text_mem_email_flag: register_text_mem_email_flag },
			dataType: 'json',
			success: function(data){
				//alert(register_text_mem_email_flag_G);
				$('#register_text_mem_email_answer').text(data.register_text_mem_email_answer);
				register_text_mem_email_flag_G = data.register_text_mem_email_flag;
				checkRegisterBtn();
				//alert(register_text_mem_email_flag_G);
			},
			error: function(){
	           	 
            }
		});
	}
	
	
	function getRegister_text_mem_acc(register_text_mem_acc, register_text_mem_acc_flag){
		$('#register_text_mem_acc_answer').text('資料驗證中');
		$.ajax({
			url:'<%=request.getContextPath()%>/lorenTest',
			type:"POST",
			data:{ action:'askACC', 
				   register_text_mem_acc: $('#register_text_mem_acc').val().trim(),
				   register_text_mem_acc_flag: register_text_mem_acc_flag},
			dataType: 'json',
			success: function(data){
// 				console.log(res.register_text_mem_acc_answer);
				//alert(data.register_text_mem_acc_answer);
				$('#register_text_mem_acc_answer').text(data.register_text_mem_acc_answer);
				register_text_mem_acc_flag_G = data.register_text_mem_acc_flag;
				//alert(register_text_mem_acc_flag_G);
				checkRegisterBtn();
				//alert(register_text_mem_acc_flag);
			},
			error: function(){
				alert(2);
            }
		});
	}
	function checkRegisterBtn(){
		if(register_text_mem_acc_flag_G && register_text_mem_pw_flag_G && register_text_mem_email_flag_G){
			alert(666);
			$('#btnRegister').attr('disabled',false);
		}
	}
		
	
	function _debounce(callback, time){
		if(timer)
			 clearTimeout(timer);
		timer = setTimeout(function(){
			callback();
		}, time);
	}
	
	/*==================================================*/
	var datas = [
    {title:'基隆市',value:'基隆市'},
    {title:'臺北市',value:'臺北市'},
    {title:'新北市',value:'新北市'},
    {title:'桃園市',value:'桃園市'},
    {title:'新竹市',value:'新竹市'},
    {title:'新竹縣',value:'新竹縣'},
    {title:'苗栗縣',value:'苗栗縣'},
    {title:'臺中市',value:'臺中市'},
    {title:'彰化縣',value:'彰化縣'},
    {title:'南投縣',value:'南投縣'},    
    {title:'雲林縣',value:'雲林縣'},
    {title:'嘉義市',value:'嘉義市'},
    {title:'嘉義縣',value:'嘉義縣'},
    {title:'臺南市',value:'臺南市'},
    {title:'高雄市',value:'高雄市'},
    {title:'屏東縣',value:'屏東縣'},
    {title:'臺東縣',value:'臺東縣'},
    {title:'花蓮縣',value:'花蓮縣'},
    {title:'宜蘭縣',value:'宜蘭縣'},
    {title:'澎湖縣',value:'澎湖縣'},
    {title:'金門縣',value:'金門縣'},
    {title:'連江縣',value:'連江縣'},
    
	];

    </script>
    
    
    
    
    
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
  </body>
</html>