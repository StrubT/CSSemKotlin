package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.NamedQueries
import javax.persistence.NamedQuery
import javax.persistence.OneToMany
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry
import ch.bfh.cssem.kotlin.api.State as ApiState

/**
 * Represents a [Country][ch.bfh.cssem.kotlin.api.Country] implementation using the [Java Persistence API](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html).
 *
 * @author strut1 & touwm1
 *
 * @constructor Constructs a new country entity with the provided properties.
 *
 * @property statesJpa [List] of states in the country (typed to the specific entity class)
 * @property citiesJpa [List] of cities in the country (typed to the specific entity class)
 */
@Entity
@Table(name = "countries")
@NamedQueries(NamedQuery(name = Country.findAll, query = "select c from Country c order by c.name"),
							NamedQuery(name = Country.findByAbbreviation, query = "select c from Country c where c.abbreviation = :abbreviation"),
							NamedQuery(name = Country.findByName, query = "select c from Country c where c.abbreviation like :name or c.name like :name order by c.name"))
data class Country(

	@Column(name = "abbreviation")
	override var abbreviation: String,

	@Column(name = "name")
	override var name: String) : ApiCountry, PersistentEntity {

	/**
	 * Constructs a new empty country entity.
	 */
	protected constructor() : this("", "")

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	override var id: Int? = null
		get
		internal set

	@OneToMany(mappedBy = "countryJpa", fetch = FetchType.LAZY)
	internal lateinit var statesJpa: List<State>

	@OneToMany(mappedBy = "countryJpa", fetch = FetchType.LAZY)
	internal lateinit var citiesJpa: List<City>

	override val states: List<ApiState>
		get() = statesJpa

	override val cities: List<ApiCity>
		get() = citiesJpa

	companion object {

		internal const val findAll = "Country.findAll"
		internal const val findByAbbreviation = "Country.findByAbbreviation"
		internal const val findByName = "Country.findByName"

		internal val undef = Country()
	}
}
