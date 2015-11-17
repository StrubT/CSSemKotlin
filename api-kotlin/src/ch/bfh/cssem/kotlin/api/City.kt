package ch.bfh.cssem.kotlin.api

interface City {

	var name: String

	var postalCode: String

	var state: State?

	var country: Country

	val people: List<Person>
}
