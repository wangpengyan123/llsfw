$(function() {

    // 忘记密码
    $('#forgot-password-link').click(function() {
        toastr.options = {
            "closeButton" : true,
            "progressBar" : true,
            "positionClass" : "toast-top-center",
            "timeOut" : "3000"
        };
        toastr["info"]("请联系管理员重置密码");
    });

    // 设置语言默认值
    if (locale) {
        $("#localeChange").find("option[name='" + locale + "']").attr("selected", true);
    } else {
        $("#localeChange").find("option[name='zh_CN']").attr("selected", true);
    }
    // 绑定选择事件
    $('#localeChange').change(function() {
        var localeValue = $('#localeChange').val();
        var url = basePath + 'index/localeChange?locale=' + localeValue;
        document.location = url;
    });

    // 默认焦点
    $('#fm-login-id').focus();

    // 表单验证
    $('#login-form').validate({
        rules : {
            username : {
                required : true
            },
            password : {
                required : true
            }
        }
    });

    // 弹出服务器消息
    if ($('#msg').val()) {
        toastr.options = {
            "closeButton" : true,
            "progressBar" : true,
            "positionClass" : "toast-top-center",
            "timeOut" : "3000"
        };
        toastr["error"]($('#msg').val());
    }
});

/**
 * 验证码刷新
 */
function refreshCaptcha() {
    document.getElementById("img_captcha").src = basePath + "static/images/kaptcha.jpg?t=" + Math.random();
}