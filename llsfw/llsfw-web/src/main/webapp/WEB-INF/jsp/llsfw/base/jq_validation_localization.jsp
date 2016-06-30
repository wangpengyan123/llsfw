<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 这里判断jquery validation的国际化显示 -->
<c:choose>
	<c:when test="${locale eq 'zh_CN'}">
		<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/jquery-validation-1.14.0/dist/localization/messages_zh.min.js" charset="UTF-8"></script>
	</c:when>
	<c:when test="${locale eq 'en_US'}">
		<!-- 默认英文,无需引入js -->
	</c:when>
	<c:otherwise>
		<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/jquery-validation-1.14.0/dist/localization/messages_zh.min.js" charset="UTF-8"></script>
	</c:otherwise>
</c:choose>

