/**
 * 
 */
$(function() {

	// 名称
	$('#name').textbox({
		required : true,
		validType : [ "length[1,100]" ]
	});

	// key
	$('#key').textbox({
		required : true,
		validType : [ "length[1,100]" ]
	});

	// 描述
	$('#description').textbox({
		height : 50,
		required : true,
		multiline : true,
		validType : [ "length[1,100]" ]
	});

	// 保存按钮
	$('#addWorkFlowModel_form_btn_add').linkbutton({});

	// 绑定保存按钮事件
	$('#addWorkFlowModel_form_btn_add').click(function() {
		save();
	});

	// 保存方法
	function save() {
		$('#addWorkFlowModel_form_add').attr('action', basePath + 'activiti/management/workFlowModelEdit/addWorkFlowModel');
		// 提交
		$('#addWorkFlowModel_form_add').form('submit', {
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
						$('#addWorkFlowModelWindow').window('close');
						$('#workFlowModel_search_btn').click();
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
