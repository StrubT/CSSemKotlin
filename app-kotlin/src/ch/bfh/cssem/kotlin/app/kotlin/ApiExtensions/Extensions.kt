package ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions

import ch.bfh.cssem.kotlin.api.AddressBook
import ch.bfh.cssem.kotlin.api.City
import ch.bfh.cssem.kotlin.api.State

/**
 * Alternative to [AddressBook.fetchCityByPostalCodeName] taking one parameter instead.
 */
fun AddressBook.fetchCityByPostalCodeName(postalCodeName: String): City? {
	val abbrs = postalCodeName.split('-')
	if (abbrs.size != 2) throw IllegalArgumentException("There must be both the postal code and the name separated by a dash.")
	return fetchCityByPostalCodeName(abbrs[0], abbrs[1])
}

/**
 * Alternative to [AddressBook.fetchStateByAbbreviation] taking one parameter instead.
 */
fun AddressBook.fetchStateByAbbreviation(countryStateAbbreviation: String): State? {
	val abbrs = countryStateAbbreviation.split('-')
	if (abbrs.size != 2) throw IllegalArgumentException("There must be both the country and the state abbreviation separated by a dash.")
	return fetchStateByAbbreviation(abbrs[0], abbrs[1])
}
