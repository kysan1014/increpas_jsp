$(document).ready(function() {

	$('#hbtn').click(function() {
		$(location).attr('href', '/cls/main.cls');
	});

	$('#obtn').click(function() {
		$(location).attr('href', '/cls/member/logout.cls');
	});

	$('#rbtn').click(function() {
		$(location).attr('href', '/cls/board/boardWrite.cls');
	});

	$('#ibtn').click(function() {
		$(location).attr('href', '/cls/member/login.cls');
	});

	$('#jbtn').click(function() {
		$(location).attr('href', '/cls/member/join.cls');
	});

	$('.listItems').click(function() {
		var sbid = $(this).children().first().attr('id');
		$('#bbno').val(sbid.substring(1));
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/board/boardDetail.cls');
		console.log('ready to send request!');
		//$('#frm').submit();
	});

	$('.pagebtn').click(function() {
		var btnTxt = $(this).text();
		var btnId = $(this).attr('id');
		var nowPage = 1;
		
		if (btnTxt.charCodeAt(0) == 171 || btnTxt.charCodeAt(0) == 187) {
			// <<
			if ($(this).attr("id")) {
				nowPage = btnId;
			} else {
				return;
			}
		} else {
			nowPage = btnTxt;
		}
		$('#nowPage').val(nowPage);
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/board/boardList.cls');
		$('#frm').submit();
	});
	
	
	// 게시글 작성창
	
	$('#wbtn').click(function() {
		var shead = $('#title').val();
		var sbody = $('#body').val();
		if ((!shead) || (!sbody)) {
			return;
		}
		$('.upfile').each(function(index, item) {
			if (!$(item).val()) {
				$(item).attr("disabled", "disabled");
			}
		});

		$('#wfrm').submit();
	});
	
	$('.filediv').on('change','.upfile',function() {
		var lastElem = $('.upfile').last()
		console.log(lastElem.val());
		if (lastElem.val()) {
			var temp = $('.filerow').last().clone();
			var index = $('.upfile').index(lastElem) + 2;
			var tempInput = temp.children().last().children().last();
			tempInput.attr('name', 'file' + index);
			tempInput.val('');
			$('.filediv').append(temp);
		}
	});

});