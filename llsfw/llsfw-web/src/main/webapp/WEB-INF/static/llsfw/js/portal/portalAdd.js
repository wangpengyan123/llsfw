/**
 * 
 */
$(function() {

    // 选择面板
    if ($('#op').val() == 'add') {
        $('#portalCode').combobox({
            required : true,
            method : 'get',
            url : basePath + 'portalController/loadPoartList',
            valueField : 'PORTAL_CODE',
            textField : 'PORTAL_TITLE',
            editable : false
        });
    } else {
        $('#portalCodeLable').css('display', 'none');
    }

    // 面板位置
    $('#portalIndex').combobox({
        required : true,
        valueField : 'value',
        textField : 'text',
        editable : false,
        data : [ {
            "value" : '0',
            "text" : '左'
        }, {
            "value" : '1',
            "text" : '中'
        }, {
            "value" : '2',
            "text" : '右'
        } ],
        value : $('#portalIndex').val()
    });

    // 面板顺序
    $('#portalSort').numberbox({
        required : true,
        validType : [ "length[1,5]" ],
        min : 0
    });

    // 保存按钮
    $('#portal_add_btn').linkbutton({});

    // 绑定保存按钮事件
    $('#portal_add_btn').click(function() {
        save();
    });

    // 保存方法
    function save() {
        $('#function_portal_form_add').attr('action', basePath + 'portalController/portalAdd');
        // 提交
        $('#function_portal_form_add').form('submit', {
            onSubmit : function() {// 提交前置事件
                var isValid = $(this).form('validate');
                if (isValid) {// 验证通过,弹出遮罩
                    $.messager.progress({
                        text : '保存中...',
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
                        $('#function_add_window').window('close');
                        $('#portal_search_table').datagrid('load');
                    } else if (datas.returnCode == '-1') {
                        showErrorWindow(datas.result);
                    }
                } catch (e) {
                    showErrorWindow(data);
                }

            }
        });
    }
});
