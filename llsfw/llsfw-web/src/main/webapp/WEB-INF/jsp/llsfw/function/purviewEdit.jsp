<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/purviewEdit.js"></script>
<form id="function_purview_form_edit" name="function_purview_form_edit" method="post">
	<spring:message code="function.puw.edit.window.functionCode" />
	:
	<input id="function_code_edit" name="functionCode" style="width: 150px; margin: 5px;" value="${functionCode}" readonly="readonly" />
	<br />
	<spring:message code="function.puw.edit.window.purviewCode" />
	:
	<input id="purviewCode_edit" name="purviewCode" style="width: 150px; margin: 5px;" value="${purviewCode}" readonly="readonly" />
	<br />
	<spring:message code="function.puw.edit.window.purviewName" />
	:
	<input id="purviewName_edit" name="purviewName" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="purview_edit_btn" href="#"><spring:message code="function.puw.edit.window.btn.save" /></a>
	</center>
</form>