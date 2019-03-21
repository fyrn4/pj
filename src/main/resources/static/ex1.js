function move(seq) {
    // $(".nav-item").addClass("item-active");
    $(".nav-item").find("a").click(function () {
        $(".nav-item").removeClass("item-active");
        $(this).parent().addClass("item-active");
    });

    var offset = $("#section" + seq).offset();
    $('html, body').animate({ scrollTop: offset.top - 120 }, 300);
}
function navbtn(e) {
    if (!$('.item-container').hasClass("nav-active")) {
        $(".item-container").addClass("nav-active");

    } else {
        $(".item-container").removeClass("nav-active");
    }
}
function mb_btn(e) {
    if (!$('.mb-menu').hasClass("menu-active")) {
        $(".mb-menu").addClass("menu-active");
        $(".fa-bars").addClass("fa-times");

    } else {
        $(".mb-menu").removeClass("menu-active");
        $(".fa-bars").removeClass("fa-times");
    }
}

$(document).ready(function(){
  var imgs;
  var img_count;
  var img_position = 1;
  $('#back').css('display','none');
  imgs = $(".slide ul");
  img_count = imgs.children().length;
  $('.slide-page').text('- '+img_position+'/'+img_count+' -');
  //버튼을 클릭했을 때 함수 실행
  $('#back').click(function () {
    back();
  });
  $('#next').click(function () {
    next();
  });

  function back() {
    if(1<img_position){
      imgs.animate({
        left:'+=280px'
      });
      img_position--;
      $('.slide-page').text('- '+img_position+'/'+img_count+' -');
      console.log('img_position:',img_position,'&img_count:',img_count);
      console.log('<-:',img_position);
      if(img_position==1){
    	  console.log('pre');
    	  $('#back').css('display','none');
      }else{
    	  $('#next').css('display','block');
      }
    }
  }
  function next() {
    if(img_count>img_position){
      imgs.animate({
        left:'-=280px'
      });
      img_position++;
      $('.slide-page').text('- '+img_position+'/'+img_count+' -');
      console.log('img_position:',img_position,'&img_count:',img_count);
      console.log('->:',img_position);
      if(img_position==img_count){
    	  console.log('rast');
    	  $('#next').css('display','none');
      }else{
    	  $('#back').css('display','block');
      }
    }
  }
  
  

  //이미지 끝까지 가면 버튼 사라지기
  

  //첫 이미지로 돌아오기


});
( function( $ ) {
	$( document ).ready(function() {
	$('#cssmenu li.has-sub>a').on('click', function(){
			$(this).removeAttr('href');
			var element = $(this).parent('li');
			if (element.hasClass('open')) {
				element.removeClass('open');
				element.find('li').removeClass('open');
				element.find('ul').slideUp();
			}
			else {
				element.addClass('open');
				element.children('ul').slideDown();
				element.siblings('li').children('ul').slideUp();
				element.siblings('li').removeClass('open');
				element.siblings('li').find('li').removeClass('open');
				element.siblings('li').find('ul').slideUp();
			}
		});

		$('#cssmenu>ul>li.has-sub>a').append('<span class="holder"></span>');

		(function getColor() {
			var r, g, b;
			var textColor = $('#cssmenu').css('color');
			textColor = textColor.slice(4);
			r = textColor.slice(0, textColor.indexOf(','));
			textColor = textColor.slice(textColor.indexOf(' ') + 1);
			g = textColor.slice(0, textColor.indexOf(','));
			textColor = textColor.slice(textColor.indexOf(' ') + 1);
			b = textColor.slice(0, textColor.indexOf(')'));
			var l = rgbToHsl(r, g, b);
			if (l > 0.7) {
				$('#cssmenu>ul>li>a').css('text-shadow', '0 1px 1px rgba(0, 0, 0, .35)');
				$('#cssmenu>ul>li>a>span').css('border-color', 'rgba(0, 0, 0, .35)');
			}
			else
			{
				$('#cssmenu>ul>li>a').css('text-shadow', '0 1px 0 rgba(255, 255, 255, .35)');
				$('#cssmenu>ul>li>a>span').css('border-color', 'rgba(255, 255, 255, .35)');
			}
		})();

		function rgbToHsl(r, g, b) {
		    r /= 255, g /= 255, b /= 255;
		    var max = Math.max(r, g, b), min = Math.min(r, g, b);
		    var h, s, l = (max + min) / 2;

		    if(max == min){
		        h = s = 0;
		    }
		    else {
		        var d = max - min;
		        s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
		        switch(max){
		            case r: h = (g - b) / d + (g < b ? 6 : 0); break;
		            case g: h = (b - r) / d + 2; break;
		            case b: h = (r - g) / d + 4; break;
		        }
		        h /= 6;
		    }
		    return l;
		}
	});
	} )( jQuery );

    
// // 모달 로그인
//        // Get the modal
//        var modal = document.getElementById('myModal');
 
//        // Get the button that opens the modal
//        var btn = document.getElementById("myBtn");

//        // Get the <span> element that closes the modal
//        var span = document.getElementsByClassName("close")[0];                                          

//        // When the user clicks on the button, open the modal 
//        btn.onclick = function() {
//            modal.style.display = "block";
//        }

//        // When the user clicks on <span> (x), close the modal
//        span.onclick = function() {
//            modal.style.display = "none";
//        }

//        // When the user clicks anywhere outside of the modal, close it
//        window.onclick = function(event) {
//            if (event.target == modal) {
//                modal.style.display = "none";
//            }
//        }

// $(document).ready(function(){
//     $('.item-container').mouseover(function(){
//         $('.nav-dropWrap').css("display","block");
//     });
//     $('.item-container').mouseout(function(){
//         $('.nav-dropWrap').css("display","none");
//     });
// });


