/**
 * 
 */

$(function() {
	$('#add_user_function_tree').tree({
		url : basePath + 'userController/loadUserFunctionTree?userName=' + $('#add_user_function_userName').val(),
		checkbox : true,
		onlyLeafCheck : true,
		parentField : "PARENT_FUNCTION_CODE",
		textFiled : "FUNCTION_NAME",
		idFiled : "FUNCTION_CODE",
		onCheck : function(node, checked) {
			var functionCode = node.F_CODE;
			var purviewCode = node.P_CODE;
			$('#add_user_function_function_code').val(functionCode);
			$('#add_user_function_purview_code').val(purviewCode);
			if (checked) {
				$('#add_user_function_form_edit').attr('action', basePath + 'userController/addUserFunction');
				$('#add_user_function_form_edit').form('submit', {
					onSubmit : function() {// 提交前置事件
						return $(this).form('validate');
					},
					success : function(data) {
						try {
							// 关闭遮罩
							$.messager.progress('close');

							// 解析数据
							var datas = strToJson(data);

							if (datas.returnCode != '1') {
								showErrorWindow(datas.result);
							}
						} catch (e) {
							showErrorWindow(data);
						}
					}
				});
			} else {
				$.messager.progress({
					text : message_code_6,
					interval : 200
				});

				$.ajax({
					type : 'POST',
					async : false,
					url : basePath + 'userController/deleteUserFunction?userName=' + $('#add_user_function_userName').val() + '&functionCode=' + functionCode + '&purviewCode=' + purviewCode,
					success : function(data) {
						// 解析数据
						var datas = strToJson(data);
						if (datas.returnCode != '1') {
							showErrorWindow(datas.result);
						}
					}
				});

				// 关闭遮罩
				$.messager.progress('close');
			}
		}
	});
});