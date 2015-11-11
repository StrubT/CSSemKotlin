package ch.bfh.cssem.kotlin.jpa.kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import ch.bfh.cssem.kotlin.api.City as ApiCity
import ch.bfh.cssem.kotlin.api.Person as ApiPerson

@Entity
@Table(name = "people")
public data class Person(

	@Column(name = "surname")
	public override var lastName: String,

	@Column(name = "forename")
	public override var firstName: String,

	@Column
	public override var street: String? = null,

	@ManyToOne
	@JoinColumn(name = "city")
	internal var cityJpa: City? = null,

	@Column
	public override var email: String? = null,

	@Column(name = "phonepriv")
	public override var phonePrivate: String? = null,

	@Column(name = "phonework")
	public override var phoneWork: String? = null) : ApiPerson {

	protected constructor() : this("", "")

	@Id
	@Column(name = "`key`")
	public override var id: Int = 0
		get
		internal set

	public override var city: ApiCity?
		get() = cityJpa
		set(value) {
			cityJpa = value as City?
		}
}
