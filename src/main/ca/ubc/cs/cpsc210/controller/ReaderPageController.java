package ca.ubc.cs.cpsc210.controller;


import ca.ubc.cs.cpsc210.gui.*;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class ReaderPageController {

    @FXML
    private JFXButton returnOrBorrow;
    @FXML
    private JFXButton borrowedBooks;
    @FXML
    private JFXButton interestedBooks;
    @FXML
    private JFXButton allBooks;
    @FXML
    private JFXButton back;
    @FXML
    private Label label;

    private static ReaderList readerlist;
    private static BookList booklist;
    private static Reader reader;
    private String notification;


    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void set(Reader reader, ReaderList readerlist, BookList booklist) {
        this.booklist = booklist;
        this.readerlist = readerlist;
        this.reader = reader;
        load();

    }

    // EFFECTS: show the greeting
    public void load() {
        notification = "Hi " + reader.getusername() + "!"
                + "\n\n Latest nofitications:\n" + reader.notificationsToString();
        label.setText(notification);
    }


    // EFFECTS: turn to the UI page of SearchBook
    @FXML
    public void returnOrBorrowBook() {
        LibrarySystem.setScene(
                new SearchBook(reader, null, readerlist, booklist));
    }


    // EFFECTS: turn to the UI page of ViewBooks(borrowed books)
    @FXML
    public void showBorrowedBooks() {
        LibrarySystem.setScene(new BorroweddBookList(
                (reader.getBorrowedBooks(booklist)), reader, readerlist, booklist));
    }

    // EFFECTS: turn to the UI page of ViewBooks (all books)
    @FXML
    public void showAllBooks() {
        LibrarySystem.setScene(new AllBookListInReaderPage(
                reader, readerlist, booklist, booklist));
    }

    // EFFECTS: turn to the UI page of ViewBooks(subscribed books)
    @FXML
    public void showInterestedBooks() {
        LibrarySystem.setScene(new SubscribedBookList(reader.getSubscribeBooks(), reader, readerlist, booklist));
    }

    // EFFECTS: return to the readerLogin page
    @FXML
    public void backPage() {
        LibrarySystem.setScene(new ReaderLogIn(readerlist, booklist));
    }


}
