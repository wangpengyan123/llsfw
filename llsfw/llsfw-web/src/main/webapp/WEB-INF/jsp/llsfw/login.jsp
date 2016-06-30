<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="login.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/common/plugins/toastr/toastr.min.css">
<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/toastr/toastr.min.js"></script>
<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/jquery-validation-1.14.0/dist/additional-methods.min.js"></script>
<jsp:include page="/WEB-INF/jsp/llsfw/base/jq_validation_localization.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/css/login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/login.js"></script>
</head>
<body>
	<div id="header">
		<div class="header-layout">
			<h2 class="logo-title">
				<spring:message code="system.systemName" />
				-
				<spring:message code="login.page.title" />
			</h2>
			<ul class="header-nav">
				<li class="nav-first"><a href="${pageContext.request.contextPath}">首页</a></li>
				<li><select id="localeChange" class="localeChange localeChange-expand">
						<option name="zh_CN" value="zh_CN">中文</option>
						<option name="en_US" value="en_US">English</option>
				</select></li>
			</ul>
		</div>
	</div>
	<div class="content">
		<div id="login-wrap" class="login-static">
			<input type="hidden" id="msg" value="${rv}" />
			<form id="login-form" name="login-form" method="post" action="${pageContext.request.contextPath}/login" class="form clr style-type-vertical lang- vertical- ">
				<div id="login-title" style="font-size: 12px; font-weight: normal;">
					<spring:message code="login.page.summary" />
				</div>
				<div id="login-content" class="form clr">
					<dl>
						<dt class="fm-label">
							<div class="fm-label-wrap clr">
								<span id="login-id-label-extra" class="fm-label-extra"></span> <label for="fm-login-id"><spring:message code="login.page.username" />:</label>
							</div>
						</dt>
						<dd id="fm-login-id-wrap" class="fm-field">
							<div class="fm-field-wrap">
								<input id="fm-login-id" class="fm-text" name="username" tabindex="1" placeholder="<spring:message code="login.page.username" />" value="" autocomplete="off" autocorrect="off" autocapitalize="off" />
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="fm-label">
							<div class="fm-label-wrap clr">
								<span class="fm-label-extra"> <a id="forgot-password-link" href="#">
										<spring:message code="login.page.forgotPassword" />
									</a>
								</span> <label for="fm-login-password"><spring:message code="login.page.password" />:</label>
							</div>
						</dt>
						<dd id="fm-login-password-wrap" class="fm-field">
							<div class="fm-field-wrap">
								<input id="fm-login-password" class="fm-text" type="password" name="password" tabindex="2" autocomplete="off" placeholder="<spring:message code="login.page.password" />" autocorrect="off" autocapitalize="off" />
							</div>
						</dd>
					</dl>
					<dl>
						<dt class="fm-label">
							<div class="fm-label-wrap clr">
								<label for="fm-login-captcha"><spring:message code="login.page.captcha" />:</label>
							</div>
						</dt>
						<dd id="fm-login-password-wrap" class="fm-field">
							<div class="fm-field-wrap">
								<input id="fm-login-captcha" class="fm-text" type="text" name="captcha" tabindex="3" autocomplete="off" placeholder="<spring:message code="login.page.captcha" />" autocorrect="off" autocapitalize="off" style="width: 60px; margin-right: 10px;" />
								<img alt="<spring:message code="login.page.captcha" />" src="${pageContext.request.contextPath}/static/images/kaptcha.jpg" title="<spring:message code="login.page.captcha.change" />" id="img_captcha" onclick="javascript:refreshCaptcha();" />
							</div>
						</dd>
					</dl>
				</div>
				<div id="login-submit">
					<input id="fm-login-submit" value="<spring:message code="login.page.submit" />" class="fm-button fm-submit" type="submit" tabindex="3" name="submit-btn" />
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
        //当前语言	
        var locale = '${locale}';

        /**
         * 当session超时的时候,会在当前页面刷新出登录界面,但是由于起初的设计原因和shiro的跳转策略问题,
         * 重新登陆后跳转的并不完美,则诞生如下代码,当弹出登陆界面的时候,刷新主页面,让整个系统跳转到登陆界面中, 登陆后则重新开始.
         */
        try {
            if (self.frameElement && self.frameElement.tagName == "IFRAME") {
                top.location = basePath;
            }
        } catch (e) {
        }
    </script>
</body>
</html>