//打开流程追踪
function taskNameClick(processDefinitionId, processInstanceId) {
	var url = basePath + 'diagram-viewer/index.jsp?processDefinitionId=' + processDefinitionId + '&processInstanceId=' + processInstanceId;
	var content = '<iframe id="' + processDefinitionId + '_' + processInstanceId + '" src="' + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;overflow: hidden;"></iframe>';
	$('#processPendingProcessStatusWindow').window({
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
	$('#processPendingTable').datagrid({
		url : basePath + 'activiti/process/processPending/loadProcessPending',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		toolbar : '#processPendingTableToolBar',
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
			$('#processPendingTable').datagrid('getPager').pagination({
				layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
			});
		},
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 查询
	$('#processPending_search_btn').click(function() {
		$('#processPendingTable').datagrid('reload');
	});

	// 办理
	$('#processPending_Handle_btn').click(function() {
		var row = $('#processPendingTable').datagrid('getSelected');
		if (row) {
			var url = basePath + 'activiti/process/processPending/loadTaskForm?taskId=' + row.id;
			$('#processPendingProcessHandleWindow').dialog({
				title : '流程处理',
				collapsible : false,
				minimizable : false,
				maximizable : false,
				resizable : false,
				modal : true,
				width : 500,
				height : 500,
				href : url,
				buttons : [ {
					text : '办理',
					iconCls : 'icon-ok',
					handler : submitTask
				} ]
			});
		} else {
			showErrorMsg(message_code_11);
		}
	});

	// 提交流程
	function submitTask() {
		$.messager.confirm(base_showErrorOrInfoMsg_title, '请确认表单内容后点击确认提交', function(r) {
			if (r) {
				$('#processTaskForm').attr('action', basePath + 'activiti/process/processPending/completeTask');
				// 提交
				$('#processTaskForm').form('submit', {
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
								$('#processPending_search_btn').click();
								$('#processPendingProcessHandleWindow').dialog('close');
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
	}

});
