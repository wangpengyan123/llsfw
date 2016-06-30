/**
 * 
 */
$(function() {
    // 组织表格
    $('#org_search_table').treegrid({
        title : org_page_table_title,
        border : true,
        url : basePath + 'orgController/getOrgNode',
        method : 'post',
        fit : true,
        rownumbers : false,
        fitColumns : false,
        singleSelect : true,
        pagination : false,
        toolbar : '#org_search',
        idField : 'ORG_CODE',
        treeField : 'ORG_NAME',
        columns : [ [ {
            title : org_page_table_orgName,
            field : 'ORG_NAME',
            align : 'left'
        } ] ],
        onClickRow : function(row) {
            $('#job_search_btn').click();
            $('#job_role_search_table').datagrid({
                url : basePath + 'orgController/getRole?jobCode=""'
            });
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 绑定查询按钮事件
    $('#org_search_btn').click(function() {
        $('#org_search_table').treegrid('reload');
    });

    // 新增根组织
    $('#org_add_root_btn').click(function() {
        $('#org_add_window').window({
            title : org_page_add_window_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 470,
            height : 150,
            href : basePath + 'orgController/toOrgAdd'
        });
    });

    // 新增叶组织
    $('#org_add_item_btn').click(function() {
        var row = $('#org_search_table').treegrid('getSelected');
        if (row) {
            $('#org_add_window').window({
                title : org_page_add_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 470,
                height : 150,
                href : basePath + 'orgController/toOrgAdd?parentOrgCode=' + row.ORG_CODE
            });
        } else {
            showErrorMsg(org_page_add_window_error_1);
        }
    });

    // 修改按钮
    $('#org_edit_btn').click(function() {
        var row = $('#org_search_table').treegrid('getSelected');
        if (row) {
            $('#org_edit_window').window({
                title : org_page_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 470,
                height : 150,
                href : basePath + 'orgController/toOrgEdit?orgCode=' + row.ORG_CODE
            });
        } else {
            showErrorMsg(message_code_5);
        }
    });

    // 删除按钮
    $('#org_delete_btn').click(function() {
        var row = $('#org_search_table').treegrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'orgController/deleteOrg?orgCode=' + row.ORG_CODE,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#org_search_btn').click();
                                $('#job_search_btn').click();
                                $('#job_role_search_table').datagrid('reload');
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

    // -----------------------------------------------------

    // 岗位表格
    $('#job_search_table').datagrid({
        title : org_page_job_table_title,
        border : true,
        url : basePath + 'orgController/getJob',
        method : 'post',
        fit : true,
        rownumbers : true,
        singleSelect : true,
        pagination : false,
        toolbar : '#job_search',
        columns : [ [ {
            title : org_page_job_table_jobCode,
            field : 'JOB_CODE',
            align : 'left',
            width : 100
        }, {
            title : org_page_job_table_jobName,
            field : 'JOB_NAME',
            align : 'left',
            width : 100
        }, {
            title : org_page_job_table_orgName,
            field : 'ORG_NAME',
            align : 'left',
            width : 100
        }, {
            title : org_page_job_table_createBy,
            field : 'CREATE_BY',
            align : 'left',
            width : 100
        }, {
            title : org_page_job_table_createDate,
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
            title : org_page_job_table_updateBy,
            field : 'UPDATE_BY',
            align : 'left',
            width : 100
        }, {
            title : org_page_job_table_updateDate,
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
        onClickRow : function(rowIndex, rowData) {
            $('#job_role_search_table').datagrid({
                url : basePath + 'orgController/getRole?jobCode=' + rowData.JOB_CODE
            });
        },
        onLoadError : function() {
            showErrorMsg(message_code_1);
        }
    });

    // 绑定查询按钮事件
    $('#job_search_btn').click(function() {
        var row = $('#org_search_table').treegrid('getSelected');
        var orgCode = row ? row.ORG_CODE : '';
        var url = '';
        if (orgCode) {
            url = basePath + 'orgController/getJob?orgCode=' + orgCode;

        } else {
            url = basePath + 'orgController/getJob';
        }
        $('#job_search_table').datagrid({
            url : url
        });
    });

    // 绑定新增
    $('#job_add_btn').click(function() {
        var row = $('#org_search_table').treegrid('getSelected');
        var orgCode = row ? row.ORG_CODE : '';
        $('#job_add_window').window({
            title : org_page_job_add_window_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 470,
            height : 150,
            href : basePath + 'orgController/toJobAdd?orgCode=' + orgCode
        });
    });

    // 修改按钮
    $('#job_edit_btn').click(function() {
        var row = $('#job_search_table').datagrid('getSelected');
        if (row) {
            $('#job_edit_window').window({
                title : org_page_job_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 470,
                height : 150,
                href : basePath + 'orgController/toJobEdit?jobCode=' + row.JOB_CODE
            });
        } else {
            showErrorMsg(message_code_5);
        }
    });

    // 删除按钮
    $('#job_delete_btn').click(function() {
        var row = $('#job_search_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'orgController/deleteJob?jobCode=' + row.JOB_CODE,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#job_search_btn').click();
                                $('#job_role_search_table').datagrid('reload');
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

    // --------------------------

    // 角色表格
    $('#job_role_search_table').datagrid({
        title : org_page_role_table_title,
        border : true,
        method : 'post',
        fit : true,
        rownumbers : true,
        singleSelect : true,
        pagination : false,
        toolbar : '#job_role_search',
        columns : [ [ {
            title : org_page_role_table_roleCode,
            field : 'ROLE_CODE',
            align : 'left',
            width : 100
        }, {
            title : org_page_role_table_roleName,
            field : 'ROLE_NAME',
            align : 'left',
            width : 100
        }, {
            title : org_page_role_table_jobCode,
            field : 'JOB_CODE',
            align : 'left',
            width : 100
        }, {
            title : org_page_role_table_createBy,
            field : 'CREATE_BY',
            align : 'left',
            width : 100
        }, {
            title : org_page_role_table_createDate,
            field : 'CREATE_DATE',
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

    // 新增功能
    $('#job_role_add_btn').click(function() {
        var row = $('#job_search_table').datagrid('getSelected');
        if (row) {
            // 弹出新增窗口
            $('#job_role_add_window').window({
                title : org_page_role_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 300,
                height : 350,
                href : basePath + 'orgController/toAddRole?jobCode=' + row.JOB_CODE
            });
        } else {
            showErrorMsg(org_page_role_window_error_1);
        }
    });

    // 删除按钮
    $('#job_role_delete_btn').click(function() {
        var row = $('#job_role_search_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'orgController/deleteRole?jobCode=' + row.JOB_CODE + '&roleCode=' + row.ROLE_CODE,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#job_role_search_table').datagrid('reload');
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