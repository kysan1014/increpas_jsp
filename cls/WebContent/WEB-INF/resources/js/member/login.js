$(document).ready(function() {
	$('#btn1').click(function() {
		$(location).attr("href", "/cls/main.cls")
	});
	$('#btn2').click(function() {
		$('#fid').val($('#id').val());
		$('#fpw').val($('#pw').val());
		console.log($('#fid').val(), '  ', $('#fpw').val());
		
		if (!($('#fid').val()) || !($('#fpw').val())) {
			return;
		}
		
		$('#frm').submit();
	});
});