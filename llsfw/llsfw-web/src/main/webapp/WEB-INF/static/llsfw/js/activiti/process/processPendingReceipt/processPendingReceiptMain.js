//打开流程追踪
function taskNameClick(processDefinitionId, processInstanceId) {
	var url = basePath + 'diagram-viewer/index.jsp?processDefinitionId=' + processDefinitionId + '&processInstanceId=' + processInstanceId;
	var content = '<iframe id="' + processDefinitionId + '_' + processInstanceId + '" src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;overflow: hidden;"></iframe>';
	$('#processPendingReceiptProcessStatusWindow').window({
		title : '流程跟踪',
		collapsible : false,
		minimizable : false,
		maximizable : true,
		resizable : true,
		modal : true,
		width : 1000,
		height : 750,
		content : content,
	});
}

$(function() {
	// 分页条数
	var tablePageSize = getServerParam(basePath, 'PAGE_SIZE');

	// 构造表格
	$('#processPendingReceiptTable').datagrid({
		url : basePath + 'activiti/process/processPendingReceipt/loadProcessPendingReceipt',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		toolbar : '#processPendingReceiptTableToolBar',
		pagination : true,
		pageSize : tablePageSize,
		pageList : [ tablePageSize, tablePageSize * 2, tablePageSize * 4, tablePageSize * 6 ],
		columns : [ [ {
			title : '流程实例ID',
			field : 'processInstanceId',
			align : 'left',
			width : 80
		}, {
			title : '流程名称',
			field : 'processDefinitionName',
			align : 'left',
			width : 200
		}, {
			title : '任务名称',
			field : 'name',
			align : 'left',
			width : 200,
			formatter : function(value, row, index) {
				if (value) {
					return '<a href="#" onclick="taskNameClick(\'' + row.processDefinitionId + '\',\'' + row.processInstanceId + '\')">' + value + '</a>'
				}
			}
		}, {
			title : '优先级',
			field : 'priority',
			align : 'left',
			width : 50
		}, {
			title : '流程开始日期',
			field : 'processInstanceStartTime',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '任务创建日期',
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
			title : '任务过期日期',
			field : 'dueDate',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		}, {
			title : '流程发起人',
			field : 'processInstanceStartUserName',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				return row.processInstanceStartUserName + '(' + row.processInstanceStartUserId + ')';
			}
		} ] ],
		onLoadSuccess : function(data) {
			$('#processPendingReceiptTable').datagrid('getPager').pagination({
				layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
			});
		},
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 查询
	$('#processPendingReceipt_search_btn').click(function() {
		$('#processPendingReceiptTable').datagrid('reload');
	});

	// 签收
	$('#processPendingReceipt_Pending_btn').click(function() {
		var row = $('#processPendingReceiptTable').datagrid('getSelected');
		if (row) {
			$.messager.confirm(base_showErrorOrInfoMsg_title,message_code_10, function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						async : false,
						url : basePath + 'activiti/process/processPendingReceipt/claim?taskId=' + row.id,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#processPendingReceiptTable').datagrid('reload');
							} else {
								showErrorWindow(datas.result);
							}
						}
					});
				}
			});
		} else {
			showErrorMsg(message_code_11);
		}
	});
});
