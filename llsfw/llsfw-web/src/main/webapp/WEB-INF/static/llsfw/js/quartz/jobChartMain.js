$(function() {

    // url数组
    var jobChartMainTabsUrlArray = new Array();
    jobChartMainTabsUrlArray[0] = basePath + 'quartzController/reportJobRunStatus';

    // 构造
    $('#jobCharPlane').panel({
        fit : true,
        border : false,
        tools : '#jobCharPlaneTooleBar'
    });

    // 报表类型
    $('#jobCharType').combobox({
        required : false,
        valueField : 'value',
        textField : 'text',
        editable : false,
        value : $('#jobCharType').val(),
        data : [ {
            "value" : '',
            "text" : '请选择'
        }, {
            "value" : '0',
            "text" : "作业执行状态统计"
        } ],
        onSelect : function(record) {
            if (record.value) {
                var rowData = $('#jobs_table').datagrid('getSelected');
                if (rowData) {
                    var url = jobChartMainTabsUrlArray[parseInt(record.value, 10)];
                    url += '?jobName=' + rowData.JOB_NAME + '&jobGroup=' + rowData.JOB_GROUP;
                    $.messager.progress({
                        text : '请稍后...',
                        interval : 200
                    });
                    $('#jobCharPlane').panel('refresh', url);
                } else {
                    showErrorMsg(message_code_11);
                }
            }
        }
    });
});
