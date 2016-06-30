/**
 * 
 */
$(function() {
    // 分页条数
    var pageSize = getServerParam(basePath, 'PAGE_SIZE');

    // 查询方法
    function executionHistorySearch() {
        $('#execution_history_table').datagrid('load', {
            executionHistoryTriggerGroup : $('#execution_history_trigger_group').combobox('getValue'),
            executionHistoryTriggerName : $('#execution_history_trigger_name').val(),
            executionHistoryJobGroup : $('#execution_history_job_group').combobox('getValue'),
            executionHistoryJobName : $('#execution_history_job_name').val(),
            executionStatus : $('#execution_status').combobox('getValue'),
            op : $('#op').val()
        });
    }

    // 刷新
    $('#execution_history_search_btn').click(function() {
        $('#execution_history_table').datagrid('load', {
            op : $('#op').val()
        });
        $('#execution_history_trigger_group').combobox('select', '');
        $('#execution_history_trigger_name').val('');
        $('#execution_history_job_group').combobox('select', '');
        $('#execution_history_job_name').val('');
        $('#execution_status').combobox('select', '');
        $('#execution_history_trigger_group').combobox('reload');
        $('#execution_history_job_group').combobox('reload');
    });

    // 绑定查询按钮
    $('#execution_history_trigger_group,#execution_history_trigger_name,#execution_history_job_group,#execution_history_job_name').keydown(function(e) {
        if (e.keyCode == 13) {
            executionHistorySearch();
        }
    });

    // triggerGroup
    $('#execution_history_trigger_group').combobox({
        required : false,
        method : 'get',
        url : basePath + 'quartzController/getTriggerGroupList',
        valueField : 'value',
        textField : 'text',
        editable : true,
        value : $('#execution_history_trigger_group').val(),
        onSelect : function(record) {
            executionHistorySearch();
        }
    });

    // jobGroup
    $('#execution_history_job_group').combobox({
        required : false,
        method : 'get',
        url : basePath + 'quartzController/getJobGroupList',
        valueField : 'value',
        textField : 'text',
        editable : true,
        value : $('#execution_history_job_group').val(),
        onSelect : function(record) {
            executionHistorySearch();
        }
    });

    // 状态
    $('#execution_status').combobox({
        method : 'get',
        url : basePath + 'static/llsfw/json/executionHistoryStatus.json',
        valueField : 'value',
        textField : 'text',
        editable : false,
        onSelect : function(record) {
            executionHistorySearch();
        },
        onLoadSuccess : function() { // 加载完成后,设置选中第一项
            var val = $(this).combobox('getData');
            for ( var item in val[0]) {
                if (item == 'PARAMETERS_TYPE_CODE') {
                    $(this).combobox('select', val[0][item]);
                }
            }
        }
    });

    // 构造表格
    $('#execution_history_table').datagrid({
        url : basePath + 'quartzController/loadExecutionHistoryList',
        toolbar : '#execution_history_search',
        method : 'post',
        rownumbers : true,
        fit : true,
        singleSelect : true,
        pagination : true,
        queryParams : {
            executionHistoryTriggerGroup : $('#execution_history_trigger_group').combobox('getValue'),
            executionHistoryTriggerName : $('#execution_history_trigger_name').val(),
            executionHistoryJobGroup : $('#execution_history_job_group').combobox('getValue'),
            executionHistoryJobName : $('#execution_history_job_name').val(),
            executionStatus : $('#execution_status').combobox('getValue'),
            op : $('#op').val()
        },
        pageSize : pageSize,
        pageList : [ pageSize, pageSize * 2, pageSize * 4, pageSize * 6 ],
        columns : [ [ {
            title : quartz_page_executionHistory_table_SCHEDULED_FIRE_TIME,
            field : 'SCHEDULED_FIRE_TIME',
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : quartz_page_executionHistory_table_FIRE_TIME,
            field : 'FIRE_TIME',
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : quartz_page_executionHistory_table_END_TIME,
            field : 'END_TIME',
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : quartz_page_executionHistory_table_JOB_RUN_TIME,
            field : 'JOB_RUN_TIME',
            align : 'left',
            width : 70
        }, {
            title : quartz_page_executionHistory_table_STATUS,
            field : 'STATUS',
            align : 'left',
            width : 65
        }, {
            title : quartz_page_executionHistory_table_RESULT,
            field : 'RESULT',
            align : 'left',
            width : 60
        }, {
            title : quartz_page_executionHistory_table_ERROR_MSG,
            field : 'ERROR_MSG',
            align : 'center',
            width : 40,
            formatter : function(value, row, index) {
                if (value) {
                    return "<input type='button' class='icon-error' style='border:0px' title='" + quartz_page_executionHistory_table_msg_1 + "' />";
                } else {
                    return "";
                }
            }
        }, {
            title : quartz_page_executionHistory_table_TRIGGER_NAME,
            field : 'TRIGGER_NAME',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_TRIGGER_GROUP,
            field : 'TRIGGER_GROUP',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_TRIGGER_GROUP,
            field : 'JOB_NAME',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_JOB_GROUP,
            field : 'JOB_GROUP',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_JOB_CLASS,
            field : 'JOB_CLASS',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_THREAD_GROUP_NAME,
            field : 'THREAD_GROUP_NAME',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_THREAD_ID,
            field : 'THREAD_ID',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_THREAD_PRIORITY,
            field : 'THREAD_PRIORITY',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_SCHEDULED_ID,
            field : 'SCHEDULED_ID',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_SCHEDULED_NAME,
            field : 'SCHEDULED_NAME',
            align : 'left',
            width : 100
        }, {
            title : quartz_page_executionHistory_table_CREATE_DATE,
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
        onHeaderContextMenu : function(e, field) {
            e.preventDefault();
            if (!cmenu) {
                createColumnMenu();
            }
            cmenu.menu('show', {
                left : e.pageX,
                top : e.pageY
            });
        },
        rowStyler : function(rowIndex, rowData) {
            if (rowData.STATUS == 'error') {
                return 'background-color:red;color:#fff;font-weight:bold;';
            } else if (rowData.STATUS == 'executionVetoed') {
                return 'background-color:blue;color:#fff;font-weight:bold;';
            } else if (rowData.STATUS == 'misfired') {
                return 'background-color:yellow;color:#000;font-weight:bold;';
            } else if (rowData.STATUS == 'triggering' || rowData.STATUS == 'vetoed(false)' || rowData.STATUS == 'vetoed(true)' || rowData.STATUS == 'toBeExecuted' || rowData.STATUS == 'executed') {
                return 'background-color:green;color:#fff;font-weight:bold;';
            }
        },
        onDblClickCell : function(rowIndex, field, value) {
            $('#execution_history_errorMsg_dialog').dialog({
                title : quartz_page_executionHistory_table_msg_2,
                width : 500,
                height : 500,
                cache : false,
                maximizable : true,
                content : value
            });
        },
        onLoadSuccess : function(data) {
            $('#execution_history_table').datagrid('getPager').pagination({
                layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
            });
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    var cmenu;
    function createColumnMenu() {
        cmenu = $('<div/>').appendTo('body');
        cmenu.menu({
            onClick : function(item) {
                if (item.iconCls == 'icon-ok') {
                    $('#execution_history_table').datagrid('hideColumn', item.name);
                    cmenu.menu('setIcon', {
                        target : item.target,
                        iconCls : 'icon-empty'
                    });
                } else {
                    $('#execution_history_table').datagrid('showColumn', item.name);
                    cmenu.menu('setIcon', {
                        target : item.target,
                        iconCls : 'icon-ok'
                    });
                }
            }
        });
        var fields = $('#execution_history_table').datagrid('getColumnFields');
        for (var i = 0; i < fields.length; i++) {
            var field = fields[i];
            var col = $('#execution_history_table').datagrid('getColumnOption', field);
            cmenu.menu('appendItem', {
                text : col.title,
                name : field,
                iconCls : 'icon-ok'
            });
        }
    }
});