<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />
<html>
<head>
<title>custom 404 page</title>
<s:include value="/jsp/fragments/resources.jsp" />
</head>
<body>
	<div class="container">
		<s:include value="/jsp/fragments/top-navigation-bar.jsp" />
		<div class="content">
			<h2>Page not found</h2>
			<p>The requested page could not be found.</p>
			<p>The page may have moved or changed its name.</p>
			<h2>HTTP 404 - File not found</h2>
		</div>
		<s:include value="/jsp/fragments/bottom-navigation-bar.jsp" />
	</div>
</body>
</html>