package ch.bfh.cssem.kotlin.app.kotlin

/**
 * Alternative to [Collection.intersect] returning a [List] instead.
 */
infix fun <T> List<T>.intersectList(other: List<T>): List<T> {
	val mut = this.toArrayList()
	mut.retainAll(other)
	return mut
}

//infix fun <T> List<T>.intersectList(other: List<T>): List<T> {
//	val intersection = arrayListOf<T>()
//	for (element in this)
//		if (element in other) intersection.add(element)
//	return intersection
//}

/**
 * Turns a list into a pair.
 */
@Throws(IllegalArgumentException::class)
fun <T> List<T>.makePair(): Pair<T, T> = if (size == 2) Pair(get(0), get(1)) else throw IllegalArgumentException("There must be exactly two elements to make a pair.")

/**
 * Turns a list into a triple.
 */
@Throws(IllegalArgumentException::class)
fun <T> List<T>.makeTriple(): Triple<T, T, T> = if (size == 3) Triple(get(0), get(1), get(2)) else throw IllegalArgumentException("There must be exactly three elements to make a triple.")

/**
 * Splits a string in two.
 *
 * @param delimiters One or more strings to be used as delimiters.
 * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
 * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
 */
@JvmOverloads
fun CharSequence.splitPair(vararg delimiters: String, ignoreCase: Boolean = false, limit: Int = 0) = split(*delimiters, ignoreCase = ignoreCase, limit = limit).makePair()

/**
 * Splits a string in two.
 *
 * @param delimiters One or more characters to be used as delimiters.
 * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
 * @param limit      The maximum number of substrings to return.
 */
@JvmOverloads
fun CharSequence.splitPair(vararg delimiters: Char, ignoreCase: Boolean = false, limit: Int = 0) = split(*delimiters, ignoreCase = ignoreCase, limit = limit).makePair()

/**
 * Splits a string in two.
 *
 * @param limit Non-negative value specifying the maximum number of substrings to return.
 */
@JvmOverloads
fun CharSequence.splitPair(regex: Regex, limit: Int = 0) = split(regex, limit).makePair()

/**
 * Splits a string in three.
 *
 * @param delimiters One or more strings to be used as delimiters.
 * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
 * @param limit      The maximum number of substrings to return. Zero by default means no limit is set.
 */
@JvmOverloads
fun CharSequence.splitTriple(vararg delimiters: String, ignoreCase: Boolean = false, limit: Int = 0) = split(*delimiters, ignoreCase = ignoreCase, limit = limit).makeTriple()

/**
 * Splits a string in three.
 *
 * @param delimiters One or more characters to be used as delimiters.
 * @param ignoreCase `true` to ignore character case when matching a delimiter. By default `false`.
 * @param limit      The maximum number of substrings to return.
 */
@JvmOverloads
fun CharSequence.splitTriple(vararg delimiters: Char, ignoreCase: Boolean = false, limit: Int = 0) = split(*delimiters, ignoreCase = ignoreCase, limit = limit).makeTriple()

/**
 * Splits a string in three.
 *
 * @param limit Non-negative value specifying the maximum number of substrings to return.
 */
@JvmOverloads
fun CharSequence.splitTriple(regex: Regex, limit: Int = 0) = split(regex, limit).makeTriple()

/**
 * Creates a predicate that represents the logical negation of this predicate.
 */
fun <T> ((T) -> Boolean).negate() = { t: T -> !this(t) }

/**
 * Creates a composed predicate that represents a short-circuiting logical AND of this predicate and another.
 *
 * @param other the other predicate to use
 */
fun <T> ((T) -> Boolean).and(other: (T) -> Boolean) = { t: T -> this(t) && other(t) }

/**
 * Creates a composed predicate that represents a short-circuiting logical OR of this predicate and another.
 *
 * @param other the other predicate to use
 */
fun <T> ((T) -> Boolean).or(other: (T) -> Boolean) = { t: T -> this(t) || other(t) }

/**
 * Tries to parse a string to an integer number.
 * Returns null if parsing failed.
 */
fun String.tryToInt(): Int? {

	return try {
		toInt()

	} catch(ex: NumberFormatException) {
		null
	}
}
