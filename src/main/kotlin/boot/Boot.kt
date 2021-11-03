package boot

import entity.Author
import entity.Book
import entity.Genre
import org.hibernate.SessionFactory

class Boot {

    fun bootstrap(): List<Book> {

        val genre1 = Genre(name = "Классическая литература")
        val genre2 = Genre(name = "Публицистика")
        val genre3 = Genre(name = "Русская литература")

        val author1 = Author(firstName = "Ричард", lastName = "Фейнман")
        val book1 = Book(title = "Вы, конечно, шутите, мистер Фейнман!", numberOfPage = 325, cost = 437)
        book1.authors.add(author1)
        book1.genre = genre2

        val author2 = Author(firstName = "Робби", lastName = "Вайсман")
        val author3 = Author(firstName = "Сьюзен", lastName = "Макклелланд")
        val book2 = Book(title = "Мальчик из Бухенвальда", numberOfPage = 400, cost = 443)
        book2.authors.add(author2)
        book2.authors.add(author3)
        book2.genre = genre2

        val author4 = Author(firstName = "", lastName = "Сунь-Цзы")
        val book3 = Book(title = "Искусство войны", numberOfPage = 160, cost = 153)
        book3.authors.add(author4)
        book3.genre = genre1

        val author5 = Author(firstName = "Федор", lastName = "Достоевский")
        val book4 = Book(title = "Братья Карамазовы", numberOfPage = 800, cost = 176)
        book4.authors.add(author5)
        book4.genre = genre3

        val book5 = Book(title = "Идиот", numberOfPage = 900, cost = 256)
        book5.authors.add(author5)
        book5.genre = genre3

        return listOf(book1, book2, book3, book4, book5)
    }
}