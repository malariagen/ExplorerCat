<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%-- Context path (has to prefix any link) --%>
<s:url value='/' var="contextPrefix" />

<div class="footer">
	<div class="grid-left footer-top footer-top-prefix footer-top-suffix">
		<a href="http://explorercat.sanger.ac.uk" target="_blank"> <img
			src="<s:url value='/css/img/explorercat-logo.jpg'/>"
			alt="ExplorerCat" width="51" height="51" /> </a> <a
			href="http://www.sanger.ac.uk/research/projects/malariaprogramme-kwiatkowski"
			target="_blank"> <img
			src="<s:url value='/css/img/team112-logo.jpg'/>"
			alt="Kwiatkowski group" width="39" height="51" /> </a> <a
			href="http://www.wellcome.ac.uk" target="_blank"> <img
			src="<s:url value='/css/img/logo-welcometrust.gif'/>"
			alt="Wellcome Trust" width="148" height="33" /> </a> <a
			href="http://www.gatesfoundation.org" target="_blank"> <img
			src="<s:url value='/css/img/logo-melinda.gif'/>"
			alt="Bill &amp; Melinda Gates foundation" width="128" height="26" />
		</a> <a href="http://www.fnih.org" target="_blank"> <img
			src="<s:url value='/css/img/FNIH_Logo_FINAL%20%282%29.gif'/>"
			alt="Foundation for the National Institute of Health" width="120"
			height="51" /> </a> <a href="http://www.mrc.ac.uk/index.htm"
			target="_blank"> <img
			src="<s:url value='/css/img/WG10-RGB-MRC.jpg'/>"
			alt="Medical Research Council" width="120" height="51" /> </a>
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
