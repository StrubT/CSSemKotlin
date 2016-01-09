package ch.bfh.cssem.kotlin.app.kotlin

/**
 * Represents the contents of a search filter.
 *
 * @property valueFilters the special value filters enclosed in square brackets
 * @property textFilter   the default text filter
 *
 * @author strut1 & touwm1
 */
data class SearchFilter(var valueFilters: MutableMap<String, String>, var textFilter: String) {

	/**
	 * Gets the combined filter text.
	 */
	val combined: String
		get() = "${valueFilters.entries.joinToString(separator = " ") { "[${it.key}=${it.value}]" }} $textFilter"

	companion object {

		internal const val cityKey = "city"
		internal const val stateKey = "state"
		internal const val countryKey = "country"
	}
}

/**
 * Creates a [SearchFilter] from the filter text.
 */
val String.filter: SearchFilter
	get() {
		val regex = Regex("\\s*\\[\\s*(\\w+)\\s*=\\s*([^\\]]+)\\s*\\]\\s*")
		val map = hashMapOf<String, String>()
		regex.findAll(this).forEach { map.put(it.groups[1]!!.value, it.groups[2]!!.value) }
		return SearchFilter(map, regex.replace(this, ""))
	}
