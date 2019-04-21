var initBody;
function beforePrint() {
	initBody = document.body.innerHTML;
	document.body.innerHTML = print_area.innerHTML;
}
function afterPrint() {
	document.body.innerHTML = initBody;
}
function pageprint() {
	window.onbeforeprint = beforePrint;
	window.onafterprint = afterPrint;
	window.print();
}