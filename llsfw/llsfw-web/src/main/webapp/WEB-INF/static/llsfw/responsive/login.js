//当前语言  
var locale = '${locale}';

/**
 * 当session超时的时候,会在当前页面刷新出登录界面,但是由于起初的设计原因和shiro的跳转策略问题, 重新登陆后跳转的并不完美,则诞生如下代码,当弹出登陆界面的时候,刷新主页面,让整个系统跳转到登陆界面中, 登陆后则重新开始.
 */
try {
    if (self.frameElement && self.frameElement.tagName == "IFRAME") {
        top.location = basePath;
    }
} catch (e) {
}

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