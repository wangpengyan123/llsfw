$(function() {

    // url数组
    var schedulerChartMainTabsUrlArray = new Array();
    schedulerChartMainTabsUrlArray[0] = basePath + 'quartzController/reportSchedulerRunStatus';

    // 构造
    $('#schedulerCharPlane').panel({
        fit : true,
        border : false,
        tools : '#schedulerCharPlaneTooleBar'
    });

    // 报表类型
    $('#schedulerCharType').combobox({
        required : false,
        valueField : 'value',
        textField : 'text',
        editable : false,
        value : $('#schedulerCharType').val(),
        data : [ {
            "value" : '',
            "text" : '请选择'
        }, {
            "value" : '0',
            "text" : "触发器执行状态统计"
        } ],
        onSelect : function(record) {
            if (record.value) {
                $.messager.progress({
                    text : '请稍后...',
                    interval : 200
                });
                var url = schedulerChartMainTabsUrlArray[parseInt(record.value, 10)];
                $('#schedulerCharPlane').panel('refresh', url);
            }
        }
    });
});
