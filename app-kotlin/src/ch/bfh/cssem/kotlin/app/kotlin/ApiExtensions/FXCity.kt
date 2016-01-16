package ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions

import ch.bfh.cssem.kotlin.api.City

/**
 * Special JavaFX implementation of the interface [City].
 *
 * @property implementation      concrete implementation of the interface
 * @property stateAbbreviation   the state's abbreviation
 * @property stateName           the state's name
 * @property countryAbbreviation the country's abbreviation
 * @property countryName         the country's name
 * @property nofPeople           the number of people in this city
 *
 * @author strut1 & touwm1
 */
class FXCity(val implementation: City) : City by implementation {

	val stateAbbreviation: String?
		get() = state?.abbreviation

	val stateName: String?
		get() = state?.name

	val countryAbbreviation: String
		get() = country.abbreviation

	val countryName: String
		get() = country.name

	val nofPeople: Int
		get() = people.size
}
