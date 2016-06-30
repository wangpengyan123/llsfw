<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-流程申请</title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/process/processApplication/processApplicationMain.js"></script>
</head>
<body>
	<div style="float: left;">
		<form id="processApplicationForm" name="processApplicationForm" method="post">${formData}
			<div style="float: right;">
				<input type="hidden" id="processDefinitionId" name="processDefinitionId" value="${processDefinitionId}">
				<a id="processApplication_btn_submitPorcess" class="easyui-linkbutton">提交流程</a>
			</div>
		</form>
	</div>
</body>
</html>