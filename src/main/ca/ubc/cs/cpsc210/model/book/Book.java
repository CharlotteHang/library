package ca.ubc.cs.cpsc210.model.book;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class Book extends Observable {
    private String id; // use String for book id
    private BookInfo bookinfo;
    private Status status;
    private List<String> followers = new ArrayList<>();


    // MODIFIES: this
    // EFFECTS: create a Book with given id and book information. The status is set
    // as not has been borrowed' by default
    public Book(String id, BookInfo bookinfo) {
        if (id == null || id.equals("")) {
            throw new EmptyStringException("Please provide book id");
        }
        if (bookinfo == null) {
            throw new NullArgumentException("Please provide book information");
        }
        this.id = id;
        this.bookinfo = bookinfo;
        this.status = new Status();
    }


    // EFFECTS: get the status of this book
    public Status getStatus() {
        return status;
    }


    // EFFECTS: return the id of the book
    public String getId() {
        return id;
    }

    // EFFECTS: return the information of the book
    public BookInfo getInfo() {
        return bookinfo;
    }


    // EFFECTS: return true if two books has the same isbn number, i.e. they are the same book id
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    // MODIFIES: this
    // EFFECTS: set the borrowing status of this book to the given status, used when a book is borrowed or returned
    public void setStatus(Status status) {
        if (status == null) {
            throw new EmptyStringException("Please provide status");
        }
        setChanged();
        notifyObservers(bookinfo.getTitle() + (status.getBorrowed()
                ? (" has been borrowed and is expected to be returned on " + status.getreturnday())
                : " has been returned"));
        System.out.println("status");
        this.status = status;
    }


    // MODIFIES: this
    // EFFECTS: set the borrowing status of this book to the given status, used in data parsing
    public void setStatusInDaraParsing(Status status) {
        if (status == null) {
            throw new NullArgumentException("Please provide status");
        }
        this.status = status;
    }




    // MODIFIES: this
    // EFFECTS: add the reader to the observer list of this book if the reader is not in it
    // (also add the book to reader's list of subscribed books)
    public void subscribe(Reader reader) {
        if (reader == null) {
            throw new NullArgumentException("Illegal argument: reader null");
        }
        if (!(followers.contains(reader.getusername()))) {
            addObserver(reader);
            followers.add(reader.getusername());
            reader.addSubscribeBook(this);
        }
    }


    // MODIFIES: this
    // EFFECTS: add the reader to the observer list of this book if the reader is not in it
    public void subscribe(String username, ReaderList readerlist) {
        if (username == null || username.equals("")) {
            throw new EmptyStringException("Illegal argument: reader username is empty");
        }
        if (readerlist == null) {
            throw new NullArgumentException("Illegal argument: reader list is null");
        }
        if (!(followers.contains(username))) {
            Reader reader = readerlist.searchReader(username);
            addObserver(reader);
            followers.add(username);
            reader.addSubscribeBook(this);
        }

    }

    // MODIFIES: this
    // EFFECTS: remove the reader from the observer list of this book
    // (also remove the book to reader's list of subscribed books)
    public void unsubscribe(Reader reader) {
        if (reader == null) {
            throw new NullArgumentException("Illegal argument: reader null");
        }
        deleteObserver(reader);
        followers.remove(reader.getusername());
        reader.removeSubscribeBook(this);
    }


    // EFFECTS: return a string representation of a book
    @Override
    public String toString() {
        return ("ISBN number: " + id + ", Status: " + status + "\n" + bookinfo);
    }

    // EFFECTS: return a list of usernames of the readers who subscribe the book
    public List<String> getFollowers() {
        return followers;
    }
}


