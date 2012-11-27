<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="header">
	<div class="header-top">
		<div
			class="grid-left header-top-first-menu header-top-first-menu-prefix header-top-first-menu-suffix">
			<a href="http://www.malariagen.net/search">Search</a> <span
				class="delimiter">|</span><a
				href="http://www.malariagen.net/sitemap">Sitemap</a> <span
				class="delimiter">|</span><a
				href="http://www.malariagen.net/about/contact-us">Contact</a>
		</div>
		<div class="clear">&nbsp;</div>
		<div
			class="grid-left header-top-middle-logo header-top-middle-logo-prefix header-top-middle-logo-suffix">
			<a href="http://www.malariagen.net"> <img
				src="<s:url value='/css/img/malariagen_logo.png'/>" /> </a>
		</div>
		<div class="clear">&nbsp;</div>
		<div
			class="grid-left grid header-top-second-menu header-top-second-menu-prefix header-top-second-menu-suffix">
			<a href="http://www.malariagen.net/about">About</a> | <a
				href="http://www.malariagen.net/community">Community</a> | <a
				href="http://www.malariagen.net/projects">Projects</a> | <a
				class="selected" href="http://www.malariagen.net/data">Data</a>
		</div>
		<div class="clear">&nbsp;</div>
	</div>

	<div class="header-middle"></div>
	<div class="header-bottom">
		<div class="header-bottom-breadcrumb">
			<a href="http://www.malariagen.net">Home</a> &gt; <a
				href="http://www.malariagen.net/data">Data</a> &gt; <a
				href="<s:url action='frontPage'></s:url>"> Catalogues </a> &gt;
			${catalog.name}
		</div>
	</div>
</div>