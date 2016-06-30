<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/functionEdit.js"></script>
<form id="function_form_edit" name="function_form_edit" method="post">
	<table>
		<tr>
			<td><spring:message code="function.edit.window.functionCode" />:</td>
			<td><input id="functionCode_edit" name="functionCode" value="${param.functionCode}" readonly="readonly" style="width: 150px;" /></td>
			<td><spring:message code="function.edit.window.functionName" />:</td>
			<td><input id="functionName_edit" name="functionName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.edit.window.parentFunctionCode" />:</td>
			<td><input id="parentFunctionCode_edit" name="parentFunctionCode" style="width: 150px;" /></td>
			<td><spring:message code="function.edit.window.status" />:</td>
			<td><input id="status_edit" name="status" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.edit.window.sort" />:</td>
			<td><input id="sort_edit" name="sort" value="1" style="width: 150px;" /></td>
			<td><spring:message code="function.edit.window.levelNo" />:</td>
			<td><input id="levelNo_edit" name="levelNo" type="hidden" /> <input id="levelNoDisplay_edit" name="levelNoDisplay" readonly="readonly" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.edit.window.functionAccessChannel" />:</td>
			<td><input id="functionAccessChannel_edit" name="functionAccessChannel" style="width: 150px;" /></td>
			<td><spring:message code="function.edit.window.icon" />:</td>
			<td><input id="icon_edit" name="icon" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.edit.window.requestUrl" />:</td>
			<td colspan="3"><input id="requestUrl_edit" name="requestUrl" style="width: 99%;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="function_save_edit_btn" href="#"><spring:message code="function.edit.window.btn.save" /></a></td>
		</tr>
	</table>
</form>