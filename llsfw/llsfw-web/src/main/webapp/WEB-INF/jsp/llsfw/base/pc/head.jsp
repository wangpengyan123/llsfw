<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- meta -->
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- css -->
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/easyui_themes.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/themes/icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/themes/color.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/css/base.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/html5shiv/html5shiv-printshiv.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/respond/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/llsfw/common/plugins/respond/html5shiv-printshiv.min.js"></script>
<![endif]-->

<!-- localStript -->
${localStript}

<!-- js -->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/common/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/common/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<jsp:include page="/WEB-INF/jsp/llsfw/base/pc/easyui_lang.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/js/validatebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/js/base.js"></script>

<!-- plugin -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/common/plugins/font-awesome-4.5.0/css/font-awesome.min.css">

<!-- script -->
<script type="text/javascript">
    var path = '${pageContext.request.contextPath}';//上下文路径
    var basePath = '${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/';//全路径
    //easyui组件初始化时，由于样式后面才会加载，会造成页面瞬间样式乱掉，如果数据量大则看起来很不美观，下面语句加载loading遮罩。
    function closeGlobalLoading(){
        $("#GlobalLoading").fadeOut("normal",function(){
            $(this).remove();
        });
    }
    var globalLoading;
    $.parser.onComplete = function(){
        closeGlobalLoading();
    }
</script>

<!-- default dom -->
<div id="ajaxRequestErrWindow"></div>
<div id='GlobalLoading' style="position:absolute;z-index:9999;top:0px;left:0px;width:100%;height:100%;background:#FFFFFF;text-align:center;padding-top: 20%;"></div>
