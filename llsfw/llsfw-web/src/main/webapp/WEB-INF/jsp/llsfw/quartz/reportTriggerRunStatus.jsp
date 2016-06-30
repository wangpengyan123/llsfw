<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<div id="container" style="height: auto; margin: 0 auto"></div>
<script type="text/javascript">
    $(function() {

        // 关闭遮罩
        $.messager.progress('close');

        //构造报表
        var reportData = JSON.parse('${data}');
        $('#container').highcharts({
            chart : {
                plotBackgroundColor : null,
                plotBorderWidth : null,
                plotShadow : false,
                type : 'pie'
            },
            title : {
                text : 'Report Trigger Run Status'
            },
            credits : {
                enabled : false
            },
            plotOptions : {
                pie : {
                    allowPointSelect : true,
                    cursor : 'pointer',
                    dataLabels : {
                        enabled : true,
                        format : '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style : {
                            color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
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

