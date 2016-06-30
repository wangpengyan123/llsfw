<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html >
<html>
<head>
<title>主页</title>
</head>
<body>
	<div>主页</div>
	<a href="${pageContext.request.contextPath}/testNullCol">测试列为空值的时候,map映射的请情况</a>
	<p />
	<a id="ajaxTest" href="#">ajax请求</a>
	<p />
	<a href="${pageContext.request.contextPath}/pageQuery">分页查询</a>
	<p />
	<a href="${pageContext.request.contextPath}/testLang">国际化测试(url后面加上locale=en,则可以切换为英文)</a>
	<p />
	<a href="${pageContext.request.contextPath}/services">WebService</a>
	<p />
	<a href="${pageContext.request.contextPath}/testTransactional">testTransactional</a>
	<p />
	<a href="${pageContext.request.contextPath}/testTransactionalNoExtendsBaseService">testTransactionalNoExtendsBaseService</a>
	<p />
	<a href="${pageContext.request.contextPath}/testTtDynamicDataSource">testTtDynamicDataSource</a>
	<p />
	<a href="${pageContext.request.contextPath}/t/a">无权限访问</a>
	<p />
	<a href="${pageContext.request.contextPath}/logout">退出</a>
	<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#ajaxTest').click(function() {
				$.ajax({
					url : '${pageContext.request.contextPath}/jsontest',
					type : 'get',
					cache : false,
					success : function(data) {
						alert(data.dbsName);
					},
					error : function() {
						// view("异常！");    
						alert("异常！");
					}
				});
			});
		});
	</script>
</body>
</html>
