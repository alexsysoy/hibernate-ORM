package entity

import javax.persistence.*

@Entity
@Table(name = "book")
class Book (
            var title: String,
            var numberOfPage: Int = 0,
            var cost: Int = -1,

            @OneToOne(
                fetch = FetchType.LAZY,
                cascade = [CascadeType.ALL],
            )
            var genre: Genre? = null,

            @ManyToMany(
                cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY
            )
            @JoinTable(name = "book_author",
                joinColumns = [JoinColumn(name = "book_id")],
                inverseJoinColumns = [JoinColumn(name = "author_id")]
            )
            var authors: MutableList<Author> = mutableListOf()
): BaseEntity<Long>()