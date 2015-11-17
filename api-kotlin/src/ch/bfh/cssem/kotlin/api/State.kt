package ch.bfh.cssem.kotlin.api

interface State {

	var abbreviation: String

	var name: String

	var country: Country

	val cities: List<City>
}
