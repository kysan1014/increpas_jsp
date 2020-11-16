function sendData() {
	var inputs = $('from > input');
	$('.tdata').each(function(index) {
		inputs.eq(index).val($(this).text());
	});
	$('form').submit();
}

function revealAndPaintRed(jqObj) {
	jqObj.removeClass('w3-hide').removeClass('w3-text-green').addClass('w3-text-red');
}

function revealAndPaintGreen(jqObj) {
	jqObj.removeClass('w3-hide').removeClass('w3-text-red').addClass('w3-text-green');
}

function pwCheck(input) {
	var patternSpecChar = /(?=[!@#$%^&*])/;
	var patternAlpha = /(?=[a-zA-Z])/;
	var patternNumber = /(?=[0-9])/;
	var patternLength = /^.{8,20}$/;
	var patternNoKor = /(?=[ㄱ-힣])/;
	var warning = input.parent().siblings().eq(1);
	var icon = input.siblings();
	console.log(icon);

	if (input.val().length === 0) {
		revealAndPaintRed(warning);
		warning.text('필수 요소 입니다.');
	} else if (patternNoKor.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('한글은 사용하실 수 없습니다.');
	} else if (!patternSpecChar.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('비밀번호는 !@#$%^&*중 하나 이상을 반드시 포함해야 합니다.');
	} else if (!patternAlpha.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('비밀번호는 대문자나 소문자 하나 이상을 반드시 포함해야 합니다.');
	} else if (!patternNumber.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('비밀번호는 숫자 하나 이상을 반드시 포함해야 합니다.');
	} else if (!patternLength.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('비밀번호는 8~20자 사이로 입력해주세요.');
	} else {
		revealAndPaintGreen(warning);
		warning.text('사용가능한 비밀번호입니다.');
	}
}

function idCheck(input) {
	var patternNoSpecChar = /(?=[^a-zA-Z0-9._-])/;
	var patternLength = /^.{8,20}$/;
	var patternNoKor = /(?=[ㄱ-힣])/;
	var warning = input.parent().siblings().eq(1);

	if (input.val().length === 0) {
		revealAndPaintRed(warning);
		warning.text('필수 요소 입니다.');
	} else if (patternNoKor.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('한글은 사용하실 수 없습니다.');
	} else if (patternNoSpecChar.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('아이디에 특수기호는 ._-만 사용할 수 있습니다.');
	} else if (!patternLength.test(input.val())) {
		revealAndPaintRed(warning);
		warning.text('아이디는 8~20자 사이로 입력해주세요.');
	} else {
		revealAndPaintGreen(warning);
		warning.text('멋진 아이디네요.');
	}
}

$(document).ready(function() {

	$('#idck').click(function() {
		var sid = $('#id').val();
		if (!(sid)) {
			return;
		}
		$.ajax({
			// url : 'http://localhost:80/cls/member/IdCheck.cls',
			url: '/cls/member/idCheck.cls',
			type: 'post',
			dataType: 'json',
			data: {
				id: sid
			},
			success: function(obj) {
				if (obj.result == 'OK') {

					$('#idmsg').removeClass('w3-text-red').removeClass('w3-hide').addClass('w3-text-green');
					$('#idmsg').text('*** 사용 가능한 아이디 입니다. ***');
					$('#idmsg').stop().slideDown(300);

				} else {

					$('#idmsg').addClass('w3-text-red').addClass('w3-hide').removeClass('w3-text-green');
					$('#idmsg').text('*** 사용할 수 없는 아이디 입니다. ***');
					$('#idmsg').stop().slideDown(300);
				}
			},
			error: function(err) {
				console.log(err.message);
				alert('통신 에러');
				$('#id').focus();
			}

		});
	});

	// 성별 선택하면 성별맞는 아바타 선택창 보여주는 기능 처리
	$('.gen').click(function(){
		// 할일
		// 1. 무슨 성별이 선택됬는지 알아낸다.
		var sgen = $(this).val();
		
		if(sgen == 'M'){
			$('.avtFfr').stop().slideUp(100);
			$('.avtMfr').stop().slideDown(300);
		} else if(sgen == 'F'){
			$('.avtMfr').stop().slideUp(100);
			$('.avtFfr').stop().slideDown(300);
		}
	});

	$('#repw').keyup(function() {
		var spw = $('#pw').val();
		var msg = '';
		if ($(this).val() == spw) {
			msg = '*** 비밀번호가 확인되었습니다. ***';
			$('#repwmsg').addClass('w3-text-green').removeClass('w3-text-red');
		} else {
			msg = '*** 비밀번호를 확인해주세요. ***';
			$('#repwmsg').addClass('w3-text-red').removeClass('w3-text-green');
		}
		$('#repwmsg').text(msg);
		$('#repwmsg').removeClass('w3-hide');		
	});
	
	$('#name').change(function() {
		var sname = $(this).val();
		var pattern = /^[가-힣]{2,10}$/;
		if (!pattern.test(sname)) {
			revealAndPaintRed($('this'));
			$('#namemsg').text('정확한 이름인가요?');
			$('#namemsg').removeClass('w3-hide');
		} else {
			$('#namemsg').text('');
			$('#namemsg').addClass('w3-hide');
		}
	});
	
	$('#pw').keyup(function() {
		var spw = $(this).val();
		
		var pattern = /^.*(?=^[a-zA-Z0-9#@!$%&-_*]{8,})(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])(?=.*?[#@!$%&-_*]).*$/;
		
		if (!pattern.test(spw)) {
			$('#pwmsg').text('소문자, 대문자, 특수문자를 반드시 포함해주세요');
			revealAndPaintRed($('#pwmsg'));
		} else {
			$('#pwmsg').text('올바른 비밀번호입니다.');
			revealAndPaintGreen($('#pwmsg'));
		}
		
	});

	$('#hbtn').click(function() {
		$(location).attr('href', '/cls/main.cls');
	});
	
	$('#jbtn').click(function() {
		var sid = $('#id').val();
		var spw = $('#pw').val();
		var sname = $('#name').val();
		var smail = $('#mail').val();
		var tel = $('#tel').val();
		var sgen = $('.gen:checked').val();
		var savt = $('.avt:checked').val();
		
		if (!(sid && spw && sname && smail && tel && sgen && savt)) {
			return;
		}
		
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/member/joinProc.cls');
		
		$('#frm').submit();
	});

});

/*
$(document).ready(function() {

	$('#send').click(function() {
		idCheck($('#tid'));
	});

	$('#tid').on('keyup', function() {
		idCheck($(this));
	});

	$('#tpw').on('keyup', function() {
		pwCheck($(this));
	});

	$('#tpw-confirm').on('keyup', function() {
		pwConfirmCheck($(this), $('#tpw'));
	});

	//sendData();

});	*/
