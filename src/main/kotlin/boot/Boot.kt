package boot

import dao.BookDAO
import domain.Author
import domain.Book
import domain.Genre
import org.hibernate.cfg.Configuration

class Boot {
    fun bootstrap() {

        val genre1 = Genre(name = "Классическая литература")
        val genre2 = Genre(name = "Публицистика")
        val genre3 = Genre(name = "Русская литература")

        val author1 = Author(firstName = "Ричард", lastName = "Фейнман")
        val book1 = Book(title = "Вы, конечно, шутите, мистер Фейнман!", numberOfPage = 325, cost = 437)
        book1.addAuthor(author1)
        book1.genres.add(genre2)

        val author2 = Author(firstName = "Робби", lastName = "Вайсман")
        val author3 = Author(firstName = "Сьюзен", lastName = "Макклелланд")
        val book2 = Book(title = "Мальчик из Бухенвальда", numberOfPage = 400, cost = 443)
        book2.addAuthor(author2)
        book2.addAuthor(author3)
        book2.genres.add(genre2)

        val author4 = Author(firstName = "", lastName = "Сунь-Цзы")
        val book3 = Book(title = "Искусство войны", numberOfPage = 160, cost = 153)
        book3.addAuthor(author4)
        book3.genres.add(genre1)

        val author5 = Author(firstName = "Федор", lastName = "Достоевский")
        val book4 = Book(title = "Братья Карамазовы", numberOfPage = 800, cost = 176)
        book4.addAuthor(author5)
        book4.genres.add(genre1)
        book4.genres.add(genre3)

        val sessionFactory = Configuration().configure()
            .addAnnotatedClass(Author::class.java)
            .addAnnotatedClass(Book::class.java)
            .addAnnotatedClass(Genre::class.java)
            .buildSessionFactory()

        sessionFactory.use { factory ->
            val dao = BookDAO(factory)

            dao.save(book1)
            dao.save(book2)
            dao.save(book3)
            dao.save(book4)
        }

    }
}