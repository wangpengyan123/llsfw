$(function() {
	// 皮肤切换选择框
	$('#changeTheme_window_datalist').datalist({
		url : basePath + '/static/llsfw/json/changeTheme.json',
		method : 'get',
		fit : true,
		lines : true,
		onClickRow : function(index, row) {
			var themeValue = row.value;
			var url = basePath + 'admin/changeTheme?themeName=' + themeValue;
			document.location = url;
		}
	});
});