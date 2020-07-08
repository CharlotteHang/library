package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.WelcomePageController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
// Welcome UI (the first page)

public class WelcomePage extends VBox {

    private static final String FXML = "src/fmlFiles/WelcomePage.fxml";
    private File fxmlFile = new File(FXML);
    private static LibrarianList librarianlist;
    private static ReaderList readerlist;
    private static BookList booklist;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public WelcomePage(LibrarianList librarianlist, ReaderList readerlist, BookList booklist) {
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.librarianlist = librarianlist;
        this.load();
    }


    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            WelcomePageController controller = fxmlLoader.<WelcomePageController>getController();
            controller.setLogIn(librarianlist, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
