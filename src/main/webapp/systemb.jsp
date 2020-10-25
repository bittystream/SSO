<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cn.edu.cqu.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>System B</title>
</head>
<body>
<%
	User user = (User)request.getSession().getAttribute("CAS_TGC");
%>
	<h1>Welcome, <%= user.getUsername() %>! You've entered system B.</h1>
	<a href="LogoutController">登出</a>
	<a href="systema.jsp">跳转到系统A</a>
</body>
</html>