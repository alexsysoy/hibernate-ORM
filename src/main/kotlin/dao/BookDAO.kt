package dao

import domain.Book
import org.hibernate.SessionFactory

class BookDAO (private val sessionFactory: SessionFactory) {

    fun save(book: Book) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()

            session.save(book)
            session.transaction.commit()
        }
    }

    fun findById(id: Long): Book? {
        val result: Book?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(Book::class.java, id)
            session.transaction.commit()
        }
        return result
    }
}