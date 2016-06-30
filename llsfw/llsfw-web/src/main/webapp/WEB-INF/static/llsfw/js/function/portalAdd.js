/**
 * 
 */
$(function() {

    // 面板标题
    $('#portalTitle_add').textbox({
        required : true,
        validType : [ "length[1,100]" ]
    });

    // 面板高度
    $('#portalHeight_add').numberbox({
        required : true,
        validType : [ "length[1,5]" ],
        min : 150
    });

    // 面板地址
    $('#portalUrl_add').textbox({
        required : true,
        validType : [ "length[1,100]" ]
    });

    // 保存按钮
    $('#portal_add_btn').linkbutton({});

    // 绑定保存按钮事件
    $('#portal_add_btn').click(function() {
        save();
    });

    // 保存方法
    function save() {
        $('#function_portal_form_add').attr('action', basePath + 'functionController/portalAdd');
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
                        $('#function_Portal_add_windwos').window('close');
                        $('#function_Portal_search_table').datagrid('load');
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
