jQuery(document).ready(function(){'use strict';


	jQuery("#poll-form").on('submit',function(event){
		
		var postdata = jQuery(this).serializeArray();
		var formURL = jQuery(this).attr("action");
		
		console.log(postdata);

			jQuery.ajax({

					url : formURL,
					type: "POST",
					data : postdata,
					//dataType :'json',
					success:function(data) {
						//jQuery('#poll-form')[0].reset();
						//alert(data.slice(0,-1));
						jQuery("#pull-reasult-html").html(data.slice(0,-1));
						jQuery(".themeum-poll").hide();
						jQuery( "#pull-reasult-html" ).addClass( "reasult-class" );
					},
					error: function(errorThrown){
						jQuery("#pull-reasult-html").html("Poll Request Failed");
						// jQuery("#simple-msg-err").html('<span style="color:red">AJAX Request Failed<br/> textStatus='+textStatus+', errorThrown='+errorThrown+'</span>');
					}
				});
				
		event.preventDefault();	//STOP default action
		//event.unbind(); //Remove a previously-attached event handler
		
	});
	//jQuery("#poll-form").submit(poll_submit_form); //SUBMIT FORM


});