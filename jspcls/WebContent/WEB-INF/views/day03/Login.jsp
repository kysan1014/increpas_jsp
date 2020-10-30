<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/jspcls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/jspcls/css/cls.css">
<script type="text/javascript" src="/jspcls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="/jspcls/js/login.js"></script>
<style>
	label {
		font-size: 16pt;
	}
	
	#msg {
		font-size: 20px;
		font-weight: bold;
		color: indigo;
	}
</style>
</head>
<body>

	<div class="w3-content w3-center mw500">
		<h1 class="w3-blue w3-padding w3-card-4">로그인 비동기 통신</h1>
		<form method="post" action="/jspcls/day03/loginProc.cls" class="w3-col w3-padding-large w3-card-4" id="frm" name="frm">
			<div class="w3-col">
				<label for="id" class="w3-col m2 w3-right-align w3-text-grey">I D : &nbsp;</label>
				<input type="text" id="id" name="id" placeholder="아이디를 입력하세요!"
						class="w3-col m10 w3-input w3-border w3-round-4">
			</div>
			<div class="w3-col w3-margin-top">
				<label for="pw" class="w3-col m2 w3-right-align w3-text-grey">P W : &nbsp;</label>
				<input type="password" id="pw" name="pw" placeholder="비밀번호를 입력하세요!"
						class="w3-col m10 w3-input w3-border w3-round-4">
			</div>
			<div class="w3-col w3-margin-top">
				<div class="w3-half w3-button w3-red w3-hover-orange" id="btn1">Home</div>
				<div class="w3-half m2 w3-button w3-blue w3-hover-aqua" id="btn2">Login</div>
			</div>
		</form>
		<div class="w3-col w3-padding w3-card-4 w3-hide" id="msg">
			<span>이미 로그인했습니다.</span>
		</div>
	</div>

</body>
</html>