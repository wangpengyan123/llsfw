/**
 * 
 */
$(function() {
	// 构造表格
	$('#function_table').treegrid({
		url : basePath + 'roleController/getFunctionList?roleCode=' + $('#roleCode_function').val(),
		title : role_page_function_window_table_f_title,
		method : 'post',
		border : false,
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		idField : 'FUNCTION_CODE',
		treeField : 'FUNCTION_NAME',
		columns : [ [ {
			title : role_page_function_window_table_f_functionName,
			field : 'FUNCTION_NAME',
			align : 'left',
			width : 150
		}, {
			title : role_page_function_window_table_f_functionCode,
			field : 'FUNCTION_CODE',
			align : 'left',
			width : 150
		}, {
			title : role_page_function_window_table_f_levelNo,
			field : 'LEVEL_NO',
			align : 'left',
			width : 60,
			formatter : function(value, row, index) {
				if (value) {
					return levelDisplay(value);
				}
			}
		} ] ],
		onClickRow : function(row) {
			$('#role_function_purview_table').datagrid({
				url : basePath + 'roleController/getFunctionPurviewList?roleCode=' + $('#roleCode_function').val() + '&functionCode=' + row.FUNCTION_CODE
			});
		},
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 权限清单
	$('#role_function_purview_table').datagrid({
		title : role_page_function_window_table_p_title,
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : false,
		pagination : false,
		queryParams : {},
		columns : [ [ {
			title : role_page_function_window_table_p_purviewName,
			field : 'PURVIEW_NAME',
			align : 'left',
			width : 100
		}, {
			title : role_page_function_window_table_p_purviewCode,
			field : 'PURVIEW_CODE',
			align : 'left',
			width : 100
		} ] ],
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 保存按钮
	$('#function_table_save_btn').click(function() {
		var functionRow = $('#function_table').treegrid('getSelected');
		if (functionRow) {
			var purviewRows = $('#role_function_purview_table').datagrid('getSelections');
			var purviewCodes = [];
			for (var i = 0; i < purviewRows.length; i++) {
				purviewCodes.push(purviewRows[i].PURVIEW_CODE);
			}
			var codes = purviewCodes.join(',');
			if (codes) {
				$('#role_functionCode').val(functionRow.FUNCTION_CODE); // 设置表单值
				$('#role_purviewCodes').val(codes); // 设置表单值
				$('#role_function_form_add').attr('action', basePath + 'roleController/addRoleFunction');
				$('#role_function_form_add').form('submit', {
					onSubmit : function() {// 提交前置事件
						var isValid = $(this).form('validate');
						if (isValid) {// 验证通过,弹出遮罩
							$.messager.progress({
								text : message_code_6,
								interval : 200
							});
						}
						return isValid;
					},
					success : function(data) {
						try {
							// 关闭遮罩
							$.messager.progress('close');

							// 解析数据
							var datas = strToJson(data);

							// 判断操作结果
							if (datas.returnCode == '1') {
								$('#role_function_table').treegrid('reload');
								$('#function_table').treegrid('reload');
								$('#role_function_purview_table').datagrid('reload');
							} else {
								showErrorWindow(datas.result);
							}
						} catch (e) {
							showErrorWindow(data);
						}
					}
				});
			} else {
				showErrorMsg(role_page_function_window_error_2);
			}
		} else {
			showErrorMsg(role_page_function_window_error_3);
		}
	});
});