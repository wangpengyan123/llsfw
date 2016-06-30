<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/purviewAdd.js"></script>
<form id="function_purview_form_add" name="function_purview_form_add" method="post">
	<spring:message code="function.puw.add.window.functionCode" />
	:
	<input id="function_code_add" name="functionCode" style="width: 150px; margin: 5px;" value="${functionCode}" readonly="readonly" />
	<br />
	<spring:message code="function.puw.add.window.purviewCode" />
	:
	<input id="purviewCode_add" name="purviewCode" style="width: 150px; margin: 5px;" />
	<br />
	<spring:message code="function.puw.add.window.purviewName" />
	:
	<input id="purviewName_add" name="purviewName" style="width: 150px; margin: 5px;" />
	<br />
	<center style="margin: 5px;">
		<a id="purview_add_btn" href="#"><spring:message code="function.puw.add.window.btn.save" /></a>
	</center>
</form>