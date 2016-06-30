<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/addJobDetail.js"></script>
<form id="job_detail_form_add" name="job_detail_form_add" method="post">
	<input id="jobDetailDataMapHid" name="jobDetailDataMapHid" type="hidden" />
	<input id="jobName" name="jobName" value="${jobDetail.JOB_NAME}" type="hidden" />
	<input id="jobGroup" name="jobGroup" value="${jobDetail.JOB_GROUP}" type="hidden" />
	<table>
		<tr>
			<td><spring:message code="quartz.page.jobsmain.edit.window.jName" />:</td>
			<td><input id="jName_add" name="jName" style="width: 360px;" value='${op eq "copyAdd"?"":jobDetail.JOB_NAME}' ${op eq "edit"?"readonly='readonly' ":""} /></td>
		</tr>
		<tr>
			<td><spring:message code="quartz.page.jobsmain.edit.window.jGroup" />:</td>
			<td><input id="jGroup_add" name="jGroup" style="width: 360px;" value='${jobDetail.JOB_GROUP}' ${op eq "edit"?"readonly='readonly' ":""} /></td>
		</tr>
		<tr>
			<td><spring:message code="quartz.page.jobsmain.edit.window.jClass" />:</td>
			<td><input id="jClass_add" name="jClass" style="width: 360px;" value="${jobDetail.JOB_CLASS_NAME}" /></td>
		</tr>
		<tr>
			<td><spring:message code="quartz.page.jobsmain.edit.window.jDesc" />:</td>
			<td><input id="jDesc_add" name="jDesc" style="width: 360px;" value="${jobDetail.DESCRIPTION}" /></td>
		</tr>
		<tr>
			<td><spring:message code="quartz.page.jobsmain.edit.window.jobShouldRecover" />:</td>
			<td><input id="jobShouldRecover_add" name="jobShouldRecover" type="checkbox" ${jobDetail.REQUESTS_RECOVERY==null||jobDetail.REQUESTS_RECOVERY==0?"":"checked='checked'"} title="<spring:message code="quartz.page.jobsmain.edit.window.jobShouldRecover.message" />" /></td>
		</tr>
		<tr>
			<td><spring:message code="quartz.page.jobsmain.edit.window.jobDurability" />:</td>
			<td><input id="jobDurability_add" name="jobDurability" type="checkbox" ${jobDetail.IS_DURABLE==null||jobDetail.IS_DURABLE==0?"":"checked='checked'"} /></td>
		</tr>
		<tr>
			<td><spring:message code="quartz.page.jobsmain.edit.window.nonConcurrent" />:</td>
			<td><font color="red"><spring:message code="quartz.page.jobsmain.edit.window.nonConcurrent.message" /> </font></td>
		</tr>
		<tr>
			<td colspan="2">
				<div id="jobDetailDataMapParam">
					<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" id="jobDetailDataMapAdd" />
					<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" id="jobDetailDataMapRemove" />
				</div>
				<table id="jobDetailDataMapTable"></table>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a id="job_detail_form_add_btn" href="#">
					<spring:message code="quartz.page.jobsmain.edit.window.btn.save" />
				</a></td>
		</tr>
	</table>
</form>