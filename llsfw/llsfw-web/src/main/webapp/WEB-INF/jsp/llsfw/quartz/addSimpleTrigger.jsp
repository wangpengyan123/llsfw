<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/addSimpleTrigger.js"></script>
<form id="simple_triggers_form_add" name="simple_triggers_form_add" method="post">
	<div class="easyui-panel" title="<spring:message code="quartz.page.filed.msg.2" />" data-options="border:false">
		<table>
			<tr>
				<td width="90px"><spring:message code="quartz.page.filed.jobName" />:</td>
				<td><input id="simple_jName_view" name="jName" style="width: 380px;" value='${addTriggerOp eq "add" ? job.JOB_NAME : trigger.JOB_NAME}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.jobGroup" />:</td>
				<td><input id="simple_jGroup_view" name="jGroup" style="width: 380px;" value='${addTriggerOp eq "add" ? job.JOB_GROUP : trigger.JOB_GROUP}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
		</table>
	</div>
	<div class="easyui-panel" title="<spring:message code="quartz.page.filed.msg.3" />" data-options="border:false">
		<table>
			<tr>
				<td width="90px"><spring:message code="quartz.page.filed.triggerName" />:</td>
				<td><input id="simple_tName_add" name="tName" style="width: 380px;" value='${addTriggerOp eq "copyAdd" ? "" : trigger.TRIGGER_NAME}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.triggerGroup" />:</td>
				<td><input id="simple_tGroup_add" name="tGroup" style="width: 380px;" value='${empty trigger.TRIGGER_GROUP ? defaultGroup : addTriggerOp eq "copyAdd" ? "" : trigger.TRIGGER_GROUP}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.triggerDescription" />:</td>
				<td><input id="simple_tDescription_add" name="tDescription" style="width: 380px;" value="${trigger.DESCRIPTION}" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.triggerStartTime" />:</td>
				<td><input id="simple_triggerStartTime_add" name="triggerStartTime" style="width: 380px;" value="${trigger.START_TIME}" title="<spring:message code="quartz.page.filed.msg.10" />" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.triggerEndTime" />:</td>
				<td><input id="simple_triggerEndTime_add" name="triggerEndTime" style="width: 380px;" value="${trigger.END_TIME eq 0 ? '' : trigger.END_TIME}" title="<spring:message code="quartz.page.filed.msg.9" />可以为空" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.triggerPriority" />:</td>
				<td><input id="simple_priority_add" name="priority" style="width: 380px;" value="${empty trigger.PRIORITY ? defaultPriority : trigger.PRIORITY }" title="<spring:message code="quartz.page.filed.msg.8" />" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.triggermisfireInstruction" />:</td>
				<td><input id="simple_misfireInstruction_add" name="misfireInstruction" value="${empty trigger.MISFIRE_INSTR ? defaultMisFireInstruction : trigger.MISFIRE_INSTR }" style="width: 380px;" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.filed.triggerCalendar" />:</td>
				<td><input id="simple_Calendar_add" name="triggerCalendar" style="width: 380px;" value="${trigger.CALENDAR_NAME}" /></td>
			</tr>
		</table>
	</div>
	<div id="addSimpleTriggersTabs" class="easyui-tabs" data-options="border:true,height:200">
		<div title="<spring:message code="quartz.page.filed.msg.1" />">
			<table>
				<tr>
					<td width="90px"><spring:message code="quartz.page.filed.triggerRepeatCount" />:</td>
					<td><input id="simple_triggerRepeatCount_add" name="triggerRepeatCount" value="${trigger.REPEAT_COUNT eq -1 ? '' : trigger.REPEAT_COUNT}" style="width: 380px;" title="<spring:message code="quartz.page.filed.msg.7" />" value="${REPEAT_INDEFINITELY}" /></td>
				</tr>
				<tr>
					<td><spring:message code="quartz.page.filed.triggerIntervalInMilliseconds" />:</td>
					<td><input id="simple_intervalInMilliseconds_add" name="intervalInMilliseconds" value="${trigger.REPEAT_INTERVAL}" style="width: 380px;" title="<spring:message code="quartz.page.filed.msg.6" />" /></td>
				</tr>
			</table>
		</div>
		<div title="<spring:message code="quartz.page.filed.jobDataMap" />" data-options="border:false">
			<input id="simpleHidTriggerName" name="simpleHidTriggerName" value="${trigger.TRIGGER_NAME}" type="hidden" />
			<input id="simpleHidTriggerGroup" name="simpleHidTriggerGroup" value="${trigger.TRIGGER_GROUP}" type="hidden" />
			<input id="cronTriggerDetailDataMapHid" name="cronTriggerDetailDataMapHid" type="hidden" />
			<input id="triggerDetailDataMapHid" name="triggerDetailDataMapHid" type="hidden" />
			<div id="triggerDataMapParam">
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" id="triggerDataMapParamAdd" />
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" id="triggerDataMapParamRemove" />
			</div>
			<table id="triggerDataMapParamTable"></table>
		</div>
	</div>
	<center>
		<a id="simple_triggers_job_from_add_btn" href="#">
			<spring:message code="quartz.page.filed.btn.save" />
		</a>
	</center>
</form>