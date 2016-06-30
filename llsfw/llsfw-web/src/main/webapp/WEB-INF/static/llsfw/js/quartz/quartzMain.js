$(function() {
    // url数组
    var urlArray = new Array();
    urlArray[0] = basePath + 'quartzController/jobsInit';
    urlArray[1] = basePath + 'quartzController/toCurrentlyEexutingJobs';
    urlArray[2] = basePath + 'quartzController/toTriggerInit';
    urlArray[3] = basePath + 'quartzController/toSchDetailInit';
    urlArray[4] = basePath + 'quartzController/toExecutionHistoryPage?op=executionHistory';
    urlArray[5] = basePath + 'quartzController/toSchedulerChart';
    var emptyurl = basePath + '/static/llsfw/json/empty.json';

    // 初始化tabs
    $('#quartzTabs').tabs({
        fit : true,
        border : false,
        plain : true,
        onSelect : function(title, index) {
            // 清理其他标签的内容,避免ID重复
            for (var i = 0; i < urlArray.length; i++) {
                var tabClear = $('#quartzTabs').tabs('tabs')[i];
                tabClear.panel('refresh', emptyurl);
            }
            // 加载当前选择的ID
            var tab = $('#quartzTabs').tabs('getSelected');
            $('#quartzTabs').tabs('update', {
                tab : tab,
                options : {
                    href : urlArray[index]
                }
            });

        }
    });
});