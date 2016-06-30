<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<table style="width: 500px; border: 1px #A0A0A0 solid; border-spacing: 0; border-collapse: collapse;">
	<tr>
		<td width="50%" style="border: 1px #A0A0A0 solid; background-color: #D1DFF1;"><strong>Property</strong></td>
		<td width="50%" style="border: 1px #A0A0A0 solid; background-color: #D1DFF1;"><strong>Value</strong></td>
	</tr>
	<c:forEach var="item" items="${data}">
		<tr>
			<td style="border: 1px #A0A0A0 solid;">${item.p}</td>
			<td style="border: 1px #A0A0A0 solid;">${item.v}</td>
		</tr>
	</c:forEach>
</table>