package domain

import javax.persistence.*

@Entity
@Table(name = "author")
class Author(
    var firstName: String,
    var lastName: String,
    @ManyToOne
    var book: Book? = null
): BaseEntity<Long>()
