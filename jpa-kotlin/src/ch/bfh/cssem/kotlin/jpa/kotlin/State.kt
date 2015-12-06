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
import ch.bfh.cssem.kotlin.api.State as ApiState

/**
 * Represents a [State][ch.bfh.cssem.kotlin.api.State] implementation using the [Java Persistence API](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html).
 *
 * @author strut1 & touwm1
 *
 * @constructor Constructs a new state entity with the provided properties.
 *
 * @property country country the state belongs to (typed to the specific entity class)
 * @property cities  [List] of cities in the state (typed to the specific entity class)
 */
@Entity
@Table(name = "states")
@NamedQuery(name = State.findByName, query = "select s from State s where s.name like :name")
data class State(

	@Column(name = "abbreviation")
	override var abbreviation: String,

	@Column(name = "name")
	override var name: String,

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "country")
	internal var countryJpa: Country) : ApiState, PersistentEntity {

	/**
	 * Constructs a new empty state entity.
	 */
	protected constructor() : this("", "", Country.undef)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	override var id: Int? = null
		get
		internal set

	@OneToMany(mappedBy = "stateJpa", fetch = FetchType.LAZY)
	internal lateinit var citiesJpa: List<City>

	override var country: ApiCountry
		get() = countryJpa
		set(value) {
			countryJpa = value as Country
		}

	override val cities: List<ApiCity>
		get() = citiesJpa

	companion object {

		internal const val findByName = "State.findByName"

		internal val undef = State()
	}
}
