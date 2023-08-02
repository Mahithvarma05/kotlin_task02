package LibrarManagementSystem

import kotlinx.coroutines.delay

class Library {

    val books: MutableList<Book> = mutableListOf()

    val getAvailableBooksByAuthor: (List<Book>, String) -> List<Book> = { books, author ->
        books.filter { it.author == author && it.available }
    }

    fun addBook(book: Book) {
        books.add(book)
    }
    fun removeBookByISBN(isbn: String) {
        books.removeIf { it.isbn == isbn }
    }
    fun searchBooksByAuthor(list: List<Book>, author: String): List<Book> {
        return getAvailableBooksByAuthor(list, author)
    }
    fun searchAvailableBooksByTitle(title: String): List<Book> {
        return books.filter { it.title == title && it.available }
    }
    fun borrowBook(isbn: String) {
        val book = books.find { it.isbn == isbn && it.available }
        book?.available = false
    }
    fun returnBook(isbn: String) {
        val book = books.find { it.isbn == isbn }
        book?.available = true
    }
    fun printAllBooks() {
        books.forEach { println(it) }
    }
    fun getOldestBook(books: List<Book>): Book? {
        return books.minByOrNull { it.year }
    }
    suspend fun processBooks(books: List<Book>) {
        books.forEach { book ->
            delay(1000)
            println("Processed book: $book")
        }
    }

}
