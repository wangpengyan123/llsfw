<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" />-<spring:message code="admin.page.title" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/head.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/css/admin.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/admin.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true" style="overflow-y: hidden" scroll="no">
	<!-- 顶部 -->
	<div data-options="region:'north',border:false,collapsible:false" class="topDiv">
		<div id="header">
			<div class="header-layout">
				<h2 class="logo-title">
					<spring:message code="system.systemName" />
					-
					<spring:message code="admin.page.title" />
				</h2>
				<ul class="header-nav">
					<li class="nav-first" title="<spring:message code="admin.page.home" />"><a href="${pageContext.request.contextPath}">
							<i class="fa fa-home"></i>
						</a></li>
					<li><a id="changeLanguage" href="#" title="<spring:message code="admin.page.language" />">
							<i class="fa fa-language"></i> :
							<c:choose>
								<c:when test="${locale eq 'zh_CN'}">
								    中文
								</c:when>
								<c:when test="${locale eq 'en_US'}">
								English
								</c:when>
								<c:otherwise>
								中文
								</c:otherwise>
							</c:choose>
						</a></li>
					<li><a id="changeTheme" href="#" title="<spring:message code="admin.page.theme" />">
							<i class="fa fa-laptop"></i> : ${cookie.themeName.value}
						</a></li>
					<%-- 暂时不实现 <li><a id="changeJob" href="#" title="<spring:message code="admin.page.changeJob" />"><i class="fa fa-male"></i></a></li> --%>
					<li><a id="changePswd" href="#" title="<spring:message code="admin.page.changePswd" />">
							<i class="fa fa-user-secret"></i>
						</a></li>
					<li><a href="${pageContext.request.contextPath}/logout" title="<spring:message code="admin.page.logout" />">
							<i class="fa fa-share-square-o"></i>
						</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 左侧 -->
	<div data-options="region:'west',split:false,collapsible:true" title=" ${userName} (${loginName})" style="width: 180px;">${menu}</div>
	<!-- 中间 -->
	<div data-options="region:'center'">
		<div id="maintabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true"></div>
	</div>
	<!-- 窗体 -->
	<div id="changeLanguage_window"></div>
	<div id="changeTheme_window"></div>
	<div id="change_pswd_window"></div>
	<!-- 变量 -->
	<script type="text/javascript">
        var locale = '${locale}';//当前语言
        var themeName = '${cookie.themeName.value}';//当前主题
    </script>
</body>
</html>
