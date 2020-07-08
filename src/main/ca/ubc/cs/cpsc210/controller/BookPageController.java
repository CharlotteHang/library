package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.gui.*;
import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class BookPageController {


    @FXML
    private ScrollPane pane;
    @FXML
    private JFXButton returnbook;
    @FXML
    private JFXButton borrowbook;
    @FXML
    private JFXButton subscribebook;
    @FXML
    private JFXButton unscribebook;
    @FXML
    private JFXButton back;

    private static Book book;
    private static Reader reader;
    private static ReaderList readerlist;
    private static BookList booklist;
    private static Iterable<Book> machtedbooks;
    private static String lastpage;
    private String searchmethod;
    private Librarian librarian;

    // REQUIRES: Exactly one of reader and librarian should be null. All the other parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields and load the buttons
    public void set(String searchmethod, String lastpage, Book book, Iterable<Book> machtedbooks,
                    Reader reader, Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.reader = reader;
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.book = book;
        this.machtedbooks = machtedbooks;
        this.lastpage = lastpage;
        this.searchmethod = searchmethod;
        this.librarian = librarian;
        load();

    }

    // EFFECTS: show the detailed information of this book
    // set the borrow, return, unsubscribe and subscribe buttons
    public void load() {
        Label label = new Label((book.toString()));
        label.setWrapText(true);
        label.setPrefWidth(600);
        label.setTextAlignment(TextAlignment.JUSTIFY);

        VBox root = new VBox();
        root.getChildren().addAll(label);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setContent(root);

        canBorrow();
        canReturn();
        canUnsubscribe();
        canSubscribe();
    }


    // EFFECTS: return this book. Show the alert window if the books is returned.
    @FXML
    public void returnBook() {
        if (canReturn()) {
            String username = book.getStatus().getUsername();
            System.out.println(book.getId());
            System.out.println(username);
            System.out.println(readerlist);
            booklist.returnBook(book.getId(), readerlist);

            if (reader == null) {
                AlertBox.displayAlert("Return Book",
                        ("you have successfully help " + username + " return the book"));
            } else {
                AlertBox.displayAlert("Return Book", ("you have successfully return the book"));
            }
            LibrarySystem.setScene(new BookPage(
                    searchmethod, lastpage, book, machtedbooks, reader, librarian, readerlist, booklist));
        }

    }


    // EFFECTS: borrow this book. Show the alert window of borrowing a book
    @FXML
    public void borrowBook() {
        if (canBorrow()) {

            new BorrowBox(reader, readerlist, booklist, book);
            LibrarySystem.setScene(new BookPage(
                    searchmethod, lastpage, book, machtedbooks, reader, librarian, readerlist, booklist));
        }

    }

    // EFFECTS: subscribe this book (also add this reader to the book's Observers)
    @FXML
    public void subscribeBook() {
        if (canSubscribe()) {
            book.subscribe(reader);
            AlertBox.displayAlert("Subscribe Book", ("you have successfully subscribe the book"));
            LibrarySystem.setScene(new BookPage(
                    searchmethod, lastpage, book, machtedbooks, reader, librarian, readerlist, booklist));
            for (String s : book.getFollowers()) {
                System.out.println(s);
            }
            for (Book b : reader.getSubscribeBooks()) {
                System.out.println(b);
            }

        }
    }


    // EFFECTS: unsubscribe this book (also remove this reader from the book's Observers)
    @FXML
    public void unscribeBook() {
        if (canUnsubscribe()) {
            book.unsubscribe(reader);
            AlertBox.displayAlert("Unsubscribe Book", ("you have successfully unsubscribe the book"));
            LibrarySystem.setScene(new BookPage(
                    searchmethod, lastpage, book, machtedbooks, reader, librarian, readerlist, booklist));
        }
    }

    // EFFECTS: Return to the last UI page
    @FXML
    public void backPage() {
        if (lastpage == "Borrowed books") {
            LibrarySystem.setScene(new BorroweddBookList(reader.getBorrowedBooks(booklist),
                    reader, readerlist, booklist));
        } else if (lastpage == "SearchBooks") {
            LibrarySystem.setScene(new ViewBooksPage(
                    searchmethod, lastpage, machtedbooks, reader, librarian, readerlist, booklist));
        } else if (lastpage == "Subscribed books") {
            LibrarySystem.setScene(new SubscribedBookList(
                    machtedbooks, reader, readerlist, booklist));
        } else if (lastpage == "All books in librarian page") {
            LibrarySystem.setScene(new AllBookListInLibrarianPage(
                    librarian, readerlist, machtedbooks, booklist));
        } else if (lastpage == "All books in reader page") {
            LibrarySystem.setScene(new AllBookListInReaderPage(
                    reader, readerlist, machtedbooks, booklist));

        }
    }


    // EFFECTS: show the borrow button and return true if the book can be borrowed by this person
    public boolean canBorrow() {
        if (!book.getStatus().getBorrowed() && ((reader != null && reader.canborrow()))) {
            borrowbook.setText("Borrow");
            return true;
        } else {
            borrowbook.setText("");
            return false;
        }
    }

    // EFFECTS: show the return button and return true if this person can
    // return the book or this person is a librarian(a librarian can help return a book)
    public boolean canReturn() {
        if (book.getStatus().getBorrowed() && (reader == null || (
                reader != null && reader.getBorrowedBooksByName().contains(book.getId())))) {
            returnbook.setText("Return");
            return true;
        } else {
            returnbook.setText("");
            return false;
        }
    }

    // EFFECTS: show the subscribe button and return true if the person can subscribe this book
    public boolean canSubscribe() {
        if (reader != null && !reader.getSubscribeBooks().contains(book)) {
            subscribebook.setText("Subscribe");
            return true;
        } else {
            subscribebook.setText("");
            return false;
        }

    }

    // EFFECTS: show the unsubscribe button and return true if the person has already subscribed this book
    public boolean canUnsubscribe() {

        if (reader != null && reader.getSubscribeBooks().contains(book)) {
            unscribebook.setText("Unsubscribe");
            return true;
        } else {
            unscribebook.setText("");
            return false;
        }
    }
}
