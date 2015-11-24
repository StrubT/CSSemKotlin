package ch.bfh.cssem.kotlin.api

/**
 * Represents the API of a person.
 *
 * @author strut1 & touwm1
 *
 * @property lastName     last (family) name of the person
 * @property firstName    first (given) name of the person
 * @property street       optional street part of the person's postal address
 * @property city         optional city of the person's postal address
 * @property emailPrivate optional private e-mail address of the person
 * @property emailWork    optional work (business) e-mail address of the person
 * @property phoneHome    optional private fixed line telephone number of the person
 * @property phoneMobile  optional private mobile (cell) telephone number of the person
 * @property phoneWork    optional work (business) telephone number of the person
 */
interface Person {

	var lastName: String
	var firstName: String
	var street: String?
	var city: City?
	var emailPrivate: String?
	var emailWork: String?
	var phoneHome: String?
	var phoneMobile: String?
	var phoneWork: String?
}
