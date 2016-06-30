<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/jobAdd.js"></script>
<form id="job_form_add" name="job_form_add" method="post">
	<table>
		<tr>
			<td><spring:message code="org.page.job.add.window.jobCode" />:</td>
			<td><input id="jobCode_add" name="jobCode" style="width: 150px;" /></td>
			<td><spring:message code="org.page.job.add.window.jobName" />:</td>
			<td><input id="jobName_add" name="jobName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="org.page.job.add.window.orgCode" />:</td>
			<td colspan="3"><input id="job_rogCode_add" name="orgCode" value="${orgCode}" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="job_save_add_btn" href="#"><spring:message code="org.page.job.add.window.btn.save" /></a></td>
		</tr>
	</table>
</form>