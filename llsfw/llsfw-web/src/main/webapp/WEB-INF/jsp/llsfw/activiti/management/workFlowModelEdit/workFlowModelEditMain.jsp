<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-模型工作区</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/management/workFlowModelEdit/workFlowModelEditMain.js"></script>
</head>
<body>
	<div id="workFlowModelTableToolBar">
		<a id="workFlowModel_search_btn" href="#" class="easyui-linkbutton" title="查询" data-options="plain:true">
			<i class="fa fa-search  fa-lg"></i>
		</a>
		<a id="workFlowModel_add_btn" href="#" class="easyui-linkbutton" title="新增模型" data-options="plain:true">
			<i class="fa fa-plus fa-lg"></i>
		</a>
		<a id="workFlowModel_edit_btn" href="#" class="easyui-linkbutton" title="编辑模型" data-options="plain:true">
			<i class="fa fa-pencil-square-o fa-lg"></i>
		</a>
		<a id="workFlowModel_delete_btn" href="#" class="easyui-linkbutton" title="删除模型" data-options="plain:true">
			<i class="fa fa-times fa-lg"></i>
		</a>
		<a id="workFlowModel_download_btn" href="#" class="easyui-linkbutton" title="下载模型" data-options="plain:true">
            <i class="fa fa-download fa-lg"></i>
        </a>
	</div>
	<table id="workFlowModelTable"></table>
	<div id="addWorkFlowModelWindow"></div>
</body>
</html>