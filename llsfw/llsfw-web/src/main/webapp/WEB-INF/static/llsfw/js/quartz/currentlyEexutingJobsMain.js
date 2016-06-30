$(function() {

    // 刷新
    $('#currentlyEexutingJobs_search_btn').click(function() {
        $('#currentlyEexutingJobs_table').datagrid('load', {});
    });

    // 打断
    $('#currentlyEexutingJobs_interropt_btn').click(function() {
        showInfoMsg('暂未开发');
    });

    // 构造表格
    $('#currentlyEexutingJobs_table').datagrid({
        url : basePath + 'quartzController/loadCurrentlyEexutingJobs',
        toolbar : '#currentlyEexutingJobs_search',
        method : 'post',
        rownumbers : true,
        fit : true,
        singleSelect : true,
        pagination : false,
        columns : [ [ {
            title : '触发器名称',
            field : 'TRIGGER_NAME',
            align : 'left',
            width : 150
        }, {
            title : '触发器组别',
            field : 'TRIGGER_GROUP',
            align : 'left',
            width : 150
        }, {
            title : '触发时间',
            field : 'SCHED_TIME',
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : '执行时间',
            field : 'FIRED_TIME',
            align : 'left',
            width : 150,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleString();
                }
            }
        }, {
            title : '优先级',
            field : 'PRIORITY',
            align : 'left',
            width : 150
        }, {
            title : '状态',
            field : 'STATE',
            align : 'left',
            width : 150
        }, {
            title : '作业名称',
            field : 'JOB_NAME',
            align : 'left',
            width : 200
        }, {
            title : '作业组别',
            field : 'JOB_GROUP',
            align : 'left',
            width : 150
        }, {
            title : '禁止并发',
            field : 'IS_NONCONCURRENT',
            align : 'left',
            width : 150,
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
            title : '执行恢复',
            field : 'REQUESTS_RECOVERY',
            align : 'left',
            width : 150,
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
            title : '计划任务名称',
            field : 'SCHED_NAME',
            align : 'left',
            width : 150
        }, {
            title : '条目编号',
            field : 'ENTRY_ID',
            align : 'left',
            width : 240
        }, {
            title : '实例名称',
            field : 'INSTANCE_NAME',
            align : 'left',
            width : 150
        } ] ],
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

});
