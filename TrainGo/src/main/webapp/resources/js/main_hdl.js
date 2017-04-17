/**
 * 
 */

jQuery(document).ready(function(){
	var wid, hgt;
	var rat;
    jQuery(".regular").slick({
        dots: false,
        infinite: true,
        slidesToShow: 1,
        slidesToScroll: 1
      });
	
/* Member Control */
	jQuery("#memberForm").submit(function(event){
		// Empty Check
	});
	
	// Image Size Control
	jQuery("img.present").each(function(index, item){
	    var context=item;
	    // Get Context Width&Height
	    wid = context.naturalWidth;
	    hgt = context.naturalHeight;
	    
	    // Calc Ratio
	    rat = 600/wid;
	    
	    // Apply Size
	    jQuery(item).prop("width", wid*rat);
	    jQuery(item).prop("height", hgt*rat);
	});
	
	/* Revolution Slider */
	jQuery("#rev_caracell").show().revolution(
	        {
	            delay:9000
	            , startwidth:1170
	            , startheight:500
	            , hideThumbs:10
	        }
	);
	
	/* For Funny */
	jQuery("#main_header").click(function(){
	    jQuery("#header_fun").toggle(function(){"slide", {}, 500,afterHide(jQuery("#header_fun"))},
	                                 function(){"slide", {}, 500,afterShow(jQuery("#header_fun"))}
	    );
	});
	
	jQuery("#header_fun").hide();
});

function afterHide(context){
    context.css("background-color","skyblue");
}
function afterShow(context){
    context.css("background-color","pink");
}

function doNothing(event){
	event.preventDefault();
	return;
}