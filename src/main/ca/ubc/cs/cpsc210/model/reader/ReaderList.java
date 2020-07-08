package ca.ubc.cs.cpsc210.model.reader;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.InvalidReaderException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReaderList implements Iterable<Reader> {

    private Map<String, Reader> readerlist;

    // MODIFIES: this
    // EFFECTS: creates a new ReaderList
    public ReaderList() {
        readerlist = new HashMap<String, Reader>();
    }


    // EFFECTS: return true if the username is already in the ReaderList
    public boolean containReader(String username) {
        if (username == null || username == "") {
            throw new EmptyStringException("please provide the filename containint data of librarians");
        }
        if (readerlist.containsKey(username)) {
            return true;
        }

        return false;
    }

    // EFFECTS: return the list of readers
    public Map<String, Reader> getReaderList() {
        return readerlist;
    }


    // EFFECTS: return the target reader with given username
    public Reader searchReader(String username) {
        for (Map.Entry<String, Reader> e : readerlist.entrySet()) {
            if (e.getKey().equals(username)) {
                return e.getValue();
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: add 1 reader to the list of readers
    public void addReader(Reader reader) throws InvalidReaderException {
        if (reader == null) {
            throw new NullArgumentException("please provide a reader");
        } else if (containReader(reader.getusername())) {
            throw new InvalidReaderException("this username has already been used");
        }
        readerlist.put(reader.getusername(), reader);
    }

    // EFFECTS: return the reader if this username and password can log in, otherwise return null
    public Reader logIn(String username, String password) {
        if (username == null || username == "" || password == null || password == "") {
            return null;
        }
        if (readerlist.containsKey(username) && readerlist.get(username).getpassowrd().equals(password)) {
            return readerlist.get(username);
        }
        return null;

    }

    // MODIFIES: this,
    // EFFECTS: delete the account of one reader
    public void deleteReader(Reader reader) throws InvalidReaderException {
        if (reader == null) {
            throw new NullArgumentException("please provide a reader");
        } else if (!containReader(reader.getusername())) {
            throw new InvalidReaderException("this username has not registered in our library");
        } else if (reader.getBorrowedBooksByName().size() > 0) {
            throw new InvalidReaderException("You have to return all the books before deleting your account");
        }
        readerlist.remove(reader.getusername());
    }


    // MODIFIES: this
    // EFFECTS: borrow the book,
    public void borrowBook(String bookid, String username) throws InvalidReaderException {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException(("please provide the the book"));
        }
        if (canborrow(username)) {
            searchReader(username).borrowBook(bookid);
        }
    }

    // EFFECTS: return true if the reader can borrow extra book,
    // throw InvalidReaderException if this reader is not in our list
    public boolean canborrow(String username) throws InvalidReaderException {
        if (username == null || username == "") {
            throw new EmptyStringException(("please provide the the username"));
        } else if (searchReader(username) == null) {
            throw new InvalidReaderException("This reader has not registered in our library");
        }
        return readerlist.get(username).canborrow();
    }


    // MODIFIES: this
    // EFFECTS: return the book, throw InvalidReaderException if this reader is not in our list
    public void returnBook(String bookid, String username) throws InvalidReaderException {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException(("please provide the the book"));
        } else if (username == null || username == "") {
            throw new EmptyStringException(("please provide the the username"));
        } else if (searchReader(username) == null) {
            throw new InvalidReaderException("This reader has not registered in our library");
        }
        searchReader(username).returnBook(bookid);
    }


    @Override
    public Iterator<Reader> iterator() {
        return readerlist.values().iterator();
    }
}