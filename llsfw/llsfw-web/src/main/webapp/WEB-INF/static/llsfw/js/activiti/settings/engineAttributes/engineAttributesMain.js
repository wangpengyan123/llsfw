/**
 * 
 */
$(function() {
    $('#engineAttributesTable').propertygrid({
        url : basePath + 'activiti/settings/engineAttributes/loadEngineAttributesData',
        method : 'post',
        border : false,
        fit : true,
        showGroup : false,
        columns : [ [ {
            title : '属性名称',
            field : 'name'
        }, {
            title : '属性值',
            field : 'value'
        } ] ]
    });
});