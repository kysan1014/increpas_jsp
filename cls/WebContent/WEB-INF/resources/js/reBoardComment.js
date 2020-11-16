$(document).ready(function() {
	$('#wrbtn').click(function() {
		
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/reBoard/reBoardComment.cls');
		$('#frm').submit();
	});
});