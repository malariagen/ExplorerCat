
<%@ taglib prefix="s" uri="/struts-tags"%>

<!------------ Plug-in Specific Content Start------------>
<div class="plugin-container">
	<div class="plugin-description">
		<h2>Download Resources</h2>
		<p>This page allows you to download static resources related to
			the catalogue you are currently exploring. All resources are in
			comma-separated format (csv) and compressed (zip).</p>
	</div>


	<div class="plugin-separator"></div>

	<div class="plugin-steps">
		<div class="data-release">
			<h3>
				Data release: ${selectedCatalogVersion}
				<!-- 
			<span> Data release: </span> <select id="dataReleaseSelect">
				<s:iterator value="catalogs" var="catalog">
					<option value="${catalog.id}">${catalog.version} [ Release
						Date: ${catalog.releaseDateAsString} ]</option>
				</s:iterator>
			</select>
			 -->
			</h3>

			<p>
				Explore or download customised data sets using the search tools provided <a
					href="<s:url action='executePlugin'>
       							<s:param name='pluginName'>PopGen Explorer</s:param>
       							<s:param name='selectedCatalogId'>${selectedCatalogId}</s:param>
       							</s:url>">here</a>.
			</p>

			<s:set name="catalogVersion" value="selectedCatalogVersion" />
			<s:if test="%{#catalogVersion=='1.0'}">
				<p>
					<b>Citation for this dataset</b>: Manske, Miotto et al., Analysis
					of <i>Plasmodium falciparum</i> diversity in natural infections by deep
					sequencing, Nature (2012), <a
						href="http://dx.doi.org/10.1038/nature11174" target="_blank">doi:10.1038/nature11174</a>
					[<a
						href="http://eorder.sheridan.com/3_0/display/index.php?flashprint=1866"
						target="_blank">open access ePrint</a>]
				</p>
			</s:if>
			<s:elseif test="%{#catalogVersion=='2.0.draft'}">
				<p>
					<b>Citation for this dataset</b>: in preparation. If you would like
					to use or cite this dataset in your own publication, please contact
					Olivo Miotto &lt;<a
						href="mailto:olivo@tropmedres.ac?Subject=PGV 2.0 dataset enquiry">olivo@tropmedres.ac</a>&gt;
				</p>
			</s:elseif>

		</div>
		<div class="clear">&nbsp;</div>
		<table id="downloadLinks" class="orange-table"
			summary="Download resources">
			<thead>
				<tr>
					<th scope="col" class="resource-name">Name</th>
					<th scope="col">Type</th>
					<th scope="col">Description</th>
				</tr>
			</thead>

			<tbody>
			</tbody>

		</table>
		<div class="clear">&nbsp;</div>
	</div>


</div>

<!------------ Plug-in Specific Content End------------>



