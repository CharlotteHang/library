package ca.ubc.cs.cpsc210.gui;

import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import ca.ubc.cs.cpsc210.parsers.ParseFromFile;
import ca.ubc.cs.cpsc210.persistence.Persistence;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Library System Demo
public class LibrarySystem extends Application {

    public static final String TITLE = "Library System";
    public static final String BOOKFILE = "src/JSON files/BooksInLibrary";
    public static final String READERFILE = "src/JSON files/Readers";
    public static final String LIBRARIANFILE = "src/JSON files/Librarians";
    public static final double WIDTH = 600;
    public static final double HEIGHT = 800;
    private static Stage primaryStage;
    private static LibrarianList librarianlist;
    private static BookList booklist;
    private static ReaderList readerlist;


    public static void main(String[] args) {
        launch(args);
    }

    // EFFECTS: Start the application
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadLibrary();
        setPrimaryStage(primaryStage);
        setScene(new WelcomePage(librarianlist, readerlist, booklist));
    }

    // REQUIRES: stage != null
    // MODIFIES: this
    // EFFECTS: sets the primary stage
    private static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    // REQUIRES: root != null
    public static void setScene(Parent root) {
        try {
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            primaryStage.setTitle(TITLE);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Failed to load the LogIn Scene!");
        }

    }

    // MODIFIES: this
    // EFFECTS: parse the JSON file
    public void loadLibrary() {
        readerlist = ParseFromFile.readerlistParser(READERFILE);
        System.out.println(readerlist);
        booklist = ParseFromFile.booklistParser(BOOKFILE, readerlist);
        librarianlist = ParseFromFile.librarianListParser(LIBRARIANFILE);
    }


    // EFFECTS: save the changes to JSON file before quitting the application
    @Override
    public void stop() {
        Persistence p = new Persistence(booklist, readerlist, librarianlist);
        p.librarylistPersistence(LIBRARIANFILE);
        p.readerlistPersistence(READERFILE);
        p.booklistPersistence(BOOKFILE);

        try {
            super.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
