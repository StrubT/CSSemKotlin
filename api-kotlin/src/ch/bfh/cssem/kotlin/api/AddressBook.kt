package ch.bfh.cssem.kotlin.api

/**
 * Represents the API to the address book.
 *
 * @author strut1 & touwm1
 */
interface AddressBook {

	/**
	 * Fetches all people from the data source.
	 *
	 * @return a [List] containing all [Person] objects
	 */
	fun fetchAllPeople(): List<Person>

	/**
	 * Fetches the people with a [Person.firstName] or [Person.lastName] matching the provided filter from the data source.
	 *
	 * @param filter the filter to match the names against
	 *
	 * @return a [List] containing the matching [Person] objects
	 */
	fun fetchPeopleByName(filter: String): List<Person>

	/**
	 * Fetches the person with the specified [Person.emailPrivate] or [Person.emailWork] from the data source.
	 *
	 * @param email the e-mail address to search for
	 *
	 * @return the [Person] with the specified e-mail address; or null, if none
	 */
	fun fetchPersonByEmail(email: String): Person?

	/**
	 * Fetches all cities from the data source.
	 *
	 * @return a [List] containing all [City] objects
	 */
	fun fetchAllCities(): List<City>

	/**
	 * Fetches the city with the specified [City.postalCode] and [City.name] from the data source.
	 *
	 * @param postalCode the postal code to search for
	 * @param name       the name to search for
	 *
	 * @return the [City] with the specified postal code and name; or null, if none
	 */
	fun fetchCityByPostalCodeName(postalCode: String, name: String): City?

	/**
	 * Fetches all cities with a [City.name] matching the provided filter from the data source.
	 *
	 * @param filter the filter to match the names against
	 *
	 * @return a [List] containing the matching [City] objects
	 */
	fun fetchCitiesByName(filter: String): List<City>

	/**
	 * Fetches all cities with a [City.postalCode] matching the provided filter from the data source.
	 *
	 * @param filter the filter to match the postal codes against
	 *
	 * @return a [List] containing the matching [City] objects
	 */
	fun fetchCitiesByPostalCode(filter: String): List<City>

	/**
	 * Fetches all states from the data source.
	 *
	 * @return a [List] containing all [State] objects
	 */
	fun fetchAllStates(): List<State>

	/**
	 * Fetches the state with the specified [State.abbreviation] from the data source.
	 *
	 * @param country the country to search for states in
	 * @param abbreviation the abbreviation to search for
	 *
	 * @return the [State] with the specified abbreviation; or null, if none
	 */
	fun fetchStateByAbbreviation(country: Country, abbreviation: String) = fetchStateByAbbreviation(country.abbreviation, abbreviation)

	/**
	 * Fetches the state with the specified [State.abbreviation] from the data source.
	 *
	 * @param countryAbbreviation the abbreviation of the country to search for states in
	 * @param abbreviation the abbreviation to search for
	 *
	 * @return the [State] with the specified abbreviation; or null, if none
	 */
	fun fetchStateByAbbreviation(countryAbbreviation: String, abbreviation: String): State?

	/**
	 * Fetches all states with a [State.name] matching the provided filter from the data source.
	 *
	 * @param filter the filter to match the names against
	 *
	 * @return a [List] containing the matching [State] objects
	 */
	fun fetchStatesByName(filter: String): List<State>

	/**
	 * Fetches all countries from the data source.
	 *
	 * @return a [List] containing all [Country] objects
	 */
	fun fetchAllCountries(): List<Country>

	/**
	 * Fetches the country with the specified [Country.abbreviation] from the data source.
	 *
	 * @param abbreviation the abbreviation to search for
	 *
	 * @return the [Country] with the specified abbreviation; or null, if none
	 */
	fun fetchCountryByAbbreviation(abbreviation: String): Country?

	/**
	 * Fetches all countries with a [Country.name] matching the provided filter from the data source.
	 *
	 * @param filter the filter to match the names against
	 *
	 * @return a [List] containing the matching [Country] objects
	 */
	fun fetchCountriesByName(filter: String): List<Country>

	/**
	 * Instantiates a new [Person] with the provided properties.
	 *
	 * @param lastName  last (family) name of the person
	 * @param firstName first (given) name of the person
	 *
	 * @return a [Person] with the specified properties.
	 */
	fun createPerson(lastName: String, firstName: String): Person

	/**
	 * Instantiates a new [City] with the provided properties.
	 *
	 * @param name       name of the city
	 * @param postalCode postal code of the city
	 * @param state      optional state the city belongs to
	 * @param country    country the city belongs to
	 *
	 * @return a [City] with the specified properties.
	 */
	fun createCity(name: String, postalCode: String, state: State, country: Country): City

	/**
	 * Instantiates a new [State] with the provided properties.
	 *
	 * @param abbreviation abbreviation (alpha-2 / -3 code) of the state
	 * @param name         name of the state
	 * @param country      country the state belongs to
	 *
	 * @return a [State] with the specified properties.
	 */
	fun createState(abbreviation: String, name: String, country: Country): State

	/**
	 * Instantiates a new [Country] with the provided properties.
	 *
	 * @param abbreviation abbreviation (alpha-2 / -3 code) of the country
	 * @param name         name of the country
	 *
	 * @return a [Country] with the specified properties.
	 */
	fun createCountry(abbreviation: String, name: String): Country

	/**
	 * Stores / updates a [Person] in the data source.
	 *
	 * @param person person to store
	 *
	 * @return stored [Person] object
	 */
	fun storePerson(person: Person): Person

	/**
	 * Stores / updates a [City] in the data source.
	 *
	 * @param city city to store
	 *
	 * @return stored [City] object
	 */
	fun storeCity(city: City): City

	/**
	 * Stores / updates a [State] in the data source.
	 *
	 * @param state state to store
	 *
	 * @return stored [State] object
	 */
	fun storeState(state: State): State

	/**
	 * Stores / updates a [Country] in the data source.
	 *
	 * @param country country to store
	 *
	 * @return stored [Country] object
	 */
	fun storeCountry(country: Country): Country

	/**
	 * Removes a [Person] from the data source.
	 *
	 * @param person person to delete
	 */
	fun removePerson(person: Person)

	/**
	 * Removes a [City] from the data source.
	 *
	 * @param city city to delete
	 */
	fun removeCity(city: City)

	/**
	 * Removes a [State] from the data source.
	 *
	 * @param state state to delete
	 */
	fun removeState(state: State)

	/**
	 * Removes a [Country] from the data source.
	 *
	 * @param country country to delete
	 */
	fun removeCountry(country: Country)
}
