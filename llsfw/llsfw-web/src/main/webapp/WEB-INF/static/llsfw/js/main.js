$(function() {

	// 如果页面宽度小于769,则添加body-small
	if ($(this).width() < 769) {
		$('body').addClass('body-small')
	} else {
		$('body').removeClass('body-small')
	}

	// 初始化菜单
	$('#side-menu').metisMenu();

	// 最小化菜单
	$('.navbar-minimalize').click(function() {
		$("body").toggleClass("mini-navbar");
		SmoothlyMenu();

	});

	// Full height of sidebar
	function fix_height() {
		var heightWithoutNavbar = $("body > #wrapper").height() - 61;
		$(".sidebard-panel").css("min-height", heightWithoutNavbar + "px");
		var navbarHeigh = $('nav.navbar-default').height();
		var wrapperHeigh = $('#page-wrapper').height();
		if (navbarHeigh > wrapperHeigh) {
			$('#page-wrapper').css("min-height", navbarHeigh + "px");
		}
		if (navbarHeigh < wrapperHeigh) {
			$('#page-wrapper').css("min-height", $(window).height() + "px");
		}
		if ($('body').hasClass('fixed-nav')) {
			$('#page-wrapper')
					.css("min-height", $(window).height() - 60 + "px");
		}
	}
	fix_height();

	// Fixed Sidebar
	$(window).bind("load", function() {
		if ($("body").hasClass('fixed-sidebar')) {
			$('.sidebar-collapse').slimScroll({
				height : '100%',
				railOpacity : 0.9
			});
		}
	});

	// Move right sidebar top after scroll
	$(window).scroll(function() {
		if ($(window).scrollTop() > 0 && !$('body').hasClass('fixed-nav')) {
			$('#right-sidebar').addClass('sidebar-top');
		} else {
			$('#right-sidebar').removeClass('sidebar-top');
		}
	});

	$(window).bind("load resize scroll", function() {
		if (!$("body").hasClass('body-small')) {
			fix_height();
		}
	});

	$("[data-toggle=popover]").popover();

	// Add slimscroll to element
	$('.full-height-scroll').slimscroll({
		height : '100%'
	});

	// Open close right sidebar
	$('.right-sidebar-toggle').click(function() {
		$('#right-sidebar').toggleClass('sidebar-open');
	});

});

// Minimalize menu when screen is less than 768px
$(window).bind("resize", function() {
	if ($(this).width() < 769) {
		$('body').addClass('body-small')
	} else {
		$('body').removeClass('body-small')
	}
});

function SmoothlyMenu() {
	if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
		// Hide menu in order to smoothly turn on when maximize menu
		$('#side-menu').hide();
		// For smoothly turn on menu
		setTimeout(function() {
			$('#side-menu').fadeIn(500);
		}, 100);
	} else if ($('body').hasClass('fixed-sidebar')) {
		$('#side-menu').hide();
		setTimeout(function() {
			$('#side-menu').fadeIn(500);
		}, 300);
	} else {
		// Remove all inline style from jquery fadeIn function to reset menu
		// state
		$('#side-menu').removeAttr('style');
	}
}