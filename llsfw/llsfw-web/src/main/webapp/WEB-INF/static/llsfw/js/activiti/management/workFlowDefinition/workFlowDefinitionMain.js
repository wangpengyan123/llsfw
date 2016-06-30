/**
 * 
 */

function resourceNameClick(id, type) {
	window.open(basePath + 'activiti/management/workFlowDefinition/readResource?id=' + id + '&type=' + type);
}
function suspendedClick(id, type) {
	$.ajax({
		type : 'POST',
		async : false,
		url : basePath + 'activiti/management/workFlowDefinition/updateState?id=' + id + '&type=' + type,
		success : function(data) {
			// 解析数据
			var datas = strToJson(data);
			if (datas.returnCode == '1') {
				$('#workFlowDefinition_search_btn').click();
			} else {
				showErrorWindow(datas.result);
			}
		}
	});
}

$(function() {
	// 分页条数
	var tablePageSize = getServerParam(basePath, 'PAGE_SIZE');

	// 构造表格
	$('#workFlowDefinitionTable').datagrid({
		url : basePath + 'activiti/management/workFlowDefinition/loadWorkFlowDefinitionList',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		toolbar : '#workFlowDefinitionTableToolBar',
		pagination : true,
		pageSize : tablePageSize,
		pageList : [ tablePageSize, tablePageSize * 2, tablePageSize * 4, tablePageSize * 6 ],
		columns : [ [ {
			title : 'ID',
			field : 'id',
			align : 'left',
			width : 200
		}, {
			title : '名称',
			field : 'name',
			align : 'left',
			width : 200
		}, {
			title : 'KEY',
			field : 'key',
			align : 'left',
			width : 200
		}, {
			title : '描述',
			field : 'description',
			align : 'left',
			width : 200
		}, {
			title : '版本',
			field : 'version',
			align : 'left',
			width : 50
		}, {
			title : '部署ID',
			field : 'deploymentId',
			align : 'left',
			width : 50
		}, {
			title : 'XML',
			field : 'resourceName',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					return '<a href="#" onclick="resourceNameClick(\'' + row.id + '\',\'xml\')">' + value + '</a>'
				}
			}
		}, {
			title : '图片',
			field : 'diagramResourceName',
			align : 'left',
			width : 150,
			formatter : function(value, row, index) {
				if (value) {
					return '<a href="#" onclick="resourceNameClick(\'' + row.id + '\',\'image\')">' + value + '</a>'
				}
			}
		}, {
			title : '启动表单KEY',
			field : 'hasStartFormKey',
			align : 'center',
			width : 80,
			formatter : function(value, row, index) {
				if (value == true) {
					return '<i class="fa fa-check fa-lg"></i>';
				} else {
					return '<i class="fa fa-times fa-lg"></i>';
				}
			}
		}, {
			title : '图形符号',
			field : 'hasGraphicalNotation',
			align : 'center',
			width : 60,
			formatter : function(value, row, index) {
				if (value == true) {
					return '<i class="fa fa-check fa-lg"></i>';
				} else {
					return '<i class="fa fa-times fa-lg"></i>';
				}
			}
		}, {
			title : '是否挂起',
			field : 'isSuspended',
			align : 'center',
			width : 60,
			formatter : function(value, row, index) {
				if (value == true) {
					return '<a href="#" onclick="suspendedClick(\'' + row.id + '\',\'1\')">挂起</a>';
				} else {
					return '<a href="#" onclick="suspendedClick(\'' + row.id + '\',\'0\')">激活</a>';
				}
			}
		}, {
			title : '经办人',
			field : 'tenantId',
			align : 'left',
			width : 50
		}, {
			title : '部署时间',
			field : 'deploymentTime',
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
			$('#workFlowDefinitionTable').datagrid('getPager').pagination({
				layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
			});
		},
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});

	// 查询
	$('#workFlowDefinition_search_btn').click(function() {
		$('#workFlowDefinitionTable').datagrid('reload');
	});

	// 删除
	$('#workFlowDefinition_delete_btn').click(function() {
		var row = $('#workFlowDefinitionTable').datagrid('getSelected');
		if (row) {
			$.messager.confirm(base_showErrorOrInfoMsg_title, message_code_2, function(r) {
				if (r) {
					$.ajax({
						type : 'POST',
						async : false,
						url : basePath + 'activiti/management/workFlowDefinition/deleteDeployment?deploymentId=' + row.deploymentId,
						success : function(data) {
							// 解析数据
							var datas = strToJson(data);
							if (datas.returnCode == '1') {
								$('#workFlowDefinitionTable').datagrid('reload');
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
