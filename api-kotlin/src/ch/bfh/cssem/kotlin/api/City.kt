package ch.bfh.cssem.kotlin.api

/**
 * Represents the API of a city.
 *
 * @property name       name of the city
 * @property postalCode postal code of the city
 * @property state      optional state the city belongs to
 * @property country    country the city belongs to
 * @property people     [List] of people living in the city
 *
 * @author strut1 & touwm1
 */
interface City {

	var name: String
	var postalCode: String
	var state: State?
	var country: Country

	val people: List<Person>
}
