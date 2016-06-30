<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="serverParam.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/serverParam/serverParam.js"></script>
</head>
<body data-options="region:'center',border:false,fit:true">
	<div id="server_param_search" style="height: auto;">
		<spring:message code="serverParam.page.search.parametersCode" />
		:
		<input type="text" id="parametersCode" name="parametersCode" size="10" />
		<spring:message code="serverParam.page.search.parametersDesc" />
		:
		<input type="text" id="parametersDesc" name="parametersDesc" size="10" />
		<br />
		<a id="serverParamSearchBtn" href="#" title="<spring:message code="serverParam.page.btn.search" />" class="easyui-linkbutton" data-options="plain:true">
			<i class="fa fa-search  fa-lg"></i>
		</a>
		<a id="serverParamAddBtn" href="#" title="<spring:message code="serverParam.page.btn.add" />" class="easyui-linkbutton" data-options="plain:true">
			<i class="fa fa-plus  fa-lg"></i>
		</a>
		<a id="serverParamEditBtn" href="#" title="<spring:message code="serverParam.page.btn.edit" />" class="easyui-linkbutton" data-options="plain:true">
			<i class="fa fa-pencil-square-o  fa-lg"></i>
		</a>
		<a id="serverParamDeleteBtn" href="#" title="<spring:message code="serverParam.page.btn.delete" />" class="easyui-linkbutton" data-options="plain:true">
			<i class="fa fa-times  fa-lg"></i>
		</a>
	</div>
	<table id="serverParamTable"></table>
	<div id="addServerParamWindows"></div>
	<div id="editServerParamWindows"></div>
</body>
</html>