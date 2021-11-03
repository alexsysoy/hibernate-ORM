package entity

import javax.persistence.*

@Entity
@Table(name = "genre")
class Genre(
    var name: String,
): BaseEntity<Long>()