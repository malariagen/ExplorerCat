<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript"
	src=" <s:url value='/js/lib/jquery-ui-1.8.21.custom.min.js'/>">
	
</script>

<script type="text/javascript"
	src="<s:url value='/js/lib/jquery.cookie.js'/>" /></script>


<script type="text/javascript"
	src=" <s:url value='/js/lib/jquery.tools.min.js'/>">
	
</script>

<script type="text/javascript"
	src=" <s:url value='/js/lib/jquery.ui.selectmenu.js'/>">
	
</script>

<script type="text/javascript"
	src="<s:url value='/js/lib/jquery.dataTables.js'/>" /></script>



<script type="text/javascript"
	src="<s:url value='/js/lib/expcat-explorer-debug.js'/>" /></script>
<script type="text/javascript"
	src="<s:property value="getResourcePath('pluginJS')"/>" /></script>





<!------------ Plug-in Specific Resources End------------>

<!------------ Plug-in Specific Content Start------------>
<div class="plugin-container">
	<div class="plugin-description">
		<h2>
			<i>Plasmodium falciparum</i> Genetic Variations (SNPs) &amp; Allele
			Frequencies
		</h2>
		<p>
			This page allows you to browse and query genetic variations (single
			nucleotide polymorphisms - SNPs) found in <i>P.falciparum</i> samples
			contributed from around the world by partners of the <a
				href="http://www.malariagen.net/node/44" target=_blank>MalariaGEN
				community project on the population genomics of <i>Plasmodium
					falciparum</i> </a>, and compare frequencies of different variant forms
			(alleles) between populations of parasites from different
			geographical regions, eg., South East Asia and Africa.
		</p>
		<!-- <p>
			For help using this page, see the <a href="http://www.youtube.com/"
				target=_blank>video tutorial.</a>
		</p> -->
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
				Download complete data set from <a
					href="<s:url action='executePlugin'>
       							<s:param name='pluginName'>Download Resources</s:param>
       							<s:param name='selectedCatalogId'>${selectedCatalogId}</s:param>
       							</s:url>">
					here</a>.

				<s:set name="catalogVersion" value="selectedCatalogVersion" />
				<s:if test="%{#catalogVersion=='1.0'}">
					<p>
						<b>Citation for this dataset</b>: Manske, Miotto et al., Analysis
						of <i>Plasmodium falciparum</i> diversity in natural infections by
						deep sequencing, Nature (2012), <a
							href="http://dx.doi.org/10.1038/nature11174" target="_blank">doi:10.1038/nature11174</a>
						[<a
							href="http://eorder.sheridan.com/3_0/display/index.php?flashprint=1866"
							target="_blank">open access ePrint</a>]
					</p>
				</s:if>
				<s:elseif test="%{#catalogVersion=='2.0.draft'}">
					<p>
						<b>Citation for this dataset</b>: in preparation. If you would
						like to use or cite this dataset in your own publication, please
						contact Olivo Miotto &lt;<a
							href="mailto:olivo@tropmedres.ac?Subject=PGV 2.0 dataset enquiry">olivo@tropmedres.ac</a>&gt;
					</p>
				</s:elseif>
		</div>

		<div id="tabs">
			<ul>
				<li><a href="#tabs-1">Search by population </a></li>
				<li><a href="#tabs-2">Search by gene</a></li>
				<li><a href="#tabs-3">Search by genome region</a></li>
				<li><a href="#tabs-4">Advanced query </a></li>
			</ul>
			<div id="tabs-1">
				<div class="grid-left tab-block-left">
					<div class="tab-block-left-top">
						<label for="alleleFrequencyFrom">Find SNPs where:</label> <select
							id="alleleFrequencyFrom">
							<option value="MAF">Minor Allele Frequency</option>
							<option value="DAF">Derived Allele Frequency</option>
							<option value="NRAF">Non Reference Allele Frequency</option>

						</select> <label for="populationFrom">in </label> <select
							id="populationFrom">
							<option value="AFR">Africa</option>
							<option value="SEA">South-East Asia</option>
							<option value="PNG">Papua New Guinea</option>
						</select> <label for="operatorFrom">is </label> <select id="operatorFrom">
							<option value="&gt;">greater than</option>
							<option value="&lt;">less than</option>
						</select> <input type="text" id="valueFrom" />
					</div>
					<div class="tab-block-left-middle">
						<label for="alleleFrequencyTo" id="alleleFrequencyLabel">and</label>
						<select id="alleleFrequencyTo">
							<option value="MAF">Minor Allele Frequency</option>
							<option value="DAF">Derived Allele Frequency</option>
							<option value="NRAF">Non Reference Allele Frequency</option>
						</select> <label for="populationTo">in </label> <select id="populationTo">
							<option value="AFR">Africa</option>
							<option value="SEA">South-East Asia</option>
							<option value="PNG">Papua New Guinea</option>
						</select> <label for=operatorTo>is </label> <select id="operatorTo">
							<option value="&gt;">greater than</option>
							<option value="&lt;">less than</option>
						</select> <input type="text" id="valueTo" />
					</div>
					<div class="tab-block-left-bottom">
						<input type="button" id="searchByPopulation" value="Search">
					</div>
				</div>
				<div class="grid-left tab-block-right">
					<p>Use this form to search for SNPs that show differences in
						allele frequencies between two populations.</p>
					<p>
						Need to ask a different question? Try the <span id="tab1_to_tab4"
							class="standard-link">advanced query</span> form.
					</p>
				</div>
				<div class="clear">&nbsp;</div>
			</div>
			<div id="tabs-2">
				<div class="grid-left tab-block-left">
					<div class="tab-block-left-top">
						<input type="text" id="geneInput" class="grid-left" /> <input
							type="button" id="searchByGene" value="Search" class="grid-left">
						<div class="clear">&nbsp;</div>
					</div>
					<div class="tab-block-left-middle">
						<div id="preDefinedGenes"></div>
					</div>
					<div class="tab-block-left-bottom">
						<div id="multipleResults"></div>
					</div>
				</div>
				<div class="grid-left tab-block-right">
					<p>Use this form to search for SNPs in a particular gene.</p>
					<p>
						Need to ask a different question? Try the <span id="tab2_to_tab4"
							class="standard-link">advanced query</span> form.
					</p>
					<p>
						<a href="http://www.malariagen.net/data/faq#more" target=_blank
							class="standard-link">Why aren't there more SNPs?</a>
					</p>

				</div>
				<div class="clear">&nbsp;</div>
			</div>
			<div id="tabs-3">
				<div class="grid-left tab-block-left">
					<div class="tab-block-left-top">
						<label for="chromosome">Chromosome: </label> <select
							id="chromosome">
						</select>
					</div>
					<div class="tab-block-left-middle">
						<label for="endPosition">Start position: </label> <input
							type="text" id="startPosition" /> <label for="endPosition">End
							position:</label> <input type="text" id="endPosition" />
					</div>
					<div class="tab-block-left-bottom">
						<input type="button" id="searchByRegion" value="Search">
					</div>
				</div>
				<div class="grid-left tab-block-right">
					<p>Use this form to search for SNPs in a particular gene.</p>
					<p>
						Need to ask a different question? Try the <span id="tab3_to_tab4"
							class="standard-link">advanced query</span> form.
					</p>
					<p>
						<a href="http://www.malariagen.net/data/faq#more" target=_blank
							class="standard-link">Why aren't there more SNPs?</a>
					</p>

				</div>
				<div class="clear">&nbsp;</div>
			</div>
			<div id="tabs-4">
				<div class="grid-left tab-block-left">
					<div class="queryPanel" id="query-container">
						<div class="delimiter" id="query-delimiter"></div>
					</div>
					<div id="cqlCode">Select everything</div>
					<div class="buttonContainer">
						<div id="addConditionButton">Add Condition</div>
						<div id="executeQueryButton">Execute Query</div>
					</div>
				</div>
				<div class="grid-left tab-block-right">
					<p>Use this form to design a custom query for SNPs.</p>
					<p>Click "Add Condition" to add more conditions to the query,
						click "Execute Query" to find matching SNPs.</p>
				</div>
				<div class="clear">&nbsp;</div>
			</div>
		</div>
		<div id="results">
			<div id="searchStatus"></div>
			<div id="finalResults">
				<div id="tableOptionsContainer"></div>
				<div class="clear">&nbsp;</div>
				<div id="tableContainer"></div>
				<div class="clear">&nbsp;</div>
				<div id="exportFileLink"></div>
			</div>
		</div>

		<div class="clear">&nbsp;</div>
	</div>



</div>
<!------------ Plug-in Specific Content End------------>