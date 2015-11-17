package ch.bfh.cssem.kotlin.app.kotlin

import ch.bfh.cssem.kotlin.api.AddressBook
import java.util.ServiceLoader

fun main(vararg args: String) {

	if (args.size > 0)
		throw RuntimeException("No command-line arguments supported!")

	val book = ServiceLoader.load(AddressBook::class.java).single()
	book.fetchAllPeople().forEach { println(it) }
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
 *
 * IDIOMS
 * Creating DTOs (POJOs/POCOs)
 * Default values for function parameters
 * Filtering a list
 * String Interpolation
 * Instance Checks
 * Traversing a map/list of pairs
 * Using ranges
 * Read-only list
 * Read-only map
 * Accessing a map
 * Lazy property
 * Extension Functions
 * Creating a singleton
 * If not null shorthand
 * If not null and else shorthand
 * Executing a statement if null
 * Execute if not null
 * Return on when statement
 * 'try/catch' expression
 * 'if' expression
 * Builder-style usage of methods that return Unit
 * Single-expression functions
 * Calling multiple methods on an object instance ('with')
 * Java 7's try with resources
 */
