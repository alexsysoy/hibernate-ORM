package domain

import javax.persistence.*

@Entity
@Table(name = "book")
class Book (@Id
            @GeneratedValue
            var id: Long = 0,
            var title: String,
            var numberOfPage: Int = 0,
            var cost: Int = -1,

            @OneToMany(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL],
                mappedBy = "book"
            )
            var authors: MutableList<Author> = mutableListOf(),

            @ManyToMany(
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY
            )
            @JoinTable(name = "book_genre",
                joinColumns = [JoinColumn(name = "book_id")],
                inverseJoinColumns = [JoinColumn(name = "genre_id")]
            )
            var genres: MutableSet<Genre> = mutableSetOf()
) {

    fun addAuthor(author: Author): Book {
        author.book = this
        this.authors.add(author)
        return this
    }
}