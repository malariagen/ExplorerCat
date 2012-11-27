<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- JQuery library  --%>
<script type="text/javascript"
	src=" <s:url value='/js/lib/jquery-1.7.2.min.js'/>">
	
</script>

<%-- CQL Connectors (APIs) --%>
<script type="text/javascript"
	src="<s:url value='/js/lib/expcat-connectors.js'/>">
	
</script>


<%-- CQL Connector to communicate with the API, Initializes the connectors (APIs) --%>
<script>
	expcat.namespace("expcat.init.buildConnectors");

	url = "<s:url value='/' encode='false'/>"

	/**
	 * Configures and creates the API connectors to communicate
	 * with the explorercat server. 
	 */

	expcat.init.buildConnectors = function() {
		var catalogId = <s:property value="selectedCatalogId"/>;

		var cqlConnectorConfiguration = {
			catalogId : catalogId,
			baseURL : url
		};

		var utilConnectorConfiguration = {
			catalogId : catalogId,
			baseURL : url
		};

		return {
			cqlConnector : new expcat.cql.CQLConnector(
					cqlConnectorConfiguration),
			utilConnector : new expcat.cql.UtilConnector(
					utilConnectorConfiguration)
		};
	};
</script>




