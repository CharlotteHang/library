package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.ReaderLogInController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// ReaderLogin UI
public class ReaderLogIn extends VBox {
    private static final String FXML = "src/fmlFiles/ReaderLogIn.fxml";
    private File fxmlFile = new File(FXML);
    //private LibrarianList librarianlist;
    private ReaderList readerlist;
    private BookList booklist;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public ReaderLogIn(ReaderList readerlist, BookList booklist) {
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.load();
    }


    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            ReaderLogInController controller = fxmlLoader.<ReaderLogInController>getController();
            controller.setLogIn(readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }



}
