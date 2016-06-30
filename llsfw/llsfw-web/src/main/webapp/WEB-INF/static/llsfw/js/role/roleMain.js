/**
 * 
 */
$(function() {

	// 构造表格
	$('#role_table').datagrid({
		title : role_page_table_title,
		url : basePath + 'roleController/getRoleList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#role_table_param',
		queryParams : {},
		frozenColumns : [ [ {
			title : role_page_table_roleCode,
			field : 'ROLE_CODE',
			align : 'left',
			width : 100
		}, {
			title : role_page_table_roleName,
			field : 'ROLE_NAME',
			align : 'left',
			width : 100
		} ] ],
		columns : [ [ {
			title : role_page_table_createBy,
			field : 'CREATE_BY',
			align : 'left',
			width : 100
		}, {
			title : role_page_table_createDate,
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
			title : role_page_table_updateBy,
			field : 'UPDATE_BY',
			align : 'left',
			width : 100
		}, {
			title : role_page_table_updateDate,
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
			$('#curr_role_code').val(rowData.ROLE_CODE);// 记录当前选择的角色代码
			$('#role_function_table').treegrid({
				url : basePath + 'roleController/getRoleFunctionList?roleCode=' + rowData.ROLE_CODE
			});
		},
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 构造表格
	$('#role_function_table').treegrid({
		title : role_page_function_table_title,
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		toolbar : '#role_function_table_param',
		idField : 'FUNCTION_CODE',
		treeField : 'FUNCTION_NAME',
		columns : [ [ {
			title : role_page_function_table_functionName,
			field : 'FUNCTION_NAME',
			align : 'left',
			width : 300,
			formatter : function(value, row, index) {
				if (value) {
					// 特殊逻辑转换
					if (row.LEVEL_NO == 'PURVIEW') {
						return value + '(' + row.FUNCTION_CODE_DISPLY + ':' + row.PURVIEW_CODE + ')';
					} else {
						return value + '(' + row.FUNCTION_CODE + ')';
					}

				}
			}
		}, {
			title : role_page_function_table_levelNo,
			field : 'LEVEL_NO',
			align : 'left',
			width : 100,
			formatter : function(value, row, index) {
				if (value) {
					return levelDisplay(value);
				}
			}
		} ] ],
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 删除功能
	$('#role_function_table_delete_btn').click(function() {
		var row = $('#role_function_table').treegrid('getSelected');
		if (row) {
			var url;
			if (row.LEVEL_NO == 'PURVIEW') {
				url = basePath + 'roleController/deleteRoleFunction?functionCode=' + row.FUNCTION_CODE_DISPLY + '&purviewCode=' + row.PURVIEW_CODE + '&roleCode=' + $('#curr_role_code').val();
			} else {
				url = basePath + 'roleController/deleteRoleFunction?functionCode=' + row.FUNCTION_CODE + '&roleCode=' + $('#curr_role_code').val();
			}
			$.messager.confirm(base_showErrorOrInfoMsg_title, message_code_4, function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						async : false,
						url : url,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#role_function_table').treegrid('reload');
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

	// 新增功能
	$('#role_function_table_add_btn').click(function() {
		var row = $('#role_table').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#role_function_window_add').window({
				title : role_page_function_window_title,
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 800,
				height : 400,
				href : basePath + 'roleController/toAddRoleFunction?roleCode=' + row.ROLE_CODE
			});
		} else {
			showErrorMsg(role_page_function_window_error_1);
		}
	});

	// -------------------------------------------------

	// 绑定查询按钮事件
	$('#role_table_search_btn').click(function() {
		$('#role_table').datagrid('load');
	});

	// 新增按钮
	$('#role_table_add_btn').click(function() {
		// 弹出新增窗口
		$('#role_window_add').window({
			title : role_page_add_window_title,
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 250,
			height : 150,
			href : basePath + 'roleController/toRoleAdd'
		});
	});

	// 修改按钮
	$('#role_table_edit_btn').click(function() {
		var row = $('#role_table').datagrid('getSelected');
		if (row) {
			// 弹出新增窗口
			$('#role_window_edit').window({
				title : role_page_edit_window_title,
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 250,
				height : 150,
				href : basePath + 'roleController/toRoleEdit?roleCode=' + row.ROLE_CODE
			});
		} else {
			// 登录失败,弹出提示
			showErrorMsg(message_code_5);
		}
	});

	// 删除
	$('#role_table_delete_btn').click(function() {
		var row = $('#role_table').datagrid('getSelected');
		if (row) {
			$.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						async : false,
						url : basePath + 'roleController/deleteRole?roleCode=' + row.ROLE_CODE,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#role_table_search_btn').click();
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