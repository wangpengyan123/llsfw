$(function() {

	// 作业名称
	$('#jName_add').textbox({
		required : true,
		validType : [ "length[1,250]", "not_chinese" ]
	});

	// 作业组别
	$('#jGroup_add').combobox({
		required : true,
		validType : [ "length[1,250]", "not_chinese" ],
		method : 'get',
		url : basePath + 'quartzController/getJobGroupList',
		valueField : 'value',
		textField : 'text',
		editable : true,
		value : $('#jGroup_add').val()
	});
	
	// 作业class
    $('#jClass_add').combobox({
        required : true,
        validType : [ "length[1,250]", "not_chinese", "remote[basePath + 'quartzController/jClassCheck', 'jClass']" ],
        method : 'get',
        url : basePath + 'quartzController/getJobClassNameList',
        valueField : 'value',
        textField : 'text',
        editable : true,
        value:$('#jClass_add').val()
    });

	// 作业描述
	$('#jDesc_add').textbox({
		required : true,
		validType : [ "length[1,100]" ]
	});

	// 作业dataMap
	var editIndex = undefined;
	$('#jobDetailDataMapTable').datagrid({
		title : quartz_page_jobsmain_edit_window_jobData,
		url : basePath + 'quartzController/getJobDetailDataMap?jobName=' + $('#jobName').val() + '&jobGroup=' + $('#jobGroup').val(),
		method : 'post',
		toolbar : '#jobDetailDataMapParam',
		height : 200,
		rownumbers : true,
		singleSelect : true,
		fitColumns : true,
		scrollbarSize : 0,
		idField : 'name',
		columns : [ [ {
			title : 'Name',
			field : 'name',
			width : '45%',
			editor : {
				type : 'validatebox',
				options : {
					validType : [ "length[1,100]", "not_chinese" ],
					required : true
				}
			}
		}, {
			title : 'Value',
			field : 'value',
			width : '45%',
			editor : {
				type : 'validatebox',
				options : {
					validType : [ "length[1,100]", "not_chinese" ],
					required : true
				}
			}
		} ] ],
		onClickRow : onClickRow,
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 添加作业参数
	$('#jobDetailDataMapAdd').linkbutton({});

	$('#jobDetailDataMapAdd').click(function() {
		addJobDetailDataMap();
	});

	// 删除作业参数
	$('#jobDetailDataMapRemove').linkbutton({});

	$('#jobDetailDataMapRemove').click(function() {
		removeJobDetailDataMap();
	});
	
	function endEditing() {
		if (editIndex == undefined) {
			return true;
		}
		if ($('#jobDetailDataMapTable').datagrid('validateRow', editIndex)) {
			$('#jobDetailDataMapTable').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	
	function onClickRow(index) {
		if (editIndex != index) {
			if (endEditing()) {
				$('#jobDetailDataMapTable').datagrid('selectRow', index).datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#jobDetailDataMapTable').datagrid('selectRow', editIndex);
			}
		}
	}
	
	function addJobDetailDataMap() {
		if (endEditing()) {
			$('#jobDetailDataMapTable').datagrid('appendRow', {
				name : quartz_page_jobsmain_edit_window_msg_1,
				value :quartz_page_jobsmain_edit_window_msg_2,
				editor : 'text'
			});
			editIndex = $('#jobDetailDataMapTable').datagrid('getRows').length - 1;
			$('#jobDetailDataMapTable').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
		}
	}

	function removeJobDetailDataMap() {
		if (editIndex == undefined) {
			return
		}
		$('#jobDetailDataMapTable').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}

	// 保存按钮
	$('#job_detail_form_add_btn').linkbutton({});

	// 绑定保存按钮事件
	$('#job_detail_form_add_btn').click(function() {
		save();
	});

	// 保存方法
	function save() {
		
		// 获得数据
		endEditing();
		var dataArr=$('#jobDetailDataMapTable').datagrid('getData').rows;
		var jsonData=JSON.stringify(dataArr);
		$('#jobDetailDataMapHid').val(jsonData);
		
		// 表单操作
		$('#job_detail_form_add').attr('action', basePath + 'quartzController/addJobDetail');
		$('#job_detail_form_add').form('submit', {
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
						jobs_table_search();
						$('#job_detail_add_window').window('close');
						showInfoMsg(message_code_8);
					} else {
					    showErrorWindow(datas.result);
					}
				} catch (e) {
					showErrorWindow(e);
				}
			}
		});
	}
});
