/**
 * 
 */
$(function() {

    // 当前选择的用户
    var currUserName = '';
    // 当前选择的岗位
    var currJobCodeList = '';
    // 当前用户选择的角色
    var currRoleCodeList = '';

    // 分页条数
    var userTablePageSize = getServerParam(basePath, 'PAGE_SIZE');

    // 构造表格
    $('#user_table').datagrid({
        title : user_page_table_title,
        url : basePath + 'userController/getUserList',
        method : 'post',
        fit : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : userTablePageSize,
        pageList : [ userTablePageSize, userTablePageSize * 2, userTablePageSize * 4, userTablePageSize * 6 ],
        toolbar : '#user_table_param',
        queryParams : {},
        frozenColumns : [ [ {
            title : user_page_table_loginName,
            field : 'LOGIN_NAME',
            align : 'left',
            width : 80
        }, {
            title : user_page_table_userName,
            field : 'USER_NAME',
            align : 'left',
            width : 80
        } ] ],
        columns : [ [ {
            title : user_page_table_userStatus,
            field : 'USER_STATUS',
            align : 'left',
            width : 45,
            formatter : function(value, row, index) {
                if (value) {
                    if (value == 1) {
                        return '启用';
                    } else if (value == 0) {
                        return '停用';
                    } else {
                        return '未知';
                    }
                }
            }
        }, {
            title : user_page_table_userPhone,
            field : 'USER_PHONE',
            align : 'left',
            width : 100
        }, {
            title : user_page_table_userMPhone,
            field : 'USER_M_PHOME',
            align : 'left',
            width : 100
        }, {
            title : user_page_table_userEmail,
            field : 'USER_EMAIL',
            align : 'left',
            width : 100
        }, {
            title : user_page_table_createBy,
            field : 'CREATE_BY',
            align : 'left',
            width : 80
        }, {
            title : user_page_table_createDate,
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
            title : user_page_table_updateBy,
            field : 'UPDATE_BY',
            align : 'left',
            width : 80
        }, {
            title : user_page_table_updateDate,
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
            currUserName = rowData.LOGIN_NAME;
            currJobCodeList = '';
            currRoleCodeList = '';
            loadUserJobTable(currUserName);
            loadRoleTable(currUserName, currJobCodeList);
            loadOrgTable(currUserName, currJobCodeList);
            loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
        },
        onLoadSuccess : function(data) {
            $('#user_table').datagrid('getPager').pagination({
                layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
            });
            currUserName = '';
            currJobCodeList = '';
            currRoleCodeList = '';
            loadUserJobTable(currUserName);
            loadRoleTable(currUserName, currJobCodeList);
            loadOrgTable(currUserName, currJobCodeList);
            loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 加载岗位列表
    function loadUserJobTable() {
        $('#user_job_table').datagrid({
            url : basePath + 'userController/getUserJobList?loginName=' + currUserName
        });
    }

    // 加载角色列表
    function loadRoleTable() {
        $('#user_job_role_table').datagrid({
            url : basePath + 'userController/getUserJobRoleList?loginName=' + currUserName + currJobCodeList
        });

    }

    // 加载功能树
    function loadFunctionTable() {
        $('#user_job_org_function_all_table').tree({
            url : basePath + 'userController/getUserJobRoleFunctionTree?loginName=' + currUserName + currJobCodeList + currRoleCodeList
        });
        $('#user_job_org_function_job_table').tree({
            url : basePath + 'userController/getUserJobRoleFunctionTree?loadFunctionType=1&loginName=' + currUserName + currJobCodeList + currRoleCodeList
        });
        $('#user_job_org_function_user_table').tree({
            url : basePath + 'userController/getUserJobRoleFunctionTree?loadFunctionType=2&loginName=' + currUserName
        });

    }

    // 加载组织树
    function loadOrgTable() {
        $('#user_job_org_all_table').tree({
            url : basePath + 'userController/getUserJobOrgTree?loginName=' + currUserName + currJobCodeList
        });
        $('#user_job_org_higher_table').tree({
            url : basePath + 'userController/getUserJobOrgTree?loadOrgType=1&loginName=' + currUserName + currJobCodeList
        });
        $('#user_job_org_lower_table').tree({
            url : basePath + 'userController/getUserJobOrgTree?loadOrgType=2&loginName=' + currUserName + currJobCodeList
        });
    }

    // 绑定查询按钮事件
    $('#user_table_search_btn').click(function() {
        $('#user_table').datagrid('load', {
            loginName : $('#loginNameSearch').val(),
            userName : $('#userNameSearch').val()
        });
    });

    // 绑定查询按钮
    $('#loginNameSearch,#userNameSearch').keydown(function(e) {
        if (e.keyCode == 13) {
            $('#user_table_search_btn').click();
        }
    });

    // 新增按钮
    $('#user_table_add_btn').click(function() {
        // 弹出新增窗口
        $('#user_window_add').window({
            title : user_page_add_window_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 470,
            height : 200,
            href : basePath + 'userController/toUserAdd'
        });
    });

    // 修改按钮
    $('#user_table_edit_btn').click(function() {
        var row = $('#user_table').datagrid('getSelected');
        if (row) {
            // 弹出新增窗口
            $('#user_window_edit').window({
                title : user_page_edit_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 470,
                height : 200,
                href : basePath + 'userController/toUserEdit?loginName=' + row.LOGIN_NAME
            });
        } else {
            // 登录失败,弹出提示
            showErrorMsg(message_code_5);
        }
    });

    // 删除
    $('#user_table_delete_btn').click(function() {
        var row = $('#user_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'userController/userDelete?loginName=' + row.LOGIN_NAME,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#user_table_search_btn').click();
                                $('#user_job_table').datagrid('reload');
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

    // 密码初始化
    $('#user_table_set_defpswd_btn').click(function() {
        var row = $('#user_table').datagrid('getSelected');
        if (row) {
            $.messager.confirm(base_showErrorOrInfoMsg_title, user_page_delete_error_1, function(r) {
                if (r) {
                    $.ajax({
                        type : 'POST',
                        async : false,
                        url : basePath + 'userController/saveDefPswd?loginName=' + row.LOGIN_NAME,
                        success : function(data) {
                            // 解析数据
                            var datas = strToJson(data);
                            if (datas.returnCode == '1') {
                                $('#user_table_search_btn').click();
                            } else {
                                showErrorWindow(datas.result);
                            }
                        }
                    });
                }
            });
        } else {
            showErrorMsg(user_page_delete_error_3);
        }
    });

    // -------------------------------------------

    // 岗位表格
    $('#user_job_table').datagrid({
        title : user_page_job_table_title,
        method : 'post',
        fit : true,
        rownumbers : false,
        singleSelect : false,
        pagination : false,
        queryParams : {},
        columns : [ [ {
            title : user_page_job_table_jobCode,
            field : 'JOB_CODE',
            align : 'left',
            width : 80
        }, {
            title : user_page_job_table_jobName,
            field : 'JOB_NAME',
            align : 'left',
            width : 80
        }, {
            title : user_page_job_table_orgName,
            field : 'ORG_NAME',
            align : 'left',
            width : 80
        }, {
            title : user_page_job_table_createBy,
            field : 'CREATE_BY',
            align : 'left',
            width : 80
        }, {
            title : user_page_job_table_createDate,
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
        onClickRow : function(rowIndex, rowData) {
            var selections = $('#user_job_table').datagrid("getSelections");
            var jobCodes = '';
            if (selections.length > 0) {
                for (var i = 0; i < selections.length; i++) {
                    jobCodes += "&jobCodes=" + selections[i].JOB_CODE;
                }
            } else {
                jobCodes = '';
            }
            currJobCodeList = jobCodes;
            currRoleCodeList = '';
            loadRoleTable(currUserName, currJobCodeList);
            loadOrgTable(currUserName, currJobCodeList);
            loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 角色表格
    $('#user_job_role_table').datagrid({
        title : user_page_org_table_title,
        method : 'post',
        fit : true,
        rownumbers : false,
        singleSelect : false,
        pagination : false,
        queryParams : {},
        columns : [ [ {
            title : user_page_org_table_roleCode,
            field : 'ROLE_CODE',
            align : 'left',
            width : 80
        }, {
            title : user_page_org_table_roleName,
            field : 'ROLE_NAME',
            align : 'left',
            width : 80
        }, {
            title : user_page_org_table_jobName,
            field : 'JOB_NAME',
            align : 'left',
            width : 80
        }, {
            title : user_page_org_table_createBy,
            field : 'CREATE_BY',
            align : 'left',
            width : 80
        }, {
            title : user_page_org_table_createDate,
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
        onClickRow : function(rowIndex, rowData) {
            var selections = $('#user_job_role_table').datagrid("getSelections");
            var roleCodes = '';
            if (selections.length > 0) {
                for (var i = 0; i < selections.length; i++) {
                    roleCodes += "&roleCodes=" + selections[i].ROLE_CODE;
                }
            } else {
                roleCodes = '';
            }
            currRoleCodeList = roleCodes;
            loadFunctionTable(currUserName, currJobCodeList, currRoleCodeList);
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

    // 组织机构树
    $('#user_job_org_all_table').tree({
        checkbox : false,
        parentField : "PARENT_ORG_CODE",
        textFiled : "ORG_NAME",
        idFiled : "ORG_CODE",
        formatter : function(node) {
            var text = node.text;
            if (node.MAIN_ORG == "1") {
                text = "<strong>" + text + "</strong>";
            }
            return text;
        }
    });
    $('#user_job_org_higher_table').tree({
        checkbox : false,
        parentField : "PARENT_ORG_CODE",
        textFiled : "ORG_NAME",
        idFiled : "ORG_CODE",
        formatter : function(node) {
            var text = node.text;
            if (node.MAIN_ORG == "1") {
                text = "<strong>" + text + "</strong>";
            }
            return text;
        }
    });
    $('#user_job_org_lower_table').tree({
        checkbox : false,
        parentField : "PARENT_ORG_CODE",
        textFiled : "ORG_NAME",
        idFiled : "ORG_CODE",
        formatter : function(node) {
            var text = node.text;
            if (node.MAIN_ORG == "1") {
                text = "<strong>" + text + "</strong>";
            }
            return text;
        }
    });

    $('#user_job_org_function_all_table').tree({
        checkbox : false,
        parentField : "PARENT_FUNCTION_CODE",
        textFiled : "FUNCTION_NAME",
        idFiled : "FUNCTION_CODE"
    });
    $('#user_job_org_function_job_table').tree({
        checkbox : false,
        parentField : "PARENT_FUNCTION_CODE",
        textFiled : "FUNCTION_NAME",
        idFiled : "FUNCTION_CODE"
    });
    $('#user_job_org_function_user_table').tree({
        checkbox : false,
        parentField : "PARENT_FUNCTION_CODE",
        textFiled : "FUNCTION_NAME",
        idFiled : "FUNCTION_CODE"
    });

    // ----岗位授权
    $('#user_table_job_permissions_btn').click(function() {
        var row = $('#user_table').datagrid('getSelected');
        if (row) {
            $('#user_job_window_add').window({
                title : user_page_jobAuth_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 900,
                height : 500,
                href : basePath + 'userController/toAddUserJob?userName=' + row.LOGIN_NAME,
                tools : [ {
                    iconCls : 'icon-reload',
                    handler : function() {
                        $('#user_job_window_add').panel('refresh');
                    }
                } ]
            });
        } else {
            showErrorMsg(user_page_jobAuth_window_error_1);
        }
    });

    // ----直接授权
    $('#user_table_user_permissions_defpswd_btn').click(function() {
        var row = $('#user_table').datagrid('getSelected');
        if (row) {
            $('#user_user_function_window_add').window({
                title : user_page_auth_window_title,
                collapsible : false,
                minimizable : false,
                maximizable : false,
                resizable : false,
                modal : true,
                width : 300,
                height : 500,
                href : basePath + 'userController/toAddUserFunction?userName=' + row.LOGIN_NAME,
                tools : [ {
                    iconCls : 'icon-reload',
                    handler : function() {
                        $('#user_user_function_window_add').panel('refresh');
                    }
                } ]
            });
        } else {
            showErrorMsg(user_page_auth_window_error_1);
        }
    });
});