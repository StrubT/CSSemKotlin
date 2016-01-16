@file:JvmName("Util")
@file:JvmMultifileClass

package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.EntityManager
import javax.persistence.Persistence
import javax.persistence.PersistenceException
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
	protected inline fun <reified E : PersistentEntity> findById(id: Int?): E? = entityManager.find(E::class.java, id)

	/**
	 * Finds all [PersistentEntities][PersistentEntity] in the [entityManager].
	 *
	 * @return a [List] containing all [PersistentEntities][PersistentEntity]
	 */
	protected inline fun <reified E : PersistentEntity> findAll(pageIndex: Int = 0, pageSize: Int? = null): List<E> {

		val criteriaQuery = entityManager.criteriaBuilder.createQuery(E::class.java)
		val query = entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(E::class.java)))

		if (pageSize !== null)
			query.setFirstResult(pageIndex * pageSize).setMaxResults(pageSize)

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
	protected inline fun <reified E : PersistentEntity> findByQuery(queryName: String, queryParameters: Map<String, Any?>? = null, pageIndex: Int = 0, pageSize: Int? = null): List<E> {

		val query = entityManager.createNamedQuery(queryName, E::class.java)

		queryParameters?.forEach { query.setParameter(it.key, it.value) }

		if (pageSize !== null)
			query.setFirstResult(pageIndex * pageSize).setMaxResults(pageSize)

		return query.resultList
	}

	/**
	 * Persists a [PersistentEntity] in the [entityManager].
	 *
	 * @param entity entity to persist
	 */
	protected fun <E : PersistentEntity> persist(entity: E) = entityManager.transact { persist(entity) }

	/**
	 * Merges a [PersistentEntity] into the [entityManager].
	 *
	 * @param entity entity to merge
	 *
	 * @return merged [PersistentEntity]
	 */
	protected fun <E : PersistentEntity> merge(entity: E) = entityManager.transact { merge(entity)!! }

	/**
	 * Persists or merges a [PersistentEntity] in/into the [entityManager].
	 *
	 * @param entity entity to persist / merge
	 *
	 * @return [PersistentEntity] to persist or merged [PersistentEntity]
	 */
	protected fun <E : PersistentEntity> persistMerge(entity: E): E {

		return if (entity.status === PersistenceStatus.PERSISTENT)
			merge(entity)
		else {
			persist(entity); entity
		}
	}

	/**
	 * Removes a [PersistentEntity] from the [entityManager].
	 *
	 * @param entity entity to remove
	 */
	protected fun <E : PersistentEntity> remove(entity: E) = entityManager.transact { remove(entity) }

	/**
	 * Casts an entity to the desired type or throws an [IllegalArgumentException].
	 *
	 * @param entity entity to cast
	 */
	protected fun <E : PersistentEntity> cast(entity: Any) = entity as? E ?: throw IllegalArgumentException("Cannot pass instance from different implementation.")

	override fun fetchAllPeople() = findByQuery<Person>(Person.findAll)

	override fun fetchPeopleByName(filter: String) = findByQuery<Person>(Person.findByName, mapOf("name" to filter.makeFilter()))

	override fun fetchPersonByEmail(email: String) = findByQuery<Person>(Person.findByEMail, mapOf("email" to email)).singleOrNull()

	override fun fetchAllCities() = findByQuery<City>(City.findAll)

	override fun fetchCityByPostalCodeName(postalCode: String, name: String) = findByQuery<City>(City.findByPostalCodeName, mapOf("postalCode" to postalCode, "name" to name)).single()

	override fun fetchCitiesByName(filter: String) = findByQuery<City>(City.findByName, mapOf("name" to filter.makeFilter()))

	override fun fetchCitiesByPostalCode(filter: String) = findByQuery<City>(City.findByPostalCode, mapOf("postalCode" to filter.makeFilter()))

	override fun fetchAllStates() = findByQuery<State>(State.findAll)

	override fun fetchStateByAbbreviation(countryAbbreviation: String, abbreviation: String) = findByQuery<State>(State.findByAbbreviation, mapOf("countryAbbreviation" to countryAbbreviation, "abbreviation" to abbreviation)).single()

	override fun fetchStatesByName(filter: String) = findByQuery<State>(State.findByName, mapOf("name" to filter.makeFilter()))

	override fun fetchAllCountries() = findByQuery<Country>(Country.findAll)

	override fun fetchCountryByAbbreviation(abbreviation: String) = findByQuery<Country>(Country.findByAbbreviation, mapOf("abbreviation" to abbreviation)).singleOrNull()

	override fun fetchCountriesByName(filter: String) = findByQuery<Country>(Country.findByName, mapOf("name" to filter.makeFilter()))

	override fun createPerson(lastName: String, firstName: String) = Person(lastName, firstName)

	override fun createCity(name: String, postalCode: String, state: ApiState, country: ApiCountry) = City(name, postalCode, cast(state), cast(country))

	override fun createState(abbreviation: String, name: String, country: ApiCountry) = State(abbreviation, name, cast(country))

	override fun createCountry(abbreviation: String, name: String) = Country(abbreviation, name)

	override fun storePerson(person: ApiPerson) = persistMerge(cast<Person>(person))

	override fun storeCity(city: ApiCity) = persistMerge(cast<City>(city))

	override fun storeState(state: ApiState) = persistMerge(cast<State>(state))

	override fun storeCountry(country: ApiCountry) = persistMerge(cast<Country>(country))

	override fun removePerson(person: ApiPerson) = remove(cast<Person>(person))

	override fun removeCity(city: ApiCity) = remove(cast<City>(city))

	override fun removeState(state: ApiState) = remove(cast<State>(state))

	override fun removeCountry(country: ApiCountry) = remove(cast<Country>(country))

	companion object {

		/**
		 * Name of the [javax.persistence.PersistenceUnit] to use
		 */
		internal const val persistenceUnit = "AddressBook"
	}
}

/**
 * Runs a certain operation inside a transactions.
 *
 * @param operation operation to run inside a transaction
 */
private inline fun <T> EntityManager.transact(operation: EntityManager.() -> T): T {

	with (transaction) {
		try {
			begin()
			val result = operation()
			commit()

			return result

		} catch (ex: Exception) {
			rollback()
			throw PersistenceException("Could not successfully complete transaction.", ex)
		}
	}
}

//private inline fun <T> EntityManager.transact(operation: EntityManager.() -> T): T {
//
//	try {
//		transaction.begin()
//		val result = operation()
//		transaction.commit()
//
//		return result
//
//	} catch (ex: Exception) {
//		transaction.rollback()
//		throw PersistenceException("Could not successfully complete transaction.", ex)
//
//	} finally {
//		println("This message will always be printed.")
//	}
//}

//private inline fun <T> EntityManager.transact(operation: EntityManager.() -> T): T? {
//
//	return try {
//		transaction.begin()
//		val result = operation()
//		transaction.commit()
//
//		result
//
//	} catch (ex: Exception) {
//		transaction.rollback()
//
//		null
//	}
//}
