/**
 * 
 */
$(function() {
    // 构造表格
    $('#engineDataBaseTableNames').datalist({
        url : basePath + 'activiti/settings/engineDataBase/loadEngineDataBaseTablesName',
        method : 'post',
        boder : false,
        fit : true,
        lines : true,
        columns : [ [ {
            title : '表名',
            field : 'TABLE_NAME',
            align : 'left',
            formatter : function(value, row, index) {
                return value + "(" + row.TABLE_COUNT + ")";
            }
        } ] ],
        onClickRow : function(index, row) {
            var tableName = row.TABLE_NAME;
            var url = basePath + 'activiti/settings/engineDataBase/toloadEngineDataBaseTablesRows?tableName=' + tableName;
            $('#engineDataBaseTableRowsPanle').panel('refresh', url);
        },
        onLoadError : function() {
            showErrorWindow(message_code_1);
        }
    });
});