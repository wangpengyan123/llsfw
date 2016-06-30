<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/datagrid-detailview.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/jobTriggers.js"></script>
<div id="trigger_table_toolbar">
	<a id="trigger_table_toolbar_btn_refresh" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobTriggers.search.refresh" />" data-options="plain:true">
		<i class="fa fa-refresh fa-lg"></i>
	</a>
	|
	<a id="trigger_table_toolbar_btn_add" href="#" class="easyui-menubutton" title="<spring:message code="quartz.page.jobTriggers.search.add" />" data-options="plain:true,menu:'#trigger_add_div_menu'">
		<i class="fa fa-plus fa-lg"></i>
	</a>
	<div id="trigger_add_div_menu" style="width: 100px;">
		<div id="simple_trigger_add" data-options="iconCls:'e-icon fa fa-clock-o fa-lg'">
			<spring:message code="quartz.page.jobTriggers.search.add.simple" />
		</div>
		<div id="cron_trigger_add" data-options="iconCls:'e-icon fa fa-calendar-check-o fa-lg'">
			<spring:message code="quartz.page.jobTriggers.search.add.cron" />
		</div>
	</div>
	<a id="trigger_table_toolbar_btn_copy_add" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobTriggers.search.copy.add" />" data-options="plain:true">
		<i class="fa fa-files-o fa-lg"></i>
	</a>
	<a id="trigger_table_toolbar_btn_edit" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobTriggers.search.edit" />" data-options="plain:true">
		<i class="fa fa-pencil-square-o fa-lg"></i>
	</a>
	<a id="trigger_table_toolbar_btn_delete" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobTriggers.search.delete" />" data-options="plain:true">
		<i class="fa fa-times fa-lg"></i>
	</a>
	|
	<a id="trigger_table_toolbar_btn_puse_job" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobTriggers.search.puse.job" />" data-options="plain:true">
		<i class="fa fa-pause-circle-o fa-lg"></i>
	</a>
	<a id="trigger_table_toolbar_btn_resume_job" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobTriggers.search.resume.job" />" data-options="plain:true">
		<i class="fa fa-play-circle-o fa-lg"></i>
	</a>
	|
	<spring:message code="quartz.page.jobTriggers.search.jobName" />
	:
	<input id="trigger_table_toolbar_input_jobName" size="15" value="${jobName}" ${op eq 'jobsMain' && not empty jobName && not empty jobName ? "readonly='readonly'":"" } />
	<spring:message code="quartz.page.jobsmain.search.jobGroup" />
	:
	<input id="trigger_table_toolbar_input_jobGroup" size="15" value="${jobGroup}" ${op eq 'jobsMain' && not empty jobName && not empty jobName ? "readonly='readonly'":"" } />
	<spring:message code="quartz.page.jobTriggers.search.triggerName" />
	:
	<input id="trigger_table_toolbar_input_triggerName" size="15" value="${triggerName}" />
	<spring:message code="quartz.page.jobTriggers.search.triggerGroup" />
	:
	<input id="trigger_table_toolbar_input_triggerGroup" size="15" value="${triggerGroup}" />
	<input id="op" type="hidden" value="${op}" />
</div>
<table id="trigger_table"></table>