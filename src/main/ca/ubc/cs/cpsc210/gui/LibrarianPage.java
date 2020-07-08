package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.LibrarianPageController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// LibrarianPage UI (main menu)
public class LibrarianPage extends VBox {
    private static final String FXML = "src/fmlFiles/LibrarianPage.fxml";
    private File fxmlFile = new File(FXML);
    //private LibrarianList librarianlist;
    private ReaderList readerlist;
    private BookList booklist;
    private Librarian librarian;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public LibrarianPage(ReaderList readerlist, Librarian librarian, BookList booklist) {
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.librarian = librarian;

        this.load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            LibrarianPageController controller = fxmlLoader.<LibrarianPageController>getController();
            controller.set(librarian, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
