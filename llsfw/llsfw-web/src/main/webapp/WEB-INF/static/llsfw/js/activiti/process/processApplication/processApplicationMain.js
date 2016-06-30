$(function() {
	// 提交流程
	$('#processApplication_btn_submitPorcess').click(function() {
		$.messager.confirm(base_showErrorOrInfoMsg_title, '请确认表单内容后点击确认提交', function(r) {
			if (r) {
				$('#processApplicationForm').attr('action', basePath + 'activiti/process/processApplication/startProcessInstance');
				// 提交
				$('#processApplicationForm').form('submit', {
					onSubmit : function() {// 提交前置事件
						var isValid = $(this).form('validate');
						if (isValid) {// 验证通过,弹出遮罩
							$.messager.progress({
								text : message_code_6,
								interval : 200
							});
						}
						return isValid; // return false will stop the form
						// submission
					},
					success : function(data) {
						try {
							// 关闭遮罩
							$.messager.progress('close');

							// 解析数据
							var datas = strToJson(data);

							if (datas.returnCode == '1') {
								$('#processApplicationForm').form('reset');
								showInfoMsg(datas.result);
							} else {
								showErrorWindow(datas.result);
							}
						} catch (e) {
							showErrorWindow(data);
						}
					}
				});
			}
		});
	});
});
