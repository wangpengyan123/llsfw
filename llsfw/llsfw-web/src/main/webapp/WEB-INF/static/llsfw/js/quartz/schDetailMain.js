// ajax请求
function schedulerOpAjax(url) {
    // 开始请求
    $.ajax({
        type : 'POST',
        async : false,
        url : url,
        success : function(data) {
            // 解析数据
            var datas = strToJson(data);
            if (datas.returnCode == '1') {
                $('#scheduler_table').datagrid('load', {});
                showInfoMsg(message_code_8);
            } else {
                showErrorWindow(datas.result);
            }
        }
    });
}

$(function() {

    // 分页条数
    var pageSize = getServerParam(basePath, 'PAGE_SIZE');

    // 清理计划任务数据
    $('#scheduler_table_toolbar_btn_clear').click(function() {
        $.messager.prompt('提示', '清除后数据将无法恢复,请输入操作密码:', function(r) {
            if (r) {
                $.messager.progress({
                    text : '删除中...',
                    interval : 200
                });
                $.ajax({
                    type : 'POST',
                    url : basePath + 'quartzController/schedulerClear?pswd=' + r,
                    success : function(data) {

                        // 关闭遮罩
                        $.messager.progress('close');

                        // 解析数据
                        var datas = strToJson(data);
                        if (datas.returnCode == '1') {
                            $('#scheduler_table').datagrid('load', {});
                            showInfoMsg(message_code_8);
                        } else {
                            showErrorWindow(datas.result);
                        }
                    }
                });
            }
        });
    });

    // 查看操作历史
    $('#scheduler_table_toolbar_btn_option_list').click(function() {
        $('#scheduler_log_window').window({
            title : '操作历史',
            collapsible : false,
            minimizable : false,
            maximizable : true,
            resizable : false,
            modal : true,
            width : 800,
            height : 500
        });
        $('#scheduler_log_table').datagrid('load', {});
    });

    // 刷新按钮
    $('#scheduler_table_toolbar_btn_refresh').click(function() {
        $('#scheduler_table').datagrid('load', {});
    });

    // 恢复所有按钮
    $('#scheduler_table_toolbar_btn_resume_all').click(function() {
        $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_10, function(r) {
            if (r) {
                // url
                var url = basePath + 'quartzController/resumeAllJobOp';
                // 开始请求
                schedulerOpAjax(url);
            }
        });
    });

    // 暂停所有按钮
    $('#scheduler_table_toolbar_btn_puse_job_all').click(function() {
        $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_10, function(r) {
            if (r) {
                // url
                var url = basePath + 'quartzController/puseAllJobOp';
                // 开始请求
                schedulerOpAjax(url);
            }
        });
    });

    // 构造表格
    $('#scheduler_table').datagrid({
        url : basePath + 'quartzController/loadSchedulerState',
        method : 'post',
        rownumbers : true,
        fit : true,
        singleSelect : true,
        toolbar : '#scheduler_table_toolbar',
        columns : [ [ {
            title : '计划任务名称',
            field : 'SCHED_NAME',
            align : 'left',
            width : 200
        }, {
            title : '实例名称',
            field : 'INSTANCE_NAME',
            align : 'left',
            width : 200
        }, {
            title : '最后检查时间',
            field : 'LAST_CHECKIN_TIME',
            align : 'left',
            width : 200,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : '检查间隔(毫秒)',
            field : 'CHECKIN_INTERVAL',
            align : 'left',
            width : 200
        } ] ],
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 构造表格
    $('#scheduler_log_table').datagrid({
        url : basePath + 'quartzController/loadSchedulerLogList',
        method : 'post',
        rownumbers : true,
        fit : true,
        singleSelect : true,
        pagination : true,
        pageSize : pageSize,
        pageList : [ pageSize, pageSize * 2, pageSize * 4, pageSize * 6 ],
        columns : [ [ {
            title : '记录',
            field : 'MSG',
            align : 'left',
            width : 550
        }, {
            title : '时间',
            field : 'CREATE_DATE',
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        } ] ],
        onLoadSuccess : function(data) {
            $('#scheduler_log_table').datagrid('getPager').pagination({
                layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
            });
        },
        onDblClickRow : function(rowIndex, rowData) {
            $('#scheduler_log_msg_dialog').dialog({
                title : '详细信息',
                width : 500,
                height : 500,
                cache : false,
                maximizable : true,
                content : rowData.MSG
            });
        },
        onLoadError : function() {
            showErrorWindow('数据加载失败!');
        }
    });

});
