<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/management/workFlowModelEdit/addworkFlowModel.js"></script>
<form id="addWorkFlowModel_form_add" name="addWorkFlowModel_form_add" method="post">
	<table>
		<tr>
			<td>名称:</td>
			<td><input id="name" name="name" /></td>
		</tr>
		<tr>
			<td>KEY:</td>
			<td><input id="key" name="key" /></td>
		</tr>
		<tr>
			<td>描述:</td>
			<td><input id="description" name="description" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a id="addWorkFlowModel_form_btn_add" href="#">保存</a></td>
		</tr>
	</table>
</form>