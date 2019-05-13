
function setCookie(name, val, expier) {
	var today = new Date();
	today.setDate(today.getDate() + expier);

	document.cookie = name + "=" + escape(val) + ";path=/;expires="
			+ today.toGMTString() + ';'
	console.log('setCookie:', document.cookie);
}
function getCookie(name) {
	var cName = name + "=";
	var x = 0;
	while (x <= document.cookie.length) {
		var y = (x + cName.length);
		if (document.cookie.substring(x, y) == cName) {
			if ((endOfCookie = document.cookie.indexOf(";", y)) == -1)
				endOfCookie = document.cookie.length;
			return unescape(document.cookie.substring(y, endOfCookie));
		}
		x = document.cookie.indexOf(" ", x) + 1;
		if (x == 0)
			break;
	}
	return "";
}
function closePopup() {
	var check = $('#popup-check').prop("checked");
	if (check) {
		setCookie('notToday', 'Y', 1);
		$('.popup-area').hide('fadeout');
	} else {
		$('.popup-area').hide('fadeout');
	}
}
$(window).ready(function(){
	if(getCookie("notToday")!="Y"){
		$('.popup-area').css('display','block');
	}
});