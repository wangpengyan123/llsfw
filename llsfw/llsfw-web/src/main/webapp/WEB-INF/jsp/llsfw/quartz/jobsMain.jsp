<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/jobsMain.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:true,border:false" style="height: 300px;">
		<div id="jobs_table_toolbar">
			<a id="jobs_table_toolbar_btn_refresh" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.refresh" />" data-options="plain:true">
				<i class="fa fa-refresh fa-lg"></i>
			</a>
			|
			<a id="jobs_table_toolbar_btn_trigger" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.trigger" />" data-options="plain:true">
				<i class="fa fa-play fa-lg"></i>
			</a>
			|
			<a id="jobs_table_toolbar_btn_add" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.add" />" data-options="plain:true">
				<i class="fa fa-plus fa-lg"></i>
			</a>
			<a id="jobs_table_toolbar_btn_copy_add" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.copy.add" />" data-options="plain:true">
				<i class="fa fa-files-o fa-lg"></i>
			</a>
			<a id="jobs_table_toolbar_btn_edit" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.edit" />" data-options="plain:true">
				<i class="fa fa-pencil-square-o fa-lg"></i>
			</a>
			<a id="jobs_table_toolbar_btn_delete" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.delete" />" data-options="plain:true">
				<i class="fa fa-times fa-lg"></i>
			</a>
			|
			<a id="jobs_table_toolbar_btn_puse_job" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.puse.job" />" data-options="plain:true">
				<i class="fa fa-pause-circle-o fa-lg"></i>
			</a>
			<a id="jobs_table_toolbar_btn_resume_job" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.resume.job" />" data-options="plain:true">
				<i class="fa fa-play-circle-o fa-lg"></i>
			</a>
			<%-- 这两个功能会迁到 info选项卡中去 |
				<a id="jobs_table_toolbar_btn_puse_job_all" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.puse.job.all" />" data-options="plain:true">
					<i class="fa fa-pause-circle fa-lg"></i>
				</a>
				<a id="jobs_table_toolbar_btn_resume_all" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.resume.job.all" />" data-options="plain:true">
					<i class="fa fa-play-circle fa-lg"></i>
				</a> --%>
			|
			<spring:message code="quartz.page.jobsmain.search.jobName" />
			:
			<input id="jobs_table_toolbar_input_jobName" size="15" />
			<spring:message code="quartz.page.jobsmain.search.jobGroup" />
			:
			<input id="jobs_table_toolbar_input_jobGroup" size="15" />
			<spring:message code="quartz.page.jobsmain.search.jobClassName" />
			:
			<input id="jobs_table_toolbar_input_jobClassName" size="15" />
			<spring:message code="quartz.page.jobsmain.search.description" />
			:
			<input id="jobs_table_toolbar_input_description" size="15" />
		</div>
		<table id="jobs_table"></table>
	</div>
	<div data-options="region:'center',split:true,border:false">
		<div id="jobMainTabs">
			<div title="<spring:message code="quartz.page.jobsmain.jobMainTabs.1" />" data-options="iconCls:'e-icon fa fa-history fa-lg'"></div>
			<div title="<spring:message code="quartz.page.jobsmain.jobMainTabs.2" />" data-options="iconCls:'e-icon fa fa-caret-square-o-right fa-lg'"></div>
			<div title="<spring:message code="quartz.page.jobsmain.jobMainTabs.3" />" data-options="iconCls:'e-icon fa fa-line-chart fa-lg'"></div>
		</div>
	</div>
</div>