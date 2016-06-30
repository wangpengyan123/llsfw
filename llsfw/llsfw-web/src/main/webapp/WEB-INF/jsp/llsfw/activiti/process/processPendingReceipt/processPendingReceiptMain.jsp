<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-流程待签收</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/process/processPendingReceipt/processPendingReceiptMain.js"></script>
</head>
<body>
	<div id="processPendingReceiptTableToolBar">
		<a id="processPendingReceipt_search_btn" href="#" class="easyui-linkbutton" title="查询" data-options="plain:true">
			<i class="fa fa-search fa-lg"></i>
		</a>
		<a id="processPendingReceipt_Pending_btn" href="#" class="easyui-linkbutton" title="签收" data-options="plain:true">
			<i class="fa fa-check-square-o fa-lg"></i>
		</a>
	</div>
	<table id="processPendingReceiptTable"></table>
	<div id="processPendingReceiptProcessStatusWindow"></div>
</body>
</html>