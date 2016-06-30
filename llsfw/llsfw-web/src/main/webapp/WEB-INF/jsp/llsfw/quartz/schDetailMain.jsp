<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/schDetailMain.js"></script>
<div id="scheduler_table_toolbar">
	<a id="scheduler_table_toolbar_btn_refresh" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.refresh" />" data-options="plain:true">
		<i class="fa fa-refresh fa-lg"></i>
	</a>
	|
	<a id="scheduler_table_toolbar_btn_puse_job_all" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.puse.job.all" />" data-options="plain:true">
		<i class="fa fa-pause-circle fa-lg"></i>
	</a>
	<a id="scheduler_table_toolbar_btn_resume_all" href="#" class="easyui-linkbutton" title="<spring:message code="quartz.page.jobsmain.search.resume.job.all" />" data-options="plain:true">
		<i class="fa fa-play-circle fa-lg"></i>
	</a>
	|
	<a id="scheduler_table_toolbar_btn_option_list" href="#" class="easyui-linkbutton" title="查看操作历史" data-options="plain:true">
		<i class="fa fa-info fa-lg"></i>
	</a>
	|
	<a id="scheduler_table_toolbar_btn_clear" href="#" class="easyui-linkbutton" title="清除计划任务数据" data-options="plain:true">
		<i class="fa fa-trash fa-lg"></i>
	</a>
</div>
<table id="scheduler_table"></table>