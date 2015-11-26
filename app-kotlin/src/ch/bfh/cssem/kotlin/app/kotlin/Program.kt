package ch.bfh.cssem.kotlin.app.kotlin

import ch.bfh.cssem.kotlin.api.AddressBook
import java.util.ServiceLoader

/**
 * Entry point to the application.
 *
 * @param args command-line arguments (none supported)
 */
fun main(vararg args: String) {

	if (args.size > 0)
		throw IllegalArgumentException("No command-line arguments supported!")

	val book = ServiceLoader.load(AddressBook::class.java).single()
	book.fetchAllPeople().forEach { println(it) }
}

/* BASIC SYNTAX
 * [_] packages
 * [_] functions
 * [_] local variables
 * [_] string templates
 * [_] conditional expressions
 * [_] nullable values and checking for null
 * [_] type checks and automatic casts
 * [_] for loop
 * [_] while loop
 * [_] when expression
 * [_] ranges
 * [_] collections
 *
 * IDIOMS
 * [_] Creating DTOs (POJOs/POCOs)
 * [_] Default values for function parameters
 * [_] Filtering a list
 * [_] String Interpolation
 * [_] Instance Checks
 * [_] Traversing a map/list of pairs
 * [_] Using ranges
 * [_] Read-only list
 * [_] Read-only map
 * [_] Accessing a map
 * [_] Lazy property
 * [_] Extension Functions
 * [_] Creating a singleton
 * [_] If not null shorthand
 * [_] If not null and else shorthand
 * [_] Executing a statement if null
 * [_] Execute if not null
 * [_] Return on when statement
 * [_] 'try/catch' expression
 * [_] 'if' expression
 * [_] Builder-style usage of methods that return Unit
 * [_] Single-expression functions
 * [_] Calling multiple methods on an object instance ('with')
 * [_] Java 7's try with resources
 */
