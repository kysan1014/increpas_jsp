$(function() {
/*	$('#lbtn').click(function() {
		$(location).attr("href", "/cls/member/login.cls");
	})
	$('#obtn').click(function() {
		$(location).attr("href", "/cls/member/logout.cls");
	})
	$('#jbtn').click(function() {
		$(location).attr("href", "/cls/member/join.cls");
	})
	$('#ibtn').click(function() {
		$('#frm').submit();
	})*/
	$('.w3-button').click(function(){
		var tmp = $(this).attr('id');
		var spath = '';
		switch(tmp){
			case 'lbtn':
				spath = '/cls/member/login.cls';
				break;
			case 'obtn':
				spath = '/cls/member/logout.cls';
				break;
			case 'jbtn':
				spath = '/cls/member/join.cls';
				break;
			case 'gbtn':
				spath= '/cls/guestBoard/guestBoard.cls';
				break;
			case 'ibtn':
				$('#frm').submit();
				return;
				break;
			case 'irbtn':
				spath = '/cls/reBoard/initRBD.cls';
				break;
			case 'rbtn':
				spath = '/cls/reBoard/reBoardList.cls';
				break;
			case 'fbtn':
				spath = '/cls/board/boardList.cls';
				break;
		}
		$(location).attr("href", spath);
	});
});