$(document).ready(function() {
	var formObj = $("#f1");
	$(".paging-ul a").click(function(e) {
		e.preventDefault();
		formObj.find('[name="page"]').val($(this).attr('href'));
		formObj.submit();
	});
	$("#searchBtn").click(function(e) {
		var keywordStr = $("#searchKeyword").val();
		formObj.find("[name='keyword']").val(keywordStr);
		formObj.find("[name='page']").val('1');
		formObj.submit();
	});
	$("#searchKeyword").keydown(function(e) {
		if (e.keyCode == 13) {
			var keywordStr = $("#searchKeyword").val();
			formObj.find("[name='keyword']").val(keywordStr);
			formObj.find("[name='page']").val('1');
			formObj.submit();
		}
	});
});
