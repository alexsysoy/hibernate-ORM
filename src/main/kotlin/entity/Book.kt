package entity

import javax.persistence.*

@Entity
@Table(name = "book")
class Book (
    var title: String,
    var numberOfPage: Int = 0,
    var cost: Int = -1,

    @ManyToOne(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE],
        fetch = FetchType.LAZY
    )
    var publisher: Publisher? = null,

    @ManyToMany(
                cascade = [CascadeType.PERSIST, CascadeType.MERGE],
                fetch = FetchType.LAZY
            )
            @JoinTable(name = "book_author",
                joinColumns = [JoinColumn(name = "book_id")],
                inverseJoinColumns = [JoinColumn(name = "author_id")]
            )
            var authors: MutableList<Author> = mutableListOf()
): BaseEntity<Long>() {

    fun addAuthor(author: Author) {
        authors.add(author)
        author.books.add(this)
    }

    fun addPublisher(publisher: Publisher) {
        this.publisher = publisher
        publisher.books.add(this)
    }
}