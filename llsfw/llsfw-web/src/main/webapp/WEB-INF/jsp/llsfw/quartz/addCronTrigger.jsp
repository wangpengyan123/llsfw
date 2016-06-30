<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/addCronTrigger.js"></script>
<form id="cron_triggers_form_add" name="cron_triggers_form_add" method="post">
	<div class="easyui-panel" title="<spring:message code="quartz.page.addCronTirgger.title.1" />" data-options="border:false">
		<table>
			<tr>
				<td width="90px"><spring:message code="quartz.page.addCronTirgger.filed.cron_jName_view" />:</td>
				<td><input id="cron_jName_view" name="jName" style="width: 380px;" value='${addTriggerOp eq "add" ? job.JOB_NAME : trigger.JOB_NAME}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_jGroup_view" />:</td>
				<td><input id="cron_jGroup_view" name="jGroup" style="width: 380px;" value='${addTriggerOp eq "add" ? job.JOB_GROUP : trigger.JOB_GROUP}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
		</table>
	</div>
	<div class="easyui-panel" title="<spring:message code="quartz.page.addCronTirgger.title.2" />" data-options="border:false">
		<table>
			<tr>
				<td width="90px"><spring:message code="quartz.page.addCronTirgger.filed.cron_tName_add" />:</td>
				<td><input id="cron_tName_add" name="tName" style="width: 380px;" value='${addTriggerOp eq "copyAdd" ? "" : trigger.TRIGGER_NAME}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_tGroup_add" />:</td>
				<td><input id="cron_tGroup_add" name="tGroup" style="width: 380px;" value='${empty trigger.TRIGGER_GROUP ? defaultGroup : addTriggerOp eq "copyAdd" ? "" : trigger.TRIGGER_GROUP}' ${addTriggerOp eq "edit"?"readonly='readonly' ":""} /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_tDescription_add" />:</td>
				<td><input id="cron_tDescription_add" name="tDescription" style="width: 380px;" value="${trigger.DESCRIPTION}" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_triggerStartTime_add" />:</td>
				<td><input id="cron_triggerStartTime_add" name="triggerStartTime" style="width: 380px;" value="${trigger.START_TIME}" title='<spring:message code="quartz.page.addCronTirgger.message.1" />' /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_triggerEndTime_add" />:</td>
				<td><input id="cron_triggerEndTime_add" name="triggerEndTime" style="width: 380px;" value="${trigger.END_TIME eq 0 ? '' : trigger.END_TIME}" title="<spring:message code="quartz.page.addCronTirgger.message.2" />" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_priority_add" />:</td>
				<td><input id="cron_priority_add" name="priority" style="width: 380px;" value="${empty trigger.PRIORITY ? defaultPriority : trigger.PRIORITY }" title="<spring:message code="quartz.page.addCronTirgger.message.3" />" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_misfireInstruction_add" />:</td>
				<td><input id="cron_misfireInstruction_add" name="misfireInstruction" value="${empty trigger.MISFIRE_INSTR ? defaultMisFireInstruction : trigger.MISFIRE_INSTR }" style="width: 380px;" /></td>
			</tr>
			<tr>
				<td><spring:message code="quartz.page.addCronTirgger.filed.cron_Calendar_add" />:</td>
				<td><input id="cron_Calendar_add" name="triggerCalendar" style="width: 380px;" value="${trigger.CALENDAR_NAME}" /></td>
			</tr>
		</table>
	</div>
	<div id="addCronTriggersTabs" class="easyui-tabs" data-options="border:true,height:200">
		<div title="<spring:message code="quartz.page.addCronTirgger.filed.addCronTriggersTabs.title.1" />">
			<table>
				<tr>
					<td width="90px"><spring:message code="quartz.page.addCronTirgger.filed.cron_triggerCronExcp_add" />:</td>
					<td><input id="cron_triggerCronExcp_add" name="triggerCronExcp" value="${trigger.CRON_EXPRESSION}" style="width: 380px;" /></td>
				</tr>
				<tr>
					<td><spring:message code="quartz.page.addCronTirgger.filed.cron_timeZoneId_add" />:</td>
					<td><input id="cron_timeZoneId_add" name="timeZoneId" value="${empty trigger.TIME_ZONE_ID ? defaultTimeZone : trigger.TIME_ZONE_ID}" style="width: 380px;" /></td>
				</tr>
			</table>
		</div>
		<div title="<spring:message code="quartz.page.addCronTirgger.filed.propTable" />" data-options="border:false">
			<input id="cronHidTriggerName" name="cronHidTriggerName" value="${trigger.TRIGGER_NAME}" type="hidden" />
			<input id="cronHidTriggerGroup" name="cronHidTriggerGroup" value="${trigger.TRIGGER_GROUP}" type="hidden" />
			<input id="cronTriggerDetailDataMapHid" name="cronTriggerDetailDataMapHid" type="hidden" />
			<div id="cronTriggerDataMapParam">
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" id="cronTriggerDataMapParamAdd" />
				<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" id="cronTriggerDataMapParamRemove" />
			</div>
			<table id="cronTriggerDataMapParamTable"></table>
		</div>
	</div>
	<center>
		<a id="cron_triggers_job_from_add_btn" href="#">
			<spring:message code="quartz.page.addCronTirgger.filed.btn.save" />
		</a>
	</center>
</form>