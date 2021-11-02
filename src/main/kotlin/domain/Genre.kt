package domain

import javax.persistence.*

@Entity
@Table(name = "genre")
class Genre(
    var name: String,
    @ManyToMany(mappedBy = "genres")
    var books: MutableSet<Book> = mutableSetOf()
): BaseEntity<Long>()