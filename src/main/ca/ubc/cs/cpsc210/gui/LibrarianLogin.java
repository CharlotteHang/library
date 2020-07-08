package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.LibrarianLoginController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// LibrarianLogin UI of a librarian
public class LibrarianLogin extends VBox {

    private static final String FXML = "src/fmlFiles/LibrarianLogIn.fxml";
    private File fxmlFile = new File(FXML);
    private LibrarianList librarianlist;
    private ReaderList readerlist;
    private BookList booklist;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public LibrarianLogin(LibrarianList librarianlist, BookList booklist, ReaderList readerlist) {
        this.librarianlist = librarianlist;
        this.booklist = booklist;
        this.readerlist = readerlist;

        this.load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            LibrarianLoginController controller = fxmlLoader.<LibrarianLoginController>getController();
            controller.setLogIn(librarianlist, booklist, readerlist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


}


