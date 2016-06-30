<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title>错误</title>
</head>
<body>
	<div>错误</div>
	${errorMsg}
	<p />
	<a href="${pageContext.request.contextPath}/logout">退出</a>
</body>
</html>
