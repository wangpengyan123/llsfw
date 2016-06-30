<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 这里判断easyui的国际化显示 -->
<c:choose>
	<c:when test="${locale eq 'zh_CN'}">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
	</c:when>
	<c:when test="${locale eq 'en_US'}">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/locale/easyui-lang-en.js"></script>
	</c:when>
	<c:otherwise>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
	</c:otherwise>
</c:choose>