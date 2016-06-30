<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登陆</title>
</head>
<body>
	<div>登陆</div>
	<form method="post" action="${pageContext.request.contextPath}/login">
		用户名:<input id="username" name="username" type="text" value="admin" style="width: 100%;" />
		<p />
		密码:<input id="password" name="password" type="password" value="123456" style="width: 100%;" />
		<p />
		<input id="submitBtn" name="submitBtn" type="submit" style="width: 100%;" value="登录" /> 
		<p />
		<input id="resetBtn" name="resetBtn" type="reset" style="width: 100%;" value="重置" />
	</form>
</body>
</html>