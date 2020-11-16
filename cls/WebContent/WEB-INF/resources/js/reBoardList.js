$(document).ready(function() {
	$('.pagebtn').click(function() {
		var sno = $(this).attr('id');
		$('#nowPage').val(sno);
		$('#pfrm').submit();

	});

	$('.btnBox').click(function() {
		var tid = $(this).attr('id');
		var url = '';
		switch (tid) {
			case 'hbtn':
				url = '/cls/main.cls';
				break;
			case 'lbtn':
				url = '/cls/member/login.cls';
				break;
			case 'jbtn':
				url = '/cls/member/join.cls';
				break;
			case 'obtn':
				url = '/cls/member/logout.cls';
				break;
			case 'wbtn':
				$('#wrbtn').text('글 등 록');
				$('#wmodal').css('display', 'block');
				return;
		}

		$(location).attr('href', url);
	});

	$('#mcbtn').click(function() {
		$('#wmodal').css('display', 'none');
	});


	$('#wrbtn').click(function() {
		var txt = $('#body').val();
		var url = '/cls/reBoard/reBoardWriteProc.cls';
		console.log(txt);
		if (!txt) {
			alert('내용을 입력하세요');
			return;
		}
		if ($('#wrbtn').text() == '글 수 정') {
			url = '/cls/reBoard/reBoardEditProc.cls'
		} 

		$('#wfrm').attr('method', 'POST');
		$('#wfrm').attr('action', url);
		$('#wfrm').submit();
	});

	$('.rebtn').click(function() {
		var str = $(this).attr('id');
		
		$('frm1').attr('method', 'POST');
		if (str.substr(0,1) == 'd') {
			$('frm1').attr('action', '');
		}
	});
	
	$('.dbtn').click(function() {
		var str = $(this).attr('id').substr(1);
		$('#dbno').val(str);
		$('#frm1').attr('method', 'POST');
		$('#frm1').attr('action', '/cls/reBoard/reBoardDelProc.cls');
		$('#frm1').submit();
	})

	$('.ebtn').click(function() {
/*		$('#wrbtn').text('글 수 정');
		var tno = $(this).attr('id').substr(1);
		$('#tno').val(tno);
		var tbody = $(this).parent().siblings().eq(0).text();
		$('#body').val(tbody);
		$('#wmodal').css('display', 'block');*/
	
		var tno = $(this).attr('id').substr(1);
		$('#tno').val(tno);

		var tbody = $(this).parent().siblings().eq(0).html().replaceAll('<br>', '\r\n');
		console.log(tbody);
		$('#body').val(tbody);
		
		$('#wfrm').attr('method', 'POST');
		$('#wfrm').attr('action', '/cls/reBoard/reBoardEditView.cls');
		$('#wfrm').submit();
	
	});
	
	$('.rebtn').click(function() {
		var tno = $(this).attr('id');
		$('#tno').val(tno);
		
		$('#wfrm').attr('method', 'POST');
		$('#wfrm').attr('action', '/cls/reBoard/reBoardComment.cls');
		$('#wfrm').submit();
	});
	
});