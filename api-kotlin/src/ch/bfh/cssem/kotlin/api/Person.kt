package ch.bfh.cssem.kotlin.api

public interface Person {

	val id: Int

	var lastName: String

	var firstName: String

	var street: String?

	var city: City?

	var email: String?

	var phonePrivate: String?

	var phoneWork: String?
}
