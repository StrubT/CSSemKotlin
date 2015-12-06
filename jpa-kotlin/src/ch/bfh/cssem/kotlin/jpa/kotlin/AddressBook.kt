package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Persistence
import kotlin.text.Regex
import kotlin.text.RegexOption
import ch.bfh.cssem.kotlin.api.AddressBook as ApiAddressBook
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry
import ch.bfh.cssem.kotlin.api.Person as ApiPerson
import ch.bfh.cssem.kotlin.api.State as ApiState

/**
 * Represents an [AddressBook][ch.bfh.cssem.kotlin.api.AddressBook] implementation using the [Java Persistence API](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html).
 *
 * @author strut1 & touwm1
 */
class AddressBook : ApiAddressBook {

	/**
	 * JPA [EntityManager][javax.persistence.EntityManager] to use.
	 */
	protected val entityManager = Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager()

	/**
	 * Finds a [PersistentEntity] in the [entityManager] by its unique identifier.
	 *
	 * @param id unique identifier to search for
	 *
	 * @return the [PersistentEntity] with the specified unique identifier; or null, if none
	 */
	inline fun <reified E : PersistentEntity> findById(id: Int?): E? = entityManager.find(E::class.java, id)

	/**
	 * Finds all [PersistentEntities][PersistentEntity] in the [entityManager].
	 *
	 * @return a [List] containing all [PersistentEntities][PersistentEntity]
	 */
	inline fun <reified E : PersistentEntity> findAll(): List<E> {

		val criteriaQuery = entityManager.criteriaBuilder.createQuery(E::class.java)
		val query = entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(E::class.java)))
		return query.resultList
	}

	/**
	 * Finds matching [PersistentEntities][PersistentEntity] in the [entityManager] using a [NamedQuery][javax.persistence.NamedQuery].
	 *
	 * @param queryName       name of the query to use
	 * @param queryParameters parameters to pass to the query
	 *
	 * @return a [List] containing the matching [PersistentEntities][PersistentEntity]
	 */
	protected inline fun <reified E : PersistentEntity> findByQuery(queryName: String, queryParameters: Map<String, Any?>?): List<E> {

		val query = entityManager.createNamedQuery(queryName, E::class.java)
		queryParameters?.forEach { query.setParameter(it.key, it.value) }
		return query.resultList
	}

	/**
	 * Persists a [PersistentEntity] in the [entityManager].
	 *
	 * @param entity entity to persist
	 */
	fun <E : PersistentEntity> persist(entity: E) = entityManager.persist(entity)

	/**
	 * Merges a [PersistentEntity] into the [entityManager].
	 *
	 * @param entity entity to merge
	 *
	 * @return merged [PersistentEntity]
	 */
	fun <E : PersistentEntity> merge(entity: E) = entityManager.merge(entity)!!

	/**
	 * Persists or merges a [PersistentEntity] in/into the [entityManager].
	 *
	 * @param entity entity to persist / merge
	 *
	 * @return [PersistentEntity] to persist or merged [PersistentEntity]
	 */
	protected fun <E : PersistentEntity> persistMerge(entity: E): E {

		return if (entity.id ?: 0 <= 0) {
			persist(entity); entity
		} else
			merge(entity)
	}

	/**
	 * Removes a [PersistentEntity] from the [entityManager].
	 *
	 * @param entity entity to remove
	 */
	fun <E : PersistentEntity> remove(entity: E) = entityManager.remove(entity)

	override fun fetchAllPeople() = findAll<Person>()

	override fun fetchPeopleByName(filter: String) = findByQuery<Person>(Person.findByName, mapOf("name" to filter.makeFilter()))

	override fun fetchPersonByEmail(email: String) = findByQuery<Person>(Person.findByEMail, mapOf("email" to email)).singleOrNull()

	override fun fetchAllCities() = findAll<City>()

	override fun fetchAllCitiesInState(state: ApiState) = (state as State).citiesJpa

	override fun fetchAllCitiesInCountry(country: ApiCountry) = (country as Country).citiesJpa

	override fun fetchCitiesByName(filter: String) = findByQuery<City>(City.findByName, mapOf("name" to filter.makeFilter()))

	override fun fetchCitiesByPostalCode(filter: String) = findByQuery<City>(City.findByPostalCode, mapOf("postalCode" to filter.makeFilter()))

	override fun fetchAllStates() = findAll<State>()

	override fun fetchAllStatesInCountry(country: ApiCountry) = (country as Country).statesJpa

	override fun fetchStatesByName(filter: String) = findByQuery<State>(State.findByName, mapOf("name" to filter.makeFilter()))

	override fun fetchAllCountries() = findAll<Country>()

	override fun fetchCountriesByName(filter: String) = findByQuery<Country>(Country.findByName, mapOf("name" to filter.makeFilter()))

	override fun createPerson(lastName: String, firstName: String) = Person(lastName, firstName)

	override fun createCity(name: String, postalCode: String, state: ApiState, country: ApiCountry) = City(name, postalCode, state as State, country as Country)

	override fun createState(abbreviation: String, name: String, country: ApiCountry) = State(abbreviation, name, country as Country)

	override fun createCountry(abbreviation: String, name: String) = Country(abbreviation, name)

	override fun storePerson(person: ApiPerson) = persistMerge(person as Person)

	override fun storeCity(city: ApiCity) = persistMerge(city as City)

	override fun storeState(state: ApiState) = persistMerge(state as State)

	override fun storeCountry(country: ApiCountry) = persistMerge(country as Country)

	override fun removePerson(person: ApiPerson) = remove(person as Person)

	override fun removeCity(city: ApiCity) = remove(city as City)

	override fun removeState(state: ApiState) = remove(state as State)

	override fun removeCountry(country: ApiCountry) = remove(country as Country)

	companion object {

		/**
		 * Name of the [javax.persistence.PersistenceUnit] to use
		 */
		internal const val persistenceUnit = "AddressBook"
	}
}

/**
 * Turns the text into a MySQL filter string.
 *
 * @return MySQL filter string to use in a [like](http://dev.mysql.com/doc/en/string-comparison-functions.html#operator_like) comparison.
 */
fun String.makeFilter() = "%${this.replace(Regex("/[^A-Z0-9]+/", RegexOption.IGNORE_CASE), "%").trim('%')}%"
