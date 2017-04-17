<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><tiles:getAsString name="title"/></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
<!-- Custom Style -->
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
<!-- BootStrap -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery UI Component -->
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" rel="stylesheet">
<!-- Plugins-CSS -->
<link href="${pageContext.request.contextPath}/resources/css/slick-theme.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/slick.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/revolution_settings.css" rel="stylesheet" media="screen">
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<!-- Plugins-JS -->
<script src="${pageContext.request.contextPath}/resources/js/slick.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.themepunch.plugins.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.themepunch.revolution.js"></script>
<!-- CustomScript -->
</head>
<body>
<div id="main" class="container">
	<div id="main_header">
		<tiles:insertAttribute name="header"/>
        <div id="header_fun">Wows!!</div>
	</div>
	<div id="main_menu">
		<tiles:insertAttribute name="menu"/>
	</div>
	<div id="main_body">
		<tiles:insertAttribute name="body"/>
	</div>
	<div id="main_footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>

<!-- jQuery, BootStrap Area -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>









