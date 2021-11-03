package entity

import javax.persistence.*

@Entity
@Table(name = "publisher")
class Publisher(
    var name: String,

    @OneToMany(mappedBy = "publisher")
    var books: MutableList<Book> = mutableListOf()

): BaseEntity<Long>()