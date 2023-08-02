package LibrarManagementSystem

data class Book(
    var isbn: String,
    var title: String,
    var author: String,
    var year: Int,
    var available: Boolean
)
