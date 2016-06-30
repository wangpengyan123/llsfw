$(function() {
    // 初始化Portal
    $('#minPortal').portal({
        border : false,
        fit : true
    });

    // 添加面板到portal中
    function addPanleToPortal(id, title, height, url, index) {
        // 创建面板
        var p = $('<div id="' + id + '"></div>').appendTo('body');
        p.panel({
            title : title,
            height : height,
            closable : false,
            collapsible : true,
            href : basePath + url,
            tools : [ {
                iconCls : 'icon-reload',
                handler : function() {
                    $('#' + id + '').panel('refresh');
                }
            } ]
        });
        // 将面板放入PORTAL中
        $('#minPortal').portal('add', {
            panel : p,
            columnIndex : index
        });
    }

    // 构造面板
    if (portal) {
        for (var i = 0; i < portal.length; i++) {
            addPanleToPortal(portal[i].PORTAL_CODE, portal[i].PORTAL_TITLE, portal[i].PORTAL_HEIGHT, portal[i].PORTAL_URL, portal[i].PORTAL_INDEX);
        }
    }
});