<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="login.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/responsive/begin.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/css/responsive/login.css">
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">
					<spring:message code="system.systemName" />
					-
					<spring:message code="login.page.title" />
				</a>
			</div>
		</div>
	</nav>
	<div class="container">
		<form id="login-form" name="login-form" method="post" action="${pageContext.request.contextPath}/login" class="form-signin">
			<h6 class="page-summary">
				<!-- required witdh=300px -->
				<img class="img-responsive" src="${pageContext.request.contextPath}<spring:message code="login.page.logo.path" />" />
			</h6>
			<input type="hidden" id="msg" value="${rv}" />
			<div class="form-group">
				<div class="input-group">
					<span id="input-group-addon-1" class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></span>
					<input id="fm-login-id" class="form-control" name="username" tabindex="1" placeholder="<spring:message code="login.page.username" />" aria-describedby="input-group-addon-1" required autofocus />
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span id="input-group-addon-2" class="input-group-addon"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></span>
					<input id="fm-login-password" class="form-control" type="password" name="password" tabindex="2" placeholder="<spring:message code="login.page.password" />" aria-describedby="input-group-addon-2" required />
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-6">
						<div class="input-group">
							<span id="input-group-addon-3" class="input-group-addon"><span class="glyphicon glyphicon-check" aria-hidden="true"></span></span>
							<input id="fm-login-captcha" class="form-control" type="text" name="captcha" tabindex="3" placeholder="<spring:message code="login.page.captcha" />" aria-describedby="input-group-addon-3" required />
						</div>
					</div>
					<div class="col-xs-6">
						<img class="img-responsive" alt="<spring:message code="login.page.captcha" />" src="${pageContext.request.contextPath}/static/images/kaptcha.jpg" title="<spring:message code="login.page.captcha.change" />" id="img_captcha" onclick="javascript:refreshCaptcha();" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<button id="fm-login-submit" class="btn btn-info btn-block" tabindex="3" name="submit-btn">
					<spring:message code="login.page.submit" />
				</button>
			</div>
			<div class="form-group  text-center">
				<a id="forgot-password-link" href="#">
					<spring:message code="login.page.forgotPassword" />
				</a>
			</div>
		</form>
	</div>
	<jsp:include page="/WEB-INF/jsp/llsfw/base/responsive/end.jsp" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/responsive/login.js"></script>
</body>
</html>