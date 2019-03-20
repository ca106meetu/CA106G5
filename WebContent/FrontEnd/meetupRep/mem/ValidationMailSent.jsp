<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.meetU.mem.model.*"%>
<%
  String mem_ID = (String) session.getAttribute("mem_ID");
  MemVO memVO = new MemService().getOneMem(mem_ID);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>驗證信已送出</h1>
<p>
<a href="mailto:${memVO.mem_email}">提醒您驗證信只有最新的一封有效</a></p>
</body>
</html>