<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Email驗證</title>
</head>
<body>

<%//  向EmailValidator請求送出email給會員的按鈕 %>
<form action="<%=request.getContextPath()%>/EmailValidator" method="post">
		<input type="hidden" name="action" value="ask_validation_email">
		<input type="submit" value="發送驗證Email">
</form>
<%//  向EmailValidator請求送出email給會員的按鈕 %>

</body>
</html>