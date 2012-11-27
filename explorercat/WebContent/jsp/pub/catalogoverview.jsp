<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />

<html>

<head>
<title><s:text name="pageTitle.overview" /> <s:text
		name="pageTitle.seperator" /> <s:text name="pageTitle.branding" /></title>

<!------------ Global Resources Start------------>
<s:include value="/jsp/fragments/resources.jsp" />
<!------------ Global Resources End------------>

</head>

<body>

	<!------------ Page Main Container Start------------>
	<div class="container">

		<!------------ Header Start------------>
		<s:include value="/jsp/fragments/top-navigation-bar-catalog.jsp" />
		<!------------ Header End------------>

		<!------------ Page Content Container Start------------>
		<div class="content">
			<h2>
				Overview for
				<s:property value="catalog.name" />
			</h2>
			<p>
				<s:text name="wording.catalogOverview" />
			</p>

			<!------------ Catalog Overview Start------------>
			<table class="orange-table" summary="Catalog overview">

				<tbody>
					<tr>
						<td class="table-property">Name</td>
						<td class="table-value"><s:property value="catalog.name" />
						</td>
					</tr>
					<tr>
						<td class="table-property">Version</td>
						<td class="table-value"><s:property value="catalog.version" />
						</td>
					</tr>
					<tr>
						<td class="table-property">Date</td>
						<td class="table-value"><s:property
								value="catalog.releaseDateAsString" /></td>
					</tr>
					<tr>
						<td class="table-property">Description</td>
						<td class="table-value"><s:property
								value="catalog.description" />
						</td>
					</tr>
				</tbody>
			</table>
			<!------------ Catalog Overview End------------>

			<h2>
				<s:text name="title.tools" />
			</h2>

			<p>
				<s:text name="wording.tools" />
			</p>

			<!------------ Available Plugins Start------------>
			<s:iterator value="plugins" var="plugin">
				<div class="tool-container">
					<div class="grid-left tool-icon">
						<a
							href="<s:url action='executePlugin'>
	        						<s:param name='pluginName'>${plugin.name}</s:param>
	        						<s:param name='selectedCatalogId'>${selectedCatalogId}</s:param>
	        					</s:url>">
							<img src="<s:url value='/'/>${plugin.iconPath}" /> </a>
					</div>
					<div class="grid-left tool-description">
						<a
							href="<s:url action='executePlugin'>
       							<s:param name='pluginName'>${plugin.name}</s:param>
       							<s:param name='selectedCatalogId'>${selectedCatalogId}</s:param>
       							</s:url>">
							${plugin.name} </a>
						<p>${plugin.description}</p>
					</div>
				</div>
				<div class="clear">&nbsp;</div>
			</s:iterator>
			<!------------ Available Plugins End------------>



		</div>
		<!------------ Page Content Container End------------>

		<!------------ Footer Start------------>
		<s:include value="/jsp/fragments/bottom-navigation-bar-catalog.jsp" />
		<!------------ Footer End------------>


	</div>
	<!------------ Page Main Container End------------>
</body>
</html>