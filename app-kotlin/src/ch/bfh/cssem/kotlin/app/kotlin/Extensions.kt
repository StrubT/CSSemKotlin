package ch.bfh.cssem.kotlin.app.kotlin

/**
 * Alternative to [Collection.intersect] returning a [List] instead.
 */
infix fun <T> List<T>.intersectList(other: List<T>): List<T> {
	val mut = this.toArrayList()
	mut.retainAll(other)
	return mut
}
