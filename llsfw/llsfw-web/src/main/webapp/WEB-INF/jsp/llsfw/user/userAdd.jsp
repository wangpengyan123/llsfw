<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/userAdd.js"></script>
<form id="user_form_add" name="user_form_add" method="post">
	<table>
		<tr>
			<td><spring:message code="user.page.add.window.loginName" />:</td>
			<td><input id="loginName_add" name="loginName" style="width: 150px;" /></td>
			<td><spring:message code="user.page.add.window.userName" />:</td>
			<td><input id="userName_add" name="userName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.page.add.window.userPhone" />:</td>
			<td><input id="userPhone_add" name="userPhone" style="width: 150px;" /></td>
			<td><spring:message code="user.page.add.window.userMPhome" />:</td>
			<td><input id="userMPhome_add" name="userMPhome" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="user.page.add.window.userEmail" />:</td>
			<td><input id="userEmail_add" name="userEmail" style="width: 150px;" /></td>
			<td><spring:message code="user.page.add.window.userStatus" />:</td>
			<td><input id="userStatus_add" name="userStatus" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="user_add_btn" href="#"><spring:message code="user.page.add.window.btn.save" /></a></td>
		</tr>
	</table>
</form>