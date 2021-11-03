package boot

import entity.Author
import entity.Book
import entity.Publisher
import org.hibernate.cfg.Configuration

class Boot {

    fun bootstrap() {

        val sessionFactory = Configuration().configure()
            .addAnnotatedClass(Author::class.java)
            .addAnnotatedClass(Book::class.java)
            .addAnnotatedClass(Publisher::class.java)
            .buildSessionFactory()

        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            val publisher1 = Publisher(name = "МИФ")
            val publisher2 = Publisher(name = "ЭКСМО")
            val publisher3 = Publisher(name = "МАНН")
            val publisher4 = Publisher(name = "Азбука")

            session.save(publisher1)
            session.save(publisher2)
            session.save(publisher3)
            session.save(publisher4)

            val author1 = Author(firstName = "Ричард", lastName = "Фейнман")
            val book1 = Book(title = "Вы, конечно, шутите, мистер Фейнман!", numberOfPage = 325, cost = 437)
            book1.addAuthor(author1)
            book1.addPublisher(publisher1)
            session.persist(book1)

            val author2 = Author(firstName = "Робби", lastName = "Вайсман")
            val author3 = Author(firstName = "Сьюзен", lastName = "Макклелланд")
            val book2 = Book(title = "Мальчик из Бухенвальда", numberOfPage = 400, cost = 443)
            book2.addAuthor(author2)
            book2.addAuthor(author3)
            book2.addPublisher(publisher2)
            session.persist(book2)

            val author4 = Author(firstName = "", lastName = "Сунь-Цзы")
            val book3 = Book(title = "Искусство войны", numberOfPage = 160, cost = 153)
            book3.addAuthor(author4)
            book3.addPublisher(publisher3)
            session.persist(book3)

            val author5 = Author(firstName = "Федор", lastName = "Достоевский")
            val book4 = Book(title = "Братья Карамазовы", numberOfPage = 800, cost = 176)
            book4.addAuthor(author5)
            book4.addPublisher(publisher4)
            session.persist(book4)

            val book5 = Book(title = "Идиот", numberOfPage = 900, cost = 256)
            book5.addAuthor(author5)
            book5.addPublisher(publisher4)
            session.persist(book5)

            session.transaction.commit()
        }
    }
}