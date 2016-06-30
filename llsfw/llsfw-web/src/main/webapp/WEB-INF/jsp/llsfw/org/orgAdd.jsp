<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/orgAdd.js"></script>
<form id="org_form_add" name="org_form_add" method="post">
	<table>
		<tr>
			<td><spring:message code="org.page.add.window.orgCode" />:</td>
			<td><input id="orgCode_add" name="orgCode" style="width: 150px;" /></td>
			<td><spring:message code="org.page.add.window.orgName" />:</td>
			<td><input id="orgName_add" name="orgName" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td><spring:message code="org.page.add.window.parentOrgCode" />:</td>
			<td><input id="parentOrgCode_add" name="parentOrgCode" value="${parentOrgCode}" readonly="readonly" style="width: 150px;" /></td>
			<td><spring:message code="org.page.add.window.orgSort" />:</td>
			<td><input id="org_sort_add" name="orgSort" value="1" style="width: 150px;" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="org_save_add_btn" href="#"><spring:message code="org.page.add.window.btn.save" /></a></td>
		</tr>
	</table>
</form>