package ch.bfh.cssem.kotlin.jpa.kotlin

/**
 * Represents the base interface for all persistently stored entity classes.
 *
 * @author strut1 & touwm1
 *
 * @property id unique identifier of the entity
 */
interface PersistentEntity {

	val id: Int?
}
