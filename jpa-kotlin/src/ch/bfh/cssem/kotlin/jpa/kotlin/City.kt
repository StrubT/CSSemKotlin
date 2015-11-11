package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Country as ApiCountry

@Entity
@Table(name = "cities")
public data class City(

	@Column
	public override var name: String,

	@Column(name = "zip")
	public override var postalCode: String,

	@ManyToOne
	@JoinColumn(name = "country")
	internal var countryJpa: Country?) : ApiCity {

	protected constructor() : this("", "", null)

	@Id
	@Column(name = "`key`")
	public override var id: Int = 0
		get
		internal set

	public override var country: ApiCountry
		get() = countryJpa!!
		set(value) {
			countryJpa = value as Country
		}
}
