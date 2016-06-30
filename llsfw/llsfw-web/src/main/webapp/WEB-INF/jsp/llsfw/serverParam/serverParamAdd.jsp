<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/serverParam/serverParamAdd.js"></script>
<form id="parameters_form_add" name="parameters_form_add" method="post">
	<spring:message code="serverParam.add.window.parametersCode" />
	:
	<input id="parametersCode_add" name="parametersCode" style="width: 150px; margin: 5px;" />
	<br />
	<spring:message code="serverParam.add.window.parametersValue" />
	:
	<input id="parametersValue_add" name="parametersValue" style="width: 150px; margin: 5px;" />
	<br />
	<spring:message code="serverParam.add.window.parametersDesc" />
	:
	<input id="parametersDesc_add" name="parametersDesc" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="parameters_add_btn" href="#"><spring:message code="serverParam.add.window.btn.save" /></a>
	</center>
</form>