<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="org.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/org/orgMain.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west',split:true,border:false" style="width: 250px;">
			<div id="org_search" style="padding: 5px; height: auto;">
				<a id="org_search_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.btn.search" />" data-options="plain:true">
					<i class="fa fa-search  fa-lg"></i>
				</a>
				<a href="#" class="easyui-menubutton" title="<spring:message code="org.page.btn.add" />" data-options="plain:true,menu:'#org_add_div_menu'">
					<i class="fa fa-plus fa-lg"></i>
				</a>
				<div id="org_add_div_menu" style="width: 100px;">
					<div id="org_add_root_btn" data-options="iconCls:'e-icon fa fa-folder-o fa-lg'">
						<spring:message code="org.page.btn.add.root" />
					</div>
					<div id="org_add_item_btn" data-options="iconCls:'e-icon fa fa-leaf fa-lg'">
						<spring:message code="org.page.btn.add.node" />
					</div>
				</div>
				<a id="org_edit_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.btn.edit" />" data-options="plain:true">
					<i class="fa fa-pencil-square-o fa-lg"></i>
				</a>

				<a id="org_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.btn.delete" />" data-options="plain:true">
					<i class="fa fa-times fa-lg"></i>
				</a>
			</div>
			<table id="org_search_table"></table>
			<div id="org_add_window"></div>
			<div id="org_edit_window"></div>
		</div>
		<div data-options="region:'center',border:false,border:false">
			<div class="easyui-layout" data-options="fit:true,border:false">
				<div data-options="region:'north',split:true" style="height: 300px;">
					<div id="job_search" style="padding: 5px; height: auto;">
						<a id="job_search_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.job.btn.search" />" data-options="plain:true">
							<i class="fa fa-search  fa-lg"></i>
						</a>
						<a id="job_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.job.btn.add" />" data-options="plain:true">
							<i class="fa fa-plus fa-lg"></i>
						</a>
						<a id="job_edit_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.job.btn.edit" />" data-options="plain:true">
							<i class="fa fa-pencil-square-o fa-lg"></i>
						</a>
						<a id="job_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.job.btn.delete" />" data-options="plain:true">
							<i class="fa fa-times fa-lg"></i>
						</a>
					</div>
					<table id="job_search_table"></table>
					<div id="job_add_window"></div>
					<div id="job_edit_window"></div>
				</div>
				<div data-options="region:'center',split:true,border:false">
					<div id="job_role_search" style="padding: 5px; height: auto;">
						<a id="job_role_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.role.btn.add" />" data-options="plain:true">
							<i class="fa fa-plus fa-lg"></i>
						</a>
						<a id="job_role_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="org.page.role.btn.delete" />" data-options="plain:true">
							<i class="fa fa-times fa-lg"></i>
						</a>
					</div>
					<table id="job_role_search_table"></table>
					<div id="job_role_add_window"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>