// url数组
var triggerMainTabsUrlArray = new Array();
triggerMainTabsUrlArray[0] = basePath + 'quartzController/toExecutionHistoryPage?op=triggersMain';
triggerMainTabsUrlArray[1] = basePath + 'quartzController/toTriggerChart?op=jobsMain';

// 刷新tab
function resetTriggerMainTabs(tab, index) {
    tab.panel('refresh', getTriggerMainUrl(index));
}

// 获得url
function getTriggerMainUrl(index) {
    var i = parseInt(index, 10);
    var rowData = $('#trigger_table').datagrid('getSelected');
    if (rowData) {
        return triggerMainTabsUrlArray[index] + '&triggerName=' + rowData.TRIGGER_NAME + '&triggerGroup=' + rowData.TRIGGER_GROUP;
    } else {
        return triggerMainTabsUrlArray[index];
    }
}

// 查询方法
function triggerSearch() {
    $('#trigger_table').datagrid('load', {
        jobName : $('#trigger_table_toolbar_input_jobName').val(),
        jobGroup : $('#trigger_table_toolbar_input_jobGroup').combobox('getValue'),
        triggerName : $('#trigger_table_toolbar_input_triggerName').val(),
        triggerGroup : $('#trigger_table_toolbar_input_triggerGroup').combobox('getValue'),
        op : $('#op').val()
    });
}

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
                triggerSearch();
                showInfoMsg(message_code_8);
            } else {
                showErrorWindow(datas.result);
            }
        }
    });
}

$(function() {

    // 刷新按钮
    $('#trigger_table_toolbar_btn_refresh').click(function() {
        $('#trigger_table').datagrid('load', {
            op : $('#op').val()
        });
        $('#trigger_table_toolbar_input_jobName').val('');
        $('#trigger_table_toolbar_input_jobGroup').combobox('select', '');
        $('#trigger_table_toolbar_input_triggerName').val('');
        $('#trigger_table_toolbar_input_triggerGroup').combobox('select', '');
        $('#trigger_table_toolbar_input_jobGroup').combobox('reload');
        $('#trigger_table_toolbar_input_triggerGroup').combobox('reload');
    });

    // 绑定查询按钮
    $('#trigger_table_toolbar_input_jobName,#trigger_table_toolbar_input_jobGroup,#trigger_table_toolbar_input_triggerName,#trigger_table_toolbar_input_triggerGroup').keydown(function(e) {
        if (e.keyCode == 13) {
            triggerSearch()
        }
    });

    // triggerGroup
    $('#trigger_table_toolbar_input_triggerGroup').combobox({
        required : false,
        method : 'get',
        url : basePath + 'quartzController/getTriggerGroupList',
        valueField : 'value',
        textField : 'text',
        editable : true,
        value : $('#trigger_table_toolbar_input_triggerGroup').val(),
        onSelect : function(record) {
            triggerSearch()
        }
    });

    // jobGroup
    $('#trigger_table_toolbar_input_jobGroup').combobox({
        required : false,
        method : 'get',
        url : basePath + 'quartzController/getJobGroupList',
        valueField : 'value',
        textField : 'text',
        editable : true,
        value : $('#trigger_table_toolbar_input_jobGroup').val(),
        onSelect : function(record) {
            triggerSearch()
        }
    });

    // 构造表格
    $('#trigger_table').datagrid({
        url : basePath + 'quartzController/getTriggers',
        method : 'post',
        queryParams : {
            jobName : $('#trigger_table_toolbar_input_jobName').val(),
            jobGroup : $('#trigger_table_toolbar_input_jobGroup').combobox('getValue'),
            triggerName : $('#trigger_table_toolbar_input_triggerName').val(),
            triggerGroup : $('#trigger_table_toolbar_input_triggerGroup').combobox('getValue'),
            op : $('#op').val()
        },
        view : detailview,
        rownumbers : true,
        fit : true,
        singleSelect : true,
        toolbar : '#trigger_table_toolbar',
        columns : [ [ {
            title : quartz_page_jobTriggers_table_TRIGGER_NAME,
            field : 'TRIGGER_NAME',
            align : 'left',
            sortable : false,
            width : 200
        }, {
            title : quartz_page_jobTriggers_table_TRIGGER_GROUP,
            field : 'TRIGGER_GROUP',
            align : 'left',
            sortable : false,
            width : 150
        }, {
            title : quartz_page_jobTriggers_table_JOB_NAME,
            field : 'JOB_NAME',
            align : 'left',
            sortable : false,
            width : 200
        }, {
            title : quartz_page_jobTriggers_table_JOB_GROUP,
            field : 'JOB_GROUP',
            align : 'left',
            sortable : false,
            width : 150
        }, {
            title : quartz_page_jobTriggers_table_JOB_DATA,
            field : 'JOB_DATA',
            sortable : false,
            align : 'center',
            width : 70,
            formatter : function(value, row, index) {
                return '<a href="#"><i class="fa fa-database fa-lg"></i></a>';
            }
        }, {
            title : quartz_page_jobTriggers_table_DESCRIPTION,
            field : 'DESCRIPTION',
            sortable : false,
            align : 'left',
            width : 150
        }, {
            title : quartz_page_jobTriggers_table_NEXT_FIRE_TIME,
            field : 'NEXT_FIRE_TIME',
            sortable : false,
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : quartz_page_jobTriggers_table_PREV_FIRE_TIME,
            field : 'PREV_FIRE_TIME',
            sortable : false,
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : quartz_page_jobTriggers_table_PRIORITY,
            field : 'PRIORITY',
            sortable : false,
            align : 'left',
            width : 50
        }, {
            title : quartz_page_jobTriggers_table_TRIGGER_STATE,
            field : 'TRIGGER_STATE',
            sortable : false,
            align : 'left',
            width : 70
        }, {
            title : quartz_page_jobTriggers_table_TRIGGER_TYPE,
            field : 'TRIGGER_TYPE',
            sortable : false,
            align : 'left',
            width : 70
        }, {
            title : quartz_page_jobTriggers_table_START_TIME,
            field : 'START_TIME',
            sortable : false,
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : quartz_page_jobTriggers_table_END_TIME,
            field : 'END_TIME',
            sortable : false,
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : quartz_page_jobTriggers_table_CALENDAR_NAME,
            field : 'CALENDAR_NAME',
            sortable : false,
            align : 'left',
            width : 150
        }, {
            title : quartz_page_jobTriggers_table_MISFIRE_INSTR,
            field : 'MISFIRE_INSTR',
            sortable : false,
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (row.TRIGGER_TYPE == 'SIMPLE') {
                    if (value == '-1') {
                        return 'Ignore Misfire Policy';
                    } else if (value == 0) {
                        return 'Smart Policy';
                    } else if (value == '1') {
                        return 'Fire Now';
                    } else if (value == '2') {
                        return 'Reschedule Now With Existing Repeat Count';
                    } else if (value == '3') {
                        return 'Reschedule Now With Remaining Repeat Count';
                    } else if (value == '4') {
                        return 'Reschedule Next With Remaining Count';
                    } else if (value == '5') {
                        return 'Reschedule Next With Existing Count';
                    } else {
                        return '未知';
                    }
                } else if (row.TRIGGER_TYPE == 'CRON') {
                    if (value == '-1') {
                        return 'Ignore Misfire Policy';
                    } else if (value == 0) {
                        return 'Smart Policy';
                    } else if (value == '1') {
                        return 'Fire Once Now';
                    } else if (value == '2') {
                        return 'Do Nothing';
                    } else {
                        return '未知';
                    }
                } else {
                    return '未知';
                }
            }
        } ] ],
        detailFormatter : function(index, row) {
            return '<div class="ddv" style="padding:2px 0"></div>';
        },
        onExpandRow : function(index, row) {
            var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
            ddv.panel({
                height : 'auto',
                border : false,
                cache : false,
                href : basePath + 'quartzController/getTriggerDetail?triggerName=' + row.TRIGGER_NAME + '&triggerGroup=' + row.TRIGGER_GROUP + '&triggerType=' + row.TRIGGER_TYPE,
                onLoad : function() {
                    $('#trigger_table').datagrid('fixDetailRowHeight', index);
                }
            });
            $('#trigger_table').datagrid('fixDetailRowHeight', index);
        },
        onClickCell : function(index, field, value) {
            if (field == 'JOB_DATA') {
                var row = $('#trigger_table').datagrid('getRows')[index];
                if (row.JOB_NAME && row.JOB_GROUP) {
                    $('#trigger_data_window').window({
                        title : quartz_page_jobsmain_jobData_window_title,
                        width : 500,
                        height : 320,
                        collapsible : false,
                        minimizable : false,
                        maximizable : false,
                        resizable : false,
                        modal : true
                    });
                    $('#trigger_data_window_table').datagrid('load', {
                        triggerName : row.TRIGGER_NAME,
                        triggerGroup : row.TRIGGER_GROUP
                    });
                }
            }
        },
        onClickRow : function(rowIndex, rowData) {
            try {
                var tab = $('#triggersMainTabs').tabs('getSelected');
                var index = $('#triggersMainTabs').tabs('getTabIndex', tab);
                resetTriggerMainTabs(tab, index);
            } catch (e) {
                // 不影响其他功能
            }
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // trigger data map
    $('#trigger_data_window_table').datagrid({
        url : basePath + 'quartzController/getTriggerDataMap',
        rownumbers : true,
        singleSelect : true,
        fitColumns : true,
        fit : true,
        scrollbarSize : 0,
        idField : 'name',
        columns : [ [ {
            title : 'Name',
            field : 'name',
            width : '45%'
        }, {
            title : 'Value',
            field : 'value',
            width : '45%'
        } ] ],
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 恢复触发器
    $('#trigger_table_toolbar_btn_resume_job').click(function() {
        var row = $('#trigger_table').datagrid('getSelected');
        if (row) {
            // 获得参数
            var triggerName = row.TRIGGER_NAME;
            var triggerGroup = row.TRIGGER_GROUP;
            // 拼接请求参数
            var param = '?triggerName=' + triggerName + '&triggerGroup=' + triggerGroup;
            // url
            var url = basePath + 'quartzController/resumeTriggerOp' + param;
            // 开始请求
            schedulerOpAjax(url);
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 暂停触发器
    $('#trigger_table_toolbar_btn_puse_job').click(function() {
        var row = $('#trigger_table').datagrid('getSelected');
        if (row) {
            // 获得参数
            var triggerName = row.TRIGGER_NAME;
            var triggerGroup = row.TRIGGER_GROUP;
            // 拼接请求参数
            var param = '?triggerName=' + triggerName + '&triggerGroup=' + triggerGroup;
            // url
            var url = basePath + 'quartzController/puseTriggerOp' + param;
            // 开始请求
            schedulerOpAjax(url);
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 删除trigger
    $('#trigger_table_toolbar_btn_delete').click(function() {
        var row = $('#trigger_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorWindow_title, message_code_10, function(r) {
                if (r) {
                    // 获得参数
                    var triggerName = row.TRIGGER_NAME;
                    var triggerGroup = row.TRIGGER_GROUP;
                    // 拼接请求参数
                    var param = '?triggerName=' + triggerName + '&triggerGroup=' + triggerGroup;
                    // url
                    var url = basePath + 'quartzController/deleteTriggerOp' + param;
                    // 开始请求
                    schedulerOpAjax(url);
                }
            });
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 打开添加cronTrigger的窗口
    function openCronTriggerWindow(param) {
        var url = basePath + 'quartzController/toAddCronTrigger' + param;
        var row;
        try {
            row = $('#jobs_table').datagrid('getSelected');
        } catch (e) {
            // 这个错误可以忽略
        }
        if (row) {
            url += '&jobName=' + row.JOB_NAME + '&jobGroup=' + row.JOB_GROUP;
        }
        $('#cron_trigger_add_window').window({
            title : '编辑CRON触发器',
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 500,
            height : 620,
            href : url,
        });
    }

    // 打开添加simpleTrigger的窗口
    function openSimpleTriggerWindow(param) {
        var url = basePath + 'quartzController/toAddSimpleTrigger' + param;
        var row;
        try {
            row = $('#jobs_table').datagrid('getSelected');
        } catch (e) {
            // 这个错误可以忽略
        }
        if (row) {
            url += '&jobName=' + row.JOB_NAME + '&jobGroup=' + row.JOB_GROUP;
        }
        $('#simple_trigger_add_window').window({
            title : '编辑SIMPLE触发器',
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 500,
            height : 620,
            href : url,
        });
    }

    // 添加cron触发器
    $('#cron_trigger_add').click(function() {
        var param = '?addTriggerOp=add';
        openCronTriggerWindow(param);
    });

    // 添加SIMPLE触发器
    $('#simple_trigger_add').click(function() {
        var addTriggerOp = 'add';
        var param = '?addTriggerOp=' + addTriggerOp;
        openSimpleTriggerWindow(param);
    });

    // 复制新增
    $('#trigger_table_toolbar_btn_copy_add').click(function() {
        var addTriggerOp = 'copyAdd';
        var row = $('#trigger_table').datagrid('getSelected');
        if (row) {
            var param = '?addTriggerOp=' + addTriggerOp + '&triggerName=' + row.TRIGGER_NAME + '&triggerGroup=' + row.TRIGGER_GROUP;
            if (row.TRIGGER_TYPE == 'CRON') {
                openCronTriggerWindow(param);
            } else if (row.TRIGGER_TYPE == 'SIMPLE') {
                openSimpleTriggerWindow(param);
            } else {
                showErrorMsg('无法复制此触发器类型' + row.TRIGGER_TYPE);
            }
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 修改
    $('#trigger_table_toolbar_btn_edit').click(function() {
        var addTriggerOp = 'edit';
        var row = $('#trigger_table').datagrid('getSelected');
        if (row) {
            var param = '?addTriggerOp=' + addTriggerOp + '&triggerName=' + row.TRIGGER_NAME + '&triggerGroup=' + row.TRIGGER_GROUP;
            if (row.TRIGGER_TYPE == 'CRON') {
                openCronTriggerWindow(param);
            } else if (row.TRIGGER_TYPE == 'SIMPLE') {
                openSimpleTriggerWindow(param);
            } else {
                showErrorMsg('无法修改此触发器类型' + row.TRIGGER_TYPE);
            }
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 这里初始化triggerMain界面的tabs,在job视图中会报错,这个错误没有后续影响
    try {
        // 初始化tabs
        $('#triggersMainTabs').tabs({
            fit : true,
            border : false,
            plain : true,
            onSelect : function(title, index) {
                var tab = $('#triggersMainTabs').tabs('getSelected');
                $('#triggersMainTabs').tabs('update', {
                    tab : tab,
                    options : {
                        href : getTriggerMainUrl(index)
                    }
                });
            }
        });
    } catch (e) {
        // 不影响其他功能
    }
})