package ch.bfh.cssem.kotlin.js

fun power(base: Double, exponent: Double) = Math.pow(base, exponent)
fun square(base: Double) = power(base, 2.0)
fun cube(base: Double) = power(base, 3.0)

fun factorial(n: Int): Long = if (n == 0) 1 else n * factorial(n - 1)

fun <T> forEachIndexed(collection: Collection<T>, operation: (Int, T) -> Unit) = collection.forEachIndexed (operation)
