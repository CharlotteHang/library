package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.AddBookPageController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;

// AddBook UI
public class AddBookPage extends VBox {
    private static final String FXML = "src/fmlFiles/AddBookPage.fxml";
    private File fxmlFile = new File(FXML);

    private ReaderList readerlist;
    private BookList booklist;
    private Librarian librarian;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public AddBookPage(Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.booklist = booklist;
        this.readerlist = readerlist;
        this.librarian = librarian;
        this.load();
    }


    private void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            AddBookPageController controller = fxmlLoader.<AddBookPageController>getController();
            controller.set(librarian, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}