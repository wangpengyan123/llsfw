$(function() {
    // 面板表格
    $('#portal_search_table').datagrid({
        url : basePath + 'portalController/getUserPortal',
        method : 'post',
        fit : true,
        border : true,
        rownumbers : true,
        singleSelect : true,
        pagination : false,
        toolbar : '#function_Portal_search',
        queryParams : {},
        columns : [ [ {
            title : '位置',
            field : 'PORTAL_INDEX',
            align : 'left',
            width : 100,
            formatter : function(value, row, index) {
                if (value == '0') {
                    return '左';
                } else if (value == '1') {
                    return '中';
                } else if (value == '2') {
                    return '右';
                } else {
                    return '未知';
                }
            }
        }, {
            title : '顺序',
            field : 'PORTAL_SORT',
            align : 'left',
            width : 50
        }, {
            title : '标题',
            field : 'PORTAL_TITLE',
            align : 'left',
            width : 200
        }, {
            title : '所属功能',
            field : 'FUNCTION_NAME',
            align : 'left',
            width : 100
        } ] ],
        onLoadError : function() {
            showErrorMsg(message_code_1);
        }
    });

    // 绑定查询按钮事件
    $('#portal_search_btn').click(function() {
        $('#portal_search_table').datagrid('reload');
    });

    // 绑定新增按钮事件
    $('#function_add_btn').click(function() {
        var url = basePath + 'portalController/toPortalAdd?op=add';
        $('#function_add_window').window({
            title : '新增面板',
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 180,
            height : 210,
            href : url
        });
    });

    // 绑定修改按钮事件
    $('#function_edit_btn').click(function() {
        var url = basePath + 'portalController/toPortalAdd?op=edit';
        var row = $('#portal_search_table').datagrid('getSelected');
        if (row) {
            url += '&portalCode=' + row.PORTAL_CODE;
            $('#function_add_window').window({
                title : '修改面板(' + row.PORTAL_TITLE + ')',
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 180,
                height : 210,
                href : url
            });
        } else {
            showErrorMsg(message_code_5);
        }
    });

    // 删除面板
    $('#function_delete_btn').click(function() {
        var row = $('#portal_search_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'portalController/portalDelete?portalCode=' + row.PORTAL_CODE,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#portal_search_table').datagrid('load');
                            } else {
                                showErrorWindow(datas.result);
                            }
                        }
                    });
                }
            });
        } else {
            showErrorMsg(message_code_4);
        }
    });
});