expcat.namespace("expcat.plugins.PopGenExplorer");

/**
 * @ignore
 */
expcat.plugins.PopGenExplorer = (function() {

	// Aliases
	var OPERATORS = expcat.cql.Operator;
	var Property = expcat.cql.Property;

	/**
	 * Component responsible for creating, and handling PopGen Explorer Plugin
	 * UI components. This is a temporary hack with lots of hard coding, should
	 * be replaced with having abstracted UI components of cql compatible in
	 * future development
	 * 
	 * @name expcat.plugins.PopGenExplorer
	 * @constructor
	 * @author Dushyanth Jyothi - ExplorerCat project.
	 */
	var PopGenExplorer = function(connectors, contextURL,
			actionParameterLookup, universalOptions, localOptions, entity) {

		var operatorMap = OPERATORS.getSymbolMap();
		var cqlConnector = connectors.cqlConnector;
		var utilConnector = connectors.utilConnector;

		var queryComposerUIManager = null;

		var tableColumns;
		var resultColumns;
		var tableConfiguration;
		var propertyDictionary;
		var popGenExplorerTable = null;

		var canExportData;
		var fileExporter = null;
		var exportFileContainerId;
		var fileExportingFormat;

		var tabsContainer;

		/**
		 * @public
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var setup = function() {
			// global setup
			tabsContainer = $("#" + universalOptions.UIContainers.tabsContainer);

			// Construct UI Components
			constructTabsContainer();
			constructSearchByPopulation();
			constructSearchByGene();
			constructSearchByRegion();

			// table related setup
			tableColumns = localOptions.tableColumns || [];
			resultColumns = composeResultColumns();
			tableConfiguration = getTableConfiguration();
			propertyDictionary = {};

			// table data export related setup
			canExportData = localOptions.canExportData ? localOptions.canExportData
					: false;
			exportFileContainerId = universalOptions.UIContainers.exportFileContainerId;
			fileExportingFormat = universalOptions.fileExportingFormat;

			// Get dictionary and set up advanced query
			utilConnector.getEntityDictionary(entity, function(response) {
				processDictionary(response);
				constructAdvancedQuery();
				// for requests coming from genotype
				setUpDynamicSearchByRegion();
			});
		};

		/**
		 * Method activates tabs container and set ups cookie
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var constructTabsContainer = function() {
			// Set up cookie
			tabsContainer.tabs({
				cookie : {
					name : 'explorercat-ui-tabs',
					expires : 1
				}
			});

			// Register advanced query tab open event
			var openTabEvents = universalOptions.UIEvents.open_tab;
			for ( var i = 0; i < openTabEvents.length; i++) {
				var sourceComponent = openTabEvents[i].component;
				var targetTab = openTabEvents[i].targetTab;
				$("#" + sourceComponent).click(function() {
					tabsContainer.tabs('select', targetTab);
					return false;
				});
			}

			// clear all dynamic UI components when tabs are changed
			tabsContainer.bind('tabsselect', function(event, ui) {
				clearTableUIComponents();
			});
		};

		/**
		 * Method constructs search by population related UI components
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var constructSearchByPopulation = function() {
			var searchByPopulation = localOptions.UIOptions.searchByPopulation
					|| {};
			/*
			 * populateSelectBox(searchByPopulation.alleleFrequencies.from,
			 * searchByPopulation.alleleFrequencies.options);
			 */
			setSelectBoxDefaultValue(searchByPopulation.alleleFrequencies.from,
					searchByPopulation.defaultOptions.alleleFrequencyFrom);
			/*
			 * populateSelectBox(searchByPopulation.alleleFrequencies.to,
			 * searchByPopulation.alleleFrequencies.options);
			 */
			setSelectBoxDefaultValue(searchByPopulation.alleleFrequencies.to,
					searchByPopulation.defaultOptions.alleleFrequencyTo);
			/*
			 * populateSelectBox(searchByPopulation.populations.from,
			 * searchByPopulation.populations.options);
			 */
			setSelectBoxDefaultValue(searchByPopulation.populations.from,
					searchByPopulation.defaultOptions.populationFrom);
			/*
			 * populateSelectBox(searchByPopulation.populations.to,
			 * searchByPopulation.populations.options);
			 */
			setSelectBoxDefaultValue(searchByPopulation.populations.to,
					searchByPopulation.defaultOptions.populationTo);
			/*
			 * populateSelectBox(searchByPopulation.operators.from,
			 * searchByPopulation.operators.options);
			 */
			setSelectBoxDefaultValue(searchByPopulation.operators.from,
					searchByPopulation.defaultOptions.operatorFrom);
			/*
			 * populateSelectBox(searchByPopulation.operators.to,
			 * searchByPopulation.operators.options);
			 */
			setSelectBoxDefaultValue(searchByPopulation.operators.to,
					searchByPopulation.defaultOptions.operatorTo);
			$("#" + searchByPopulation.inputValues.from).val(
					searchByPopulation.defaultOptions.valueFrom);
			$("#" + searchByPopulation.inputValues.to).val(
					searchByPopulation.defaultOptions.valueTo);

			registerSearchByPopulationEvents();
		};

		/**
		 * Method registers search by population related UI component events
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var registerSearchByPopulationEvents = function() {
			var searchByPopulation = localOptions.UIOptions.searchByPopulation
					|| {};

			registerIntegerInputValueChangeEvent(
					searchByPopulation.inputValues.from,
					searchByPopulation.inputValues.options.minimum,
					searchByPopulation.inputValues.options.maximum,
					((searchByPopulation.inputValues.options.maximum - searchByPopulation.inputValues.options.minimum) / 2));

			registerIntegerInputValueChangeEvent(
					searchByPopulation.inputValues.to,
					searchByPopulation.inputValues.options.minimum,
					searchByPopulation.inputValues.options.maximum,
					((searchByPopulation.inputValues.options.maximum - searchByPopulation.inputValues.options.minimum) / 2));

			var getSearchByPopulationCurrentValues = function() {
				return $(
						"#" + searchByPopulation.alleleFrequencies.from
								+ " option:selected").val()
						+ "_"
						+ $(
								"#" + searchByPopulation.populations.from
										+ " option:selected").val()
						+ " "
						+ $(
								"#" + searchByPopulation.operators.from
										+ " option:selected").val()
						+ " "
						+ $("#" + searchByPopulation.inputValues.from).val()
						+ " AND "
						+ $(
								"#" + searchByPopulation.alleleFrequencies.to
										+ " option:selected").val()
						+ "_"
						+ $(
								"#" + searchByPopulation.populations.to
										+ " option:selected").val()
						+ " "
						+ $(
								"#" + searchByPopulation.operators.to
										+ " option:selected").val()
						+ " "
						+ $("#" + searchByPopulation.inputValues.to).val();
			};

			$("#" + searchByPopulation.button).click(
					function() {
						var condition = getSearchByPopulationCurrentValues();
						var query = "SELECT FROM "
								+ universalOptions.entityType + " AS '"
								+ localOptions.queryName + "' WHERE ";
						query = query + (condition === "" ? "TRUE" : condition)
								+ ";";
						var result = " RESULT { " + localOptions.queryName
								+ " " + resultColumns + " AS '"
								+ localOptions.queryName + " Results'};";

						buildTableAndCSVResult(query + result);
					});

		};

		/**
		 * Method constructs search by gene related UI components
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var constructSearchByGene = function() {
			var searchByGenes = localOptions.UIOptions.searchByGenes || {};
			var preDefinedGeneLinks = $('<p>Enter gene name, identifier, or other descriptive text, e.g., </p>');
			for ( var i = 0; i < searchByGenes.genes.length; i++) {
				var gene = $('<span>', {
					id : searchByGenes.genes[i].name,
					text : searchByGenes.genes[i].alias
				});
				gene.addClass('styled-link');
				gene.click(function() {
					$("#" + searchByGenes.inputValue).val($(this).text());
					$("#" + searchByGenes.button).trigger('click');
				});

				preDefinedGeneLinks.append(gene);
				if (i != (searchByGenes.genes.length - 1)) {
					preDefinedGeneLinks.append(', ');
				}
			}

			preDefinedGeneLinks
					.append('<br><p>To search using regular expressions, use advanced query matches operator</p>');

			$("#" + universalOptions.UIContainers.preDefinedGenes).empty();
			$("#" + universalOptions.UIContainers.preDefinedGenes).append(
					preDefinedGeneLinks);

			registerSearchByGeneEvents();
		};

		/**
		 * Method registers search by gene related UI component events
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var registerSearchByGeneEvents = function() {
			var searchByGenes = localOptions.UIOptions.searchByGenes || {};
			var getSearchByGeneCurrentValue = function() {
				var geneInput = $("#" + searchByGenes.inputValue).val();
				geneInput = geneInput.toLowerCase();
				var regex = new RegExp(searchByGenes.cqlReplace, "g");
				return searchByGenes.cql.replace(regex, geneInput);
			};

			$("#" + searchByGenes.button)
					.click(
							function() {
								if ($("#" + searchByGenes.inputValue).val().length > 0) {
									var condition = getSearchByGeneCurrentValue();
									var query = "SELECT FROM "
											+ universalOptions.entityType
											+ " AS '" + localOptions.queryName
											+ "' WHERE ";
									query = query
											+ (condition === "" ? "TRUE"
													: condition) + ";";
									var result = " RESULT { "
											+ localOptions.queryName + " "
											+ resultColumns + " AS '"
											+ localOptions.queryName
											+ " Results'};";
									try {
										// check for multiple gene results
										checkForUniqueResults(query, result);
									} catch (error) {
										$('#error').append(
												'<p> ' + error + ' </p>');
									}
								}
							});
			// on enter event
			$("#" + searchByGenes.inputValue).keyup(function(event) {
				if (event.keyCode == 13) {
					if ($("#" + searchByGenes.inputValue).val().length > 0) {
						$("#" + searchByGenes.button).trigger('click');
					}
				}
			});

		};

		/**
		 * Method to format geneinfo data before displaying in select a result
		 * drop down box when multiple gene results found for a search
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var geneInfoFormatting = function(text) {
			var formatedText;
			var data = text
					.split(localOptions.UIOptions.searchByGenes.geneInfoFormatting
							|| ";");
			if (data.length > 1) {
				if (typeof data[0] !== 'undefined' && data[0] !== null) {
					formatedText = '<span class="ui-selectmenu-item-header">'
							+ data[0] + '</span>';
				}
				formatedText += '<span class="ui-selectmenu-item-content">'
						+ text + '</span>';
			} else {
				formatedText = text;
			}
			return formatedText;
		};

		/**
		 * Method to construct select a result drop down box when multiple gene
		 * results found for a search
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var constructSelectResultBox = function(response) {
			var selectResultBox = $("<select id='selectSpecificGene'></select>");
			for ( var i = 0; i < response.resultData.rows.length; i++) {
				selectResultBox.append($("<option></option>").attr("value",
						response.resultData.rows[i].values[0]).text(
						response.resultData.rows[i].values[1]));
			}

			updateSearchStatusMessage('');
			var multipleResults = $("#"
					+ universalOptions.UIContainers.multipleResults);
			multipleResults
					.append('<p> '
							+ response.resultData.rows.length
							+ ' matches found. Please select specific gene, and click search button</p>');

			multipleResults.append(selectResultBox);

			$('#selectSpecificGene').selectmenu({
				maxHeight : 100,
				width : 600,
				menuWidth : 600,
				format : geneInfoFormatting
			});

			$('#selectSpecificGene')
					.change(
							function() {
								$(
										"#"
												+ localOptions.UIOptions.searchByGenes.inputValue)
										.val(
												$(
														"#selectSpecificGene option:selected")
														.val());
							});

		};

		/**
		 * Method responsible to check for unique results. If multiple results
		 * found it sets up select a result drop down list
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var checkForUniqueResults = function(query, result) {
			clearTableUIComponents();
			updateSearchStatusMessage('<h2>Searching...</h2>');

			var getUniqueQueryDataCallback = function(response) {
				if (response.returnCode === 0) {
					constructSelectResultBox(response);
				} else {
					updateSearchStatusMessage('<p id="error">'
							+ response.errorMessage + '</p>');
					throw new Error(response.errorMessage);
				}
			};

			var getUniqueQuerySetupCallback = function(response) {
				if (response.returnCode === 0) {
					if (response.header.numRows == 1
							|| response.header.numRows == 0) {
						buildTableAndCSVResult(query + result);
					} else {
						getUniqueQueryData(response, getUniqueQueryDataCallback);
					}

				} else {
					updateSearchStatusMessage('<p id="error">'
							+ response.errorMessage + '</p>');
					throw new Error(response.errorMessage);
				}
			};

			getUniqueQuerySetup(query, getUniqueQuerySetupCallback);

		};

		/**
		 * Method to get data for UniqueQuery
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var getUniqueQueryData = function(response, getUniqueQueryDataCallback) {
			var ticket = response.ticketNumber;
			var hash = response.hashCode;
			var numRows = response.header.numRows;
			var offset = 0;

			cqlConnector.getResultData(ticket, hash, numRows, offset,
					getUniqueQueryDataCallback);
		};

		/**
		 * Method to setup unique query
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var getUniqueQuerySetup = function(query, getUniqueQuerySetupCallback) {
			var searchByGenes = localOptions.UIOptions.searchByGenes || {};
			var result = " RESULT { DISTINCT " + localOptions.queryName
					+ searchByGenes.distinctResultColumns + " AS '"
					+ localOptions.queryName + " Results'};";
			var parameters = {};
			var cqlQuery = query + result;

			var resultsCallback = function(response) {
				if (typeof getUniqueQuerySetupCallback === "function") {
					getUniqueQuerySetupCallback(response);
				}
			};
			// check for multiple gene results
			cqlConnector.setupQuery(cqlQuery, resultsCallback, parameters);
		};

		/**
		 * Method constructs search by region related UI components
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var constructSearchByRegion = function() {
			var searchByRegion = localOptions.UIOptions.searchByRegion || {};
			populateSelectBox(searchByRegion.chromosomeId,
					searchByRegion.chromosomes);
			$("#" + searchByRegion.inputValues.from).val(
					searchByRegion.inputValues.defaultOptions.minimum);
			$("#" + searchByRegion.inputValues.to).val(
					searchByRegion.inputValues.defaultOptions.maximum);
			registerSearchByRegionEvents();
		};

		/**
		 * Method registers search by region related UI component events
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var registerSearchByRegionEvents = function() {
			var searchByRegion = localOptions.UIOptions.searchByRegion || {};

			var updateSearchByRegionCoordinatesChangeEvents = function(minimum,
					maximum) {
				$("#" + searchByRegion.inputValues.from).unbind("change");
				$("#" + searchByRegion.inputValues.to).unbind("change");

				$("#" + searchByRegion.inputValues.from).val(minimum);
				$("#" + searchByRegion.inputValues.to).val(maximum);

				registerIntegerInputValueChangeEvent(
						searchByRegion.inputValues.from, minimum, maximum,
						minimum);
				registerIntegerInputValueChangeEvent(
						searchByRegion.inputValues.to, minimum, maximum,
						maximum);
			};

			var searchByRegionChromosomeChangeEventHandler = function() {
				var shouldSetToDefault = true;
				for ( var i = 0; i < searchByRegion.chromosomes.length; i++) {
					if (searchByRegion.chromosomes[i].name == $(
							"#" + searchByRegion.chromosomeId
									+ " option:selected").val()) {
						updateSearchByRegionCoordinatesChangeEvents(
								searchByRegion.chromosomes[i].minimum,
								searchByRegion.chromosomes[i].maximum);
						shouldSetToDefault = false;
						break;
					}
				}
				if (shouldSetToDefault == true) {
					updateSearchByRegionCoordinatesChangeEvents(
							searchByRegion.inputValues.defaultOptions.minimum,
							searchByRegion.inputValues.defaultOptions.maximum);
				}
			};

			$("#" + searchByRegion.chromosomeId).change(function() {
				searchByRegionChromosomeChangeEventHandler();
			});

			var getSearchByRegionCurrentValue = function() {
				return "Chromosome = '"
						+ $(
								"#" + searchByRegion.chromosomeId
										+ " option:selected").val()
						+ "' AND Position >= "
						+ $("#" + searchByRegion.inputValues.from).val()
						+ " AND Position <= "
						+ $("#" + searchByRegion.inputValues.to).val();
			};

			$("#" + searchByRegion.button).click(
					function() {
						var condition = getSearchByRegionCurrentValue();
						var query = "SELECT FROM "
								+ universalOptions.entityType + " AS '"
								+ localOptions.queryName + "' WHERE ";
						query = query + (condition === "" ? "TRUE" : condition)
								+ ";";
						var result = " RESULT { " + localOptions.queryName
								+ " " + resultColumns + " AS '"
								+ localOptions.queryName + " Results'};";

						buildTableAndCSVResult(query + result);
					});

			// Trigger the chromosome change event to so coordinate values are
			// updated for the first time.
			$("#" + searchByRegion.chromosomeId).trigger('change');

		};

		/**
		 * Method constructs advanced query UI components
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var constructAdvancedQuery = function() {
			var queryComposerUIContainers = generateQueryComposerUIContainers();
			var queryUIContainerId = universalOptions.UIContainers.queryUIContainerId;

			if (queryComposerUIManager !== null)
				queryComposerUIManager.destroy();
			for (i = 0; i < tableColumns.length; ++i) {
				if (tableColumns[i].aq === false) {
					tableColumns.splice(i, 1);
				}
			}
			queryComposerUIManager = new expcat.plugins.QueryComposerUIManager(
					operatorMap, propertyDictionary, tableColumns,
					queryComposerUIContainers, queryUIContainerId);
			queryComposerUIManager.updateUI();
			registerAdvancedQueryEvents();
		};

		/**
		 * Method registers advanced query UI related components events
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var registerAdvancedQueryEvents = function() {
			$("#" + universalOptions.UIContainers.addConditionButtonId).unbind(
					"click");
			$("#" + universalOptions.UIContainers.addConditionButtonId).bind(
					"click", function addCondition() {

						queryComposerUIManager.addCondition();
					});

			$("#" + universalOptions.UIContainers.executeQueryButtonId).unbind(
					"click");
			$("#" + universalOptions.UIContainers.executeQueryButtonId)
					.bind(
							"click",
							function() {
								var condition = queryComposerUIManager
										.generateCQLCode();
								var query = "SELECT FROM "
										+ entity
										+ " AS 'userQuery' WHERE "
										+ (condition === "" ? "TRUE"
												: condition) + ";";
								var result = "RESULT { userQuery"
										+ resultColumns
										+ " AS 'Query Results'};";
								buildTableAndCSVResult(query + result);
							});
		};

		/**
		 * Register table column show or hide event
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var registerTableColumnShowHideEvent = function(checkBox) {
			checkBox.unbind("click");
			var oTable = $('#' + tableConfiguration.tableDomId).dataTable();
			checkBox
					.bind(
							"click",
							function() {
								var oSettings = oTable.fnSettings();
								var value = $(this).attr("value");
								var columns = value.split(',');
								for ( var i = 0; i < oSettings.aoColumns.length; i++) {
									for ( var j = 0; j < columns.length; j++) {
										if (oSettings.aoColumns[i].sTitle === columns[j]) {
											// var bVis =
											// oSettings.aoColumns[i].bVisible;
											// oTable.fnSetColumnVis(i,bVis ?
											// false : true,false);
											var bVis = oTable.fnSettings().aoColumns[i].bVisible;
											oTable.fnSetColumnVis(i,
													bVis ? false : true);
										}
									}
								}
							});
		};

		/**
		 * Method constructs table show hide options after table rendering is
		 * completed
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var constructTableColumnOptions = function() {
			var tableOptionsContainer = $("#"
					+ universalOptions.UIContainers.tableOptionsContainer);
			var columnGroups = localOptions.columnGroups;
			tableOptionsContainer.append($("<span>Show: </span>"));
			for ( var i = 0; i < columnGroups.length; i++) {
				if (columnGroups[i].showHide === true) {
					var checkBox = $('<input type="checkbox" />');
					checkBox.attr("id", columnGroups[i].name);
					checkBox.attr("value", columnGroups[i].columns);
					checkBox.attr("checked", true);
					var label = $('<span>' + columnGroups[i].name + '</span>');
					tableOptionsContainer.append(checkBox);
					tableOptionsContainer.append(label);
					registerTableColumnShowHideEvent(checkBox);
				}
			}
		};

		/**
		 * Method called back after table rendering is completed
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.ResourceDownloader#
		 */
		var callbackAfterTableLoaded = function(error) {
			if (error) {
				clearTableUIComponents();
				updateSearchStatusMessage(error);
			} else {
				if (canExportData && fileExporter !== null)
					fileExporter.destroy();

				if (canExportData)
					fileExporter = new expcat.plugins.TextFileExporter(
							exportFileContainerId, fileExportingFormat,
							cqlConnector, popGenExplorerTable, contextURL);

				constructTableColumnOptions();
				// updateSearchStatusMessage('<h2>Search results</h2>');
				updateSearchStatusMessage();
			}
		};

		/**
		 * Destorys and clear dynamic UI components & messages
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var clearTableUIComponents = function() {
			if ((typeof fileExporter !== "undefined")
					&& (fileExporter !== null))
				fileExporter.destroy();

			if ((typeof popGenExplorerTable !== "undefined")
					&& (popGenExplorerTable !== null))
				popGenExplorerTable.destroy();

			$("#" + universalOptions.UIContainers.multipleResults).empty();
			$("#" + universalOptions.UIContainers.searchStatus).empty();
			$("#" + universalOptions.UIContainers.tableOptionsContainer)
					.empty();
			$("#" + universalOptions.UIContainers.exportFileContainerId)
					.empty();

		};

		/**
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.ResourceDownloader#
		 */
		var buildTableAndCSVResult = function(query) {
			clearTableUIComponents();
			updateSearchStatusMessage('<h2>Searching...</h2>');
			popGenExplorerTable = new expcat.plugins.DataTable(cqlConnector,
					query, tableConfiguration, callbackAfterTableLoaded);
		};

		/**
		 * Updates search status message
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var updateSearchStatusMessage = function(message) {
			$("#" + universalOptions.UIContainers.searchStatus).empty();
			if (message !== undefined) {
				$("#" + universalOptions.UIContainers.searchStatus).append(
						message);
			}
		};

		/**
		 * Temporary hack to provide dynamic link from my genotype page
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var setUpDynamicSearchByRegion = function() {
			if (typeof actionParameterLookup[universalOptions.dynamicUrlParameters.chromosome] != 'undefined'
					&& typeof actionParameterLookup[universalOptions.dynamicUrlParameters.startPosition] != 'undefined'
					&& typeof actionParameterLookup[universalOptions.dynamicUrlParameters.endPosition] != 'undefined') {

				var searchByRegion = localOptions.UIOptions.searchByRegion
						|| {};
				$("#" + searchByRegion.chromosomeId)
						.val(
								actionParameterLookup[universalOptions.dynamicUrlParameters.chromosome]);

				$("#" + searchByRegion.inputValues.from)
						.val(
								actionParameterLookup[universalOptions.dynamicUrlParameters.startPosition]);
				$("#" + searchByRegion.inputValues.to)
						.val(
								actionParameterLookup[universalOptions.dynamicUrlParameters.endPosition]);
				var tabsContainer = $("#"
						+ universalOptions.UIContainers.tabsContainer);
				tabsContainer.tabs('select', 2);
				$("#" + searchByRegion.button).trigger('click');
			}
		};

		/**
		 * Returns the table configuration.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var processDictionary = function(response) {
			var descriptions = null;
			var i;
			if (response.returnCode === 0) {
				descriptions = response.propertyDescriptors;

				for (i = 0; i < descriptions.length; ++i) {
					propertyDictionary[descriptions[i].name] = new Property(
							{
								name : descriptions[i].name,
								type : descriptions[i].type.toUpperCase(),
								description : descriptions[i].description,
								minimum : isNaN(parseFloat(descriptions[i].minimumValue)) ? null
										: parseFloat(descriptions[i].minimumValue),
								maximum : isNaN(parseFloat(descriptions[i].maximumValue)) ? null
										: parseFloat(descriptions[i].maximumValue),
								allowedValues : descriptions[i].allowedValues.length === 0 ? null
										: descriptions[i].allowedValues
							});
				}
			}
		};

		/**
		 * Returns the table configuration.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var getTableConfiguration = function() {
			var i, j, k;
			var currentColumn;
			var currentOptions;
			var tableColumnOptions = [];
			var tableColumnGroups = [];
			var cellRenderers = localOptions.cellRenderers || {};

			for (i = 0; i < tableColumns.length; ++i) {
				currentOptions = {};
				currentOptions.name = tableColumns[i].name;
				currentOptions.alias = tableColumns[i].alias;
				currentOptions.width = tableColumns[i].width;
				currentOptions.sort = tableColumns[i].sort;
				currentOptions.hide = tableColumns[i].hide;
				currentOptions.aq = tableColumns[i].aq;
				currentOptions.renderers = [];

				if (tableColumns[i].renderers) {
					for (k = 0; k < tableColumns[i].renderers.length; k++) {
						currentOptions.renderers
								.push(cellRenderers[tableColumns[i].renderers[k]]);
					}
				}

				tableColumnOptions.push({
					name : currentOptions.name,
					alias : currentOptions.alias,
					width : currentOptions.width,
					sort : currentOptions.sort,
					hide : currentOptions.hide,
					renderers : currentOptions.renderers
				});
			}

			var tableConfiguration = universalOptions.tableConfiguration;
			tableConfiguration.tableContainer = universalOptions.UIContainers.tableContainer;
			tableConfiguration.columnOptions = tableColumnOptions;
			tableConfiguration.groupOptions = localOptions.columnGroups;
			return tableConfiguration;
		};

		/**
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var composeResultColumns = function() {
			var i;
			var resultProperties = [];
			for (i = 0; i < tableColumns.length; ++i) {
				resultProperties.push(tableColumns[i].name + " AS '"
						+ tableColumns[i].alias + "'");
			}

			if (resultProperties.length > 0)
				return "[" + resultProperties.join(",") + "]";
			else
				return " ";
		};

		/**
		 * Auxiliary method to create containers for the query composer UI.
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var generateQueryComposerUIContainers = function() {

			var nestedPanel = $("<div></div>", {
				"class" : "nestedPanel"
			});

			var nestedDelimiter = $("<div></div>", {
				"class" : "nestedDelimiter"
			});

			return [ nestedPanel, nestedDelimiter ];
		};

		/**
		 * Auxiliary method to populate select input boxes
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var populateSelectBox = function(selectBox, selectOptions) {
			for ( var j = 0; j < selectOptions.length; j++) {
				$('#' + selectBox).append(
						$("<option></option>").attr("value",
								selectOptions[j].name).text(
								selectOptions[j].alias));
			}
		};

		/**
		 * Auxiliary method to set select input boxe default value
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var setSelectBoxDefaultValue = function(id, textToSet) {
			$("#" + id + " option").filter(function() {
				return this.value == textToSet;
			}).attr('selected', true);
		};

		/**
		 * Auxiliary method to validate integer input
		 * 
		 * 
		 * @private
		 * @ignore
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var registerIntegerInputValueChangeEvent = function(id, minimum,
				maximum, resetValue) {
			$("#" + id).change(function() {
				var value = $(this).val();
				if (isNaN(value)) {
					$(this).val(resetValue);
				} else if (value < minimum || value > maximum) {
					$(this).val(resetValue);
				}
			});
		};

		/**
		 * @public
		 * @memberOf expcat.plugins.PopGenExplorer#
		 */
		var destroy = function() {
			if ((typeof manager !== "undefined") && (manager !== null))
				manager.destroy();

			if ((typeof fileExporter !== "undefined")
					&& (fileExporter !== null))
				fileExporter.destroy();

			if ((typeof popGenExplorerTable !== "undefined")
					&& (popGenExplorerTable !== null))
				popGenExplorerTable.destroy();

		};

		/* Public API returned by the constructor */
		return {
			setup : setup,
			destroy : destroy
		};
	};

	/* Prototype */
	PopGenExplorer.prototype = {
		constructor : PopGenExplorer
	};

	return PopGenExplorer;

}());

/**
 * Plug-in init method (automatically called).
 */
expcat.plugins.PopGenExplorer.init = function(connectors, contextURL,
		actionParameterLookup, universalOptions, localOptions) {

	var popGenExplorer = new expcat.plugins.PopGenExplorer(connectors,
			contextURL, actionParameterLookup, universalOptions, localOptions,
			universalOptions.entityType);
	popGenExplorer.setup();
};
