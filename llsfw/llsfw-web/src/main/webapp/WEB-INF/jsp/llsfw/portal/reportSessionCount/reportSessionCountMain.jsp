<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="containerSessionCount" style="width: 97%; height: 97%; margin: 0 auto"></div>
<script type="text/javascript">
	$(function() {

		//构造报表
		var reportData = JSON.parse('${data}');
		$('#containerSessionCount').highcharts({
			chart : {
				type : 'bar'
			},
			title : {
				text : ''
			},
			xAxis : {
				categories : reportData.categories,
				title : {
					text : null
				}
			},
			yAxis : {
				min : 0,
				title : {
					text : 'SESSION个数',
					align : 'high'
				},
				labels : {
					overflow : 'justify'
				}
			},
			tooltip : {
				valueSuffix : ' 个'
			},
			plotOptions : {
				bar : {
					dataLabels : {
						enabled : true
					}
				}
			},
			credits : {
				enabled : false
			},
			series : [ {
				name : 'SESSION个数',
				data : reportData.seriesData
			} ]
		});

	});
</script>
