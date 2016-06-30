<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/userEdit.js"></script>
<form id="user_form_edit" name="user_form_edit" method="post">
	<table>
		<tr>
			<td><spring:message code="user.page.edit.window.loginName" />:</td>
			<td><input id="loginName_edit" name="loginName" value="${param.loginName}" readonly="readonly" style="width: 150px;" /></td>
			<td><spring:message code="user.page.edit.window.userName" />:</td>
			<td><input id="userName_edit" name="userName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.page.edit.window.userPhone" />:</td>
			<td><input id="userPhone_edit" name="userPhone" style="width: 150px;" /></td>
			<td><spring:message code="user.page.edit.window.userMPhome" />:</td>
			<td><input id="userMPhome_edit" name="userMPhome" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.page.edit.window.userEmail" />:</td>
			<td><input id="userEmail_edit" name="userEmail" style="width: 150px;" /></td>
			<td><spring:message code="user.page.edit.window.userStatus" />:</td>
			<td><input id="userStatus_edit" name="userStatus" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="user_edit_btn" href="#"><spring:message code="user.page.edit.window.btn.save" /></a></td>
		</tr>
	</table>
</form>