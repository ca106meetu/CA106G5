<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.meetU.emp.model.*"%>
<!doctype html>
<html lang="zh-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="Templates/bootstrap4/js/jquery-1.12.4.min.js"></script>
	
    <script src="Templates/bootstrap4/js/sweetalert2.all.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" type="text/css" href="Templates/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="Templates/bootstrap4/css/sweetalert2.css">

    <title>後端首頁</title>
    <style type="text/css" >

    </style>
  </head>
  <body bgcolor='white'>
	<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<br><br><br><br>
<div class="container">
	<form action="BackLoginHandler" method="post">
	  <div class="row justify-content-center">
	  	<div class="col-6">
			<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="basic-emp-ID2">請輸入員工ID</span>
			  </div>
			  <input type="text" class="form-control" name="emp_ID" value="" placeholder="請輸入員工ID(如E000001)" aria-describedby="basic-emp-ID2">
		   </div>
	   </div>
	</div>
	<div class="row justify-content-center">
	  	<div class="col-6">
	  		<div class="input-group mb-3">
			  <div class="input-group-prepend">
			    <span class="input-group-text" id="basic-emp-pw">請輸入員工密碼</span>
			  </div>
			  <input type="password" class="form-control" name="emp_pw" value="" placeholder="請輸入員工密碼" aria-describedby="basic-emp-pw">
		   </div>
	  	</div>
	</div>
	<div class="row justify-content-center">
	  	<div class="col-6">
	  	<center>
	  		<input type='submit' class="btn btn-outline-success btn-lg" value="  登入   ">
	  	</center>
	  	</div>
	</div>
	</form>
  </div>
  <br><br><br><br><br><br><br><br><br>
  

<!-- <button id="btn-click-me" class="btn btn-outline-success btn-lg">click me</button> -->
<jsp:include page="/Templates/bootstrap4/backFooter.jsp" />
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="Templates/bootstrap4/popper.min.js" ></script>
    <script src="Templates/bootstrap4/js/bootstrap.min.js"></script>

    <script type="text/javascript">
    	//自訂預設值
        swal.setDefaults({
            confirmButtonText: "確定",
            cancelButtonText: "取消"
        });
        //swal.resetDefaults();//清空自訂預設值


        $(function () {
            $("#btn-click-me").click(function () {
                //confirm dialog範例
                swal({
                    title: "確定刪除？",
                    html: "按下確定後資料會永久刪除",
                    type: "question",
                    showCancelButton: true//顯示取消按鈕
                }).then(
                    function (result) {
                        if (result.value) {
                            //使用者按下「確定」要做的事
                            swal("完成!", "資料已經刪除", "success");
                        } else if (result.dismiss === "cancel")
                        {
                             //使用者按下「取消」要做的事
                            swal("取消", "資料未被刪除", "error");
                        }//end else  
                    });//end then 
            });
        });
    </script>
  </body>
</html>