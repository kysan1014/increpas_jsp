$(function() {
	$('#lbtn').click(function() {
		$(location).attr("href", "http://localhost:80/jspcls/day03/login.cls");
	})
	$('#obtn').click(function() {
		$(location).attr("href", "http://localhost:80/jspcls/day03/logout.cls");
	})
});