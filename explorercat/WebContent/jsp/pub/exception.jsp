<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>


<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />

<html>

<head>

<title><s:text name="pageTitle.exceptionPage" /> <s:text
		name="pageTitle.seperator" /> <s:text name="pageTitle.branding" />
</title>

<s:include value="/jsp/fragments/resources.jsp" />
</head>


<body>

	<div class="container">

		<s:include value="/jsp/fragments/top-navigation-bar.jsp" />

		<div class="content">


			<h2>An unexpected error has occurred</h2>

			<!-- 
			<p>An exception was raised and was NOT captured.</p>

			<hr />
			<h3>Error Message</h3>
			<s:actionerror />

			<p>
				<s:property value="%{exception.message}" />
			</p>
			
			<hr />
			<h3>Stack:</h3>
			<p>
				<s:property value="%{exceptionStack}" />
			</p>
 			-->

		</div>

		<!-- Funding, branding and bottom navigation bar -------------------------------------->

		<s:include value="/jsp/fragments/bottom-navigation-bar.jsp" />


	</div>
</body>
</html>