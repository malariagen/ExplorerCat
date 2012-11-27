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

<title><s:text name="pageTitle.frontPage" /> <s:text
		name="pageTitle.seperator" /> <s:text name="pageTitle.branding" />
</title>

<!------------ Global Resources Start------------>
<s:include value="/jsp/fragments/resources.jsp" />
<!------------ Global Resources End------------>

</head>


<body>

	<!------------ Page Main Container Start------------>
	<div class="container">

		<!------------ Header Start------------>
		<s:include value="/jsp/fragments/top-navigation-bar.jsp" />
		<!------------ Header End------------>

		<!------------ Page Content Container Start------------>
		<div class="content">

			<h2>
				<s:text name="title.frontPage" />
			</h2>
			<p>
				<s:text name="wording.welcome" />
			</p>

			<h3>
				<s:text name="title.selectCatalogue" />
			</h3>
			<p>
				<s:text name="wording.selectCatalogue" />
			</p>

			<!------------ Available Catalogs Start------------>
			<table class="orange-table"
				summary="Available catalogues of genetic variation">
				<thead>
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Version</th>
						<th scope="col">Date</th>
					</tr>
				</thead>
				<tbody>

					<s:iterator value="catalogs" var="catalog">

						<tr>
							<td align=center><a
								href="<s:property value="overviewURL"/>?selectedCatalogId=${catalog.id}">
									${catalog.name} </a></td>
							<td align=center>${catalog.version}</td>
							<td align=center>${catalog.releaseDateAsString}</td>
						</tr>

					</s:iterator>

				</tbody>
			</table>
			<!------------ Available Catalogs End------------>

		</div>
		<!------------ Page Content Container End------------>

		<!------------ Footer Start------------>
		<s:include value="/jsp/fragments/bottom-navigation-bar.jsp" />
		<!------------ Footer End------------>


	</div>
	<!------------ Page Main Container End------------>

</body>
</html>