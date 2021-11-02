import boot.Boot
import dao.BookDAO
import domain.Author
import domain.Book
import org.hibernate.cfg.Configuration

class Main {
}

fun main() {

    Boot().bootstrap()

//    val sessionFactory = Configuration().configure()
//        .addAnnotatedClass(Author::class.java)
//        .addAnnotatedClass(Book::class.java)
//        .buildSessionFactory()
//
//    sessionFactory.use { factory ->
//        val dao = BookDAO(factory)
//
//    }

//    sessionFactory.use { factory ->
//        val dao = BookDAO(factory)
//
//        val findBook = dao.findById(29)
//        println(findBook)
//    }
}