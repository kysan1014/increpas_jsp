$(document).ready(function() {
	$('#hbtn').click(function() {
		var target = '/cls/main.cls';
		$(location).attr('href', target);
	});
	$('#lbtn').click(function() {
		var target = '/cls/member/login.cls';
		$(location).attr('href', target);
	});
	$('#jbtn').click(function() {
		var target = '/cls/member/join.cls';
		$(location).attr('href', target);
	});
	$('#obtn').click(function() {
		var target = '/cls/member/logout.cls';
		$(location).attr('href', target);
	});
	$('#rbtn').click(function() {
		$('#textArea').val('');
	});
	$('#wbtn').click(function() {
		$('#frm').attr("method", "POST");
		$('#frm').attr("action", "/cls/guestBoard/gBoardWrite.cls");
		$('#frm').submit();
	});



	$('.pagebtn').click(function() {
		var str = $(this).text();
		var pcode = str.charCodeAt(str);
		var sPage = '';
		if(pcode == 171){
			sPage = $(this).attr('id');
		} else if(pcode == 187){
			sPage = $(this).attr('id');
		} else {
			sPage = str;
		}
		console.log($(this).text());
		$('#nowPage').val(sPage);
		$('#gfrm').submit();

	});

});