package dao

import entity.Book
import org.hibernate.HibernateException
import org.hibernate.SessionFactory

class BookDAO (private val sessionFactory: SessionFactory) {

    fun save(book: Book) {
        try {
            sessionFactory.openSession().use { session ->
                session.beginTransaction()
                session.persist(book)
                session.transaction.commit()
            }
        } catch (e: HibernateException) {
            println("Ошибка сохранения")
        }
    }

    fun update(book: Book) {
        try {
            sessionFactory.openSession().use { session ->
                session.beginTransaction()
                session.update(book)
                session.transaction.commit()
            }
        } catch (e: HibernateException) {
            println("Ошибка обновления")
        }
    }

    fun remove(book: Book) {
        try {
            sessionFactory.openSession().use { session ->
                session.beginTransaction()
                session.remove(book)
                session.transaction.commit()
            }
        } catch (e: HibernateException) {
            println("Ошибка удаления")
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

    fun findAll(): List<Book> {

        val result: List<Book>
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.createQuery("from Book").list() as List<Book>
            session.transaction.commit()
        }
        return result
    }
}