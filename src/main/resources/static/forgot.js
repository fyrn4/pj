function emailSendFn() {
	var userid = $('#userID').val();
	var username = $('#userName').val();
	var birth = $('#userBirth').val();
	if (userid == '' || userid == null) {
		alert('찾으실 아이디를 입력해주세요.');
		return false;
	}
	if (username == '' || username == null) {
		alert('이름을 입력해주세요.');
		return false;
	}
	if (birth == '' || birth == null) {
		alert('주민등록번호 앞 6자리를 입력해주세요.');
		return false;
	}
	$.ajax({
		url : 'forgot/pwd',
		data : $('#findPwdForm').serialize(),
		type : 'post',
		success : function(result) {
			alert(result);
		},
		beforeSend : function() {
			$('.wrap-loading').removeClass('display-none');
		},
		complete : function() {
			$('.wrap-loading').addClass('display-none');
		}
	});
}
function findId() {
	var mail = $('#email').val();
	if (mail == '' || mail == null) {
		alert('찾으실 이메일을 입력해주세요.');
		return false;
	}
	$.ajax({
		url : 'forgot/id',
		type : 'post',
		data : $('#findIdForm').serialize(),
		success : function(list) {
			var length = list.length;
			var result = '';
			$('.modal-subject').html(length + "개의 아이디를 찾았습니다.");
			if (length == 0) {
				$('#explain').html("등록된 아이디를 찾을 수 없습니다. 다시 확인해주시기 바랍니다.");
				$('#usertable').css('display', 'none');
			} else {
				$('#explain').html("회원님의 가입하신 아이디의 앞 2글자를 제외한 정보가 표시됩니다. ");
				$('#usertable').css('display', 'table');
				$.each(list, function(idx, item) {
					result += '<tr><td>' + (idx + 1) + '</td>' + '<td>' + item
							+ '</td></tr>';
				});
				$('#userlist').html(result);
			}
			modal.style.display = "block";
		}
	});
}
$(document).ready(function() {
	$(".register").click(function() {
		$(".other").show();
		$(".content").hide();
		$(".register").addClass('active');
		$(".login").removeClass('active');
	});
	$(".login").click(function() {
		$(".content").show();
		$(".other").hide();
		$(".login").addClass('active');
		$(".register").removeClass('active');
	});
	$("#email").keydown(function(key) {
		if (key.keyCode == 13) {
			findId();
		}
	});
});
var modal = document.getElementById('myModal');
var btn = document.getElementById("sub-btn");
var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
	modal.style.display = "none";
}

window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}
