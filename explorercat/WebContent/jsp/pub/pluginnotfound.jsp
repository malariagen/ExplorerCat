<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- URLs --%>
<s:url action='adminLogin' var="adminLoginURL" />
<s:url action="catalogOverview" var="overviewURL" />

<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />

<html>

<head>

<title><s:text name="title.pluginNotFound" /> <s:text
		name="pageTitle.seperator" /> <s:text name="pageTitle.branding" /></title>

<!------------ Global Resources Start------------>
<s:include value="/jsp/fragments/resources.jsp" />
<!------------ Global Resources End------------>
</head>


<body>

	<!------------ Page Main Container Start------------>
	<div class="container">

		<!------------ Header Start------------>
		<s:include value="/jsp/fragments/top-navigation-bar-plugins.jsp" />
		<!------------ Header End------------>


		<!------------ Page Content Container Start------------>
		<div class="content">

			<h2>Plug-in not found</h2>

		</div>
		<!------------ Page Content Container End------------>


		<!------------ Footer Start------------>
		<s:include value="/jsp/fragments/bottom-navigation-bar-plugin.jsp" />
		<!------------ Footer End------------>

	</div>
	<!------------ Page Main Container End------------>

</body>
</html>