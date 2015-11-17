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
import javax.persistence.NamedQuery
import javax.persistence.OneToMany
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry
import ch.bfh.cssem.kotlin.api.State as ApiState

internal const val STATE_FIND_BY_NAME = "STATE_FIND_BY_NAME"

@Entity
@Table(name = "states")
@NamedQuery(name = STATE_FIND_BY_NAME, query = "select s from State s where s.name like :name")
data class State(

	@Column(name = "abbreviation")
	override var abbreviation: String,

	@Column(name = "name")
	override var name: String,

	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST, CascadeType.MERGE))
	@JoinColumn(name = "country")
	internal var countryJpa: Country) : ApiState, PersistenceObject {

	protected constructor() : this("", "", Country.UNDEF)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	override var id: Int? = null
		get
		internal set

	@OneToMany(mappedBy = "stateJpa", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE), orphanRemoval = true)
	internal var citiesJpa: List<City> = listOf()

	override var country: ApiCountry
		get() = countryJpa
		set(value) {
			countryJpa = value as Country
		}

	override val cities: List<ApiCity>
		get() = citiesJpa

	companion object {

		internal val UNDEF = State()
	}
}
