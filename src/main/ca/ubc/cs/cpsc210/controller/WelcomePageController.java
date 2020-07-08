package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.gui.LibrarianLogin;
import ca.ubc.cs.cpsc210.gui.LibrarySystem;
import ca.ubc.cs.cpsc210.gui.ReaderLogIn;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;

public class WelcomePageController {
    @FXML
    private JFXButton chooseReader;
    @FXML
    private JFXButton chooseLibrarian;

    private static LibrarianList librarianlist;
    private static ReaderList readerlist;
    private static BookList booklist;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void setLogIn(LibrarianList librarianlist, ReaderList readerlist, BookList booklist) {
        this.librarianlist = librarianlist;
        this.readerlist = readerlist;
        this.booklist = booklist;
    }

    // EFFECTS: turn to the UI page of ReaderPage
    @FXML
    public void readerPage() {
        LibrarySystem.setScene(new ReaderLogIn(readerlist, booklist));
    }

    // EFFECTS: turn to the UI page of LibrarianPage
    @FXML
    public void librarianPage() {
        LibrarySystem.setScene(new LibrarianLogin(librarianlist, booklist, readerlist));
    }
}
