package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.gui.*;
import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ViewBooksPageController {

    @FXML
    protected VBox books;
    @FXML
    protected Label label;
    @FXML
    protected JFXButton back;
    @FXML
    protected ScrollPane scrollPane;

    protected static Reader reader;
    protected static ReaderList readerlist;
    protected static BookList booklist;
    protected Iterable<Book> machtedbooks;
    protected String lastpage;
    protected String searchmethod;
    protected Librarian librarian;

    // REQUIRES: Exactly one of reader and librarian should be null. All the other parameters != null.
    // MODIFIES: this
    // EFFECTS: set the fields
    public void set(String searchmethod, String lastpage, Iterable<Book> books,
                    Reader reader, Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.reader = reader;
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.librarian = librarian;
        this.machtedbooks = books;
        this.lastpage = lastpage;
        this.searchmethod = searchmethod;
        setBooks();

    }

    // EFFECTS: show all the books
    public void setBooks() {
        String s = setLabel();
        label.setText(s);
        VBox root = new VBox();
        for (Book b : machtedbooks) {
            Button bookview = new Button("Book name: " + b.getInfo().getTitle() + "\n ISBN number: " + b.getId()
                    + "\n Author: " + b.getInfo().getAuthor() + "\n Status: " + b.getStatus());
            bookview.setMinWidth(600);
            bookview.setAlignment(Pos.CENTER_LEFT);
            bookview.setOnAction(e -> LibrarySystem.setScene((new BookPage(
                    searchmethod, lastpage, b, machtedbooks, reader, librarian, readerlist, booklist))));
            this.books.getChildren().addAll(bookview);
            root.getChildren().addAll(bookview);
            scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        }
        scrollPane.setContent(root);
    }

    // EFFECTS: Set the label of this page according to what the kind of these books is
    private String setLabel() {
        String s;
        if (searchmethod == null) {
            s = (lastpage == "Borrowed books") ? "Borrowed books" : "All the books in library";
        } else {
            s = searchmethod;
        }
        return s;
    }

    // EFFECTS: Return to the last UI page
    @FXML
    public void backPage() {
        if (lastpage.equals("SearchBooks")) {
            LibrarySystem.setScene(new SearchBook(reader, librarian, readerlist, booklist));
        } else if (lastpage.equals("Borrowed books") || (lastpage.equals("Subscribed books"))) {
            LibrarySystem.setScene(new ReaderPage(reader, readerlist, booklist));
        } else if (lastpage.equals("All books in librarian page")) {
            LibrarySystem.setScene(new LibrarianPage(readerlist, librarian, booklist));
        } else if (lastpage.equals("All books in reader page")) {
            LibrarySystem.setScene(new ReaderPage(reader, readerlist, booklist));
        }


    }

}
