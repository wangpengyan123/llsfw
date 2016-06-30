<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/plupload-2.1.8/js/plupload.full.min.js" charset="UTF-8"></script>
<!-- 这里判断jquery plupload的国际化显示 -->
<c:choose>
	<c:when test="${locale eq 'zh_CN'}">
		<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/plupload-2.1.8/js/i18n/zh_CN.js" charset="UTF-8"></script>
	</c:when>
	<c:when test="${locale eq 'en_US'}">
		<!-- 默认英文,无需引入js -->
	</c:when>
	<c:otherwise>
		<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/plupload-2.1.8/js/i18n/en.js" charset="UTF-8"></script>
	</c:otherwise>
</c:choose>

