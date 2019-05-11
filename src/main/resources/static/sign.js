$("input").keyup(
		function(e) {
			if (e.target.name === "id") {
				console.log("아이디:" + e.target.checkValidity());
				checkValid(e.target);
			}
			if ((e.target.name === "pwd1") || (e.target.name === "pwd2")) {
				var pwd1 = $("#pwd1").val();
				var pwd2 = $("#pwd2").val();

				if (pwd2.length > 0) {
					if (pwd1 != '' || pwd2 != '') {
						if ($("#pwd1").val() !== $("#pwd2").val()) {
							$("#pwd2").siblings().prevAll().prevObject
									.addClass('err');
							$("#pwd2").siblings().prev().prevObject.children()
									.addClass('err');
							e.target.setCustomValidity('비밀번호가 일치하지 않습니다.');
						} else {
							$("#pwd2").siblings().prevAll().prevObject
									.removeClass('err');
							$("#pwd2").siblings().prev().prevObject.children()
									.removeClass('err');
							e.target.setCustomValidity('');
						}
					}
				}
			}
			if (e.target.name === "name") {
				checkValid(e.target);
			}

			if (e.target.name === "birth") {
				var birth = $("#birth").val();
				if (birth >= 6) {
					birth = $("#birth").val().slice(0, 6);
					$("#birth").val(birth);
				}
				checkValid(e.target);
			}

			if (e.target.name === "phone") {
				var phone = $("#phone").val();
				if (phone >= 11) {
					phone = $("#phone").val().slice(0, 11);
					$("#phone").val(phone);
					console.log(e.target);

				}
				checkValid(e.target);
			}

			if (e.target.name === "email") {
				console.log("이메일:" + e.target.checkValidity());
				checkValid(e.target)
			}

		});
var checkValid = function(target) {
	if (!target.checkValidity()) {
		$(target).siblings().prevAll().prevObject.addClass('err');
		$(target).siblings().prev().prevObject.children().addClass('err');

	} else {
		$(target).siblings().prevAll().prevObject.removeClass('err');
		$(target).siblings().prev().prevObject.children().removeClass('err');
	}
}

function test() {
	var userId = $('#id').val();
	$.ajax({
		data : $('form').serialize(),
		url : "/signup/post",
		type : 'post',
		success : function(data) {
			if (data == "sign") {
				alert('회원가입이 완료되었습니다.');
				window.location.href = '/login';
			} else {
				alert(data);
			}

		}
	});
}