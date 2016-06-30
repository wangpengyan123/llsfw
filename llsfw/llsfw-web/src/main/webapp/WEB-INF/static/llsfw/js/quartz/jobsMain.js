// url数组
var jobMainTabsUrlArray = new Array();
jobMainTabsUrlArray[0] = basePath + 'quartzController/toExecutionHistoryPage?op=jobsMain';
jobMainTabsUrlArray[1] = basePath + 'quartzController/toJobTriggersPage?op=jobsMain';
jobMainTabsUrlArray[2] = basePath + 'quartzController/toJobChart?op=jobsMain';

// 刷新tab
function resetjobMainTabs(tab, index) {
    tab.panel('refresh', getUrl(index));
}

// 获得url
function getUrl(index) {
    var i = parseInt(index, 10);
    var rowData = $('#jobs_table').datagrid('getSelected');
    if (rowData) {
        return jobMainTabsUrlArray[index] + '&jobName=' + rowData.JOB_NAME + '&jobGroup=' + rowData.JOB_GROUP;
    } else {
        return jobMainTabsUrlArray[index];
    }
}

// 表格查询方法
function jobs_table_search() {
    $('#jobs_table').datagrid('load', {
        jobGroup : $('#jobs_table_toolbar_input_jobGroup').combobox('getValue'),
        jobName : $('#jobs_table_toolbar_input_jobName').val(),
        jobClassName : $('#jobs_table_toolbar_input_jobClassName').val(),
        description : $('#jobs_table_toolbar_input_description').val()
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
                jobs_table_search();
                showInfoMsg(message_code_8);
            } else {
                showErrorWindow(datas.result);
            }
        }
    });
}

$(function() {

    // jobGroup
    $('#jobs_table_toolbar_input_jobGroup').combobox({
        required : false,
        method : 'get',
        url : basePath + 'quartzController/getJobGroupList',
        valueField : 'value',
        textField : 'text',
        editable : true,
        value : $('#jobs_table_toolbar_input_jobGroup').val(),
        onSelect : function(record) {
            jobs_table_search();
        }
    });

    // 构造表格
    $('#jobs_table').datagrid({
        url : basePath + 'quartzController/getJobs',
        method : 'post',
        rownumbers : true,
        fit : true,
        singleSelect : true,
        toolbar : '#jobs_table_toolbar',
        frozenColumns : [ [ {
            title : quartz_page_jobsmain_table_jobName,
            field : 'JOB_NAME',
            align : 'left',
            sortable : false,
            width : 250
        }, {
            title : quartz_page_jobsmain_table_jobGroup,
            field : 'JOB_GROUP',
            align : 'left',
            sortable : false,
            width : 250
        } ] ],
        columns : [ [ {
            title : quartz_page_jobsmain_table_jobData,
            field : 'JOB_DATA',
            sortable : false,
            align : 'center',
            width : 70,
            formatter : function(value, row, index) {
                return '<a href="#"><i class="fa fa-database fa-lg"></i></a>';
            }
        }, {
            title : quartz_page_jobsmain_table_description,
            field : 'DESCRIPTION',
            align : 'left',
            sortable : false,
            width : 250
        }, {
            title : quartz_page_jobsmain_table_jobClassName,
            field : 'JOB_CLASS_NAME',
            align : 'left',
            sortable : false,
            width : 350
        }, {
            title : quartz_page_jobsmain_table_isDurable,
            field : 'IS_DURABLE',
            sortable : false,
            align : 'center',
            width : 70,
            formatter : function(value, row, index) {
                if (value) {
                    if (value == "1") {
                        return '<i class="fa fa-check fa-lg"></i>';
                    } else {
                        return '<i class="fa fa-times fa-lg"></i>';
                    }
                } else {
                    return '<i class="fa fa-times fa-lg"></i>';
                }

            }
        }, {
            title : quartz_page_jobsmain_table_isNonconcurrent,
            field : 'IS_NONCONCURRENT',
            sortable : false,
            align : 'center',
            width : 70,
            formatter : function(value, row, index) {
                if (value) {
                    if (value == "1") {
                        return '<i class="fa fa-check fa-lg"></i>';
                    } else {
                        return '<i class="fa fa-times fa-lg"></i>';
                    }
                } else {
                    return '<i class="fa fa-times fa-lg"></i>';
                }

            }
        }, {
            title : quartz_page_jobsmain_table_isUpdateData,
            field : 'IS_UPDATE_DATA',
            sortable : false,
            align : 'center',
            width : 70,
            formatter : function(value, row, index) {
                if (value) {
                    if (value == "1") {
                        return '<i class="fa fa-check fa-lg"></i>';
                    } else {
                        return '<i class="fa fa-times fa-lg"></i>';
                    }
                } else {
                    return '<i class="fa fa-times fa-lg"></i>';
                }

            }
        }, {
            title : quartz_page_jobsmain_table_requestsRecovery,
            field : 'REQUESTS_RECOVERY',
            sortable : false,
            align : 'center',
            width : 70,
            formatter : function(value, row, index) {
                if (value) {
                    if (value == "1") {
                        return '<i class="fa fa-check fa-lg"></i>';
                    } else {
                        return '<i class="fa fa-times fa-lg"></i>';
                    }
                } else {
                    return '<i class="fa fa-times fa-lg"></i>';
                }

            }
        } ] ],
        onClickCell : function(index, field, value) {
            if (field == 'JOB_DATA') {
                var row = $('#jobs_table').datagrid('getRows')[index];
                if (row.JOB_NAME && row.JOB_GROUP) {
                    $('#job_data_window').window({
                        title : quartz_page_jobsmain_jobData_window_title,
                        width : 500,
                        height : 320,
                        collapsible : false,
                        minimizable : false,
                        maximizable : false,
                        resizable : false,
                        modal : true
                    });
                    $('#job_data_window_table').datagrid('load', {
                        jobName : row.JOB_NAME,
                        jobGroup : row.JOB_GROUP
                    });
                }
            }
        },
        onClickRow : function(rowIndex, rowData) {
            var tab = $('#jobMainTabs').tabs('getSelected');
            var index = $('#jobMainTabs').tabs('getTabIndex', tab);
            resetjobMainTabs(tab, index);
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // job data map
    $('#job_data_window_table').datagrid({
        url : basePath + 'quartzController/getJobDetailDataMap',
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

    // 刷新按钮
    $('#jobs_table_toolbar_btn_refresh').click(function() {
        $('#jobs_table').datagrid('load', {});
        $('#jobs_table_toolbar_input_jobGroup').combobox('select', '');
        $('#jobs_table_toolbar_input_jobName').val('');
        $('#jobs_table_toolbar_input_jobClassName').val('');
        $('#jobs_table_toolbar_input_description').val('');
        $('#jobs_table_toolbar_input_jobGroup').combobox('reload');

    });

    // 绑定查询按钮
    $('#jobs_table_toolbar_input_jobName,#jobs_table_toolbar_input_jobGroup,#jobs_table_toolbar_input_jobClassName,#jobs_table_toolbar_input_description').keydown(function(e) {
        if (e.keyCode == 13) {
            jobs_table_search();
        }
    });

    // 删除JOB
    $('#jobs_table_toolbar_btn_delete').click(function() {
        var row = $('#jobs_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorWindow_title, message_code_10, function(r) {
                if (r) {
                    // 获得参数
                    var jobName = row.JOB_NAME;
                    var jobGroup = row.JOB_GROUP;
                    // 拼接请求参数
                    var param = '?jobName=' + jobName + '&jobGroup=' + jobGroup;
                    // url
                    var url = basePath + 'quartzController/deleteJobOp' + param;
                    // 开始请求
                    schedulerOpAjax(url);
                }
            });
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 新增JOB
    $('#jobs_table_toolbar_btn_add').click(function() {
        // 获得参数
        var op = 'add';
        // 弹出编辑job的窗口
        $('#job_detail_add_window').window({
            title : quartz_page_jobsmain_edit_window_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 460,
            height : 500,
            href : basePath + 'quartzController/toAddJobDetail?op=' + op
        });
    });

    // 复制新增JOB
    $('#jobs_table_toolbar_btn_copy_add').click(function() {
        var row = $('#jobs_table').datagrid('getSelected');
        if (row) {
            // 获得参数
            var op = 'copyAdd';
            var jobName = row.JOB_NAME;
            var jobGroup = row.JOB_GROUP;
            // 拼接请求参数
            var param = '?op=' + op + '&jobName=' + jobName + '&jobGroup=' + jobGroup;
            // 弹出编辑job的窗口
            $('#job_detail_add_window').window({
                title : quartz_page_jobsmain_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 480,
                height : 500,
                href : basePath + 'quartzController/toAddJobDetail' + param
            });
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 修改JOB
    $('#jobs_table_toolbar_btn_edit').click(function() {
        var row = $('#jobs_table').datagrid('getSelected');
        if (row) {
            // 获得参数
            var op = 'edit';
            var jobName = row.JOB_NAME;
            var jobGroup = row.JOB_GROUP;
            // 拼接请求参数
            var param = '?op=' + op + '&jobName=' + jobName + '&jobGroup=' + jobGroup;
            // 弹出编辑job的窗口
            $('#job_detail_add_window').window({
                title : quartz_page_jobsmain_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 480,
                height : 500,
                href : basePath + 'quartzController/toAddJobDetail' + param
            });
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 恢复所有按钮
    $('#jobs_table_toolbar_btn_resume_all').click(function() {
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
    $('#jobs_table_toolbar_btn_puse_job_all').click(function() {
        $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_10, function(r) {
            if (r) {
                // url
                var url = basePath + 'quartzController/puseAllJobOp';
                // 开始请求
                schedulerOpAjax(url);
            }
        });
    });

    // 恢复按钮
    $('#jobs_table_toolbar_btn_resume_job').click(function() {
        var row = $('#jobs_table').datagrid('getSelected');
        if (row) {
            // 获得参数
            var jobName = row.JOB_NAME;
            var jobGroup = row.JOB_GROUP;
            // 拼接请求参数
            var param = '?jobName=' + jobName + '&jobGroup=' + jobGroup;
            // url
            var url = basePath + 'quartzController/resumeJobOp' + param;
            // 开始请求
            schedulerOpAjax(url);
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 暂停按钮
    $('#jobs_table_toolbar_btn_puse_job').click(function() {
        var row = $('#jobs_table').datagrid('getSelected');
        if (row) {
            // 获得参数
            var jobName = row.JOB_NAME;
            var jobGroup = row.JOB_GROUP;
            // 拼接请求参数
            var param = '?jobName=' + jobName + '&jobGroup=' + jobGroup;
            // url
            var url = basePath + 'quartzController/puseJobOp' + param;
            // 开始请求
            schedulerOpAjax(url);
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 触发按钮
    $('#jobs_table_toolbar_btn_trigger').click(function() {
        var row = $('#jobs_table').datagrid('getSelected');
        if (row) {
            // 获得参数
            var jobName = row.JOB_NAME;
            var jobGroup = row.JOB_GROUP;
            // 拼接请求参数
            var param = '?jobName=' + jobName + '&jobGroup=' + jobGroup;
            // url
            var url = basePath + 'quartzController/triggerJobOp' + param;
            // 开始请求
            schedulerOpAjax(url);
        } else {
            showErrorMsg(message_code_11);
        }
    });

    // 初始化tabs
    $('#jobMainTabs').tabs({
        fit : true,
        border : false,
        plain : true,
        onSelect : function(title, index) {
            var tab = $('#jobMainTabs').tabs('getSelected');
            $('#jobMainTabs').tabs('update', {
                tab : tab,
                options : {
                    href : getUrl(index)
                }
            });
        }
    });
});
