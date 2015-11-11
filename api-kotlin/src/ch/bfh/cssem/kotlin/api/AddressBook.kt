package ch.bfh.cssem.kotlin.api


public interface AddressBook {

	fun getPeople(): List<Person>

	fun getPerson(id: Int): Person
}
