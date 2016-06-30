<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/executionHistory.js"></script>
<div id="execution_history_search">
	<a href="#" class="easyui-linkbutton" data-options="plain:true" id="execution_history_search_btn">
		<i class="fa fa-refresh fa-lg"></i>
	</a>
	|
	<spring:message code="quartz.page.executionHistory.jobGroup" />
	:
	<input type="text" id="execution_history_job_group" name="execution_history_job_group" size="20" value="${jobGroup}" ${op eq 'jobsMain' && not empty jobName && not empty jobName ? "readonly='readonly'":"" } />
	<spring:message code="quartz.page.executionHistory.jobName" />
	:
	<input type="text" id="execution_history_job_name" name="execution_history_job_name" size="20" value="${jobName}" ${op eq 'jobsMain' && not empty jobName && not empty jobName ? "readonly='readonly'":"" } />

	<spring:message code="quartz.page.executionHistory.triggerGroup" />
	:
	<input type="text" id="execution_history_trigger_group" name="execution_history_trigger_group" size="20" value="${triggerGroup}" ${op eq 'triggersMain' && not empty triggerGroup && not empty triggerName ? "readonly='readonly'":"" } />
	<spring:message code="quartz.page.executionHistory.triggerName" />
	:
	<input type="text" id="execution_history_trigger_name" name="execution_history_trigger_name" size="20" value="${triggerName}" ${op eq 'triggersMain' && not empty triggerGroup && not empty triggerName ? "readonly='readonly'":"" } />
	<spring:message code="quartz.page.executionHistory.static" />
	:
	<input id="execution_status" name="execution_status" />
	<input id="op" name="op" type="hidden" value="${op}">
</div>
<table id="execution_history_table"></table>
