package ch.bfh.cssem.kotlin.api

interface AddressBook {

	fun fetchAllPeople(): List<Person>

	fun fetchPeopleByName(filter: String): List<Person>

	fun fetchPersonByEmail(email: String): Person?

	fun fetchAllCities(): List<City>

	fun fetchAllCitiesInState(state: State): List<City>

	fun fetchAllCitiesInCountry(country: Country): List<City>

	fun fetchCitiesByName(filter: String): List<City>

	fun fetchAllStates(): List<State>

	fun fetchAllStatesInCountry(country: Country): List<State>

	fun fetchStatesByName(filter: String): List<State>

	fun fetchAllCountries(): List<Country>

	fun fetchCountriesByName(filter: String): List<Country>

	fun createPerson(lastName: String, firstName: String): Person

	fun createCity(name: String, postalCode: String, state: State, country: Country): City

	fun createState(abbreviation: String, name: String, country: Country): State

	fun createCountry(abbreviation: String, name: String): Country

	fun savePerson(person: Person): Person

	fun saveCity(city: City): City

	fun saveState(state: State): State

	fun saveCountry(country: Country): Country

	fun deletePerson(person: Person)

	fun deleteCity(city: City)

	fun deleteState(state: State)

	fun deleteCountry(country: Country)
}
