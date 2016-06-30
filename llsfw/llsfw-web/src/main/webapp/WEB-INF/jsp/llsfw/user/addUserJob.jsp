<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/addUserJob.js"></script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',collapsible:false" title="<spring:message code="user.page.jobAuth.window.org" />" style="width: 250px;">
		<input id="add_user_job_org_tree_find" />
		<div id="add_user_job_org_tree"></div>
	</div>
	<div data-options="region:'center'" title="<spring:message code="user.page.jobAuth.window.job" />">
		<table id="add_user_job_table"></table>
		<input type="checkbox" checked="checked" onchange="add_user_job_table_checkbox_change(val, row, index)" />
	</div>
	<div data-options="region:'east',collapsible:false" title="<spring:message code="user.page.jobAuth.window.function" />" style="width: 250px;">
		<div id="add_user_job_function_tree"></div>
	</div>
</div>
<form id="add_user_job_form_edit" name="add_user_job_form_edit" method="post">
	<input id="add_user_job_form_userName" name="userName" value="${userName}"> <input id="add_user_job_form_jobCode" name="jobCode" type="hidden" />
</form>