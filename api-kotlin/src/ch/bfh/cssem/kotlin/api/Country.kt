package ch.bfh.cssem.kotlin.api

/**
 * Represents the API of a country.
 *
 * @author strut1 & touwm1
 *
 * @property abbreviation abbreviation (alpha-2 / -3 code) of the country
 * @property name         name of the country
 * @property states       [List] of states in the country
 * @property cities       [List] of cities in the country
 */
interface Country {

	var abbreviation: String
	var name: String

	val states: List<State>
	val cities: List<City>
}
