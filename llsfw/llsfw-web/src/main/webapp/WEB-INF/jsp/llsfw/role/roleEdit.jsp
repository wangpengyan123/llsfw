<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/role/roleEdit.js"></script>
<form id="role_form_edit" name="role_form_edit" method="post">
	<spring:message code="role.page.edit.window.roleCode" />
	:
	<input id="roleCode_edit" name="roleCode" value="${param.roleCode}" readonly="readonly" style="width: 150px; margin: 5px;" />
	<br />
	<spring:message code="role.page.edit.window.roleName" />
	:
	<input id="roleName_edit" name="roleName" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="role_edit_btn" href="#"><spring:message code="role.page.edit.window.btn.save" /></a>
	</center>
</form>