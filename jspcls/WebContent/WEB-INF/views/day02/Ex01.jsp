<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/jspcls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/jspcls/css/cls.css">
</head>
<body>

<div class="w3-content w3-center mw800">

	<% for (int dan = 2; dan < 10; dan++) {
		String id = "dan" + dan;
	%>
		
		<div id=<%=id%> class="w3-col m3 s10 w3-padding w3-border">
			<h3 class="w3-col w3-border w3-padding"><%= dan + " 단" %></h3>
			<% for (int gop = 1; gop < 10; gop++) { %>
			
				<div class=""><%= dan + " * " + gop + " = " + dan * gop%></div>
		
			<%} %>
			
		</div>
	
	<%} %>

</div>

</body>
</html>