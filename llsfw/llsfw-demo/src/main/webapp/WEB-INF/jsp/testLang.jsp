<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<title>测试国际化</title>
</head>
<body>
	<div>测试国际化</div>
	<p />
	<spring:message code="demo.testMessage" />
	<p />
	<a href="${pageContext.request.contextPath}/logout">退出</a>
</body>
</html>
