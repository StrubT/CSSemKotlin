package ch.bfh.cssem.kotlin.kotlin

public data class User(public val login: String)

fun main(args: Array<String>) {

  val users = arrayOf(User("strut1"), User("touwm1"));

  for (user in users)
    println(user)
}
