package ca.ubc.cs.cpsc210.model.reader;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.InvalidBorrowBookException;
import ca.ubc.cs.cpsc210.model.exception.InvalidReturnBookException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;

import java.util.*;

public class Reader implements Observer {
    protected String username;
    protected String password;
    private List<String> bookIds; // a list of ids of the books borrowed by this Reader
    private List<String> notifications = new ArrayList<>();
    private List<Book> subscribebooks = new ArrayList<>();

    private static final int MAX_Notifications = 5;
    public static final int maxBookNumber = 5;


    // MODIFIES: this
    // EFFECTS: creates a Reader account with the given username and password
    // this constructor is used when a new account is created
    public Reader(String username, String password) {
        if (username == null || username == "") {
            throw new EmptyStringException("please provide the username ");
        } else if (password == null || password == "") {
            throw new EmptyStringException("please provide the password");
        }
        this.username = username;
        this.password = password;
        bookIds = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: creates a Reader account
    // this constructor is used when parsing data
    public Reader(String username, String password, List<String> bookIds) {
        if (username == null || username == "") {
            throw new EmptyStringException("please provide the username ");
        } else if (password == null || password == "") {
            throw new EmptyStringException("please provide the password");
        } else if (bookIds == null) {
            throw new NullArgumentException("please provide the password");
        }
        this.username = username;
        this.password = password;
        this.bookIds = bookIds;
        // parse the data from the JSON file containing readers' information
    }


    // EFFECTS: returns the username of reader
    public String getusername() {
        return username;
    }

    // EFFECTS: returns the password of reader
    public String getpassowrd() {
        return password;
    }


    // EFFECTS: return the Ids of the list of borrowed books
    public List<String> getBorrowedBooksByName() {
        return bookIds;
    }

    // EFFECTS: return the books that borrowed by this reader
    public  List<Book> getBorrowedBooks(BookList booklist) {
        List<Book> borrowedbooks = new ArrayList<>();
        for (String s : bookIds) {
            borrowedbooks.add(booklist.searchBookid(s));
        }
        return borrowedbooks;
    }

    // EFFECTS: return true if the booked has been borrowed by this reader
    public boolean isBorrowed(String bookid) {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException("please provide the book");
        }
        for (String id : bookIds) {
            if (bookid.equals(id)) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: borrow the book, add the book to the reader's list of books borrowed
    public void borrowBook(String bookid) {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException(("please provide the the book he/she want to borrow"));
        } else if (!canborrow()) {
            throw new InvalidBorrowBookException("A reader cannot borrow more than " + maxBookNumber + " books");
        }
        bookIds.add(bookid);
    }

    // EFFECTS: return true if this reader has not borrowed the max number of books
    public boolean canborrow() {
        return bookIds.size() < maxBookNumber;
    }


    // MODIFIES: this
    // EFFECTS: return the book, delete the book to the reader's list of books borrowed
    public void returnBook(String bookid) throws InvalidReturnBookException {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException(("please provide the book he/she want to return"));
        }
        if (!isBorrowed(bookid)) {
            throw new InvalidReturnBookException("this reader has not borrowed this book");
        }
        bookIds.remove(bookid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reader reader = (Reader) o;
        return Objects.equals(username, reader.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username);
    }


    // MODIFIES: this
    // EFFECTS: update the notifications. add this new notifications to the list of notifications
    // if the number of notifications exceeds MAX_Notifications, delete the oldest one
    @Override
    public void update(Observable o, Object arg) {
        if (notifications.size() >= MAX_Notifications) {
            notifications.remove(0);
            update(o, arg);
            System.out.println("update");
        } else {
            String n = (String) arg;
            System.out.println("update");
            notifications.add(n);
        }
    }

    // MODIFIES: this
    // EFFECTS: set the notifications with the given list of notifications.
    // used when parsing data from JSON file
    public void setNotifications(List<String> nofitications) {
        this.notifications = nofitications;
    }



    // EFFECTS: return a string representation of the notifications
    public String notificationsToString() {
        String prints = "";
        for (String s: notifications) {
            prints = prints + s + "\n";
        }
        return prints;
    }

    // EFFECTS: delete all the notifications
    public void deleteNotifications() {
        notifications = new ArrayList<>();
    }

    // EFFECTS: return all the books that this reader has subscribed
    public List<Book> getSubscribeBooks() {
        return subscribebooks;
    }

    // EFFECTS: add this book to the reader's list of subscribed books if this reader has not subscribed this book
    // also add this reader to the book's observer list
    public void addSubscribeBook(Book book) {
        if (!subscribebooks.contains(book)) {
            subscribebooks.add(book);
            book.subscribe(this);
        }
    }

    // EFFECTS: remove this book from the reader's list of subscribed books if this reader has subscribed this book
    // also remove this reader from the book's observer list
    public void removeSubscribeBook(Book book) {
        if (subscribebooks.contains(book)) {
            subscribebooks.remove(book);
            book.unsubscribe(this);
        }
    }

    // EFFECTS: return this reader's current notifications
    public List<String> getNotifications() {
        return notifications;
    }
}
