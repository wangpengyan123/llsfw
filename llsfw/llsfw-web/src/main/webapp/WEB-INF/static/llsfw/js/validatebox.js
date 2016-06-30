$.extend($.fn.validatebox.defaults.rules, {
    yyyymmdd : {
        validator : function(value) {
            var reg = /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
            return reg.test(value);
        },
        message : validatebox_yyyymmdd
    },
    yyyymmddhhmiss : {
        validator : function(value) {
            var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;
            return reg.test(value);
        },
        message : validatebox_yyyymmddhhmiss
    },
    minLength : { // 判断最小长度
        validator : function(value, param) {
            return value.length >= param[0];
        },
        message : validatebox_minLength
    },
    length : {
        validator : function(value, param) {
            var len = $.trim(value).length;
            return len >= param[0] && len <= param[1];
        },
        message : validatebox_length
    },
    phone : {// 验证电话号码
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : validatebox_phone
    },
    mobile : {// 验证手机号码
        validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message : validatebox_mobile
    },
    phoneOrMobile : {// 验证手机或电话
        validator : function(value) {
            return /^(13|15|18)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : validatebox_phoneOrMobile
    },
    idcard : {// 验证身份证
        validator : function(value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message : validatebox_idcard
    },
    floatOrInt : {// 验证是否为小数或整数
        validator : function(value) {
            return /^(\d{1,3}(,\d\d\d)*(\.\d{1,3}(,\d\d\d)*)?|\d+(\.\d+))?$/i.test(value);
        },
        message : validatebox_floatOrInt
    },
    currency : {// 验证货币
        validator : function(value) {
            return /^d{0,}(\.\d+)?$/i.test(value);
        },
        message : validatebox_currency
    },
    qq : {// 验证QQ,从10000开始
        validator : function(value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message : validatebox_qq
    },
    integer : {// 验证整数
        validator : function(value) {
            return /^[+]?[1-9]+\d*$/i.test(value);
        },
        message : validatebox_integer
    },
    chinese : {// 验证中文
        validator : function(value) {
            return (/[\u4e00-\u9fa5]/g.test(value));
        },
        message : validatebox_chinese
    },
    not_chinese : {// 验证非中文
        validator : function(value) {
            return !(/[\u4e00-\u9fa5]/g.test(value));
        },
        message : validatebox_not_chinese
    },
    english : {// 验证英语
        validator : function(value) {
            return (/[[A-Za-z]]/g.test(value));
        },
        message : validatebox_english
    },
    unnormal : {// 验证是否包含空格和非法字符
        validator : function(value) {
            return /.+/i.test(value);
        },
        message : validatebox_unnormal
    },
    faxno : {// 验证传真
        validator : function(value) {
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message : validatebox_faxno
    },
    zip : {// 验证邮政编码
        validator : function(value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message : validatebox_zip
    },
    ip : {// 验证IP地址
        validator : function(value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message : validatebox_ip
    },
    same : {
        validator : function(value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message : validatebox_same
    },
    org_not_same : {
        validator : function(value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return !($("#" + param[0]).val() == value);
            } else {
                return true;
            }
        },
        message : validatebox_org_not_same
    },
    //zhangzhiyu add begin
    check_unique : {
        validator : function(value, param) {
            var postData = {};
            postData[param[1]] = value;
            var resultFlag = $.ajax({
                url : param[0],
                dataType : "json",
                data : postData,
                async : false,
                cache : false,
                type : "post"
            }).responseText;
            if (resultFlag == "false") {
                $.fn.validatebox.defaults.rules.check_unique.message = param[2];
                return false;
            } else {
                return true;
            }

        },
        message : ""
    },
    isExcel : {
        validator : function(value, param) {
            if (value.substring(value.length-5,value.length)!=".xlsx") {
                return false
            } else {
                return true;
            }
        },
        message : "请选择以xlsx结尾的Excel文件进行导入"
    },
    isTxt : {
        validator : function(value, param) {
            if (value.substring(value.length-4,value.length)!=".txt") {
                return false
            } else {
                return true;
            }
        },
        message : "请选择以txt文本文件进行导入"
    },
    //zhangzhiyu add end
});
