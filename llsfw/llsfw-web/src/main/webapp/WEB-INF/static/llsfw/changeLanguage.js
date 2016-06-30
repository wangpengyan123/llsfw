$(function() {
	// 语言切换选择框
	$('#changeLanguage_window_datalist').datalist({
		url : basePath + '/static/llsfw/json/changeLanguage.json',
		method : 'get',
		fit : true,
		lines : true,
		onClickRow : function(index, row) {
			var localeValue = row.value;
			var url = basePath + 'index/localeChange?locale=' + localeValue;
			document.location = url;
		}
	});
});