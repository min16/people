$(function(){
	
	var menus = $('.menus');
	
	$('.menus .item').mouseenter(function(){
		var list = $(this).children('.list');
		var mql = window.matchMedia('(min-width: 769px)');
		if(list.length > 0 && mql.matches){
			list.slideDown();
		}
	});
	
	$('.menus .item').mouseleave(function(){
		var list = $(this).children('.list');
		var mql = window.matchMedia('(min-width: 769px)');
		if(list.length > 0 && mql.matches){
			list.hide();
		}
	});
	
	$('.menus .item').on('touchstart', function(){
		var mql = window.matchMedia('(max-width: 769px)');
		if(list.length > 0 && mql.matches){
			alert('true');
			this.addClass('clicked');
		}
	});
	
	$('.mobile-menu').on('click', function(){
		var isOn = menus.hasClass('on');
		if(isOn){
			menus.removeClass('on');
		}else {
			menus.addClass('on');
		}
	});
});

function goList(category){
	location.href = "/posts?category=" + category;
}

function goToPost(postNo){
	location.href = "/posts/" + postNo;
}


