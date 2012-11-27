<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />

<div class="footer">
	<div class="grid-left footer-top footer-top-prefix footer-top-suffix">
	</div>
	<div class="clear">&nbsp;</div>
	<div class="footer-middle"></div>
	<div class="footer-bottom">
		<ul>
			<li><a href="http://www.malariagen.net/sitemap" alt="Sitemap">Sitemap</a>
			</li>
			<li><a href="http://www.malariagen.net/search" alt="Search">Search</a>
			</li>
			<li><a href="http://www.sanger.ac.uk/legal/cookiespolicy.html"
				alt="cookies policy" target="_blank">Cookies policy</a>
			</li>
			<li><a href="<s:url action='contactInput'/>">Provide
					feedback/report bugs/request features</a></li>
		</ul>
	</div>
</div>