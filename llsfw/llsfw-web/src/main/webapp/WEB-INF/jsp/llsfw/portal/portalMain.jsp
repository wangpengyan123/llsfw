<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-面板维护</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/portal/portalMain.js"></script>
</head>
<body>
	<div id="portal_search" style="height: auto;">
		<a id="portal_search_btn" href="#" class="easyui-linkbutton" title="查询" data-options="plain:true">
			<i class="fa fa-search  fa-lg"></i>
		</a>
		<a id="function_add_btn" href="#" class="easyui-linkbutton" title="新增" data-options="plain:true">
			<i class="fa fa-plus fa-lg"></i>
		</a>
		<a id="function_edit_btn" href="#" class="easyui-linkbutton" title="修改" data-options="plain:true">
			<i class="fa fa-pencil-square-o fa-lg"></i>
		</a>
		<a id="function_delete_btn" href="#" class="easyui-linkbutton" title="删除" data-options="plain:true">
			<i class="fa fa-times fa-lg"></i>
		</a>
	</div>
	<table id="portal_search_table"></table>
	<div id="function_add_window"></div>
</body>
</html>