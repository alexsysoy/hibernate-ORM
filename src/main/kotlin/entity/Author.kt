package entity

import javax.persistence.*

@Entity
@Table(name = "author")
class Author(
    var firstName: String,
    var lastName: String,

    @ManyToMany(mappedBy = "authors")
    var books: MutableList<Book> = mutableListOf()

): BaseEntity<Long>()
