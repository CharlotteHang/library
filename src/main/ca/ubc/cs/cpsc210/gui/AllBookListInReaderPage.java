package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;

// ViewBooks UI of a reader

public class AllBookListInReaderPage extends ViewBooksPage {
    public AllBookListInReaderPage(Reader reader, ReaderList readerlist, Iterable<Book> books, BookList booklist) {
        super(null, "All books in reader page", books, reader, null, readerlist, booklist);
    }

}
