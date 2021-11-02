package domain

import javax.persistence.*

@Entity
@Table(name = "genre")
class Genre(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String,
    @ManyToMany(mappedBy = "genres")
    var books: MutableSet<Book> = mutableSetOf()
)