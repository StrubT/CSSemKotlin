package ch.bfh.cssem.kotlin.app.kotlin

import ch.bfh.cssem.kotlin.api.AddressBook
import ch.bfh.cssem.kotlin.api.City
import ch.bfh.cssem.kotlin.api.Country
import ch.bfh.cssem.kotlin.api.State
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXCity
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXCountry
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXPerson
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXState
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.stage.Stage
import javafx.stage.WindowEvent
import java.net.URL
import java.util.ArrayList
import java.util.HashMap
import java.util.ResourceBundle
import java.util.ServiceLoader
import java.util.concurrent.Executors

/**
 * [AddressBook] instance to use throughout the project.
 */
private val addressBook = ServiceLoader.load(AddressBook::class.java).single()

/**
 * [java.util.concurrent.ExecutorService] to use when accessing the address book
 */
private val addressBookExecutor = Executors.newSingleThreadExecutor()

/**
 * Contains the logic to control the JavaFX [Window][javafx.stage.Window].
 *
 * @property rootPane              [FXML] [Node][javafx.scene.Node]
 * @property titleLabel            [FXML] [Node][javafx.scene.Node]
 * @property tabPane               [FXML] [Node][javafx.scene.Node]
 * @property peopleTab             [FXML] [Node][javafx.scene.Node]
 * @property peoplePane            [FXML] [Node][javafx.scene.Node]
 * @property peopleTitleLabel      [FXML] [Node][javafx.scene.Node]
 * @property peopleSearchLabel     [FXML] [Node][javafx.scene.Node]
 * @property peopleSearchField     [FXML] [Node][javafx.scene.Node]
 * @property peopleTable           [FXML] [Node][javafx.scene.Node]
 * @property citiesTab             [FXML] [Node][javafx.scene.Node]
 * @property citiesPane            [FXML] [Node][javafx.scene.Node]
 * @property citiesTitleLabel      [FXML] [Node][javafx.scene.Node]
 * @property citiesPostalCodeLabel [FXML] [Node][javafx.scene.Node]
 * @property citiesPostalCodeField [FXML] [Node][javafx.scene.Node]
 * @property citiesSearchLabel     [FXML] [Node][javafx.scene.Node]
 * @property citiesSearchField     [FXML] [Node][javafx.scene.Node]
 * @property citiesTable           [FXML] [Node][javafx.scene.Node]
 * @property statesTab             [FXML] [Node][javafx.scene.Node]
 * @property statesPane            [FXML] [Node][javafx.scene.Node]
 * @property statesTitleLabel      [FXML] [Node][javafx.scene.Node]
 * @property statesSearchLabel     [FXML] [Node][javafx.scene.Node]
 * @property statesSearchField     [FXML] [Node][javafx.scene.Node]
 * @property statesTable           [FXML] [Node][javafx.scene.Node]
 * @property countriesTab          [FXML] [Node][javafx.scene.Node]
 * @property countriesPane         [FXML] [Node][javafx.scene.Node]
 * @property countriesTitleLabel   [FXML] [Node][javafx.scene.Node]
 * @property countriesSearchLabel  [FXML] [Node][javafx.scene.Node]
 * @property countriesSearchField  [FXML] [Node][javafx.scene.Node]
 * @property countriesTable        [FXML] [Node][javafx.scene.Node]
 *
 * @author strut1 & touwm1
 */
class FXWindow : Initializable {

	@FXML protected lateinit var rootPane: BorderPane
	@FXML protected lateinit var titleLabel: Label
	@FXML protected lateinit var tabPane: TabPane
	@FXML protected lateinit var peopleTab: Tab
	@FXML protected lateinit var peoplePane: GridPane
	@FXML protected lateinit var peopleTitleLabel: Label
	@FXML protected lateinit var peopleSearchLabel: Label
	@FXML protected lateinit var peopleSearchField: TextField
	@FXML protected lateinit var peopleTable: TableView<FXPerson>
	@FXML protected lateinit var citiesTab: Tab
	@FXML protected lateinit var citiesPane: GridPane
	@FXML protected lateinit var citiesTitleLabel: Label
	@FXML protected lateinit var citiesPostalCodeLabel: Label
	@FXML protected lateinit var citiesPostalCodeField: TextField
	@FXML protected lateinit var citiesSearchLabel: Label
	@FXML protected lateinit var citiesSearchField: TextField
	@FXML protected lateinit var citiesTable: TableView<FXCity>
	@FXML protected lateinit var statesTab: Tab
	@FXML protected lateinit var statesPane: GridPane
	@FXML protected lateinit var statesTitleLabel: Label
	@FXML protected lateinit var statesSearchLabel: Label
	@FXML protected lateinit var statesSearchField: TextField
	@FXML protected lateinit var statesTable: TableView<FXState>
	@FXML protected lateinit var countriesTab: Tab
	@FXML protected lateinit var countriesPane: GridPane
	@FXML protected lateinit var countriesTitleLabel: Label
	@FXML protected lateinit var countriesSearchLabel: Label
	@FXML protected lateinit var countriesSearchField: TextField
	@FXML protected lateinit var countriesTable: TableView<FXCountry>

	/**
	 * Initialises the [Window][javafx.stage.Window] controller after the root [Node][javafx.scene.Node] has been processed completely.
	 */
	override fun initialize(location: URL?, resources: ResourceBundle?) {

		statesTable.setRowFactory {
			val row = TableRow<FXState>()
			row.setOnMouseClicked { event ->
				if (event.clickCount == 2 && !row.isEmpty)
					filterCities(row.item)
			}
			row
		}

		countriesTable.setRowFactory {
			val row = TableRow<FXCountry>()
			row.setOnMouseClicked { event ->
				if (event.clickCount == 2 && !row.isEmpty) {
					filterCities(country = row.item)
					filterStates(row.item)
				}
			}
			row
		}

		searchPeople()
		searchCities()
		searchStates()
		searchCountries()
	}

	/**
	 * Initialises the [Stage] and [Scene] with data from the [FXML] file.
	 */
	fun initialize(stage: Stage, scene: Scene) {

		stage.minWidth = rootPane.minWidth
		stage.minHeight = rootPane.minHeight
		stage.width = rootPane.prefWidth
		stage.height = rootPane.prefHeight

		stage.title = titleLabel.text

		stage.addEventFilter(WindowEvent.WINDOW_SHOWN) { peopleSearchField.requestFocus() }
		stage.addEventFilter(WindowEvent.WINDOW_HIDDEN) { addressBookExecutor.shutdown() }
	}

	/**
	 * Searches for people in the data source.
	 *
	 * @param event [KeyEvent] that triggered the search, if any
	 */
	fun searchPeople(event: KeyEvent? = null) {

		addressBookExecutor.submit {
			val result = if (!citiesSearchField.text.isNullOrBlank())
				addressBook.fetchPeopleByName(peopleSearchField.text)
			else
				addressBook.fetchAllPeople()

			Platform.runLater {
				peopleTable.items = result.mapTo(FXCollections.observableArrayList()) { FXPerson(it) }
			}
		}
	}

	/**
	 * Applies special filters to the city table.
	 *
	 * @param state   state to filter cities by
	 * @param country country to filter cities by
	 */
	fun filterCities(state: State? = null, country: Country? = null) {

		val filter = citiesSearchField.text.filter
		if (state !== null) {
			filter.valueFilters.remove(SearchFilter.countryKey)
			filter.valueFilters.put(SearchFilter.stateKey, "${state.country.abbreviation}-${state.abbreviation}")
		}
		if (country !== null)
			filter.valueFilters.put(SearchFilter.countryKey, country.abbreviation)

		citiesSearchField.text = filter.combined

		tabPane.selectionModel.select(citiesTab)
		searchCities()
	}


	/**
	 * Searches for cities in the data source.
	 *
	 * @param event [KeyEvent] that triggered the search, if any
	 */
	fun searchCities(event: KeyEvent? = null) {

		val filter = citiesSearchField.text.filter
		val filterByName = !filter.textFilter.isNullOrBlank()
		val filterByPostalCode = !citiesPostalCodeField.text.isNullOrBlank()
		val filterByCountry = filter.valueFilters.containsKey(SearchFilter.countryKey)
		val filterByState = filter.valueFilters.containsKey(SearchFilter.stateKey)

		addressBookExecutor.submit {
			val partialResults = ArrayList<List<City>>()
			if (filterByName) partialResults.add(addressBook.fetchCitiesByName(filter.textFilter))
			if (filterByPostalCode) partialResults.add(addressBook.fetchCitiesByPostalCode(citiesPostalCodeField.text))
			if (filterByCountry) partialResults.add(addressBook.fetchCountryByAbbreviation(filter.valueFilters[SearchFilter.countryKey]!!)?.states?.flatMap { it.cities } ?: listOf())
			if (filterByState) partialResults.add(addressBook.fetchStateByAbbreviation(filter.valueFilters[SearchFilter.stateKey]!!)?.cities ?: listOf())
			if (partialResults.isEmpty()) partialResults.add(addressBook.fetchAllCities())

			val result = partialResults.reduce { a, b -> a.intersectList(b) }

			Platform.runLater {
				citiesTable.items = result.mapTo(FXCollections.observableArrayList()) { FXCity(it) }
			}
		}
	}

	/**
	 * Applies a special filter to the state table.
	 *
	 * @param country country to filter states by
	 */
	fun filterStates(country: Country? = null) {

		val filter = statesSearchField.text.filter
		if (country !== null)
			filter.valueFilters.put(SearchFilter.countryKey, country.abbreviation)

		statesSearchField.text = filter.combined

		tabPane.selectionModel.select(statesTab)
		searchStates()
	}


	/**
	 * Searches for states in the data source.
	 *
	 * @param event [KeyEvent] that triggered the search, if any
	 */
	fun searchStates(event: KeyEvent? = null) {

		val filter = statesSearchField.text.filter
		val filterByName = !filter.textFilter.isNullOrBlank()
		val filterByCountry = filter.valueFilters.containsKey(SearchFilter.countryKey)

		addressBookExecutor.submit {
			val filterCountryResult = (if (filterByCountry) addressBook.fetchCountryByAbbreviation(filter.valueFilters[SearchFilter.countryKey] ?: "?")?.states else null) ?: listOf()

			val result = when {
				filterByName && filterByCountry -> addressBook.fetchStatesByName(filter.textFilter).intersectList(filterCountryResult)
				filterByName                    -> addressBook.fetchStatesByName(filter.textFilter)
				filterByCountry                 -> filterCountryResult
				else                            -> addressBook.fetchAllStates()
			}

			Platform.runLater {
				statesTable.items = result.mapTo(FXCollections.observableArrayList()) { FXState(it) }
			}
		}
	}


	/**
	 * Searches for countries in the data source.
	 *
	 * @param event [KeyEvent] that triggered the search, if any
	 */
	fun searchCountries(event: KeyEvent? = null) {

		addressBookExecutor.submit {
			val result = if (!countriesSearchField.text.isNullOrBlank())
				addressBook.fetchCountriesByName(countriesSearchField.text)
			else
				addressBook.fetchAllCountries()

			Platform.runLater {
				countriesTable.items = result.mapTo(FXCollections.observableArrayList()) { FXCountry(it) }
			}
		}
	}
}

/**
 * Represents the contents of a search filter.
 *
 * @property valueFilters the special value filters enclosed in square brackets
 * @property textFilter   the default text filter
 *
 * @author strut1 & touwm1
 */
data class SearchFilter(var valueFilters: MutableMap<String, String>, var textFilter: String) {

	/**
	 * Gets the combined filter text.
	 */
	val combined: String
		get() = "${valueFilters.entries.joinToString(separator = " ") { "[${it.key}=${it.value}]" }} $textFilter"

	companion object {

		internal const val stateKey = "state"
		internal const val countryKey = "country"
	}
}

/**
 * Creates a [SearchFilter] from the filter text.
 */
val String.filter: SearchFilter
	get() {
		val regex = Regex("\\s*\\[\\s*(\\w+)\\s*=\\s*([^\\]]+)\\s*\\]\\s*")
		val map = HashMap<String, String>()
		regex.findAll(this).forEach { map.put(it.groups[1]!!.value, it.groups[2]!!.value) }
		return SearchFilter(map, regex.replace(this, ""))
	}

/**
 * Alternative to [AddressBook.fetchStateByAbbreviation] taking one parameter instead.
 */
fun AddressBook.fetchStateByAbbreviation(countryStateAbbreviation: String): State? {
	val abbrs = countryStateAbbreviation.split('-')
	if (abbrs.size != 2) throw IllegalArgumentException("There must be both the country and the state abbreviation separated by a dash.")
	return fetchStateByAbbreviation(abbrs[0], abbrs[1])
}

/**
 * Alternative to [Collection.intersect] returning a [List] instead.
 */
infix fun <T> List<T>.intersectList(other: List<T>): List<T> {
	val mut = ArrayList(this)
	mut.retainAll(other)
	return mut
}
