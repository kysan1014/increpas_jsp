<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%!
		private String[] colors = { "#ADFF2F", "#7FFF00", "#7CFC00", "#00FF00", "#32CD32", "#98FB98", "#90EE90", "#00FA9A", "#00FF7F",
			"#3CB371", "#2E8B57", "#228B22", "#008000", "#006400", "#9ACD32", "#6B8E23", "#556B2F", "#66CDAA", "#8FBC8F",
			"#20B2AA", "#008B8B", "#008080" };
	
		public String getColor(int idx) {
			return colors[idx];
		}
	%>

	<h1 style="background-color: purple;">
		<center>스크립트 릿 방식 테스트</center>
	</h1>

	<%
		for (int i = 0; i < colors.length; i++) {
	%>

	<h3
		style="display: inline-block; width: 200px; height: 100px; background-color: <%=getColor(i)%>;"></h3>

	<%
		}
	%>

</body>
</html>