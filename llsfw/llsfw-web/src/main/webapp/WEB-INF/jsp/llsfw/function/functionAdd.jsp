<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/functionAdd.js"></script>
<form id="function_form_add" name="function_form_add" method="post">
	<table>
		<tr>
			<td><spring:message code="function.add.window.functionCode" />:</td>
			<td><input id="functionCode_add" name="functionCode" style="width: 150px;" /></td>
			<td><spring:message code="function.add.window.functionName" />:</td>
			<td><input id="functionName_add" name="functionName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.add.window.parentFunctionCode" />:</td>
			<td><input id="parentFunctionCode_add" name="parentFunctionCode" value="${param.parentFunctionCode}" readonly="readonly" style="width: 150px;" /></td>
			<td><spring:message code="function.add.window.status" />:</td>
			<td><input id="status_add" name="status" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.add.window.sort" />:</td>
			<td><input id="sort_add" name="sort" value="1" style="width: 150px;" /></td>
			<td><spring:message code="function.add.window.levelNo" />:</td>
			<td><input id="levelNo_add" name="levelNo" type="hidden" value="${param.levelNo+1}" /> <input id="levelNoDisplay_add" name="levelNoDisplay" readonly="readonly" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.add.window.functionAccessChannel" />:</td>
			<td><input id="functionAccessChannel_add" name="functionAccessChannel" style="width: 150px;" /></td>
			<td><spring:message code="function.add.window.icon" />:</td>
			<td><input id="icon_add" name="icon" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="function.add.window.requestUrl" />:</td>
			<td colspan="3"><input id="requestUrl_add" name="requestUrl" style="width: 99%;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="function_save_add_btn" href="#"><spring:message code="function.add.window.btn.save" /></a></td>
		</tr>
	</table>
</form>