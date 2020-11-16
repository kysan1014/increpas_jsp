$(document).ready(function() {
	$('#wrbtn').click(function() {
		var body = $('#body').val();
		var obody = $('#obody').val();
		if (body == obody) {
			return;
		}
		$('#frm').attr('method', 'POST');
		$('#frm').attr('action', '/cls/reBoard/reBoardEditProc.cls');
		$('#frm').submit();
	});
});