package ch.bfh.cssem.kotlin.js

fun power(base: Double, exponent: Double) = Math.pow(base, exponent)
fun square(base: Double) = power(base, 2.0)
fun cube(base: Double) = power(base, 3.0)

fun factorialRecursive(n: Int): Long = if (n in 0..1) 1 else if (n > 0) n * factorialRecursive(n - 1) else throw IllegalArgumentException("Cannot calculate factorial of negative numbers.")

fun factorialLoopV1(n: Int): Long {
	if (n in 0..1) return 1
	else if (n < 0) throw IllegalArgumentException("Cannot calculate factorial of negative numbers.")

	var fac = n
	var res = 1L
	do
		res *= fac--
	while (fac > 1)

	return res
}

fun factorialLoopV2(n: Int): Long {
	if (n in 0..1) return 1
	else if (n < 0) throw IllegalArgumentException("Cannot calculate factorial of negative numbers.")

	var fac = n
	var res = fac--.toLong()
	while (fac > 1)
		res *= fac--

	return res
}

fun factorialLoopV3(n: Int): Long {
	if (n in 0..1) return 1
	else if (n < 0) throw IllegalArgumentException("Cannot calculate factorial of negative numbers.")

	var res = 1L
	for (fac in 2..n) //or: n downTo 2
		res *= fac

	return res
}

fun <T> forEachIndexed(collection: Collection<T>, operation: (Int, T) -> Unit) = collection.forEachIndexed (operation)

fun testValue(x: Any?) {

	var validNumbers = listOf(1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811)
	var string = charArrayOf('1', '9', '9', '1').joinToString(separator = "")

	fun doSomething() {
		//something
	}

	when (x) {
		0                -> print("x == 0")
		parseInt(string) -> print("string encodes x")
		"hello", "Hello" -> print("x says hello")
	//in 1..10         -> print("x is in the range 1..10")
	//!in validNumbers -> {
	//	print("x is invalid")
	//	doSomething()
	//}
		is Iterable<*>   -> print("x is iterable")
		else             -> throw IllegalArgumentException("unknown argument")
	}
}
