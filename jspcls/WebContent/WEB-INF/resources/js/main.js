$(function() {
	$('#lbtn').click(function() {
		$(location).attr("href", "http://localhost:80/jspcls/member/login.cls");
	})
	$('#obtn').click(function() {
		$(location).attr("href", "http://localhost:80/jspcls/member/logout.cls");
	})
});