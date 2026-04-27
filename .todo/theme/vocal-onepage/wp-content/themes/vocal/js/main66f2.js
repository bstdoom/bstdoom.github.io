jQuery(function($){

	$(window).load(function(){
		setTimeout(function(){
			$('.preloader').fadeOut(400);
		},600);
		
		$('body').fitVids();
	});

	$(window).on('scroll', function(){
		if( $(window).scrollTop() > 0 ) {
			$('#masthead').addClass( 'sticky' );
		}

		else {
			$('#masthead').removeClass( 'sticky' );
		}
	});

	var performer = $('#carousel-performer');

		performer.owlCarousel({
	      items : 4, //10 items above 1000px browser width
	      itemsDesktop : [1000,3], //5 items between 1000px and 901px
	      itemsDesktopSmall : [900,2], // betweem 900px and 601px
	      itemsTablet: [600,1], //2 items between 600 and 0
	      pagination : false,
	      itemsMobile : false // itemsMobile disabled - inherit from itemsTablet option
	  });


	$('.performerPrev').click(function(){
		performer.trigger('owl.prev');
	});

	$('.performerNext').click(function(){
		performer.trigger('owl.next');
	});

    $(window).load(function(){
    	$('.masonry-blog').masonry();
    });

	//Menu
	$('.sub-menu.megamenu').parent().addClass('has-megamenu');


	// The slider being synced must be initialized first
	$('#carousel').flexslider({
		animation: "slide",
		controlNav: false,
		animationLoop: false,
		slideshow: false,
		itemWidth: 41,
		itemMargin: 0,
		asNavFor: '#slider'
	});

	$('#slider').flexslider({
		directionNav: false,
		animation: "fade",
		controlNav: false,
		animationLoop: false,
		slideshow: false,
		sync: "#carousel"
	});




	//Woocommerce
	$( ".woo-cart" ).hover(function() {
		$(this).find('.widget_shopping_cart').stop( true, true ).fadeIn();
	}, function() {
		$(this).find('.widget_shopping_cart').stop( true, true ).fadeOut();
	});	


	//Video Gallery
  $('#gallerycarousel').flexslider({
    animation: "slide",
    controlNav: false,
    animationLoop: false,
    slideshow: false,
    itemWidth: 210,
    itemMargin: 5,
    asNavFor: '#galleryslider'
  });
 
  $('#galleryslider').flexslider({
    animation: "slide",
    controlNav: false,
    animationLoop: false,
    slideshow: false,
    sync: "#gallerycarousel"
  });
  
	$("a[rel^='prettyPhoto']").prettyPhoto();

});