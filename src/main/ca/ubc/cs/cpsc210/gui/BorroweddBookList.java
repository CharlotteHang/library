package ca.ubc.cs.cpsc210.gui;


import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;

 // ViewBooks UI of a reader (show the borrowed books)
public class BorroweddBookList extends ViewBooksPage {
    public BorroweddBookList(Iterable<Book> books,
                                     Reader reader, ReaderList readerlist, BookList booklist) {
        super(null, "Borrowed books", books, reader, null, readerlist, booklist);
    }

}
