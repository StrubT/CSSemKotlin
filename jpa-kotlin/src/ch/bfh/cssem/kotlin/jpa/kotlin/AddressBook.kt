package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.EntityManager
import javax.persistence.Persistence
import ch.bfh.cssem.kotlin.api.AddressBook as ApiAddressBook

public class AddressBook : ApiAddressBook {

	private val entityManager: EntityManager

	init {

		entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager()
	}

	public override fun getPeople(): List<Person> {

		val criteriaQuery = entityManager.criteriaBuilder.createQuery(Person::class.java)
		val query = entityManager.createQuery(criteriaQuery.select(criteriaQuery.from(Person::class.java)))

		return query.resultList;
	}

	public override fun getPerson(id: Int): Person = entityManager.find(Person::class.java, id)

	companion object {

		internal const val PERSISTENCE_UNIT: String = "AddressBook"
	}
}
