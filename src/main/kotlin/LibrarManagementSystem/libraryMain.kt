package LibrarManagementSystem

import kotlinx.coroutines.runBlocking

fun main(){

    val library=Library()

    val book1 = Book("ISBN1", "Book1", "Author1", 2020, true)
    val book2 = Book("ISBN2", "Book2", "Author2", 2015, true)
    val book3 = Book("ISBN3", "Book3", "Author1", 2018, false)
    val book4 = Book("ISBN4", "Book4", "Author3", 2012, true)

    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)
    library.addBook(book4)

    var loop:Int=6
    while (loop!=0){
        println("0->Exit 1->addBook 2->removeBookByISBN 3->searchBooksByAuthor 4->searchAvailableBooksByTitle 5->borrowBook 6->returnBook 7->printAllBooks 8->getOldestBook")
        loop= readln().toInt()
        when(loop){
            1->{
                println("Enter the ISBN of the book")
                val isbn: String = readln()
                println("Enter the Title of the book")
                val title: String = readln()
                println("Enter the Author name")
                val author: String = readln()
                println("Enter the publish year")
                val year: Int = readln().toInt()
                val available = true
                library.addBook(Book(isbn,title,author,year,available))
                println("Added Successfully")
            }
            2->{
                println("Enter the ISBN of the book you need to remove")
                val isbn: String = readln()
                library.removeBookByISBN(isbn)
                println("Removed Successfully")
            }
            3->{
                println("Enter the Author of the book you need to Search")
                val author: String= readln()
                val listOfBooksByAuthor:List<Book> = library.searchBooksByAuthor(library.books,author)
                listOfBooksByAuthor.forEach{ println(it) }
            }

            4->{
                println("Enter the Title of the book you need to Search")
                val title : String = readln()
                val listOfBooksByTitle:List<Book> = library.searchAvailableBooksByTitle(title)
                listOfBooksByTitle.forEach{ println(it) }
            }

            5->{
                println("Enter the ISBN of the book you need to borrow")
                val isbn : String = readln()
                library.borrowBook(isbn)
            }

            6->{
                println("Enter the ISBN of th book you need to return")
                val isbn : String = readln()
                library.returnBook(isbn)
            }

            7-> {
                println("List of all books")
                library.printAllBooks()
            }

            8-> {
                println("oldest book")
                val book: Book? = library.getOldestBook(library.books)
                println(book)
            }

        }

    }

    runBlocking {
        println("Processing books asynchronously:")
        library.processBooks(library.books)
    }


}