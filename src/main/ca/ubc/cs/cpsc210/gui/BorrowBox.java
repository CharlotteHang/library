package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.exception.InvalidNumberException;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

// the alert window for borrowing a book. the reader should enter for how long they borrow this book
public class BorrowBox {
    private Reader reader;
    private BookList booklist;
    private Book book;
    private ReaderList readerlist;
    private Stage alertWindow;

    // REQUIRES: All the parameters != null.
    // MODIFIES: this
    public BorrowBox(Reader reader, ReaderList readerlist, BookList booklist, Book book) {
        this.reader = reader;
        this.booklist = booklist;
        this.book = book;
        this.readerlist = readerlist;
        displayAlert("Borrow Book");
    }

    // EFFECTS: display the alert window with given string as the window title
    public void displayAlert(String title) {
        alertWindow = new Stage();

        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(200);

        Label label = new Label();
        label.setText("How long (days) will this book be borrowed (type in a number between 1 to 365)?");
        JFXTextField borrowday = new JFXTextField();
        Button borrow = new Button("Borrow");
        Button close = new Button("Cancel");
        borrow.setOnAction(e -> displayBorrow(borrowday.getText()));
        close.setOnAction(e -> alertWindow.close());

        VBox vbox = new VBox(20);

        vbox.getChildren().addAll(label, borrowday, borrow, close);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }

    // EFFECTS: borrow this book. Show the alert window if the books is successfully borrowed.
    // Show another alert window if the books cannot be borrowed.
    public void displayBorrow(String s) {
        int i = 0;

        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            AlertBox.displayAlert("Fail to Borrow", "Please enter a valid number");
            return;
        }

        try {
            booklist.borrowBook(book.getId(), i, reader.getusername(), readerlist);
        } catch (InvalidNumberException e) {
            String exceptionMessage = e.getMessage();
            AlertBox.displayAlert("Fail to Borrow", exceptionMessage);
            return;
        }
        AlertBox.displayAlert("Borrow successfully", "Borrow the book" + book.getInfo().getTitle());
        alertWindow.close();
    }
}
