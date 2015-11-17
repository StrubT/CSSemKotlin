package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.CascadeType
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

internal const val PERSON_FIND_BY_NAME = "PERSON_FIND_BY_NAME"
internal const val PERSON_FIND_BY_EMAIL = "PERSON_FIND_BY_EMAIL"

@Entity
@Table(name = "people")
@NamedQueries(NamedQuery(name = PERSON_FIND_BY_NAME, query = "select p from Person p where p.firstName like :name or p.lastName like :name"),
							NamedQuery(name = PERSON_FIND_BY_EMAIL, query = "select p from Person p where p.emailPrivate = :email or p.emailWork = :email"))
data class Person(

	@Column(name = "lastname")
	override var lastName: String,

	@Column(name = "firstname")
	override var firstName: String,

	@Column(name = "addrstreet")
	override var street: String? = null,

	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE))
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
	override var phoneWork: String? = null) : ApiPerson, PersistenceObject {

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

		internal val UNDEF = Person()
	}
}
