<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script type="text/javascript">
    var tableName = '${tableName}';
    var columns = JSON.parse('[${columns}]');
</script>
<script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/llsfw/js/activiti/settings/engineDataBase/engineDataBaseTableRows.js"></script>
<table id="engineDataBaseTableRowsTable"></table>