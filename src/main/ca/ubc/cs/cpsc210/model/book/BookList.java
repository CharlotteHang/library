package ca.ubc.cs.cpsc210.model.book;


import ca.ubc.cs.cpsc210.model.exception.*;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;

import java.util.*;

import static ca.ubc.cs.cpsc210.model.reader.Reader.maxBookNumber;

public class BookList implements Iterable<Book> {
    private Map<String, Book> booklist; //key is ISBN number, value is the Book object
    private Map<String, List<String>> booknamelist; //key is book name, value is a list of ISBN numbers
    // (some books may have the same name)
    // used for searching with book name

    // MODIFIES: this
    // EFFECTS: create a new BookList
    public BookList() {
        booklist = new HashMap<String, Book>();
        booknamelist = new HashMap<String, List<String>>();
    }


    // MODIFIES: this
    // EFFECTS: borrow a book, change its status in BookList
    public void borrowBook(String bookid, int borrowday,
                           String username, ReaderList readerlist) throws InvalidBookException, InvalidReaderException {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException("please provide the bookid");
        } else if (username == null || username == "") {
            throw new EmptyStringException("please provide the name of reader who borrows the book");
        }

        if (!isInBookMap(bookid)) {
            throw new InvalidBookException("Our library does not have this book");
        } else if (booklist.get(bookid).getStatus().getBorrowed()) {
            throw new InvalidBorrowBookException("Sorry, this book has already been borrowed");
        } else if (!readerlist.canborrow(username)) {
            throw new InvalidBorrowBookException("A reader cannot borrow more than " + maxBookNumber + " books");
        }

        booklist.get(bookid).setStatus(new Status(borrowday, username));
        readerlist.borrowBook(bookid, username);

    }


    // MODIFIES: this
    // EFFECTS: return a book, change its status in BookList
    public void returnBook(String bookid, ReaderList readerlist) throws InvalidBookException, InvalidReaderException {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException("please provide the bookid");
        }
        if (!isInBookMap(bookid)) {
            throw new InvalidBookException("this book is not in our library");
        } else if (!booklist.get(bookid).getStatus().getBorrowed()) {
            throw new InvalidReturnBookException("Sorry, this book has not been borrowed");
        }

        String username = booklist.get(bookid).getStatus().getUsername();
        readerlist.returnBook(bookid, username);

        booklist.get(bookid).setStatus(new Status());
    }


    // EFFECTS: return the booklist which uses ISBN number as the key
    public Map<String, Book> getBookList() {
        return booklist;
    }

    // EFFECTS: return the booknamelist which uses the bookname as the key and ISBN numbers as the value
    public Map<String, List<String>> getBookNameList() {
        return booknamelist;
    }


    // EFFECTS: return whether the book with given id is in the library
    public boolean isInBookMap(String bookid) {
        if (bookid == null || bookid == "") {
            throw new EmptyStringException("please provide the name of the file storing books");
        }
        return booklist.containsKey(bookid);
    }

    // MODIFIES: this
    // EFFECTS: add new book to the booklist,
    // this add method used when this library buy a new book
    public void addNewBook(BookInfo bookinfo, String bookid) throws InvalidBookException {
        if (bookinfo == null) {
            throw new NullArgumentException("please provide the information of this book and the booklist");
        } else if (bookid == null || bookid == "") {
            throw new EmptyStringException("please provide the information of this book and the booklist");
        } else if (isInBookMap(bookid)) {
            throw new InvalidBookException("this book is already in the library");
        }
        Book book = new Book(bookid, bookinfo);
        booklist.put(bookid, book);

        String booktitle = bookinfo.getTitle();
        if (!booknamelist.containsKey(booktitle)) {
            List<String> l = new ArrayList<String>();
            l.add(bookid);
            booknamelist.put(booktitle, l);
        } else {
            booknamelist.get(booktitle).add(bookid);
        }

    }

    // MODIFIES: this
    // EFFECTS: add new book to the booklist,
    // this add method used when parsing data from file
    public void addBook(Book book) {
        if (book == null) {
            throw new NullArgumentException("please provide the information of this book and the booklist");
        } else if (isInBookMap(book.getId())) {
            throw new InvalidBookException("this book is already in the library");
        }
        booklist.put(book.getId(), book);

        String booktitle = book.getInfo().getTitle();
        String bookid = book.getId();
        if (!booknamelist.containsKey(booktitle)) {
            List<String> l = new ArrayList<String>();
            l.add(bookid);
            booknamelist.put(booktitle, l);
        } else {
            booknamelist.get(booktitle).add(bookid);
        }
    }

    // EFFECTS: find the book with given id, if there is not such a book, return null
    public Book searchBookid(String bookid) {
        if (bookid == null || bookid == "") {
            return null;
        }
        if (booklist.containsKey(bookid)) {
            return booklist.get(bookid);
        }
        return null;
    }

    // EFFECTS: find the book with the exact given book name, if there is not such a book, return null
    public List<Book> extactSearchByBookname(String title) {
        if (title == null || title == "") {
            return new ArrayList<Book>();
        }
        List<Book> searchResult = new ArrayList<>();
        if (booknamelist.containsKey(title)) {
            for (String bookid : booknamelist.get(title)) {
                searchResult.add(booklist.get(bookid));
            }
        }
        return searchResult;
    }

    // EFFECTS: find the book whose name contains the given string, if there is not such a book, return null
    public List<Book> fuzzySearchByBookname(String title) {
        if (title == null || title == "") {
            return new ArrayList<Book>();
        }
        List<Book> searchResult = new ArrayList<>();
        for (String booktitle : booknamelist.keySet()) {
            if (booktitle.contains(title)) {
                for (String bookid : booknamelist.get(booktitle)) {
                    searchResult.add(booklist.get(bookid));

                }
            }
        }
        return searchResult;
    }


    // EFFECTS: iterates over books in the library, order: the available books, and then the borrowed books
    @Override
    public Iterator<Book> iterator() {
        return new BookListIterator();
    }

    // BookListIterator that accesses books in the library,
    public class BookListIterator implements Iterator<Book> {
        private Iterator itrAvailable = booklist.values().iterator();
        private Iterator itrBorrowed = booklist.values().iterator();
        private Book available;
        private Book borrowed;

        // EFFECTS: constructs iterator
        public BookListIterator() {
            available = nextAvailable();
            borrowed = null;
        }

        @Override
        public boolean hasNext() {
            return ((available != null) || (borrowed != null));
        }

        @Override
        public Book next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Book tmpAvailable = available;
            Book tmpBborrowed = borrowed;
            available = nextAvailable();
            if (available == null) {
                borrowed = nextBorrowed();
            }
            return (tmpAvailable != null ? tmpAvailable : tmpBborrowed);
        }


        //EFFECTS: return the next available book, if there is none, return null
        public Book nextAvailable() {
            while (itrAvailable.hasNext()) {
                Book temp = (Book) itrAvailable.next();
                if (!temp.getStatus().getBorrowed()) {
                    return temp;
                }
            }
            return null;
        }

        //EFFECTS: return the next borrowed book, if there is none, return null
        public Book nextBorrowed() {
            while (itrBorrowed.hasNext()) {
                Book temp = (Book) itrBorrowed.next();
                if (temp.getStatus().getBorrowed()) {
                    return temp;
                }
            }
            return null;
        }


    }

}