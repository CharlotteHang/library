package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.ViewBooksPageController;
import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// View Books UI
public class ViewBooksPage extends VBox {
    protected static final String FXML = "src/fmlFiles/ViewBooksPage.fxml";
    protected File fxmlFile = new File(FXML);
    protected Iterable<Book> books;
    protected String searchMethod;
    protected Reader reader;
    protected ReaderList readerlist;
    protected BookList booklist;
    protected String lastpage;
    protected Librarian librarian;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public ViewBooksPage(String searchMethod, String lastpage, Iterable<Book> books, Reader reader,
                         Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.reader = reader;
        this.booklist = booklist;
        this.searchMethod = searchMethod;
        this.readerlist = readerlist;
        this.books = books;
        this.lastpage = lastpage;
        this.librarian = librarian;
        this.load();
    }

    protected void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            ViewBooksPageController controller = fxmlLoader.<ViewBooksPageController>getController();
            controller.set(searchMethod, lastpage, books, reader, librarian, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
