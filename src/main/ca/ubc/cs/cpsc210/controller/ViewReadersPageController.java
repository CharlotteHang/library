package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.gui.LibrarianPage;
import ca.ubc.cs.cpsc210.gui.LibrarySystem;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ViewReadersPageController {
    @FXML
    protected VBox readers;
    @FXML
    protected Label label;
    @FXML
    protected JFXButton back;
    @FXML
    protected ScrollPane scrollPane;


    protected static ReaderList readerlist;
    protected static BookList booklist;
    protected Librarian librarian;


    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void set(Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.librarian = librarian;
        setReaders();

    }

    // EFFECTS: show all the readers
    public void setReaders() {
        label.setText("All the readers of our library");
        VBox root = new VBox();
        for (Reader r : readerlist) {
            String s;
            s = "Reader username: " + r.getusername() + "\nBorrowed books: ";
            for (int i = 0; i < r.getBorrowedBooksByName().size(); i++) {
                String bookname = r.getBorrowedBooksByName().get(i);
                s = s + "\n" + (i + 1) + ": " + bookname;
            }
            Label readerview = new Label(s);
            readerview.setStyle("-fx-border-color: blue;");
            readerview.setMinWidth(600);
//            bookview.setOnAction(e -> LibrarySystem.setScene((new BookPage(
//                    searchmethod, lastpage, b, machtedbooks, reader, librarian, readerlist, booklist))));
            this.readers.getChildren().addAll(readerview);
            root.getChildren().addAll(readerview);
        }
        scrollPane.setContent(root);
    }

    // EFFECTS: Return to the last UI page
    @FXML
    public void backPage() {
        LibrarySystem.setScene(new LibrarianPage(readerlist, librarian, booklist));



    }

}
