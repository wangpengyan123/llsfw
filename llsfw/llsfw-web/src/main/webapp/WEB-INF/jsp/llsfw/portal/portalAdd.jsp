<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/portal/portalAdd.js"></script>
<form id="function_portal_form_add" name="function_portal_form_add" method="post">
	<div style="padding: 7px">
		<input id="op" name="op" type="hidden" value="${op}" />
		<label id='portalCodeLable' style="display: inline;">选择面板:</label>
		<input id="portalCode" name="portalCode" style="width: 150px; margin: 5px;" value="${portal.portalCode}" ${op eq 'edit' ? 'type="hidden"' : 'type="text"'} />
		<br /> 面板位置:
		<input id="portalIndex" name="portalIndex" style="width: 150px; margin: 5px;" value="${portal.portalIndex}" />
		<br /> 面板排序:
		<input id="portalSort" name="portalSort" style="width: 150px; margin: 5px;" value="${portal.portalSort}" />
		<br />
	</div>
	<center style="margin: 5px;">
		<a id="portal_add_btn" href="#">保存 </a>
	</center>
</form>