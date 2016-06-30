<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-流程待处理</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/process/processPending/processPendingMain.js"></script>
</head>
<body>
	<div id="processPendingTableToolBar">
		<a id="processPending_search_btn" href="#" class="easyui-linkbutton" title="查询" data-options="plain:true">
			<i class="fa fa-search fa-lg"></i>
		</a>
		<a id="processPending_Handle_btn" href="#" class="easyui-linkbutton" title="办理" data-options="plain:true">
			<i class="fa fa-caret-square-o-right fa-lg"></i>
		</a>
	</div>
	<table id="processPendingTable"></table>
	<div id="processPendingProcessStatusWindow"></div>
	<div id="processPendingProcessHandleWindow"></div>
</body>
</html>