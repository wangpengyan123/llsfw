$(function() {

    // 添加主选项卡
    addTab('mainIframe', admin_page_welcome, 'admin/toMainPage', false);

    // 修改密码
    $('#changePswd').click(function() {
        // 弹出密码修改窗口
        $('#change_pswd_window').window({
            title : admin_changePswd_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 200,
            height : 210,
            href : basePath + 'admin/toChangePswd'
        });
    });

    // 语言切换弹出层
    $('#changeLanguage').click(function() {
        $('#changeLanguage_window').window({
            href : basePath + 'admin/toChangeLanguage',
            title : admin_changeLanguage_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 200,
            height : 300
        });
    });

    // 皮肤切换弹出层
    $('#changeTheme').click(function() {
        $('#changeTheme_window').window({
            href : basePath + 'admin/toChangeTheme',
            title : admin_changeTheme_title,
            collapsible : false,
            minimizable : false,
            maximizable : false,
            resizable : false,
            modal : true,
            width : 200,
            height : 300
        });
    });

    // 释放内存
    $.fn.panel.defaults = $.extend({}, $.fn.panel.defaults, {
        onBeforeDestroy : function() {
            var frame = $('iframe', this);
            if (frame.length > 0) {
                try {
                    frame[0].contentWindowcument.write('');
                } catch (e) {
                }
                frame[0].contentWindow.close();
                frame.remove();
            }
        }
    });

});

// 处理菜单树,点击旁边空白地方的事件响应
function openThisNoed(node) {
    var children = $('.easyui-tree').tree('getChildren', node.target);
    var fun = $(node.target).find('div').attr("openFunction");
    if (fun) {
        var params = fun.substring(7, fun.length - 2).replace(/'/g, '').split(",");
        addTab(params[0], params[1], params[2], true);
    }
}

// 添加选项卡
function addTab(iframeId, subtitle, url, closable) {
    $.messager.progress({
        text : '页面加载中....',
        interval : 200
    });
    // 是否关闭标志
    var closeFlg = true;
    if (closable != null) {
        closeFlg = closable;
    }
    if (!$('#maintabs').tabs('exists', subtitle)) {
        // iframe方式
        var content = '<iframe id="' + iframeId + '" src="' + basePath + url + '" frameborder="0" style="border:0;width:100%;height:99.4%;overflow: hidden;"></iframe>';
        $('#maintabs').tabs('add', {
            title : subtitle,
            content : content,
            closable : closeFlg,
            tools : [ {
                iconCls : 'icon-mini-refresh',
                handler : function() {
                    refreshTab();
                }
            } ]
        });

        // 自带href的方式
        // $('#maintabs').tabs('add', {
        // title : subtitle,
        // href : url,
        // closable : true
        // });
    } else {
        $('#maintabs').tabs('select', subtitle);
    }
    $.messager.progress('close');
}

function refreshTab() {
    var currTab = $('#maintabs').tabs('getSelected');
    var iframe = $(currTab.panel('options').content);
    var id = iframe.attr('id');
    var src = iframe.attr('src');
    document.getElementById(id).src = src;
}
