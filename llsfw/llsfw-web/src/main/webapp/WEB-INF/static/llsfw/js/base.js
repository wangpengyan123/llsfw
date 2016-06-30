/**
 * 设置未来(全局)的AJAX请求默认选项 主要设置了AJAX请求遇到Session过期的情况
 */
$.ajaxSetup({
    error: function (data) {
        showErrorWindow(data);
    }
});

/**
 * 显示错误信息
 *
 * @param errorMessage
 *            错误信息
 */
function showErrorWindow(errorMessage) {
    $('#ajaxRequestErrWindow').window({
        title: base_showErrorWindow_title,
        content: errorMessage,
        width: '600',
        height: '400',
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closable: true,
        resizable: false,
        draggable: false
    });
}

/**
 * 弹出提示信息
 *
 * @param msg
 *            消息
 */
function showErrorMsg(msg) {
    $.messager.show({
        title: base_showErrorOrInfoMsg_title,
        width: 200,
        height: 130,
        msg: '<center>' + msg + '</center>',
        timeout: 2000,
        showType: null,
        style: {
            right: '',
            top: document.body.scrollTop + document.documentElement.scrollTop,
            bottom: ''
        }
    });
}

/**
 * 弹出提示信息
 *
 * @param msg
 *            消息
 */
function showInfoMsg(msg) {
    $.messager.show({
        title: base_showErrorOrInfoMsg_title,
        width: 200,
        height: 80,
        msg: '<center>' + msg + '</center>',
        timeout: 1500,
        showType: null,
        style: {
            right: '',
            top: document.body.scrollTop + document.documentElement.scrollTop,
            bottom: ''
        }
    });
}

/**
 * <p>
 * Description: 获得指定参数
 * </p>
 *
 * @param parametersCode
 *            参数代码
 * @return 参数对象
 */
function getServerParam(path, parametersCode) {
    var parameters = null;
    $.ajax({
        type: 'POST',
        async: false,
        url: path + '/pageInit/getServerParam?parametersCode=' + parametersCode,
        success: function (data) {
            // 解析数据
            parameters = strToJson(data);
        }
    });
    return parameters;
}

/**
 * 在页面中任何嵌套层次的窗口中获取顶层窗口
 *
 * @return 当前页面的顶层窗口对象
 */
function getTopWinow() {
    var p = window;
    while (p != p.parent) {
        p = p.parent;
    }
    return p;
}

function strToJson(str) {
    /**
     * 由AnnotationMethodHandlerAdapter更换到RequestMappingHandlerAdapter后无需手动将字符串转为json对象
     * 这里更改为直接返回传入的值,什么都不做 同时,这个方法转为弃用
     *
     * 兼容string和object
     */
    if (typeof (str) == "string") {
        return JSON.parse(str);
    }
    return str;
}

/**
 * 格式化功能级别
 */
function levelDisplay(value) {
    if (value) {
        if (value == '1') {
            return base_function_level_app + '(' + value + ')';
        } else if (value == '2') {
            return base_function_level_menu + '(' + value + ')';
        } else if (value == '3') {
            return base_function_level_function + '(' + value + ')';
        } else if (value == 'PURVIEW') {
            return base_function_level_puw + '(' + value + ')';
        } else {
            return base_function_level_unknown + '(' + value + ')';
        }
    }
}

/**
 * 生成GUID
 *
 * @return {}
 */
function newGuid() {
    var guid = "";
    for (var i = 1; i <= 32; i++) {
        var n = Math.floor(Math.random() * 16.0).toString(16);
        guid += n;
        if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
            guid += "-";
    }
    return guid;
}
/**zhangzhiyu add begin */
/**
 * 导入失败时的提示窗口
 *
 * @return {}
 */
function showImportErrorMsg(msg) {
    var strArr = JSON.parse(msg);
    var message = '';
    $.each(strArr, function (index, value) {
        message += value + '<br>';
    })
    $.messager.alert({
        title: '导入失败',
        width: 500,
        height: 500,
        msg: '<center><h2><font color="red">' + message + '</font></h2></center>',
    });
}

/**
 * 为了不在IE8上报错
 *
 * @return {}
 */
function traceLog(s) {
    try {
        console.log(s)
    } catch (e) {

    }
}
/**
 * 把空格替换为转义字符 &nbsp;
 *
 * @return {}
 */
function formatHtmlBlank(value){
    if(value){
        return value.replace(/ /g, "&nbsp;");
    }
    return "";
}
// $.ajaxSetup({
// cache : false,
// async : false,
// error : function(jqXHR, textStatus, errorThrown) {
// switch (jqXHR.status) {
// case (500):
// alert("错误500");
// break;
// case (401):
// alert("错误401");
// break;
// case (403):
// alert("错误403");
// break;
// case (408):
// alert("错误408");
// break;
// default:
// alert("未知错误");
// }
// }
// });
