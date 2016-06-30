<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/portalAdd.js"></script>
<form id="function_portal_form_add" name="function_portal_form_add" method="post">
	<div style="padding: 7px">
		<!--功能代码:-->
		<input id="function_code_add" name="functionCode" type="hidden" style="width: 150px; margin: 5px;" value="${functionCode}" readonly="readonly" />
		<!-- 面板代码: -->
		<input id="portalCode_add" name="portalCode" type="hidden" style="width: 150px; margin: 5px;" value="${portal.portalCode}" readonly="readonly" />
		面板标题:
		<input id="portalTitle_add" name="portalTitle" style="width: 150px; margin: 5px;" value="${portal.portalTitle}" />
		<br /> 面板高度:
		<input id="portalHeight_add" name="portalHeight" style="width: 150px; margin: 5px;" value="${portal.portalHeight}" />
		<br /> 面板地址:
		<input id="portalUrl_add" name="portalUrl" style="width: 150px; margin: 5px;" value="${portal.portalUrl}" />
		<br />
	</div>
	<center style="margin: 5px;">
		<a id="portal_add_btn" href="#">保存 </a>
	</center>
</form>