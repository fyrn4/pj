$(function(){
		    var duration = 300;
		    var $img_cap = $('.image_caption');
		    $img_cap.find('.image-box')
		        .on('mouseover', function(){
		            $(this).find('strong').stop(true).animate({bottom: '0px'}, duration);
		            $(this).find('span').stop(true).animate({opacity: 1}, duration);
		        })
		        .on('mouseout', function(){
		            $(this).find('strong').stop(true).animate({bottom: '-80px'}, duration);
		            $(this).find('span').stop(true).animate({opacity: 0}, duration);
		        });
		});
