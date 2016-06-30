<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="function.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/function/functionMain.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:true">
		<div id="function_search" style="height: auto;">
			<a id="function_search_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.search" />" data-options="plain:true">
				<i class="fa fa-search  fa-lg"></i>
			</a>
			<a id="function_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.add" />" data-options="plain:true">
				<i class="fa fa-plus fa-lg"></i>
			</a>
			<a id="function_edit_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.edit" />" data-options="plain:true">
				<i class="fa fa-pencil-square-o fa-lg"></i>
			</a>
			<a id="function_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.delete" />" data-options="plain:true">
				<i class="fa fa-times fa-lg"></i>
			</a>
		</div>
		<table id="function_search_table"></table>
		<div id="function_add_window"></div>
		<div id="function_edit_window"></div>
	</div>
	<div data-options="region:'east',split:true,border:true" style="width: 450px;">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:true">
				<div id="function_purview_search" style="padding: 5px; height: auto;">
					<a id="function_purview_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.add" />" data-options="plain:true">
						<i class="fa fa-plus fa-lg"></i>
					</a>
					<a id="function_purview_edit_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.edit" />" data-options="plain:true">
						<i class="fa fa-pencil-square-o fa-lg"></i>
					</a>
					<a id="function_purview_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.delete" />" data-options="plain:true">
						<i class="fa fa-times fa-lg"></i>
					</a>
				</div>
				<table id="function_purview_search_table"></table>
				<div id="function_purview_add_windwos"></div>
				<div id="function_purview_edit_windwos"></div>
				<div id="function_warring_windows"></div>
			</div>
			<div data-options="region:'south',split:true,border:true" style="height: 50%;">
				<div id="function_Portal_search" style="padding: 5px; height: auto;">
					<a id="function_Portal_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.add" />" data-options="plain:true">
						<i class="fa fa-plus fa-lg"></i>
					</a>
					<a id="function_Portal_edit_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.edit" />" data-options="plain:true">
						<i class="fa fa-pencil-square-o fa-lg"></i>
					</a>
					<a id="function_Portal_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="function.page.btn.delete" />" data-options="plain:true">
						<i class="fa fa-times fa-lg"></i>
					</a>
				</div>
				<table id="function_Portal_search_table"></table>
				<div id="function_Portal_add_windwos"></div>
			</div>
		</div>
	</div>
</body>
</html>