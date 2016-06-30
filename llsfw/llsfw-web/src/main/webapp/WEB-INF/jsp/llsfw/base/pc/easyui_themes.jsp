<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 这里判断easyui的主题显示 -->
<c:choose>
	<c:when test="${not empty cookie.themeName.value}">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/themes/${cookie.themeName.value}/easyui.css">
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/themes/default/easyui.css">
	</c:otherwise>
</c:choose>
