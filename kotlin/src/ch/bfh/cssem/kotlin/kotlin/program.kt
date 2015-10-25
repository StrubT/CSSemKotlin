package ch.bfh.cssem.kotlin.kotlin

public data class User(public val login: String, public var name: String = login)

fun main(args: Array<String>) {

  val users = arrayListOf(
    User("strut1", "Strub, Thomas Reto"),
    User("touwm1"),
    User("weidj1"));

  users[1].name = "Touw, Marc"

  users.forEach { println(it) }
}
