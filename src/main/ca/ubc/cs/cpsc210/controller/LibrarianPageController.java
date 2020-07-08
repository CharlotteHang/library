package ca.ubc.cs.cpsc210.controller;


import ca.ubc.cs.cpsc210.gui.*;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LibrarianPageController {

    @FXML
    private JFXButton returnOrBorrow;
    @FXML
    private JFXButton addBook;
    @FXML
    private JFXButton readers;
    @FXML
    private JFXButton books;
    @FXML
    private Label label;

    private static ReaderList readerlist;
    private static BookList booklist;
    private static Librarian librarian;

    private String notification;


    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void set(Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.booklist = booklist;
        this.readerlist = readerlist;
        this.librarian = librarian;
        load();

    }

    // EFFECTS: show the greeting
    public void load() {
        Calendar calendar = GregorianCalendar.getInstance();
        Date borrowdate = calendar.getTime();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("YYYY-MM-dd-EE"); // the day of the week abbreviated
        String today = simpleDateformat.format(borrowdate);
        notification = "Hi " + librarian.getusername() + " (" + librarian.getposition() + ")"
                + "!\n" + today + " Have a nice day!";
        label.setText(notification);
    }

    // EFFECTS: turn to the UI page of SearchBook
    @FXML
    public void returnOrBorrowBook() {
        LibrarySystem.setScene(
                new SearchBook(null, librarian, readerlist, booklist));
    }

    // EFFECTS: turn to the UI page of AddBook
    @FXML
    public void addBook() {
        LibrarySystem.setScene(new AddBookPage(librarian, readerlist, booklist));
    }

    // EFFECTS: turn to the UI page of ViewReaders
    @FXML
    public void viewReaders() {
        LibrarySystem.setScene(new ViewReadersPage(librarian, readerlist, booklist));
    }

    // EFFECTS: turn to the UI page of ViewBooks
    @FXML
    public void viewBooks() {
        LibrarySystem.setScene(new AllBookListInLibrarianPage(librarian, readerlist, booklist, booklist));
    }

}
