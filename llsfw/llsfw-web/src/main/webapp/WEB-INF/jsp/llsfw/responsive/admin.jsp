<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title><spring:message code="system.systemName" /></title>
<jsp:include page="/WEB-INF/jsp/llsfw/base/responsive/begin.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/llsfw/css/responsive/admin.css">
</head>
<body>
	<!-- 头部 -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- 标题部分 -->
			<div class="navbar-header">
				<!-- 小屏幕的菜单按钮 -->
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="icon-bar"></span><span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<!-- 标题 -->
				<a class="navbar-brand" href="#">
					<spring:message code="system.systemName" />
				</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<!-- 靠左的标签 -->
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">控制台 </a></li>
					<li class="visible-sm-block visible-xs-block"><a href="#">功能菜单(<768px)(≥768px)</a></li>
				</ul>
				<!-- 靠右的标签 -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">
							<span title="变更语言" class="glyphicon glyphicon-text-background visible-md-block visible-lg-block visible-sm-block" aria-hidden="true"></span><span class="visible-xs-block">变更语言</span>
						</a></li>
					<li><a href="#">
							<span title="变更主题" class="glyphicon glyphicon-blackboard visible-md-block visible-lg-block visible-sm-block" aria-hidden="true"></span><span class="visible-xs-block">变更主题</span>
						</a></li>
					<li><a href="#">
							<span title="修改密码" class="glyphicon glyphicon-lock visible-md-block visible-lg-block visible-sm-block" aria-hidden="true"></span><span class="visible-xs-block">修改密码</span>
						</a></li>
					<li><a href="${pageContext.request.contextPath}/logout">
							<span title="退出" class="glyphicon glyphicon-off visible-md-block visible-lg-block visible-sm-block" aria-hidden="true"></span><span class="visible-xs-block">退出</span>
						</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 主体 -->
	<div class="container-fluid">
		<div class="row">
			<!-- 左边菜单-->
			<div class="col-md-2 left-user-panle visible-md-block visible-lg-block">
				<div class="thumbnail">
					<img width="50%" height="50%" class="img-circle" src="${pageContext.request.contextPath}/static/llsfw/img/default-user.png" alt="头像">
					<div class="text-center">
						<h3>张三三</h3>
						<h6>销售经理</h6>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-body">
						<div clss="row">
							<div class="col-lg-6 text-center left-function-panle">
								<img width="48px" height="48px" src="${pageContext.request.contextPath}/static/llsfw/img/function_png_48x48/test0.png">
								<p>系统管理</p>
							</div>
							<div class="col-lg-6 text-center left-function-panle">
								<img width="48px" height="48px" src="${pageContext.request.contextPath}/static/llsfw/img/function_png_48x48/test1.png">
								<p>功能维护</p>
							</div>
						</div>
						<div clss="row">
							<div class="col-lg-6 text-center left-function-panle">
								<img width="48px" height="48px" src="${pageContext.request.contextPath}/static/llsfw/img/function_png_48x48/test2.png">
								<p>功能维护</p>
							</div>
							<div class="col-lg-6 text-center left-function-panle">
								<img width="48px" height="48px" src="${pageContext.request.contextPath}/static/llsfw/img/function_png_48x48/test3.png">
								<p>功能维护</p>
							</div>
						</div>
						<div clss="row">
							<div class="col-lg-6 text-center left-function-panle">
								<img width="48px" height="48px" src="${pageContext.request.contextPath}/static/llsfw/img/function_png_48x48/test4.png">
								<p>功能维护</p>
							</div>
							<div class="col-lg-6 text-center left-function-panle">
								<img width="48px" height="48px" src="${pageContext.request.contextPath}/static/llsfw/img/function_png_48x48/test5.png">
								<p>功能维护</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 右边功能 -->
			<div class="col-md-10">
				<!-- 二级和三级菜单 -->
				<div class="panel panel-default visible-md-block visible-lg-block">
					<div class="panel-body">
						<ul class="nav nav-pills">
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									权限维护 <span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">用户维护</a></li>
									<li><a href="#">岗位维护</a></li>
									<li><a href="#">角色维护</a></li>
									<li><a href="#">功能维护</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									权限维护 <span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">用户维护</a></li>
									<li><a href="#">岗位维护</a></li>
									<li><a href="#">角色维护</a></li>
									<li><a href="#">功能维护</a></li>
								</ul></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
									权限维护 <span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#">用户维护</a></li>
									<li><a href="#">岗位维护</a></li>
									<li><a href="#">角色维护</a></li>
									<li><a href="#">功能维护</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
				<!-- 功能主体 -->
				<div style="height: 1000px" class="panel panel-default">
					<div class="panel-body">asdasd</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/llsfw/base/responsive/end.jsp" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/llsfw/responsive/admin.js"></script>
</body>
</html>
