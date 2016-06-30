<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/serverParam/serverParamEdit.js"></script>
<form id="parameters_form_edit" name="parameters_form_edit" method="post">
	<spring:message code="serverParam.edit.window.parametersCode" />
	:
	<input id="parametersCode_edit" name="parametersCode" value="${PARAMETERS_CODE}" style="width: 150px; margin: 5px;" readonly="readonly" />
	<br />
	<spring:message code="serverParam.edit.window.parametersValue" />
	:
	<input id="parametersValue_edit" name="parametersValue" style="width: 150px; margin: 5px;" />
	<br />
	<spring:message code="serverParam.edit.window.parametersDesc" />
	:
	<input id="parametersDesc_edit" name="parametersDesc" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="parameters_edit_btn" href="#"><spring:message code="serverParam.edit.window.btn.save" /></a>
	</center>
</form>