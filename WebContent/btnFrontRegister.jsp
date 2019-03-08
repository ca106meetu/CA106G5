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
<button type="button" class="btn btn-outline-success my-2 btn-lg my-sm-0" data-toggle="modal" data-target="#frontRegister">
註冊
</button>

<div class="modal fade" id="frontRegister" tabindex="-1" role="dialog" aria-labelledby="registerLabel" aria-hidden="true">
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
           <input type="text" class="form-control" id="exampleInputACC" aria-describedby="ACCHelp" placeholder="輸入您的帳號">
           <!-- <small id="ACCHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->
      </div>
       <div class="form-group">
           <label for="InputPassword">會員密碼</label>
           <input type="password" class="form-control" id="InputPassword" placeholder="輸入您的密碼">
       </div>
       <div class="form-group">
           <label for="InputEmail">E-mail</label>
           <input type="text" class="form-control" id="InputEmail" placeholder="輸入您的E-mail">
       </div>
       <div class="form-group">
           <label for="InputAddress">居住地</label>
           <input type="text" class="form-control" id="InputAddress" placeholder="輸入您的居住地">
       </div>
       
       <!-- <div class="form-check">
           <input type="checkbox" class="form-check-input" id="exampleCheck1">
           <label class="form-check-label" for="exampleCheck1">Check me out</label>
       </div> -->
       <input type="submit" class="btn btn-success" value="註冊">
  
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

$(function(){
	swal("Hello world!");
});

</script>


</html>