package ch.bfh.cssem.kotlin.api

interface Country {

	var abbreviation: String

	var name: String

	val states: List<State>

	val cities: List<City>
}
