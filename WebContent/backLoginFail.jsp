<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<!doctype html>
<html lang='zh-TW'>
<head>
	<meta charset='utf-8'>
	<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>
	<script src='Templates/bootstrap4/js/jquery-3.2.1.min.js'></script>
	<script src='Templates/bootstrap4/js/sweetalert2.all.js'></script>
	<link rel='stylesheet' type='text/css' href='Templates/bootstrap4/css/sweetalert2.css'>
	<TITLE>登入失敗</TITLE>
</head>
<body>
	<script type='text/javascript'>
	$(function(){
		swal({title: '你的帳號,密碼無效!',text: '請您重新輸入帳號密碼',type:'error'})
		.then(function(){location.href = "backIndex.jsp"});});
	</script>
</body>
</HTML>