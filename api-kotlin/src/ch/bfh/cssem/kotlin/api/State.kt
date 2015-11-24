package ch.bfh.cssem.kotlin.api

/**
 * Represents the API of a [Country]'s state.
 *
 * @property abbreviation abbreviation (alpha-2 / -3 code) of the state
 * @property name         name of the state
 * @property country      country the state belongs to
 * @property cities       [List] of cities in the state
 *
 * @author strut1 & touwm1
 */
interface State {

	var abbreviation: String
	var name: String
	var country: Country

	val cities: List<City>
}
