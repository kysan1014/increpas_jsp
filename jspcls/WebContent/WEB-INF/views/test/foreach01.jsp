<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/jspcls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/jspcls/css/cls.css">
<script type="text/javascript" src="/jspcls/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src=></script>
</head>
<body>

	<div class="w3-content w3-center mw700">
		<h2 class="w3-orange w3-padding w3-card-4 w3-margin-top"> forEach01</h2>
		<div class="w3-col">
			<c:forEach var="no" begin="1" end="10">
				<div class="w200 h30 w3-blue mgnh10 w3-card-4" style="display: inline-block; width: 200px; height: 30px; margin: 5px 10px;">${no} div</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>