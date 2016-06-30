<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 这里判断jquery jqGrid的国际化显示 -->
<c:choose>
	<c:when test="${locale eq 'zh_CN'}">
		<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/jqGrid/js/i18n/grid.locale-cn.js" charset="UTF-8"></script>
	</c:when>
	<c:when test="${locale eq 'en_US'}">
		<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/jqGrid/js/i18n/grid.locale-en.js" charset="UTF-8"></script>
	</c:when>
	<c:otherwise>
		<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/jqGrid/js/i18n/grid.locale-cn.js" charset="UTF-8"></script>
	</c:otherwise>
</c:choose>

