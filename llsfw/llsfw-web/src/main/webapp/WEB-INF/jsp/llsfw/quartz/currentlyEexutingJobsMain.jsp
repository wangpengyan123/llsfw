<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/quartz/currentlyEexutingJobsMain.js"></script>
<div id="currentlyEexutingJobs_search">
	<a href="#" class="easyui-linkbutton" data-options="plain:true" id="currentlyEexutingJobs_search_btn">
		<i class="fa fa-refresh fa-lg"></i>
	</a>
	|
	<a href="#" class="easyui-linkbutton" data-options="plain:true" id="currentlyEexutingJobs_interropt_btn">
		<i class="fa fa-stop-circle-o fa-lg"></i>
	</a>
</div>
<table id="currentlyEexutingJobs_table"></table>