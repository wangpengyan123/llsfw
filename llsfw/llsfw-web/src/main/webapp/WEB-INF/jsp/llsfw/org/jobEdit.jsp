<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/jobEdit.js"></script>
<form id="job_form_edit" name="job_form_edit" method="post">
	<table>
		<tr>
			<td><spring:message code="org.page.job.edit.window.jobCode" />:</td>
			<td><input id="jobCode_edit" name="jobCode" value="${jobCode}" readonly="readonly" style="width: 150px;" /></td>
			<td><spring:message code="org.page.job.edit.window.jobName" />:</td>
			<td><input id="jobName_edit" name="jobName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="org.page.job.edit.window.orgCode" />:</td>
			<td colspan="3"><input id="job_rogCode_edit" name="orgCode" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="job_save_edit_btn" href="#"><spring:message code="org.page.job.edit.window.btn.save" /></a></td>
		</tr>
	</table>
</form>