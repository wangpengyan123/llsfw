/**
 * 
 */
$(function() {
    // 构造表格
    $('#function_search_table').treegrid({
        title : function_page_title,
        url : basePath + 'functionController/getAppNode',
        method : 'post',
        fit : true,
        border : false,
        rownumbers : true,
        singleSelect : true,
        pagination : false,
        toolbar : '#function_search',
        // queryParams : {},
        idField : 'FUNCTION_CODE',
        treeField : 'FUNCTION_NAME',
        frozenColumns : [ [ {
            title : function_table_functionName,
            field : 'FUNCTION_NAME',
            align : 'left',
            width : 150
        }, {
            title : function_table_functionCode,
            field : 'FUNCTION_CODE',
            align : 'left',
            width : 100
        }, {
            title : function_table_parentFunctionCode,
            field : 'PARENT_FUNCTION_CODE',
            align : 'left',
            width : 100
        } ] ],
        columns : [ [ {
            title : function_table_status,
            field : 'STATUS',
            align : 'left',
            width : 60,
            formatter : function(value, row, index) {
                if (value) {
                    if (value == 1) {
                        return function_status_enable;
                    } else if (value == 0) {
                        return function_status_disabled;
                    } else {
                        return function_status_unknown;
                    }
                }
            }
        }, {
            title : function_table_levelNo,
            field : 'LEVEL_NO',
            align : 'left',
            width : 60,
            formatter : function(value, row, index) {
                if (value) {
                    return levelDisplay(value);
                }
            }
        }, {
            title : function_table_requestUrl,
            field : 'REQUEST_URL',
            align : 'left',
            width : 250
        }, {
            title : function_table_createBy,
            field : 'CREATE_BY',
            align : 'left',
            width : 100
        }, {
            title : function_table_createDate,
            field : 'CREATE_DATE',
            align : 'left',
            width : 100,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        }, {
            title : function_table_updateBy,
            field : 'UPDATE_BY',
            align : 'left',
            width : 100
        }, {
            title : function_table_updateDate,
            field : 'UPDATE_DATE',
            align : 'left',
            width : 100,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        } ] ],
        onClickRow : function(row) {
            $('#function_purview_search_table').datagrid({
                url : basePath + 'functionController/getPurviewList?functionCode=' + row.FUNCTION_CODE
            });
            $('#function_Portal_search_table').datagrid({
                url : basePath + 'functionController/getPortalList?functionCode=' + row.FUNCTION_CODE
            });
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 绑定查询按钮事件
    $('#function_search_btn').click(function() {
        $('#function_search_table').treegrid('reload');
    });

    // 删除按钮
    $('#function_delete_btn').click(function() {
        var row = $('#function_search_table').treegrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'functionController/deleteFunction?functionCode=' + row.FUNCTION_CODE,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#function_search_btn').click();
                                $('#function_purview_search_table').datagrid('reload');
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
    $('#function_edit_btn').click(function() {
        var row = $('#function_search_table').treegrid('getSelected');
        if (row) {
            // 弹出修改窗口
            $('#function_edit_window').window({
                title : function_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 470,
                height : 220,
                href : basePath + 'functionController/toFunctionEdit?functionCode=' + row.FUNCTION_CODE
            });
        } else {
            // 提示
            showErrorMsg(message_code_5);
        }
    });

    // 新增按钮
    $('#function_add_btn').click(function() {
        var row = $('#function_search_table').treegrid('getSelected');
        var levelNo = row ? row.LEVEL_NO : 0;
        var parentFunctionCode = row ? row.FUNCTION_CODE : '';

        // 如果当前级别已经为3了,则不允许添加了
        if (levelNo == 3) {
            showErrorMsg(function_add_error_1);
        } else {
            var url = basePath + 'functionController/toFunctionAdd?levelNo=' + levelNo + '&parentFunctionCode=' + parentFunctionCode;
            // 弹出新增窗口
            $('#function_add_window').window({
                title : function_add_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 470,
                height : 220,
                href : url
            });
        }
    });

    // 权限表格
    $('#function_purview_search_table').datagrid({
        title : function_page_puw_title,
        method : 'post',
        fit : true,
        border : false,
        rownumbers : true,
        singleSelect : true,
        pagination : false,
        toolbar : '#function_purview_search',
        queryParams : {},
        columns : [ [ {
            title : function_puw_table_purviewCode,
            field : 'purviewCode',
            align : 'left',
            width : 100
        }, {
            title : function_puw_table_purviewName,
            field : 'purviewName',
            align : 'left',
            width : 100
        }, {
            title : function_puw_table_createBy,
            field : 'createBy',
            align : 'left',
            width : 100
        }, {
            title : function_puw_table_createDate,
            field : 'createDate',
            align : 'left',
            width : 100,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        } ] ],
        onLoadError : function() {
            showErrorMsg(message_code_1);
        }
    });

    // 删除操作权限
    $('#function_purview_delete_btn').click(function() {
        var row = $('#function_purview_search_table').datagrid('getSelected');
        if (row) {
            if (row.purviewCode != "*" && row.purviewCode != "view") {
                $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                    if (r) {
                        $.ajax({
                            type : 'POST',
                            async : false,
                            url : basePath + 'functionController/purviewDelete?functionCode=' + row.functionCode + '&purviewCode=' + row.purviewCode,
                            success : function(data) {
                                // 解析数据
                                var datas = strToJson(data);
                                if (datas.returnCode == '1') {
                                    $('#function_purview_search_table').datagrid('load');
                                } else {
                                    showErrorWindow(datas.result);
                                }
                            }
                        });
                    }
                });
            } else {
                showErrorMsg(function_puw_delete_error_1);
            }
        } else {
            showErrorMsg(message_code_4);
        }
    });

    // 新增操作权限
    $('#function_purview_add_btn').click(function() {
        var row = $('#function_search_table').treegrid('getSelected');
        if (row) {
            if (row.LEVEL_NO == 3) {
                $('#function_purview_add_windwos').window({
                    title : function_puw_add_window_title,
                    collapsible : false,
                    minimizable : false,
                    maximizable : false,
                    resizable : false,
                    modal : true,
                    width : 180,
                    height : 210,
                    href : basePath + 'functionController/toPurviewAdd?functionCode=' + row.FUNCTION_CODE
                });
            } else {
                showErrorMsg(function_puw_add_error_1);
            }
        } else {
            showErrorMsg(function_puw_add_error_2);
        }
    });

    // 修改权限操作
    $('#function_purview_edit_btn').click(function() {
        var row = $('#function_purview_search_table').datagrid('getSelected');
        if (row) {
            $('#function_purview_edit_windwos').window({
                title : function_puw_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 180,
                height : 210,
                href : basePath + 'functionController/toPurviewEdit?functionCode=' + row.functionCode + '&purviewCode=' + row.purviewCode
            });
        } else {
            showErrorMsg(function_puw_edit_error_1);
        }
    });

    // 面板表格
    $('#function_Portal_search_table').datagrid({
        title : '面板',
        method : 'post',
        fit : true,
        border : false,
        rownumbers : true,
        singleSelect : true,
        pagination : false,
        toolbar : '#function_Portal_search',
        queryParams : {},
        columns : [ [ {
            title : '标题',
            field : 'portalTitle',
            align : 'left',
            width : 100
        }, {
            title : '高度',
            field : 'portalHeight',
            align : 'left',
            width : 50
        }, {
            title : '地址',
            field : 'portalUrl',
            align : 'left',
            width : 200
        }, {
            title : '创建人',
            field : 'createBy',
            align : 'left',
            width : 100
        }, {
            title : '创建日期',
            field : 'createDate',
            align : 'left',
            width : 100,
            formatter : function(value, row, index) {
                if (value) {
                    var unixTimestamp = new Date(value);
                    return unixTimestamp.toLocaleDateString();
                }
            }
        } ] ],
        onLoadError : function() {
            showErrorMsg(message_code_1);
        }
    });

    // 新增面板
    $('#function_Portal_add_btn').click(function() {
        var row = $('#function_search_table').treegrid('getSelected');
        if (row) {
            var url = basePath + 'functionController/toPortalAdd?op=add&functionCode=' + row.FUNCTION_CODE;
            if (row.LEVEL_NO == 3) {
                $('#function_Portal_add_windwos').window({
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
            } else {
                showErrorMsg(function_puw_add_error_1);
            }
        } else {
            showErrorMsg(function_puw_add_error_2);
        }
    });

    // 修改面板
    $('#function_Portal_edit_btn').click(function() {
        var row = $('#function_search_table').treegrid('getSelected');
        if (row) {
            var portalRow = $('#function_Portal_search_table').treegrid('getSelected');
            if (portalRow) {
                var url = basePath + 'functionController/toPortalAdd?op=add&functionCode=' + row.FUNCTION_CODE;
                url += '&portalCode=' + portalRow.portalCode;
                if (row.LEVEL_NO == 3) {
                    $('#function_Portal_add_windwos').window({
                        title : '修改面板',
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
                    showErrorMsg(function_puw_add_error_1);
                }
            } else {
                showErrorMsg(function_puw_add_error_2);
            }
        } else {
            showErrorMsg(function_puw_add_error_2);
        }
    });

    // 删除面板
    $('#function_Portal_delete_btn').click(function() {
        var row = $('#function_Portal_search_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'functionController/portalDelete?functionCode=' + row.functionCode + '&portalCode=' + row.portalCode,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#function_Portal_search_table').datagrid('load');
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