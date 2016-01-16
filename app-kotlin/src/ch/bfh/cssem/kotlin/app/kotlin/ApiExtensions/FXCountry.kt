package ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions

import ch.bfh.cssem.kotlin.api.Country

/**
 * Special JavaFX implementation of the interface [Country].
 *
 * @property implementation concrete implementation of the interface
 * @property nofCities      the number of cities in this country
 * @property nofStates      the number of states in this country
 *
 * @author strut1 & touwm1
 */
class FXCountry(val implementation: Country) : Country by implementation {

	val nofCities: Int
		get() = cities.size

	val nofStates: Int
		get() = states.size
}
