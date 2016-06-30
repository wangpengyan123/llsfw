<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="quartz.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script src="${pageContext.request.contextPath}/static/llsfw/common/Highcharts-4.2.1/js/highcharts.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/quartzMain.js"></script>
</head>
<body>
	<div id="quartzTabs">
		<div title="<spring:message code="quartz.page.tabs.title.1" />" data-options="iconCls:'e-icon fa fa-tasks fa-lg'"></div>
		<div title="<spring:message code="quartz.page.tabs.title.2" />" data-options="iconCls:'e-icon fa fa-retweet fa-lg'"></div>
		<div title="<spring:message code="quartz.page.tabs.title.3" />" data-options="iconCls:'e-icon fa fa-caret-square-o-right fa-lg'"></div>
		<div title="<spring:message code="quartz.page.tabs.title.4" />" data-options="iconCls:'e-icon fa fa-info-circle fa-lg'"></div>
		<div title="<spring:message code="quartz.page.tabs.title.5" />" data-options="iconCls:'e-icon fa fa-history fa-lg'"></div>
		<div title="<spring:message code="quartz.page.tabs.title.6" />" data-options="iconCls:'e-icon fa fa-line-chart fa-lg'"></div>
	</div>
	<div id="cron_trigger_add_window"></div>
	<div id="simple_trigger_add_window"></div>
	<div id="trigger_data_window">
		<table id="trigger_data_window_table"></table>
	</div>
	<div id="execution_history_errorMsg_dialog"></div>
	<div id='job_detail_add_window'></div>
	<div id="job_data_window">
		<table id="job_data_window_table"></table>
	</div>
	<div id="cron_triggers_generate_window"></div>
	<div id="scheduler_log_window">
		<table id="scheduler_log_table"></table>
	</div>
	<div id="scheduler_log_msg_dialog"></div>
</body>
</html>