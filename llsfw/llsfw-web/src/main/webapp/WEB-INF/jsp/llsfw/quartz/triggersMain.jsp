<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/triggersMain.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',split:true,border:false,href:basePath+'quartzController/toJobTriggersPage?op=triggersMain'" style="height: 300px;"></div>
	<div data-options="region:'center',split:true,border:false">
		<div id="triggersMainTabs">
			<div title="执行历史" data-options="iconCls:'e-icon fa fa-history fa-lg'"></div>
			<div title="统计报表" data-options="iconCls:'e-icon fa fa-line-chart fa-lg'"></div>
		</div>
	</div>
</div>