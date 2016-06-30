<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="role.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/role/roleMain.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'">
		<div id="role_table_param" style="padding: 5px; height: auto;">
			<a id="role_table_search_btn" href="#" class="easyui-linkbutton" title="<spring:message code="role.page.btn.search" />" data-options="plain:true">
				<i class="fa fa-search  fa-lg"></i>
			</a>
			<a id="role_table_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="role.page.btn.add" />" data-options="plain:true">
				<i class="fa fa-plus fa-lg"></i>
			</a>
			<a id="role_table_edit_btn" href="#" class="easyui-linkbutton" title="<spring:message code="role.page.btn.edit" />" data-options="plain:true">
				<i class="fa fa-pencil-square-o fa-lg"></i>
			</a>
			<a id="role_table_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="role.page.btn.delete" />" data-options="plain:true">
				<i class="fa fa-times fa-lg"></i>
			</a>
		</div>
		<table id="role_table"></table>
		<div id="role_window_add"></div>
		<div id="role_window_edit"></div>
	</div>
	<div data-options="region:'east',split:true" style="width: 450px;">
		<div id="role_function_table_param" style="padding: 5px; height: auto;">
			<a id="role_function_table_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="role.page.function.btn.add" />" data-options="plain:true">
				<i class="fa fa-plus fa-lg"></i>
			</a>
			<a id="role_function_table_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="role.page.function.btn.delete" />" data-options="plain:true">
				<i class="fa fa-times fa-lg"></i>
			</a>
		</div>
		<input type="hidden" id="curr_role_code" />
		<table id="role_function_table"></table>
		<div id="role_function_window_add"></div>
	</div>
</body>
</html>