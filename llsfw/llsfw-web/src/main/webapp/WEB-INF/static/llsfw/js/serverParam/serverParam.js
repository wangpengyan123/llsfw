$(function() { // NOSONAR
    // 构造表格
    $('#serverParamTable').datagrid({
        title : serverParam_table_title,
        url : basePath + 'serviceParamController/getParamsList',
        method : 'post',
        fit : true,
        rownumbers : true,
        singleSelect : true,
        pagination : false,
        toolbar : '#server_param_search',
        queryParams : {},
        columns : [ [ {
            title : serverParam_table_parametersCode,
            field : 'PARAMETERS_CODE',
            align : 'left',
            width : 200
        }, {
            title : serverParam_table_parametersValue,
            field : 'PARAMETERS_VALUE',
            align : 'left',
            width : 150
        }, {
            title : serverParam_table_parametersDesc,
            field : 'PARAMETERS_DESC',
            align : 'left',
            width : 150
        } ] ],
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 绑定查询按钮事件
    $('#serverParamSearchBtn').click(function() {
        $('#serverParamTable').datagrid('load', {
            parametersCode : $('#parametersCode').val(),
            parametersDesc : $('#parametersDesc').val()
        });
    });

    // 绑定查询事件
    $('#parametersCode,#parametersDesc').keydown(function(e) {
        if (e.keyCode === 13) {
            $('#serverParamSearchBtn').click();
        }
    });

    // 新增按钮
    $('#serverParamAddBtn').click(function() {
        // 弹出新增窗口
        $('#addServerParamWindows').window({
            title : serverParam_add_window_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 250,
            height : 200,
            href : basePath + 'serviceParamController/toServerParamAdd'
        });
    });

    // 删除
    $('#serverParamDeleteBtn').click(function() {
        var row = $('#serverParamTable').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'serviceParamController/deleteParam?parametersCode=' + row.PARAMETERS_CODE,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode === '1') {
                                $('#serverParamSearchBtn').click();
                                $('#parametersTypeCode').combobox('reload');
                            } else {
                                showErrorWindow(datas.result);
                            }
                        }
                    });
                }
            });
        } else {
            // 登录失败,弹出提示
            showErrorMsg(message_code_4);
        }
    });

    // 修改按钮
    $('#serverParamEditBtn').click(function() {
        var row = $('#serverParamTable').datagrid('getSelected');
        if (row) {
            // 弹出新增窗口
            $('#editServerParamWindows').window({
                title : serverParam_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 250,
                height : 200,
                href : basePath + 'serviceParamController/toServerParamEdit?parametersCode=' + row.PARAMETERS_CODE
            });
        } else {
            showErrorMsg(message_code_5);
        }
    });
});
