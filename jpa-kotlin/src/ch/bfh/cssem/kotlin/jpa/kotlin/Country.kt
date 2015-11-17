package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.NamedQuery
import javax.persistence.OneToMany
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry
import ch.bfh.cssem.kotlin.api.State as ApiState

internal const val COUNTRY_FIND_BY_NAME = "COUNTRY_FIND_BY_NAME"

@Entity
@Table(name = "countries")
@NamedQuery(name = COUNTRY_FIND_BY_NAME, query = "select c from Country c where c.name like :name")
data class Country(

		@Column(name = "abbreviation")
		override var abbreviation: String,

		@Column(name = "name")
		override var name: String) : ApiCountry, PersistenceObject {

	protected constructor() : this("", "")

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	override var id: Int? = null
		get
		internal set

	@OneToMany(mappedBy = "countryJpa", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE), orphanRemoval = true)
	internal var statesJpa: List<State> = listOf()

	@OneToMany(mappedBy = "countryJpa", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE), orphanRemoval = true)
	internal var citiesJpa: List<City> = listOf()

	override val states: List<ApiState>
		get() = statesJpa

	override val cities: List<ApiCity>
		get() = citiesJpa

	companion object {

		internal val UNDEF = Country()
	}
}
