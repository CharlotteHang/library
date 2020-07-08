package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.SearchBookController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// Search books UI
public class SearchBook extends VBox {
    private static final String FXML = "src/fmlFiles/SearchBook.fxml";
    private File fxmlFile = new File(FXML);
    private ReaderList readerlist;
    private BookList booklist;
    private Reader reader;
    private Librarian librarian;


    // REQUIRES: Exactly one of reader and librarian should be null. All the other parameters != null.
    // MODIFIES: this
    public SearchBook(Reader reader, Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.reader = reader;
        this.librarian = librarian;
        this.load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            SearchBookController controller = fxmlLoader.<SearchBookController>getController();
            controller.set(reader, librarian, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}