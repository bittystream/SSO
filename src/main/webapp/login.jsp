<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>登录</title>
</head>
<body>
	<h2>Hello World!</h2>
	<!-- 登陆状态保存在客户端! -->
	<form action="LoginController" method="post">
		<p>用户名: <input type="text" name="username"></p>
		<p>密码: <input type="password" name="password"></p>
		<input type="submit" value="登录">
	</form>
</body>
</html>
