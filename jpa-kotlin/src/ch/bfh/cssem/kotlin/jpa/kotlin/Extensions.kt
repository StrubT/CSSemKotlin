@file:JvmName("Util")
@file:JvmMultifileClass

package ch.bfh.cssem.kotlin.jpa.kotlin

/**
 * Turns the text into a MySQL filter string.
 *
 * @return MySQL filter string to use in a [like](http://dev.mysql.com/doc/en/string-comparison-functions.html#operator_like) comparison.
 */
fun String.makeFilter() = "%${replace(Regex("[^a-z0-9]+", RegexOption.IGNORE_CASE), "%").trim('%')}%"

//val String.filter: String
//	get() = "%${replace(Regex("[^a-z0-9]+", RegexOption.IGNORE_CASE), "%").trim('%')}%"
