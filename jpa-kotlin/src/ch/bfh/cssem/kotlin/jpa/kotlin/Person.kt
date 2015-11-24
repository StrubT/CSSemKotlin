package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Person as ApiPerson

/**
 * Represents a [Person][ch.bfh.cssem.kotlin.api.Person] implementation using the [Java Persistence API](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html).
 *
 * @author strut1 & touwm1
 *
 * @constructor Constructs a new person entity with the provided properties.
 *
 * @property cityJpa optional city of the person's postal address (typed to the specific entity class)
 */
@Entity
@Table(name = "people")
@NamedQueries(NamedQuery(name = Person.FIND_BY_NAME, query = "select p from Person p where p.firstName like :name or p.lastName like :name"),
							NamedQuery(name = Person.FIND_BY_EMAIL, query = "select p from Person p where p.emailPrivate = :email or p.emailWork = :email"))
data class Person(

	@Column(name = "lastname")
	override var lastName: String,

	@Column(name = "firstname")
	override var firstName: String,

	@Column(name = "addrstreet")
	override var street: String? = null,

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "addrcity")
	internal var cityJpa: City? = null,

	@Column(name = "emailhome")
	override var emailPrivate: String? = null,

	@Column(name = "emailwork")
	override var emailWork: String? = null,

	@Column(name = "phonehome")
	override var phoneHome: String? = null,

	@Column(name = "phonemobile")
	override var phoneMobile: String? = null,

	@Column(name = "phonework")
	override var phoneWork: String? = null) : ApiPerson, PersistentEntity {

	/**
	 * Constructs a new empty person entity.
	 */
	protected constructor() : this("", "")

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	override var id: Int? = null
		get
		internal set

	override var city: ApiCity?
		get() = cityJpa
		set(value) {
			cityJpa = value as City?
		}

	companion object {

		internal const val FIND_BY_NAME = "Person.FIND_BY_NAME"
		internal const val FIND_BY_EMAIL = "Person.FIND_BY_EMAIL"

		internal val UNDEF = Person()
	}
}
