package ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions

import ch.bfh.cssem.kotlin.api.Country
import ch.bfh.cssem.kotlin.api.Person
import ch.bfh.cssem.kotlin.api.State

class FXPerson(val impl: Person) : Person by impl {

	val cityPostalCode: String?
		get() = city?.postalCode

	val cityName: String?
		get() = city?.name

	val cityState: State?
		get() = city?.state

	val cityStateAbbreviation: String?
		get() = cityState?.abbreviation

	val cityStateName: String?
		get() = cityState?.name

	val cityCountry: Country?
		get() = city?.country

	val cityCountryAbbreviation: String?
		get() = cityCountry?.abbreviation

	val cityCountryName: String?
		get() = cityCountry?.name

	val address: String?
		get() {
			if (street === null && city === null)
				return null

			val city = this.city //enable smart cast
			val state = city?.state

			val sb = StringBuilder()
			if (street !== null) {
				sb.append(street)
				if (city !== null) sb.append(", ")
			}
			if (city !== null) {
				sb.append(city.country.abbreviation).append('-')
				sb.append(city.postalCode).append(' ').append(city.name)
				if (state !== null)
					sb.append(' ').append(state.abbreviation)
			}

			return sb.toString()
		}

	val email: String?
		get() = listOf(emailPrivate, emailWork).filter { it !== null && it.length > 0 }.joinToString(separator = " / ")

	val phone: String?
		get() = listOf(phoneHome, phoneMobile, phoneWork).filter { it !== null && it.length > 0 }.joinToString(separator = " / ")
}
