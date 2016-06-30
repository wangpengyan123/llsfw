<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-流程处理</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/process/processPending/processPendingTask.js"></script>
</head>
<body>
	<div style="float: left;">
		<form id="processTaskForm" name="processTaskForm" method="post">${formData}
			<input type="hidden" id="taskId" name="taskId" value="${taskId}">
		</form>
	</div>
</body>
</html>