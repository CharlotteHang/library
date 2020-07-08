package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.gui.AlertBox;
import ca.ubc.cs.cpsc210.gui.LibrarianPage;
import ca.ubc.cs.cpsc210.gui.LibrarySystem;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class LibrarianLoginController {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXButton logInButton;


    private static LibrarianList librarianlist;
    private static ReaderList readerlist;
    private static BookList booklist;
    private String userNameString;
    private String passWordString;


    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void setLogIn(LibrarianList librarianlist, BookList booklist, ReaderList readerlist) {
        this.librarianlist = librarianlist;
        this.readerlist = readerlist;
        this.booklist = booklist;
    }


    // EFFECTS: get the information of username and password
    @FXML
    public void pressLogIn() {
        userNameString = username.getText();
        passWordString = password.getText();
        librarianLogIn(userNameString, passWordString);
    }

    // EFFECTS: show the alert window if username and password is not valid
    // turn to the LibrarianPage if  username and password is valid
    public static void librarianLogIn(String userNameString, String passWordString) {
        if (!(librarianlist.logIn(userNameString, passWordString) == null)) {
            Librarian librarian = librarianlist.logIn(userNameString, passWordString);
            LibrarySystem.setScene(new LibrarianPage(readerlist, librarian, booklist));
            System.out.println("librarian log in");

        } else {
            AlertBox.displayAlert("Fail to Login", "Wrong username or password");
            System.out.println("fail to log in");

        }

    }


}
