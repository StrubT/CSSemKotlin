package ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions

import ch.bfh.cssem.kotlin.api.State

/**
 * Special JavaFX implementation of the interface [State].
 *
 * @property impl                concrete implementation of the interface
 * @property countryAbbreviation the country's abbreviation
 * @property countryName         the country's name
 * @property nofCities           the number of cities in this state
 *
 * @author strut1 & touwm1
 */
class FXState(val impl: State) : State by impl {

	val countryAbbreviation: String
		get() = country.abbreviation

	val countryName: String
		get() = country.name

	val nofCities: Int
		get() = cities.size
}
