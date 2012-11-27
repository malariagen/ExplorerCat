<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />

<html>

<head>
<title>Contact</title>
<s:include value="/jsp/fragments/resources.jsp" />
</head>
<body>
	<div class="container">

		<s:include value="/jsp/fragments/top-navigation-bar-catalog.jsp" />

		<div class="content">
			<p>Please provide your feedback, report bugs or request new
				features using the form below. You will be automatically redirected
				to the home page after successful submission.</p>

			<s:form action="contactAction">
				<table class="orange-table">

					<tbody>
						<tr>
							<td class="table-property">Your name:</td>
							<td class="table-value"><s:fielderror fieldName="name"
									cssClass="contact_error" /> <s:textfield name="name"
									label="Name" cssClass="contact_input" /></td>
						</tr>
						<tr>
							<td class="table-property">Your e-mail address:</td>
							<td class="table-value"><s:fielderror fieldName="email"
									cssClass="contact_error" /> <s:textfield name="email"
									label="Email" cssClass="contact_input" />
							</td>
						</tr>
						<tr>
							<td class="table-property">Message</td>
							<td class="table-value"><s:fielderror fieldName="message"
									cssClass="contact_error" /> <s:textarea name="message"
									label="Message" cols="80" rows="10" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td class="table-value"><s:submit />
							</td>
						</tr>
					</tbody>
				</table>
			</s:form>
		</div>

		<s:include value="/jsp/fragments/bottom-navigation-bar-catalog.jsp" />
	</div>
</body>
</html>
