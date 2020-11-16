$(document).ready(function() {
	$('#btn1').click(function() {
		$(location).attr("href", "/jspcls/main.cls")
	});
	$('#btn2').click(function() {
		var sid = $('#id').val();
		var spw = $('#pw').val();
		
		if (!(sid) || !(spw)) {
			return;
		}
		
		$('#frm').submit();
	});
});