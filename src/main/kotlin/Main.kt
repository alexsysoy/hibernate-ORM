import dao.BookDAO
import entity.Author
import entity.Book
import entity.Genre
import org.hibernate.cfg.Configuration

class Main {
}

fun main() {

    val sessionFactory = Configuration().configure()
        .addAnnotatedClass(Author::class.java)
        .addAnnotatedClass(Book::class.java)
        .addAnnotatedClass(Genre::class.java)
        .buildSessionFactory()

    sessionFactory.use { factory ->
        val dao = BookDAO(factory)

        // Заполняем бд
        println("***********************************************\n")
        println("Сохраняем книги в базу данных\n")
        //Boot().bootstrap().forEach { book -> dao.save(book) }

        // Ищем все записи в бд
        println("***********************************************\n")
        println("Достаём все книги из базы данных\n")
        val findAllBook = dao.findAll()
        println("Количество книг в базе данных: ${findAllBook.size}\nКниги: $findAllBook\n")

        // Ищем конкретную запись
        println("***********************************************\n")
        println("Достаём конкретную книгу из базы данных\n")
        val expectedBook = findAllBook.random()
        var foundByIdBook = dao.findById(expectedBook.id!!)
        println("Искали книгу: '${expectedBook.title}' Нашли по id книгу: '${foundByIdBook!!.title}'")
        println("Равны ли объекты: ${expectedBook == foundByIdBook}\n")

        // Обновляем конкретную запись
        println("***********************************************\n")
        println("Обновление конкретной книги из базы данных\n")
        println("Книга ДО обновления:\n" +
                "Название: ${foundByIdBook.title}\n" +
                "Цена: ${foundByIdBook.cost}\n" +
                "Количество страниц: ${foundByIdBook.numberOfPage}\n")
        foundByIdBook.title = "Новый заголовок книги"
        foundByIdBook.cost = 1000
        foundByIdBook.numberOfPage = 459
        dao.update(foundByIdBook)
        foundByIdBook = dao.findById(foundByIdBook.id!!)
        println("Книга ПОСЛЕ обновления:\n" +
                "Название: ${foundByIdBook!!.title}\n" +
                "Цена: ${foundByIdBook.cost}\n" +
                "Количество страниц: ${foundByIdBook.numberOfPage}\n")

        // Удаляем конкретную запись
        println("***********************************************\n")
        println("Удаление конкретной книги из базы данных\n")
        println("Книги в базе данных ДО: ${dao.findAll()}\n")
        foundByIdBook = dao.findById(foundByIdBook.id!!)
        println("Удаляем книгу с id: ${foundByIdBook!!.id}\n")
        dao.remove(foundByIdBook)
        println("Книги в базе данных ПОСЛЕ: ${dao.findAll()}\n")
    }


//    sessionFactory.use { factory ->
//        val dao = BookDAO(factory)
//        val foundBooks = dao.findAll()
//
//        println(foundBooks)
//
//        val foundBook = dao.findById(3)
//
//        println(foundBook!!.cost)
//    }

}