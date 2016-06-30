/**
 * 
 */
$(function() {

    // 作业名称
    $('#cron_jName_view').combogrid({
        required : true,
        editable : false,
        panelWidth : 800,
        loadMsg : '加载中...',
        method : 'post',
        rownumbers : true,
        singleSelect : true,
        url : basePath + 'quartzController/getJobs',
        idField : 'JOB_NAME',
        textField : 'JOB_NAME',
        columns : [ [ {
            title : quartz_page_filed_jobName,
            field : 'JOB_NAME',
            width : '200'
        }, {
            title : quartz_page_filed_jobGroup,
            field : 'JOB_GROUP',
            width : '150'
        }, {
            title : quartz_page_filed_jobDesc,
            field : 'DESCRIPTION',
            width : '150'
        }, {
            title : quartz_page_filed_jobClass,
            field : 'JOB_CLASS_NAME',
            width : '200'
        } ] ],
        onSelect : function(rowIndex, rowData) {
            $('#cron_jGroup_view').textbox('setValue', rowData.JOB_GROUP);
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 作业组别
    $('#cron_jGroup_view').textbox({
        editable : false
    });

    // 触发器名称
    $('#cron_tName_add').textbox({
        required : true,
        validType : [ "length[1,100]", "not_chinese" ]
    });

    // 触发器组别
    $('#cron_tGroup_add').combobox({
        required : true,
        method : 'get',
        url : basePath + 'quartzController/getTriggerGroupList',
        valueField : 'value',
        textField : 'text',
        editable : true,
        value : $('#cron_tGroup_add').val()
    });

    // 触发器描述
    $('#cron_tDescription_add').textbox({
        required : true,
        validType : [ "length[1,100]" ]
    });

    // 开始时间
    $('#cron_triggerStartTime_add').datetimebox({
        required : false,
        validType : [ "yyyymmddhhmiss" ],
        showSeconds : true
    });

    // 结束时间
    $('#cron_triggerEndTime_add').datetimebox({
        required : false,
        validType : [ "yyyymmddhhmiss" ],
        showSeconds : true
    });

    // 优先级
    $('#cron_priority_add').numberbox({
        required : true,
        validType : [ "length[1,13]" ],
        min : 1
    });

    // 触发器策略
    $('#cron_misfireInstruction_add').combobox({
        required : true,
        method : 'get',
        url : basePath + 'quartzController/getCronMisfireInstructionList',
        valueField : 'value',
        textField : 'text',
        editable : false,
        value : $('#cron_misfireInstruction_add').val()
    });

    // 日历
    $('#cron_Calendar_add').combobox({
        required : false,
        method : 'get',
        url : basePath + 'quartzController/getCalendarList',
        valueField : 'value',
        textField : 'text',
        editable : false,
        value : $('#cron_Calendar_add').val()
    });

    // CRON表达式
    $('#cron_triggerCronExcp_add').textbox({
        required : true,
        validType : [ "length[1,100]" ],
        prompt : quartz_page_filed_msg_4,
        icons : [ {
            iconCls : 'e-icon fa fa-crosshairs fa-lg',
            handler : function(e) {
                $('#cron_triggers_generate_window').window({
                    title : quartz_page_filed_msg_5,
                    collapsible : false,
                    minimizable : false,
                    maximizable : false,
                    resizable : false,
                    modal : true,
                    width : 660,
                    height : 470,
                    href : basePath + 'quartzController/toCronTriggersGeneratePage'
                });
            }
        } ],
    });

    // 时区
    $('#cron_timeZoneId_add').combobox({
        required : false,
        method : 'get',
        url : basePath + 'quartzController/getTimeZoneIdList',
        valueField : 'value',
        textField : 'text',
        editable : false,
        value : $('#cron_timeZoneId_add').val()
    });

    // triggerDataMap
    // 作业dataMap
    var editIndex = undefined;
    $('#cronTriggerDataMapParamTable').datagrid({
        border : false,
        url : basePath + 'quartzController/getTriggerDataMap?triggerName=' + $('#cronHidTriggerName').val() + '&triggerGroup=' + $('#cronHidTriggerGroup').val(),
        method : 'post',
        toolbar : '#cronTriggerDataMapParam',
        fit : true,
        rownumbers : true,
        singleSelect : true,
        fitColumns : true,
        scrollbarSize : 0,
        idField : 'name',
        columns : [ [ {
            title : 'Name',
            field : 'name',
            width : '45%',
            editor : {
                type : 'validatebox',
                options : {
                    validType : [ "length[1,100]", "not_chinese" ],
                    required : true
                }
            }
        }, {
            title : 'Value',
            field : 'value',
            width : '45%',
            editor : {
                type : 'validatebox',
                options : {
                    validType : [ "length[1,100]", "not_chinese" ],
                    required : true
                }
            }
        } ] ],
        onClickRow : onClickRow,
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 添加作业参数
    $('#cronTriggerDataMapParamAdd').linkbutton({});

    $('#cronTriggerDataMapParamAdd').click(function() {
        addJobDetailDataMap();
    });

    // 删除作业参数
    $('#cronTriggerDataMapParamRemove').linkbutton({});

    $('#cronTriggerDataMapParamRemove').click(function() {
        removeJobDetailDataMap();
    });

    function endEditing() {
        if (editIndex == undefined) {
            return true;
        }
        if ($('#cronTriggerDataMapParamTable').datagrid('validateRow', editIndex)) {
            $('#cronTriggerDataMapParamTable').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickRow(index) {
        if (editIndex != index) {
            if (endEditing()) {
                $('#cronTriggerDataMapParamTable').datagrid('selectRow', index).datagrid('beginEdit', index);
                editIndex = index;
            } else {
                $('#cronTriggerDataMapParamTable').datagrid('selectRow', editIndex);
            }
        }
    }

    function addJobDetailDataMap() {
        if (endEditing()) {
            $('#cronTriggerDataMapParamTable').datagrid('appendRow', {
                name : quartz_page_jobsmain_edit_window_msg_1,
                value : quartz_page_jobsmain_edit_window_msg_2,
                editor : 'text'
            });
            editIndex = $('#cronTriggerDataMapParamTable').datagrid('getRows').length - 1;
            $('#cronTriggerDataMapParamTable').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
        }
    }

    function removeJobDetailDataMap() {
        if (editIndex == undefined) {
            return;
        }
        $('#cronTriggerDataMapParamTable').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
        editIndex = undefined;
    }

    // 保存按钮
    $('#cron_triggers_job_from_add_btn').linkbutton({});

    // 绑定保存按钮事件
    $('#cron_triggers_job_from_add_btn').click(function() {
        save();
    });

    // 保存方法
    function save() {

        // 获得数据
        endEditing();
        var dataArr = $('#cronTriggerDataMapParamTable').datagrid('getData').rows;
        var jsonData = JSON.stringify(dataArr);
        $('#cronTriggerDetailDataMapHid').val(jsonData);

        $('#cron_triggers_form_add').attr('action', basePath + 'quartzController/addCronTirgger');
        $('#cron_triggers_form_add').form('submit', {
            onSubmit : function() {// 提交前置事件
                var isValid = $(this).form('validate');
                if (isValid) {// 验证通过,弹出遮罩
                    $.messager.progress({
                        text : message_code_6,
                        interval : 200
                    });
                }
                return isValid; // return false will stop the form submission
            },
            success : function(data) {
                try {
                    // 关闭遮罩
                    $.messager.progress('close');

                    // 解析数据
                    var datas = strToJson(data);
                    if (datas.returnCode == '1') {
                        triggerSearch();
                        $('#cron_trigger_add_window').window('close');
                    } else {
                        showErrorWindow(datas.result);// 弹出提示
                    }
                } catch (e) {
                    showErrorWindow(e);
                }
            }
        });
    }
});
