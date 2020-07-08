package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.ReaderPageController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
// Reader UI (main menu)

public class ReaderPage extends VBox {
    private static final String FXML = "src/fmlFiles/ReaderPage.fxml";
    private File fxmlFile = new File(FXML);
    //private LibrarianList librarianlist;
    private ReaderList readerlist;
    private BookList booklist;
    private Reader reader;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public ReaderPage(Reader reader, ReaderList readerlist, BookList booklist) {
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.reader = reader;
        this.load();
    }

    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            ReaderPageController controller = fxmlLoader.<ReaderPageController>getController();
            controller.set(reader, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
