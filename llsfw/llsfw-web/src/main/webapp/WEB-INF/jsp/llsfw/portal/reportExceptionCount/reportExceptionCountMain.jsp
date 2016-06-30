<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="containerExceptionCount" style="width: 97%; height: 97%; margin: 0 auto"></div>
<script type="text/javascript">
	$(function() {

		//构造报表
		var reportData = JSON.parse('${data}');
		$('#containerExceptionCount').highcharts({
			chart : {
				plotBackgroundColor : null,
				plotBorderWidth : null,
				plotShadow : false,
				type : 'pie'
			},
			title : {
				text : ''
			},
			credits : {
				enabled : false
			},
			plotOptions : {
				pie : {
					allowPointSelect : true,
					cursor : 'pointer',
					dataLabels : {
						format : '{point.percentage:.1f}%',
						enabled : true,
						style : {
							color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
						}
					},
					showInLegend : true
				}
			},
			series : [ {
				name : '次数',
				colorByPoint : true,
				data : reportData
			} ]
		});

	});
</script>
