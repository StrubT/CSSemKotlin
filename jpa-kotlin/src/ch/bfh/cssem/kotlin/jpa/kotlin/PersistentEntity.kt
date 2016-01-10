package ch.bfh.cssem.kotlin.jpa.kotlin

/**
 * Represents the base interface for all persistently stored entity classes.
 *
 * @author strut1 & touwm1
 *
 * @property id    unique identifier of the entity
 * @property status whether or not the entity has been persisted to the database
 */
interface PersistentEntity {

	val id: Int?

	val status: PersistenceStatus
		get() = if (id ?: 0 > 0) PersistenceStatus.PERSISTENT else PersistenceStatus.VOLATILE
}

/**
 * Represents a [PersistentEntity]'s different statuses.
 *
 * @author strut1 & touwm1
 */
enum class PersistenceStatus {

	/**
	 * Represents a non-persistent (volatile) status.
	 * The entity is only held in memory.
	 */
	VOLATILE,

	/**
	 * Represents a persistent status.
	 * The entity is stored in the database.
	 */
	PERSISTENT
}
