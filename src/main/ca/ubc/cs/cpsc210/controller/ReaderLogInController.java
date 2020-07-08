package ca.ubc.cs.cpsc210.controller;


import ca.ubc.cs.cpsc210.gui.AlertBox;
import ca.ubc.cs.cpsc210.gui.LibrarySystem;
import ca.ubc.cs.cpsc210.gui.ReaderPage;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class ReaderLogInController {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXButton logInButton;
    @FXML
    private JFXButton signUpButton;



    private static ReaderList readerlist;
    private static BookList booklist;
    private String userNameString;
    private String passWordString;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void setLogIn(ReaderList readerlist, BookList booklist) {
        this.booklist = booklist;
        this.readerlist = readerlist;
    }


    // EFFECTS: get the information of username and password
    @FXML
    public void pressLogIn() {
        userNameString = username.getText();
        passWordString = password.getText();
        readerLogIn(userNameString, passWordString);
    }

    // EFFECTS: show the alert window if username and password is not valid
    public static void readerLogIn(String userNameString, String passWordString) {
        System.out.println(readerlist.getReaderList().size());
        if (!(readerlist.logIn(userNameString, passWordString) == null)) {
            Reader reader = readerlist.logIn(userNameString, passWordString);
            LibrarySystem.setScene(new ReaderPage(reader, readerlist, booklist));
            System.out.println("readr log in");

        } else {
            AlertBox.displayAlert("Fail to Login", "Wrong username or password");
            System.out.println("fail to log in");

        }

    }

    // EFFECTS: get the information of username and password
    // and add this new reader to the readerlist if the username is new
    // show the alert window if the username is invalid
    // turn to the ReaderPage if  username and password is valid
    @FXML
    public void pressSignup() {
        userNameString = username.getText();
        System.out.println(userNameString);
        passWordString = password.getText();
        if (readerlist.containReader(userNameString)) {
            AlertBox.displayAlert("fail to sign in", "the account already exists");
        } else {
            readerlist.addReader(new Reader(userNameString, passWordString));
            System.out.println(readerlist);
            AlertBox.displayAlert("successfully sign in", "You have successfully create a new account");
        }
    }



}



