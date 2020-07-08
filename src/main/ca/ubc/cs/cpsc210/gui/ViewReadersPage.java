package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.controller.ViewReadersPageController;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
// View readers UI

public class ViewReadersPage extends VBox {
    protected static final String FXML = "src/fmlFiles/ViewReadersPage.fxml";
    protected File fxmlFile = new File(FXML);

    protected ReaderList readerlist;
    protected BookList booklist;
    protected Librarian librarian;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public ViewReadersPage(Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.booklist = booklist;
        this.readerlist = readerlist;
        this.librarian = librarian;
        this.load();
    }

    protected void load() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlFile.toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            ViewReadersPageController controller = fxmlLoader.<ViewReadersPageController>getController();
            controller.set(librarian, readerlist, booklist);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
