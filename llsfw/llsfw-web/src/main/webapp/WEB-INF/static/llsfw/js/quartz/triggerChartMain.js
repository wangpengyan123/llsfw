$(function() {

    // url数组
    var triggerChartMainTabsUrlArray = new Array();
    triggerChartMainTabsUrlArray[0] = basePath + 'quartzController/reportTriggerRunStatus';

    // 构造
    $('#triggerCharPlane').panel({
        fit : true,
        border : false,
        tools : '#triggerCharPlaneTooleBar'
    });

    // 报表类型
    $('#triggerCharType').combobox({
        required : false,
        valueField : 'value',
        textField : 'text',
        editable : false,
        value : $('#triggerCharType').val(),
        data : [ {
            "value" : '',
            "text" : '请选择'
        }, {
            "value" : '0',
            "text" : "触发器执行状态统计"
        } ],
        onSelect : function(record) {
            if (record.value) {
                var rowData = $('#trigger_table').datagrid('getSelected');
                if (rowData) {
                    var url = triggerChartMainTabsUrlArray[parseInt(record.value, 10)];
                    url += '?triggerName=' + rowData.TRIGGER_NAME + '&triggerGroup=' + rowData.TRIGGER_GROUP;
                    $.messager.progress({
                        text : '请稍后...',
                        interval : 200
                    });
                    $('#triggerCharPlane').panel('refresh', url);
                } else {
                    showErrorMsg(message_code_11);
                }
            }
        }
    });
});
