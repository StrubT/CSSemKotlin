package ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions

import ch.bfh.cssem.kotlin.api.AddressBook
import ch.bfh.cssem.kotlin.api.City
import ch.bfh.cssem.kotlin.api.State
import ch.bfh.cssem.kotlin.app.kotlin.splitPair

/**
 * Alternative to [AddressBook.fetchCityByPostalCodeName] taking one parameter instead.
 */
fun AddressBook.fetchCityByPostalCodeName(postalCodeName: String): City? {
	val (postalCode, name) = postalCodeName.splitPair('-')
	return fetchCityByPostalCodeName(postalCode, name)
}

/**
 * Alternative to [AddressBook.fetchStateByAbbreviation] taking one parameter instead.
 */
fun AddressBook.fetchStateByAbbreviation(countryStateAbbreviation: String): State? {
	val (countryAbbreviation, abbreviation) = countryStateAbbreviation.splitPair('-')
	return fetchStateByAbbreviation(countryAbbreviation, abbreviation)
}
