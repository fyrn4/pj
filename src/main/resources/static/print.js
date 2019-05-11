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

/*function pageprint() {
		var initBody = document.body.innerHTML;
		window.onbeforeprint = function () {
			console.log('dd');
			document.body.innerHTML = document.getElementById("print_area").innerHTML;
		}
		window.onafterprint = function () {
			document.body.innerHTML = initBody;
		}
		window.print();
	}
*/

