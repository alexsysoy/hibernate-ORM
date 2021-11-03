import boot.Boot
import dao.BookDAO
import entity.Author
import entity.Book
import entity.Publisher
import org.hibernate.cfg.Configuration

class Main {
}

fun main() {

    // Загружаем в базу данных записи
    Boot().bootstrap()
    println("***********************************************\n")
    println("Записи в базу данных внесены\n")


    val sessionFactory = Configuration().configure()
        .addAnnotatedClass(Author::class.java)
        .addAnnotatedClass(Book::class.java)
        .addAnnotatedClass(Publisher::class.java)
        .buildSessionFactory()

    sessionFactory.use { factory ->
        val dao = BookDAO(factory)
        // Ищем все записи в бд
        println("***********************************************\n")
        println("Достаём все книги из базы данных\n")
        val foundAllBooks = dao.findAll()
        println("Количество книг в базе данных: ${foundAllBooks.size}\nКниги: $foundAllBooks\n")

        // Добавляем запись в бд
        println("***********************************************\n")
        println("Добавляем книгу в базу данных\n")
        val newBook = Book("Имя розы", 600, 300)
        val author = Author("Умберто", "Эко")
        val publisher = Publisher("Просвещение")
        newBook.addAuthor(author)
        newBook.addPublisher(publisher)
        dao.save(newBook)
        val result = dao.findById(newBook.id!!)
        println("Книга c названием: '${result!!.title}' добавлена\n")

        // Ищем конкретную запись
        println("***********************************************\n")
        println("Достаём конкретную книгу из базы данных\n")
        val expectedBook = foundAllBooks.random()
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
        foundByIdBook.title = "Даодецзин"
        foundByIdBook.cost = 200
        foundByIdBook.numberOfPage = 350
        dao.update(foundByIdBook)
        foundByIdBook = dao.findById(foundByIdBook.id!!)
        println("Книга ПОСЛЕ обновления:\n" +
                "Название: ${foundByIdBook!!.title}\n" +
                "Цена: ${foundByIdBook.cost}\n" +
                "Количество страниц: ${foundByIdBook.numberOfPage}\n")

        // Удаляем конкретную запись
        println("***********************************************\n")
        println("Удаление конкретной книги из базы данных\n")
        val booksInDb = dao.findAll()
        println("Книг в базе данных ДО: ${booksInDb.size}\n")
        val bookToRemove = booksInDb.random()
        println("Удаляем книгу с id: ${bookToRemove.id}\nНазвание: ${bookToRemove.title}\n")
        dao.remove(bookToRemove)
        println("Книг в базе данных ПОСЛЕ: ${dao.findAll()}\n")
        if (dao.findById(bookToRemove.id!!) == null) println("В базе данных нет книги с id ${bookToRemove.id}")
    }
}