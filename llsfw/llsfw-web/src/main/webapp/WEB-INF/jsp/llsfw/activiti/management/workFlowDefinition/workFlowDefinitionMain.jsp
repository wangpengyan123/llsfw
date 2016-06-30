<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-流程定义</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/management/workFlowDefinition/workFlowDefinitionMain.js"></script>
</head>
<body>
	<div id="workFlowDefinitionTableToolBar">
		<a id="workFlowDefinition_search_btn" href="#" class="easyui-linkbutton" title="查询" data-options="plain:true">
			<i class="fa fa-search  fa-lg"></i>
		</a>
		<a id="workFlowDefinition_delete_btn" href="#" class="easyui-linkbutton" title="删除" data-options="plain:true">
			<i class="fa fa-times fa-lg"></i>
		</a>
	</div>
	<table id="workFlowDefinitionTable"></table>
</body>
</html>