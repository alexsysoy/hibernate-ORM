package domain

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null

    override fun equals(other: Any?): Boolean {

        other ?: return false

        if (this === other) return true

        if (other.javaClass != BaseEntity::class.java) return  false

        other as BaseEntity<*>

        return this.id != null && this.id == other.id
    }

    override fun hashCode() = 42

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id = $id)"
    }
}