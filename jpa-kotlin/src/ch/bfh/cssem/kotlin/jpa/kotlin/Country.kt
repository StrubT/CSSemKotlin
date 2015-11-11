package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.Country as ApiCountry

@Entity
@Table(name = "countries")
public data class Country(

	@Column
	public override var name: String) : ApiCountry {

	protected constructor() : this("")

	@Id
	@Column(name = "`key`")
	public override var id: Int = 0
		get
		internal set
}
