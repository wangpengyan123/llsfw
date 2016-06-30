<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="admin.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<script type="text/javascript">
    var portal = JSON.parse('${portal}');
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/jquery.portal.js"></script>
<script src="${pageContext.request.contextPath}/static/llsfw/common/Highcharts-4.2.1/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/main.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<div id="minPortal" style="overflow-x: hidden">
			<div style="width: 33%"></div>
			<div style="width: 33%"></div>
			<div style="width: 33%; padding-right: 30px"></div>
		</div>
	</div>
</body>
</html>