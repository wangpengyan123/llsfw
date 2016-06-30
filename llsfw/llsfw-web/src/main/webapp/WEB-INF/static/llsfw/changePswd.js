/**
 * 
 */
$(function() {
    // 原始代码
    $('#old_pswd').validatebox({
        required : true,
        validType : [ "length[6,100]", "not_chinese", "remote[basePath + 'admin/pswdCheck', 'oldPswd']" ]
    });

    // 新改密码
    $('#new_pswd').validatebox({
        required : true,
        validType : [ "length[6,100]", "not_chinese" ]
    });

    // 新改密码
    $('#pswf_confim').validatebox({
        required : true,
        validType : [ "length[6,100]", "not_chinese", "same['new_pswd']" ]
    });

    // 保存按钮
    $('#pswd_save_btn').linkbutton({});

    // 绑定保存按钮事件
    $('#pswd_save_btn').click(function() {
        save();
    });

    // 保存方法
    function save() {
        $('#change_pswd_form').attr('action', basePath + 'admin/changePswd');
        // 提交
        $('#change_pswd_form').form('submit', {
            onSubmit : function() {// 提交前置事件
                var isValid = $(this).form('validate');
                if (isValid) {// 验证通过,弹出遮罩
                    $.messager.progress({
                        text : '修改中...',
                        interval : 200
                    });
                }
                return isValid;
            },
            success : function(data) {
                try {
                    // 关闭遮罩
                    $.messager.progress('close');

                    // 解析数据
                    var datas = strToJson(data);
                    if (datas.returnCode == '1') {
                        var top = getTopWinow();
                        top.location.reload();
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