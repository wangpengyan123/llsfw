<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="user.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/datagrid-filter.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/tree.loadFilter.js"></script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/user/userMain.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:false">
				<div id="user_table_param">
					<a id="user_table_search_btn" href="#" class="easyui-linkbutton" title="<spring:message code="user.page.search.search" />" data-options="plain:true">
						<i class="fa fa-search  fa-lg"></i>
					</a>
					<a id="user_table_add_btn" href="#" class="easyui-linkbutton" title="<spring:message code="user.page.search.add" />" data-options="plain:true">
						<i class="fa fa-plus fa-lg"></i>
					</a>
					<a id="user_table_edit_btn" href="#" class="easyui-linkbutton" title="<spring:message code="user.page.search.edit" />" data-options="plain:true">
						<i class="fa fa-pencil-square-o fa-lg"></i>
					</a>
					<a id="user_table_delete_btn" href="#" class="easyui-linkbutton" title="<spring:message code="user.page.search.delete" />" data-options="plain:true">
						<i class="fa fa-times fa-lg"></i>
					</a>
					<a id="user_table_set_defpswd_btn" href="#" class="easyui-linkbutton" title="<spring:message code="user.page.search.resetpswd" />" data-options="plain:true">
						<i class="fa fa-street-view fa-lg"></i>
					</a>
					<a id="user_table_job_permissions_btn" href="#" class="easyui-linkbutton" title="<spring:message code="user.page.search.jobAuthorize" />" data-options="plain:true">
						<i class="fa fa-share-alt fa-lg"></i>
					</a>
					<a id="user_table_user_permissions_defpswd_btn" href="#" class="easyui-linkbutton" title="<spring:message code="user.page.search.authorize" />" data-options="plain:true">
						<i class="fa fa-share-alt-square fa-lg"></i>
					</a>
					<spring:message code="user.page.search.loginName" />
					:
					<input type="text" id="loginNameSearch" name="loginNameSearch" size="15" />
					<spring:message code="user.page.search.userName" />
					:
					<input type="text" id="userNameSearch" name="userNameSearch" size="15" />
				</div>
				<table id="user_table"></table>
				<div id="user_window_add"></div>
				<div id="user_window_edit"></div>
				<div id="user_job_window_add"></div>
				<div id="user_user_function_window_add"></div>
			</div>
			<div data-options="region:'south',split:true,border:false" style="height: 200px;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'west',split:true,border:false" style="width: 50%;">
						<table id="user_job_table"></table>
					</div>
					<div data-options="region:'center'">
						<table id="user_job_role_table"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div data-options="region:'east',split:true,border:true" style="width: 250px;">
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:false">
				<div id="PermissionsTabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
					<div title='<spring:message code="user.page.allPurview" />' style="padding: 1px;" data-options="href:''">
						<div id="user_job_org_function_all_table"></div>
					</div>
					<div title="<spring:message code="user.page.jobPurview" />" style="padding: 1px;" data-options="href:''">
						<div id="user_job_org_function_job_table"></div>
					</div>
					<div title="<spring:message code="user.page.purview" />" style="padding: 1px;" data-options="href:''">
						<div id="user_job_org_function_user_table"></div>
					</div>
				</div>
			</div>
			<div data-options="region:'south',split:true,border:false" style="height: 50%;">
				<div id="orgTabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
					<div title="<spring:message code="user.page.org" />" style="padding: 1px;" data-options="href:''">
						<div id="user_job_org_all_table"></div>
					</div>
					<div title="<spring:message code="user.page.parentOrg" />" style="padding: 1px;" data-options="href:''">
						<div id="user_job_org_higher_table"></div>
					</div>
					<div title="<spring:message code="user.page.childOrg" />" style="padding: 1px;" data-options="href:''">
						<div id="user_job_org_lower_table"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>