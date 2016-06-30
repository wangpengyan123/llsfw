<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/orgEdit.js"></script>
<form id="org_form_edit" name="org_form_edit" method="post">
	<table>
		<tr>
			<td><spring:message code="org.page.edit.window.orgCode" />:</td>
			<td><input id="orgCode_edit" name="orgCode" style="width: 150px;" value="${orgCode}" readonly="readonly" /></td>
			<td><spring:message code="org.page.edit.window.orgName" />:</td>
			<td><input id="orgName_edit" name="orgName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="org.page.edit.window.parentOrgCode" />:</td>
			<td><input id="parentOrgCode_edit" name="parentOrgCode" style="width: 150px;" /></td>
			<td><spring:message code="org.page.edit.window.orgSort" />:</td>
			<td><input id="org_sort_edit" name="orgSort" value="1" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="org_save_edit_btn" href="#"><spring:message code="org.page.edit.window.btn.save" /></a></td>
		</tr>
	</table>
</form>