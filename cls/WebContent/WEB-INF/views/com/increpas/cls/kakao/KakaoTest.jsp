<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cls/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cls/css/cls.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.6.2/proj4.min.js"></script>
<script type="text/javascript" src="/cls/js/jquery-3.5.1.min.js"></script>
</head>
<body>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0cd05f2fb014f0e5457021265f1c2477"></script>
	<div id="map" style="width: 500px; height: 400px;"></div>
	<script>
		var container = document.getElementById('map');
		var options = {
			center : new kakao.maps.LatLng(33.450701, 126.570667),
			level : 3
		};

		var map = new kakao.maps.Map(container, options);
	</script>

	<script type="text/javascript">
		Proj4js.reportError = function(msg) {
			alert(msg);
		}
		Proj4js.defs['WGS84경위도'] = '+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs';
		Proj4js.defs['BESSEL경위도'] = '+proj=longlat +ellps=bessel +towgs84=-146.43,507.89,681.46 +no_defs';

		var wgs84 = new Proj4js.Proj('WGS84경위도');
		var bessel = new Proj4js.Proj('BESSEL경위도');

		var p = new Proj4js.Point('좌표값1', '좌표값2');

		Proj4js.transform(wgs84, bessel, p);
	</script>

</body>
</html>