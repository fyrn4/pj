$('document').ready(function() {
	var AllLength = $('div[class="comment-id"]').length;
	if (AllLength == 0) {
		$('#comment-list').hide();
	}
	load('5');
	$("#more").on('click', function() {
		console.log('더보기 클릭');
		load('5', '#more');
	});
});
function load(cnt, btn) {
	var repList = ".comment-view-area:not(.display-block)";
	var repLength = $(repList).length;
	var listCnt;
	if (cnt < repLength) {
		listCnt = cnt;
	} else {
		listCnt = repLength;
		$('#more').hide();
	}
	$('.comment-view-area:not(.display-block):lt(' + listCnt + ')').addClass(
			'display-block');
}

function rp(){
	var rp_form = $('#reply_form');
	var user =  $('#replyer').val();
	var replycontent = $('#reply_text').val();
	var blank_pattern = /^\s+|\s+$/g;
	 if( user == 'anonymousUser'){
		 alert('로그인이 필요합니다.');
		 return false;
	 }
	 if( replycontent == '' || replycontent == null){
		 alert('댓글을 입력해주세요.');
		 return false;
	 }
	 if( replycontent.replace(blank_pattern,'') == "" ){
		 alert('댓글을 입력해주세요.');
		 return false;
	 }
	 else{
		 rp_form.submit();
	 }
}