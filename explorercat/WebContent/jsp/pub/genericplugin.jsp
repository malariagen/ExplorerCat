<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- URLs --%>
<s:url action='frontPage' var="frontPageURL" />

<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />

<html>
<head>
<title>${pluginTitle} <s:text name="pageTitle.seperator" /> <s:text
		name="pageTitle.branding" /></title>

<%-- Global resources for the ExplorerCat instance (CSS) --%>
<s:include value="/jsp/fragments/resources.jsp" />

<%-- CQL Connector to communicate with the API (includes jQuery library) --%>
<s:include value="/jsp/fragments/jslibs.jsp" />

<%-- Plug-in CSS files --%>
<s:iterator value="pluginCSSFilesToBeImported" var="cssFile">
	<link rel="stylesheet" type="text/css" href="${cssFile}" />
</s:iterator>

<%-- Plug-in JS files --%>
<s:iterator value="pluginJSFilesToBeImported" var="jsFile">
	<script type="text/javascript" src="${jsFile}" /></script>
</s:iterator>



<link rel="stylesheet" type="text/css" media="screen, projection"
	href="<s:url value='/css/queryComposer.css'/>" />

<link rel="stylesheet" type="text/css" media="screen, projection"
	href="<s:url value='/css/jqueryui/themes/base/jquery.ui.all.css'/>" />

<link rel="stylesheet" type="text/css" media="screen, projection"
	href="<s:url value='/css/datatabales/css/demo_table_jui_mod.css'/>" />

<link rel="stylesheet" type="text/css" media="screen, projection"
	href="<s:url value='/css/jqueryui/jquery.ui.selectmenu.css'/>" />





</head>

<body>

	<!------------ Page Main Container Start------------>
	<div class="container">

		<!------------ Header Start------------>
		<s:include value="/jsp/fragments/top-navigation-bar-plugins.jsp" />
		<!------------ Header End------------>

		<!------------ Page Content Container Start------------>
		<div class="content">
			<s:include value="/%{pluginViewPath}" />
		</div>
		<!------------ Page Content Container End------------>

		<!------------ Footer Start------------>
		<s:include value="/jsp/fragments/bottom-navigation-bar-plugin.jsp" />
		<!------------ Footer End------------>

	</div>
	<!------------ Page Main Container End------------>

</body>

<!------------ Plug-in specific JS Start------------>
<script>

	$(document).ready(function()
	{
		<%-- Get the API connectors (function defined in connector-scripts.jsp). --%>
		var connectors = expcat.init.buildConnectors(); 
		
	 	<%-- Get the parameter lookup (contains all the parameters passed to the action). --%>
		var actionParameterLookup = ${passedParametersAsJSONObject};
	
		<%-- Get the universal configuration options (options applied to all the catalogs). --%>
		var universalOptions = ${pluginJSONUniversalOptions};
	
		<%-- Get the catalog specific options (options applied ONLY to this catalog). --%>
		var localOptions = ${pluginJSONCatalogSpecificOptions};	
		
		<%-- Initiliaze the plug-in if an init function has been provided. --%>
		<s:if test="%{pluginInitFunction != null}">
		               ${pluginInitFunction}(connectors,"<s:url value='/' encode='false'/>", actionParameterLookup, universalOptions, localOptions);
		</s:if>
	}); 

</script>
<!------------ Plug-in specific JS End------------>

</html>