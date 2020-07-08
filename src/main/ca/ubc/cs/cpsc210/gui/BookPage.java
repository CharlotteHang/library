package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.BookPageController;
import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// BookPage UI
public class BookPage extends VBox {
    private static final String FXML = "src/fmlFiles/BookPage.fxml";
    private File fxmlFile = new File(FXML);
    private Book book;

    private Reader reader;
    private ReaderList readerlist;
    private BookList booklist;
    private Iterable<Book> machtedbooks;
    private String lastpage;
    private String searchmethod;
    private Librarian librarian;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public BookPage(String searchmethod, String lastpage, Book book, Iterable<Book> machtedbooks,
                    Reader reader, Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.lastpage = lastpage;
        this.reader = reader;
        this.booklist = booklist;
        this.readerlist = readerlist;
        this.book = book;
        this.machtedbooks = machtedbooks;
        this.searchmethod = searchmethod;
        this.librarian = librarian;
        this.load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            BookPageController controller = fxmlLoader.<BookPageController>getController();
            controller.set(searchmethod, lastpage, book, machtedbooks, reader, librarian, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
