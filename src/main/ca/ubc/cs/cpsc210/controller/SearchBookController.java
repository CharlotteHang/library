package ca.ubc.cs.cpsc210.controller;


import ca.ubc.cs.cpsc210.gui.*;
import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.List;

public class SearchBookController {

    @FXML
    private JFXButton searchByID;
    @FXML
    private JFXTextField bookid;
    @FXML
    private JFXButton searchByName;
    @FXML
    private JFXTextField bookname;
    @FXML
    private JFXButton searchByKeyword;
    @FXML
    private JFXTextField keyword;
    @FXML
    private JFXButton back;

    private static ReaderList readerlist;
    private static BookList booklist;
    private static Reader reader;
    private static Librarian librarian;


    // REQUIRES: Exactly one of reader and librarian should be null. All the other parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void set(Reader reader, Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.booklist = booklist;
        this.readerlist = readerlist;
        this.reader = reader;
        this.librarian = librarian;
    }


    // EFFECTS: if the ID number provided is invalid, show the alert window
    // if the ID number provided is valid, turn to the UI page of ViewBooks
    @FXML
    public void searchByID() {
        if (booklist.searchBookid(bookid.getText()) == null) {
            AlertBox.displayAlert("Invalid", "No book found");
        } else {
            Book book = booklist.searchBookid(bookid.getText());
            List<Book> books = new ArrayList<>();
            books.add(book);
            LibrarySystem.setScene(new ViewBooksPage("Results of search by ISBN number",
                    "SearchBooks", books, reader, librarian, readerlist, booklist));
        }
    }

    // EFFECTS: if the name provided is invalid, show the alert window
    // if the name provided is valid, turn to the UI page of ViewBooks
    @FXML
    public void searchByName() {
        if (booklist.extactSearchByBookname(bookname.getText()).size() == 0) {
            AlertBox.displayAlert("Invalid", "No book found");
        } else {
            List<Book> books = booklist.extactSearchByBookname(bookname.getText());
            LibrarySystem.setScene(new ViewBooksPage("Results of search by name",
                    "SearchBooks", books, reader, librarian, readerlist, booklist));
        }
    }


    // EFFECTS: if the keyword provided is invalid, show the alert window
    // if the keyword provided is valid, turn to the UI page of ViewBooks
    @FXML
    public void searchByKeyWord() {
        if (booklist.fuzzySearchByBookname(keyword.getText()).size() == 0) {
            AlertBox.displayAlert("Invalid", "No book found");
        } else {
            List<Book> books = booklist.fuzzySearchByBookname(keyword
                    .getText());
            System.out.println(books);
            LibrarySystem.setScene(new ViewBooksPage("Results of search by keyword",
                    "SearchBooks", books, reader, librarian, readerlist, booklist));
        }
    }

    // EFFECTS: Return to the last UI page
    @FXML
    public void backPage() {
        if (reader == null) {
            LibrarySystem.setScene(new LibrarianPage(readerlist, librarian, booklist));
        } else {
            LibrarySystem.setScene(new ReaderPage(reader, readerlist, booklist));
        }
    }


}
