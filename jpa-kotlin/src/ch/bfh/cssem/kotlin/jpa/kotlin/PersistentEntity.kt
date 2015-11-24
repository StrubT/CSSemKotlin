package ch.bfh.cssem.kotlin.jpa.kotlin

/**
 * Represents the base interface for all persistently stored entity classes.
 *
 * @property id unique identifier of the entity
 *
 * @author strut1 & touwm1
 */
interface PersistentEntity {

	val id: Int?
}
