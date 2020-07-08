package ca.ubc.cs.cpsc210.controller;

import ca.ubc.cs.cpsc210.gui.AlertBox;
import ca.ubc.cs.cpsc210.gui.LibrarianPage;
import ca.ubc.cs.cpsc210.gui.LibrarySystem;
import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookInfo;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public class AddBookPageController {

    @FXML
    private JFXButton addBook;
    @FXML
    private JFXButton back;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField isbn;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField genre;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField publishdate;
    @FXML
    private JFXTextArea description;

    private static ReaderList readerlist;
    private static BookList booklist;
    private Librarian librarian;
    private Book book;


    // MODIFIES: this
    // EFFECTS: set the fields
    public void set(Librarian librarian, ReaderList readerlist, BookList booklist) {
        this.readerlist = readerlist;
        this.booklist = booklist;
        this.librarian = librarian;
    }

    // EFFECTS: Return to the last UI page
    @FXML
    public void backPage() {
        LibrarySystem.setScene(new LibrarianPage(readerlist, librarian, booklist));

    }

    // EFFECTS: try to create a new book and add the book to the BookList,
    // if the book is not added successfully, an alert window will be shown with the corresponding message;
    // if it is added successfully, an alert window will be shown with which book has been added.
    // After adding the book, UI will return to the Librarian Page.
    @FXML
    public void addBook() {
        String authorInfo = author.getText();
        String isbnInfo = isbn.getText();
        String titleInfo = title.getText();
        String genreInfo = genre.getText();
        String priceInfo = price.getText();
        String publishDateInfo = publishdate.getText();
        String descriptionInfo = description.getText();
        try {
            BookInfo bookinfo = new BookInfo(authorInfo, titleInfo,
                    genreInfo, priceInfo, publishDateInfo, descriptionInfo);
            book = new Book(isbnInfo, bookinfo);
            booklist.addBook(book);
        } catch (Exception e) {
            AlertBox.displayAlert("fail to add the book", e.getMessage());
            return;
        }

        AlertBox.displayAlert("Success to add the book", (titleInfo + " is added"));
        LibrarySystem.setScene(new LibrarianPage(readerlist, librarian, booklist));
    }


}
