package ca.ubc.cs.cpsc210.gui;


import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;

// ViewBooks UI (subscribed books)
public class SubscribedBookList extends ViewBooksPage {
    public SubscribedBookList(Iterable<Book> books,
                              Reader reader, ReaderList readerlist, BookList booklist) {
        super(null, "Subscribed books", books, reader, null, readerlist, booklist);
    }


}
