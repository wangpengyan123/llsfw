$(function() {
	// 构造表格
	$('#onlineSessionTable').datagrid({
		url : basePath + 'portalController/loadOnlineSecctionData',
		method : 'post',
		fit : true,
		rownumbers : true,
		singleSelect : true,
		pagination : false,
		columns : [ [ {
			title : 'IP',
			field : 'REMOTE_HOST',
			align : 'left',
			width : '45%'
		}, {
			title : '访问时间',
			field : 'SESSION_CREATE_DATE',
			align : 'left',
			width : '45%',
			formatter : function(value, row, index) {
				if (value) {
					var unixTimestamp = new Date(value);
					return unixTimestamp.toLocaleString();
				}
			}
		} ] ],
		onLoadSuccess : function(data) {

		},
		onLoadError : function() {
			showErrorWindow(message_code_1);
		}
	});
});