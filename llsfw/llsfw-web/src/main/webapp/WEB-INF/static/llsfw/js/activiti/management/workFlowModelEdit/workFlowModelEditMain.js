$(function() {

	// 分页条数
	var tablePageSize = getServerParam(basePath, 'PAGE_SIZE');

	// 构造表格
	$('#workFlowModelTable').datagrid({
		url : basePath + 'activiti/management/workFlowModelEdit/loadWorkFlowModelList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		toolbar : '#workFlowModelTableToolBar',
		pagination : true,
		pageSize : tablePageSize,
		pageList : [ tablePageSize, tablePageSize * 2, tablePageSize * 4, tablePageSize * 6 ],
		columns : [ [ {
			title : 'id',
			field : 'id',
			align : 'left',
			width : 100
		}, {
			title : 'key',
			field : 'key',
			align : 'left',
			width : 100
		}, {
			title : '名称',
			field : 'name',
			align : 'left',
			width : 100
		}, {
			title : '版本',
			field : 'version',
			align : 'left',
			width : 50
		}
		// , {
		// title : '类别',
		// field : 'category',
		// align : 'left',
		// width : 50
		// }
		, {
			title : '元数据',
			field : 'metaInfo',
			align : 'left',
			width : 350
		}, {
			title : '部署ID',
			field : 'deploymentId',
			align : 'left',
			width : 100
		}, {
			title : 'editorSourceValueId',
			field : 'editorSourceValueId',
			align : 'left',
			width : 121
		}, {
			title : 'editorSourceExtraValueId',
			field : 'editorSourceExtraValueId',
			align : 'left',
			width : 150
		}, {
			title : '承办人ID',
			field : 'tenantId',
			align : 'left',
			width : 100
		}, {
			title : '创建时间',
			field : 'createTime',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '最后更新时间',
			field : 'lastUpdateTime',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		} ] ],
		onLoadSuccess : function(data) {
			$('#workFlowModelTable').datagrid('getPager').pagination({
				layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
			});
		},
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 查询
	$('#workFlowModel_search_btn').click(function() {
		$('#workFlowModelTable').datagrid('reload');
	});

	// 新增模型
	$('#workFlowModel_add_btn').click(function() {
		// 弹出新增窗口
		$('#addWorkFlowModelWindow').window({
			title : '新增模型',
			collapsible : false,
			minimizable : false,
			maximizable : false,
			resizable : false,
			modal : true,
			width : 230,
			height : 200,
			href : basePath + 'activiti/management/workFlowModelEdit/toAddWorkFlowModel'
		});

	});

	// 编辑模型
	$('#workFlowModel_edit_btn').click(function() {
		var row = $('#workFlowModelTable').datagrid('getSelected');
		if (row) {
			window.open(basePath + 'modeler.jsp?modelId=' + row.id, "_blank");
		} else {
			showErrorMsg(message_code_5);
		}
	});

	// 删除
	$('#workFlowModel_delete_btn').click(function() {
		var row = $('#workFlowModelTable').datagrid('getSelected');
		if (row) {
			$.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						async : false,
						url : basePath + 'activiti/management/workFlowModelEdit/deleteWorkFlowModel?modelId=' + row.id,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#workFlowModel_search_btn').click();
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

	// 下载模型
	$('#workFlowModel_download_btn').click(function() {
		var row = $('#workFlowModelTable').datagrid('getSelected');
		if (row) {
			window.location = basePath + 'activiti/management/workFlowModelEdit/downloadWorkFlowModel?modelId=' + row.id;
		} else {
			showErrorMsg(message_code_11);
		}
	});
});