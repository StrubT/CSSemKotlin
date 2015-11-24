package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.NamedQuery
import javax.persistence.OneToMany
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry
import ch.bfh.cssem.kotlin.api.Person as ApiPerson
import ch.bfh.cssem.kotlin.api.State as ApiState

internal const val CITY_FIND_BY_NAME = "CITY_FIND_BY_NAME"

@Entity
@Table(name = "cities")
@NamedQuery(name = CITY_FIND_BY_NAME, query = "select c from City c where c.name like :name")
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
	internal var countryJpa: Country) : ApiCity, PersistenceObject {

	protected constructor() : this("", "", State.UNDEF, Country.UNDEF)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	override var id: Int? = null
		get
		internal set

	@OneToMany(mappedBy = "cityJpa", fetch = FetchType.LAZY)
	internal var peopleJpa: List<Person> = listOf()

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

		internal val UNDEF = City()
	}
}
