package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;

// ViewBooks UI of a librarian
public class AllBookListInLibrarianPage extends ViewBooksPage {
    public AllBookListInLibrarianPage(
            Librarian librarian, ReaderList readerlist, Iterable<Book> books, BookList booklist) {
        super(null, "All books in librarian page",
                books, null, librarian, readerlist, booklist);
    }

}
