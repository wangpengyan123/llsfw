/**
 * 
 */
$(function() {

    // 分页条数
    var tablePageSize = getServerParam(basePath, 'PAGE_SIZE');

    // 构造表格
    $('#engineDataBaseTableRowsTable').datagrid({
        url : basePath + 'activiti/settings/engineDataBase/loadEngineDataBaseTableData?tableName=' + tableName,
        method : 'post',
        fit : true,
        rownumbers : true,
        singleSelect : true,
        pagination : true,
        pageSize : tablePageSize,
        pageList : [ tablePageSize, tablePageSize * 2, tablePageSize * 4, tablePageSize * 6 ],
        columns : columns,
        onLoadSuccess : function(data) {
            $('#engineDataBaseTableRowsTable').datagrid('getPager').pagination({
                layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
            });
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });

});