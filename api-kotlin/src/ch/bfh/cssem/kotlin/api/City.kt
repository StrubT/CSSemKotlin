package ch.bfh.cssem.kotlin.api

public interface City {

	val id: Int

	var name: String

	var postalCode: String

	var country: Country
}
