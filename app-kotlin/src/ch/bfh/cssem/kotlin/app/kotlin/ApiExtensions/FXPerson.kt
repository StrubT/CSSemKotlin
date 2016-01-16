package ch.bfh.cssem.kotlin.app.kotlin.ApiExtensions

import ch.bfh.cssem.kotlin.api.Country
import ch.bfh.cssem.kotlin.api.Person
import ch.bfh.cssem.kotlin.api.State

/**
 * Special JavaFX implementation of the interface [Person].
 *
 * @property implementation          concrete implementation of the interface
 * @property cityPostalCode          the city's postal code
 * @property cityName                the city's name
 * @property cityState               the city's state
 * @property cityStateAbbreviation   the state's abbreviation
 * @property cityStateName           the state's name
 * @property cityCountry             the city's (state's) country
 * @property cityCountryAbbreviation the country's abbreviation
 * @property cityCountryName         the country's name
 * @property address                 concatenated postal address information
 * @property email                   concatenated e-mail addresses
 * @property phone                   concatenated telephone number
 *
 * @author strut1 & touwm1
 */
open class FXPerson(val implementation: Person) : Person by implementation {

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

	open val address: String?
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

sealed class FXPersonI18n(implementation: Person) : FXPerson(implementation) {

	class CH(implementation: Person) : FXPersonI18n(implementation)

	class AT(implementation: Person) : DE(implementation)
	class FR(implementation: Person) : DE(implementation, true)
	open class DE(implementation: Person, val upperCaseCity: Boolean = false) : FXPersonI18n(implementation) {

		override val address: String?
			get() {
				if (street === null && city === null)
					return null

				val city = this.city //enable smart cast

				val sb = StringBuilder()
				if (street !== null) {
					sb.append(street)
					if (city !== null) sb.append(", ")
				}
				if (city !== null)
					sb.append(city.postalCode).append(' ').append(if (upperCaseCity) city.name.toUpperCase() else city.name)

				return sb.toString()
			}
	}

	class UK(implementation: Person) : FXPersonI18n(implementation) {

		override val address: String?
			get() {
				if (street === null && city === null)
					return null

				val city = this.city //enable smart cast

				val sb = StringBuilder()
				if (street !== null) {
					sb.append(street)
					if (city !== null) sb.append(", ")
				}
				if (city !== null)
					sb.append(city.name).append(", ").append(city.postalCode)

				return sb.toString()
			}
	}

	class US_USPS(implementation: Person) : US(implementation, true)
	open class US(implementation: Person, val upperCase: Boolean = false) : FXPersonI18n(implementation) {

		override val address: String?
			get() {
				if (street === null && city === null)
					return null

				val street = this.street //enable smart cast
				val city = this.city
				val state = city?.state

				val sb = StringBuilder()
				if (street !== null) {
					sb.append(if (upperCase) street.toUpperCase() else street)
					if (city !== null) sb.append(", ")
				}
				if (city !== null) {
					sb.append(if (upperCase) city.name.toUpperCase() else city.name).append(", ")
					if (state !== null )
						sb.append(state.abbreviation).append(' ')
					sb.append(city.postalCode)
				}

				return sb.toString()
			}
	}
}
