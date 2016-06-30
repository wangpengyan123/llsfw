<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/changePswd.js"></script>
<form id="change_pswd_form" name="change_pswd_form" method="post">
	<spring:message code="admin.changePswd.oldPassword" />
	: <br /> <input id="old_pswd" name="oldPswd" type="password" style="width: 150px; margin: 5px;" /> <br />
	<spring:message code="admin.changePswd.changePassword" />
	: <br /> <input id="new_pswd" name="newPswd" type="password" style="width: 150px; margin: 5px;" /> <br />
	<spring:message code="admin.changePswd.passwordConfirmation" />
	: <br /> <input id="pswf_confim" name="pswfConfim" type="password" style="width: 150px; margin: 5px;" /> <br />
	<center style="margin: 5px;">
		<a id="pswd_save_btn" href="#"><spring:message code="admin.changePswd.changeBtn" /></a>
	</center>
</form>