$(document).ready(function() {
	$('.pbtn').click(function() {
		var str = $(this).text();
		var sno = $(this).attr('id');
		
		if(!sno) {
			sno = str;
		}
		
		$('#pfrm').attr('method', 'POST');
		$('#pfrm').attr('href', '/cls/reBoard/reBoardList.cls');
		$('#pfrm').prepend('<input type="hidden" name="nowPage" value="' + sno + '">');
		$('#pfrm').submit();
		
	})
});