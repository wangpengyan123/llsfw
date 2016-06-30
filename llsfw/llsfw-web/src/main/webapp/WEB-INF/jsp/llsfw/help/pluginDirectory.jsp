<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 这里只是展示集成插件的引用方式,页面中不要引用 -->

<!-- jquery ui -->
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/plugins/jQueryUI/jquery-ui.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/jquery-ui.min.js"></script>

<!-- (每个页面必须添加 主样式 -->
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/style.css" rel="stylesheet">

<!-- 小型提示框  http://codeseven.github.io/toastr/demo.html -->
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/toastr/toastr.min.js"></script>

<!-- 表单验证   http://jqueryvalidation.org/-->
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/validation/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/validation/additional-methods.min.js"></script>
<jsp:include page="/WEB-INF/jsp/llsfw/help/validation/localization.jsp" />

<!-- 异步表单  https://github.com/malsup/form -->
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/form/jquery.form.min.js"></script>

<!-- 仿IOS的checkbox控件  http://abpetkov.github.io/switchery/ -->
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/plugins/switchery/switchery.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/switchery/switchery.min.js"></script>

<!-- 模态窗口  https://github.com/nakupanda/bootstrap3-dialog -->
<!-- 可以不引用样式文件,系统有默认的样式 -->
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/plugins/bootstrap3-dialog/bootstrap-dialog.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/bootstrap3-dialog/bootstrap-dialog.min.js"></script>

<!-- 滚动条控制 https://github.com/rochal/jQuery-slimScroll -->
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/slimScroll/jquery.slimscroll.min.js"></script>

<!-- 菜单控件  https://github.com/onokumus/metisMenu -->
<!-- 可以不引用样式文件,系统有默认的样式 -->
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/metisMenu/metisMenu.min.js"></script>

<!-- 页面加载进度条 -->
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/pace/pace.min.js"></script>

<!-- jqGrid  http://www.trirand.com/blog/ -->
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/plugins/jqGrid/ui.jqgrid-bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/llsfw/common/css/plugins/jqGrid/ui.jqgrid-bootstrap-ui.css" rel="stylesheet">
<jsp:include page="/WEB-INF/jsp/llsfw/help/jqGrid/localization.jsp" />
<script src="${pageContext.request.contextPath}/static/llsfw/common/js/plugins/jqGrid/js/jquery.jqGrid.min.js"></script>