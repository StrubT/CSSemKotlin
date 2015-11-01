package ch.bfh.cssem.kotlin.kotlin

/**
 * Represents a human user of the application.
 *
 * @constructor Constructs a new user with a login and a name.
 *
 * @property login Gets the login of the user account.
 * @property name Gets / sets the name of the person.
 *
 * @author strut1 & touwm1
 */
public data class User(public val login: String, public var name: String = login)

/**
 * Runs the test statements.
 *
 * @param args arguments from command line (none used)
 *
 * @author strut1 & touwm1
 */
public fun main(args: Array<String>) {

	val users = arrayListOf(
		User("strut1", "Strub, Thomas Reto"),
		User("touwm1"),
		User("weidj1"));

	users[1].name = "Touw, Marc"

	users.forEach { println(it) }
}
