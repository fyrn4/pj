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