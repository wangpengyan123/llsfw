<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- script -->
<script type="text/javascript">
    var path = '${pageContext.request.contextPath}';//上下文路径
    var basePath = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/';//全路径
</script>

<!-- localStript -->
<%-- ${localStript} --%>

<!-- js -->
<script src="${pageContext.request.contextPath}/static/llsfw/common/jquery/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath}/static/llsfw/common/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/toastr/toastr.min.js"></script>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/js/base.js"></script> --%>