$(function() {

	$('#btn1').click(function() {
		$('input').val('');
	});

	$('#btn2').click(function() {
		$('#fid').val($('#id').val());
		$('#fpw').val($('#pw').val());
		$('#submit').submit();
	});

});