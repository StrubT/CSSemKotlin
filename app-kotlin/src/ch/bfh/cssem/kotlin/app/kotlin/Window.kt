package ch.bfh.cssem.kotlin.app.kotlin

import ch.bfh.cssem.kotlin.api.AddressBook
import ch.bfh.cssem.kotlin.api.City
import ch.bfh.cssem.kotlin.api.Country
import ch.bfh.cssem.kotlin.api.State
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXCity
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXCountry
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXPerson
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXPersonI18n
import ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions.FXState
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TableRow
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.FlowPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import javafx.stage.WindowEvent
import javafx.util.Callback
import java.net.URL
import java.util.ArrayList
import java.util.NoSuchElementException
import java.util.ResourceBundle
import java.util.ServiceLoader
import java.util.concurrent.Executors

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

	//region FXML properties

	@FXML protected lateinit var rootPane: StackPane
	@FXML protected lateinit var contentPane: BorderPane
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
	@FXML protected lateinit var personEditContainerPane: FlowPane
	@FXML protected lateinit var personEditPane: GridPane
	@FXML protected lateinit var personEditTitleLabel: Label
	@FXML protected lateinit var personEditLastNameLabel: Label
	@FXML protected lateinit var personEditLastNameField: TextField
	@FXML protected lateinit var personEditFirstNameLabel: Label
	@FXML protected lateinit var personEditFirstNameField: TextField
	@FXML protected lateinit var personEditStreetLabel: Label
	@FXML protected lateinit var personEditStreetField: TextField
	@FXML protected lateinit var personEditCityLabel: Label
	@FXML protected lateinit var personEditCityCombo: ComboBox<FXCity>
	@FXML protected lateinit var personEditEmailPrivateLabel: Label
	@FXML protected lateinit var personEditEmailPrivateField: TextField
	@FXML protected lateinit var personEditEmailWorkLabel: Label
	@FXML protected lateinit var personEditEmailWorkField: TextField
	@FXML protected lateinit var personEditPhoneHomeLabel: Label
	@FXML protected lateinit var personEditPhoneHomeField: TextField
	@FXML protected lateinit var personEditPhoneMobileLabel: Label
	@FXML protected lateinit var personEditPhoneMobileField: TextField
	@FXML protected lateinit var personEditPhoneWorkLabel: Label
	@FXML protected lateinit var personEditPhoneWorkField: TextField
	//endregion

	private val addressBook = ServiceLoader.load(AddressBook::class.java).single()
	private val addressBookExecutor = Executors.newSingleThreadExecutor()

	/**
	 * Initialises the [Window][javafx.stage.Window] controller after the instance is created.
	 */
	init {

		//...
	}

	/**
	 * Initialises the [Window][javafx.stage.Window] controller after the root [Node][javafx.scene.Node] has been processed completely.
	 */
	override fun initialize(location: URL?, resources: ResourceBundle?) {

		citiesTable.rowFactory = object : Callback<TableView<FXCity>, TableRow<FXCity>> {
			override fun call(param: TableView<FXCity>?): TableRow<FXCity>? {
				val row = TableRow<FXCity>()
				row.onMouseClicked = object : EventHandler<MouseEvent> {
					override fun handle(event: MouseEvent) {
						if (event.clickCount == 2 && !row.isEmpty)
							filterPeople(row.item)
					}
				}
				return row
			}
		}

		statesTable.rowFactory = Callback {
			val row = TableRow<FXState>()
			row.onMouseClicked = EventHandler { event ->
				if (event.clickCount == 2 && !row.isEmpty) {
					filterPeople(row.item)
					filterCities(row.item)
				}
			}
			row
		}

		countriesTable.setRowFactory {
			val row = TableRow<FXCountry>()
			row.setOnMouseClicked { event ->
				if (event.clickCount == 2 && !row.isEmpty) {
					filterPeople(row.item)
					filterCities(row.item)
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
	 *
	 * @param stage [Stage] to initialise
	 * @param scene [Scene] to initialise
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
	 * Applies special filters to the people table.
	 *
	 * @param filter filter to apply to the people table
	 */
	fun filterPeople(filter: Any? = null) {

		val searchFilter = peopleSearchField.text.filter
		searchFilter.valueFilters.clear()
		when (filter) {
			is City    -> searchFilter.valueFilters.put(SearchFilter.cityKey, "${filter.postalCode}-${filter.name}")
			is State   -> searchFilter.valueFilters.put(SearchFilter.stateKey, "${filter.country.abbreviation}-${filter.abbreviation}")
			is Country -> searchFilter.valueFilters.put(SearchFilter.countryKey, filter.abbreviation)
			else       -> throw IllegalArgumentException("The filter of type '${filter?.javaClass}' is not available for people.")
		}

		peopleSearchField.text = searchFilter.combined

		tabPane.selectionModel.select(peopleTab)
		searchPeople()
	}

	/**
	 * Searches for people in the data source.
	 *
	 * @param event [KeyEvent] that triggered the search, if any
	 */
	fun searchPeople(event: KeyEvent? = null) {

		val filter = peopleSearchField.text.filter

		peopleTable.items queryUpdate {
			var people = if (!filter.textFilter.isNullOrBlank()) fetchPeopleByName(filter.textFilter) else fetchAllPeople()

			for ((key, flt) in filter.valueFilters)
				when (key) {
					SearchFilter.countryKey -> people = people.filter { it.city?.country?.abbreviation == flt }
					SearchFilter.stateKey   -> people = people.filter { val (country, state) = flt.splitPair('-'); it.city?.country?.abbreviation == country && it.city?.state?.abbreviation == state }
					SearchFilter.cityKey    -> people = people.filter { val (postalCode, name) = flt.splitPair('-'); it.city?.postalCode == postalCode && it.city?.name == name }
					else                    -> throw NoSuchElementException("The filter '$key' is not available for people.")
				}

			people.map { FXPersonI18n.CH(it) }
		}
	}

	/**
	 * Applies special filters to the city table.
	 *
	 * @param filter filter to apply to the cities table
	 */
	fun filterCities(filter: Any? = null) {

		val searchFilter = citiesSearchField.text.filter
		searchFilter.valueFilters.clear()
		when (filter) {
			is State   -> searchFilter.valueFilters.put(SearchFilter.stateKey, "${filter.country.abbreviation}-${filter.abbreviation}")
			is Country -> searchFilter.valueFilters.put(SearchFilter.countryKey, filter.abbreviation)
			else       -> throw IllegalArgumentException("The filter of type '${filter?.javaClass}' is not available for cities.")
		}
		citiesSearchField.text = searchFilter.combined

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

		citiesTable.items queryUpdate {
			val cities = ArrayList<List<City>>()
			if (!filter.textFilter.isNullOrBlank()) cities.add(fetchCitiesByName(filter.textFilter))
			if (!citiesPostalCodeField.text.isNullOrBlank()) cities.add(fetchCitiesByPostalCode(citiesPostalCodeField.text))
			if (cities.isEmpty()) cities.add(fetchAllCities())

			var cityFilter = { c: City -> true }
			for ((key, flt) in filter.valueFilters)
				when (key) {
					SearchFilter.countryKey -> cityFilter = cityFilter.and { it.country.abbreviation == flt }
					SearchFilter.stateKey   -> cityFilter = cityFilter.and { val (country, state) = flt.splitPair('-'); it.country.abbreviation == country && it.state?.abbreviation == state }
					else                    -> throw NoSuchElementException("The filter '$key' is not available for cities.")
				}

			cities.reduce { a, b -> a intersectList b }.filter(cityFilter).map { FXCity(it) }
		}
	}

	/**
	 * Applies a special filter to the state table.
	 *
	 * @param filter filter to apply to the states table
	 */
	fun filterStates(filter: Any? = null) {

		val searchFilter = statesSearchField.text.filter
		if (filter is Country)
			searchFilter.valueFilters.put(SearchFilter.countryKey, filter.abbreviation)
		else
			throw IllegalArgumentException("The filter of type '${filter?.javaClass}' is not available for states.")

		statesSearchField.text = searchFilter.combined

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

		statesTable.items queryUpdate {
			var states = if (!filter.textFilter.isNullOrBlank()) fetchStatesByName(filter.textFilter) else fetchAllStates()

			for ((key, flt) in filter.valueFilters)
				when (key) {
					SearchFilter.countryKey -> states = states.filter { it.country.abbreviation == flt }
					else                    -> throw NoSuchElementException("The filter '$key' is not available for states.")
				}

			states.map { FXState(it) }
		}
	}

	/**
	 * Searches for countries in the data source.
	 *
	 * @param event [KeyEvent] that triggered the search, if any
	 */
	fun searchCountries(event: KeyEvent? = null) {

		val filter = countriesSearchField.text.filter
		if (filter.valueFilters.isNotEmpty())
			throw NoSuchElementException("No filters are available for countries.")

		countriesTable.items queryUpdate {
			(if (!filter.textFilter.isNullOrBlank()) fetchCountriesByName(filter.textFilter) else fetchAllCountries()).map { FXCountry(it) }
		}
	}

	private infix inline fun <T> MutableList<T>.queryUpdate(crossinline query: AddressBook.() -> List<T>) {

		addressBookExecutor.submit {
			val result = addressBook.query()

			Platform.runLater {
				clear()
				addAll(result)
			}
		}
	}
}
