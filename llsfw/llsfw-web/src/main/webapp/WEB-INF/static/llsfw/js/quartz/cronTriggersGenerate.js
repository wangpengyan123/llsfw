/**
 * 
 */
$(function() {
    // 获取cron表达式
    $('#cron_triggers_generate_save_btn').click(function() {
        $('#cron_triggerCronExcp_add').textbox('setValue', '');
        $('#cron_triggerCronExcp_add').textbox('setValue', $('#cron').val());
        $('#cron_triggers_generate_window').window('close');
    });
});
