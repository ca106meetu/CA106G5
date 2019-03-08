<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <!-- Required meta tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <script src="Templates/bootstrap4/js/jquery-1.12.4.min.js"></script>
<!--     <script src="Templates/sweetalert-master/sweetalert.min.js"></script> -->
<!--     <script src="Templates/bootstrap4/js/core.js"></script> -->
<!--     <link rel="stylesheet" type="text/css" href="Templates/bootstrap4/css/sweetalert2.min.css"> -->

  </head>
  <body>
<button type="button" class="btn btn-outline-success my-2 btn-lg my-sm-0" data-toggle="modal" data-target="#frontLogin">
登入
</button>
  <div class="modal fade" id="frontLogin" tabindex="-1" action="loginhandler" role="dialog" aria-labelledby="loginLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="loginLabel">登入</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       <form  METHOD="post" ACTION="<%=request.getContextPath()%>/FrontLoginHandler">
  	      <div class="form-group">
   		     <label for="InputMem_acc">會員帳號</label>
   		     <input type="text" name="mem_acc" value="" class="form-control" id="InputMem_acc" aria-describedby="ACCHelp" placeholder="輸入您的帳號">
 		  </div>
	     <div class="form-group">
	         <label for="InputMem_pw">會員密碼</label>
	         <input type="password" name="mem_pw" value="" class="form-control" id="InputMem_pw" placeholder="輸入您的密碼">
	     </div>
	   <button type="submit" class="btn btn-success" value="  ok   ">登入</button>
	 </form>
      </div>
      <div class="modal-footer">
              
      </div>
    </div>
  </div>
</div>
	

<script>

$(function(){
	swal("Hello world!");
});

</script>


</html>