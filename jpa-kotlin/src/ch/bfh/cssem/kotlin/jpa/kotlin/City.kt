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
import javax.persistence.OneToMany
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry
import ch.bfh.cssem.kotlin.api.Person as ApiPerson
import ch.bfh.cssem.kotlin.api.State as ApiState

/**
 * Represents a [City][ch.bfh.cssem.kotlin.api.City] implementation using the [Java Persistence API](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html).
 *
 * @author strut1 & touwm1
 *
 * @constructor Constructs a new city entity with the provided properties.
 *
 * @property stateJpa   optional state the city belongs to (typed to the specific entity class)
 * @property countryJpa country the city belongs to (typed to the specific entity class)
 * @property peopleJpa  [List] of people living in the city (typed to the specific entity class)
 */
@Entity
@Table(name = "cities")
@NamedQueries(NamedQuery(name = City.findByName, query = "select c from City c where c.name like :name"),
							NamedQuery(name = City.findByPostalCode, query = "select c from City c where c.postalCode like :postalCode"))
data class City(

	@Column(name = "name")
	override var name: String,

	@Column(name = "postalcode")
	override var postalCode: String,

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "state")
	internal var stateJpa: State,

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "country")
	internal var countryJpa: Country) : ApiCity, PersistentEntity {

	/**
	 * Constructs a new empty city entity.
	 */
	protected constructor() : this("", "", State.undef, Country.undef)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	override var id: Int? = null
		get
		internal set

	@OneToMany(mappedBy = "cityJpa", fetch = FetchType.LAZY)
	internal lateinit var peopleJpa: List<Person>

	override var state: ApiState?
		get() = stateJpa
		set(value) {
			stateJpa = value as State
		}

	override var country: ApiCountry
		get() = countryJpa
		set(value) {
			countryJpa = value as Country
		}

	override val people: List<ApiPerson>
		get() = peopleJpa

	companion object {

		internal const val findByName = "City.findByName"
		internal const val findByPostalCode = "City.findByPostalCode"

		internal val undef = City()
	}
}
