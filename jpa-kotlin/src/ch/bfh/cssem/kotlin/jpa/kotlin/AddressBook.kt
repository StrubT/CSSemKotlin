package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Persistence
import kotlin.text.Regex
import kotlin.text.RegexOption
import ch.bfh.cssem.kotlin.api.AddressBook as ApiAddressBook
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry
import ch.bfh.cssem.kotlin.api.Person as ApiPerson
import ch.bfh.cssem.kotlin.api.State as ApiState

internal const val PERSISTENCE_UNIT = "AddressBook"

class AddressBook : ApiAddressBook {

	private val entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager()

	private fun <E, K> findById(type: Class<E>, id: K) = entityManager.find(type, id)

	private fun <E> findAll(type: Class<E>): List<E> {

		val criteriaQuery = entityManager.criteriaBuilder.createQuery(type)
		val query = entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(type)))
		return query.resultList
	}

	private fun <E, P> findByQuery(type: Class<E>, queryName: String, queryParameters: Map<String, P>?): List<E> {

		val query = entityManager.createNamedQuery(queryName, type);
		queryParameters?.forEach { query.setParameter(it.key, it.value) }
		return query.resultList
	}

	fun <E> persist(entity: E) = entityManager.persist(entity)

	fun <E> merge(entity: E) = entityManager.merge(entity)!!

	fun <E> persistMerge(entity: E): E where E : PersistenceObject {

		return if (entity.id ?: 0 <= 0) {
			persist(entity); entity
		} else
			merge(entity)
	}

	fun <E> remove(entity: E) = entityManager.remove(entity)

	override fun fetchAllPeople() = findAll(Person::class.java)

	override fun fetchPeopleByName(filter: String) = findByQuery(Person::class.java, PERSON_FIND_BY_NAME, mapOf("name" to filter.makeFilter()))

	override fun fetchPersonByEmail(email: String) = findByQuery(Person::class.java, PERSON_FIND_BY_EMAIL, mapOf("email" to email)).singleOrNull()

	override fun fetchAllCities() = findAll(City::class.java)

	override fun fetchAllCitiesInState(state: ApiState) = (state as State).citiesJpa

	override fun fetchAllCitiesInCountry(country: ApiCountry) = (country as Country).citiesJpa

	override fun fetchCitiesByName(filter: String) = findByQuery(City::class.java, CITY_FIND_BY_NAME, mapOf("name" to filter.makeFilter()))

	override fun fetchAllStates() = findAll(State::class.java)

	override fun fetchAllStatesInCountry(country: ApiCountry) = (country as Country).statesJpa

	override fun fetchStatesByName(filter: String) = findByQuery(State::class.java, STATE_FIND_BY_NAME, mapOf("name" to filter.makeFilter()))

	override fun fetchAllCountries() = findAll(Country::class.java)

	override fun fetchCountriesByName(filter: String) = findByQuery(Country::class.java, COUNTRY_FIND_BY_NAME, mapOf("name" to filter.makeFilter()))

	override fun createPerson(lastName: String, firstName: String) = Person(lastName, firstName)

	override fun createCity(name: String, postalCode: String, state: ApiState, country: ApiCountry) = City(name, postalCode, state as State, country as Country)

	override fun createState(abbreviation: String, name: String, country: ApiCountry) = State(abbreviation, name, country as Country)

	override fun createCountry(abbreviation: String, name: String) = Country(abbreviation, name)

	override fun savePerson(person: ApiPerson) = persistMerge(person as Person)

	override fun saveCity(city: ApiCity) = persistMerge(city as City)

	override fun saveState(state: ApiState) = persistMerge(state as State)

	override fun saveCountry(country: ApiCountry) = persistMerge(country as Country)

	override fun deletePerson(person: ApiPerson) = remove(person)

	override fun deleteCity(city: ApiCity) = remove(city)

	override fun deleteState(state: ApiState) = remove(state)

	override fun deleteCountry(country: ApiCountry) = remove(country)
}

fun String.makeFilter() = "%${this.replace(Regex("/[^A-Z0-9]+/", RegexOption.IGNORE_CASE), "%").trim('%')}%"
