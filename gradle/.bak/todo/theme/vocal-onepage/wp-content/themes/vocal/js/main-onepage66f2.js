jQuery(function($){

	//initlize onepagenav
	
	$('#menu-one-page-menu').onePageNav({
        currentClass: 'current',
        changeHash: false,
        scrollSpeed: 900,
        scrollOffset: 30,
        scrollThreshold: 0.5,
        filter: ':not(.no-scroll)'
    });

    //Scroll Menu
    $(window).on('scroll', function(){
        if( $(window).scrollTop()>200 ){
            $('#navigation').addClass('navbar-fixed-top');
        } else {
            $('#navigation').removeClass('navbar-fixed-top');
        }
    });
	
});