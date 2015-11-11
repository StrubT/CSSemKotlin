package ch.bfh.cssem.kotlin.app.kotlin

import ch.bfh.cssem.kotlin.api.AddressBook
import java.util.ServiceLoader

fun main(vararg args: String) {

	if (args.size > 0)
		throw RuntimeException("No command-line arguments supported!")

	val book = ServiceLoader.load(AddressBook::class.java).single()
	book.getPeople().forEach { println(it) }
}

/* BASIC SYNTAX
 * packages
 * functions
 * local variables
 * string templates
 * conditional expressions
 * nullable values and checking for null
 * type checks and automatic casts
 * for loop
 * while loop
 * when expression
 * ranges
 * collections
 */
