<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-引擎数据库</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/settings/engineDataBase/engineDataBaseMain.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:true">
	<div data-options="title:'表名',region:'west',split:true,border:true" style="width: 250px;">
		<table id="engineDataBaseTableNames"></table>
	</div>
	<div id="engineDataBaseTableRowsPanle" data-options="title:'记录',region:'center',border:false,border:true"></div>
</body>
</html>