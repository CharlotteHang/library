package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookInfo;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import ca.ubc.cs.cpsc210.parsers.DataParser;
import ca.ubc.cs.cpsc210.parsers.ParseFromFile;
import ca.ubc.cs.cpsc210.persistence.Jsonifier;
import ca.ubc.cs.cpsc210.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ParserAndPersistenceTest {
    DataParser dp = new DataParser();
    Persistence p;
    ParseFromFile pff = new ParseFromFile();
    Jsonifier j = new Jsonifier();
    BookList booklist;
    ReaderList readerlist;
    LibrarianList librarianlist;

    @BeforeEach
    void setup() {


        readerlist = new ReaderList();
        Reader reader1 = new Reader("reader1", "password1");
        Reader reader2 = new Reader("reader2", "password2");
        Reader reader3 = new Reader("reader3", "password3");
        Reader reader4 = new Reader("reader4", "password4");
        Reader reader5 = new Reader("reader5", "password5");

        readerlist.addReader(reader1);
        readerlist.addReader(reader2);
        readerlist.addReader(reader3);
        readerlist.addReader(reader4);
        readerlist.addReader(reader5);


        booklist = new BookList();

        BookInfo bookinfo1 = new BookInfo("authora", "titlea", "genre1",
                "12", "20180911", "");
        BookInfo bookinfo2 = new BookInfo("authorb", "titleb", "genre2",
                "23", "20131214", "");
        BookInfo bookinfo3 = new BookInfo("authora", "titlea", "genre3",
                "45", "19980615", "");
        BookInfo bookinfo4 = new BookInfo("authorb", "titleb", "genre4",
                "34", "20180911", "");
        BookInfo bookinfo5 = new BookInfo("authora", "titlea", "genre5",
                "", "20180911", "");


        Book book1 = new Book("10000", bookinfo1);
        Book book2 = new Book("20000", bookinfo2);
        Book book3 = new Book("30000", bookinfo3);
        Book book4 = new Book("40000", bookinfo4);
        Book book5 = new Book("x0000", bookinfo5);


        booklist.addBook(book1);
        booklist.addBook(book2);
        booklist.addBook(book3);
        booklist.addBook(book4);
        booklist.addBook(book5);



        book4.subscribe("reader1", readerlist);
        book5.subscribe("reader2", readerlist);
        book1.subscribe("reader1", readerlist);



        booklist.borrowBook("10000", 50, "reader1", readerlist);
        booklist.borrowBook("20000", 60, "reader1", readerlist);
        booklist.borrowBook("x0000", 40, "reader1", readerlist);
        booklist.borrowBook("40000", 89, "reader2", readerlist);

        librarianlist = new LibrarianList();
        Librarian librarian1 = new Librarian("librarian1", "password1", "position1");
        Librarian librarian2 = new Librarian("librarian2", "password1", "position1");
        Librarian librarian3 = new Librarian("librarian3", "password1", "position1");
        librarianlist.addonelibrarian(librarian1);
        librarianlist.addonelibrarian(librarian2);
        librarianlist.addonelibrarian(librarian3);

        p = new Persistence(booklist, readerlist, librarianlist);
    }

    @Test
    void testBooklistPersistenceThrowsEmptyStringException() {
        try {
            p.booklistPersistence(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testReaderlistPersistenceThrowsEmptyStringException() {
        try {
            p.readerlistPersistence(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testLibrarianlistPersistenceThrowsEmptyStringException() {
        try {
            p.librarylistPersistence(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testBooklistPersistenceIOException() {
        try {
            p.booklistPersistence("");
        } catch (EmptyStringException e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testReaderlistPersistenceIOException() {
        try {
            p.readerlistPersistence("");
        } catch (EmptyStringException e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testLibrarianlistPersistenceIOException() {
        try {
            p.librarylistPersistence("");
        } catch (EmptyStringException e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testBooklistParserThrowsEmptyStringException() {
        try {
            ReaderList readerlist = null;
            ParseFromFile.booklistParser(null, readerlist);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testReaderlistParserThrowsEmptyStringException() {
        try {
            ParseFromFile.readerlistParser(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testLibrarianlistParserThrowsEmptyStringException() {
        try {
            ParseFromFile.librarianListParser(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testBooklistParserFileNotFoundException() {
        try {
            ReaderList readerlist = null;
            assertEquals(ParseFromFile.booklistParser("", readerlist).getBookList().size(), 0);

        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testReaderlistParserFileNotFoundException() {
        try {
            assertEquals(ParseFromFile.readerlistParser("").getReaderList().size(), 0);

        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testLibrarianlistParserFileNotFoundException() {
        try {
            assertEquals(ParseFromFile.librarianListParser("").getLibrarianlist().size(), 0);

        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }



    @Test
    void testReaderlistParserAndPersistence() {
        try {
            p.readerlistPersistence("src/JSON files/ReadersTest");
            ReaderList parsedreaderlist = ParseFromFile.readerlistParser("src/JSON files/ReadersTest");

            Map<String, Reader> readerlist1 = readerlist.getReaderList();

            Map<String, Reader> parsedreaderlist1 = parsedreaderlist.getReaderList();

            for (Map.Entry<String, Reader> e : readerlist1.entrySet()) {
                assertEquals(e.getValue(), parsedreaderlist1.get(e.getKey()));
                assertEquals(e.getValue().getBorrowedBooksByName().size(), parsedreaderlist1.get(e.getKey()).getBorrowedBooksByName().size());
                assertEquals(e.getValue().getpassowrd(), parsedreaderlist1.get(e.getKey()).getpassowrd());
                assertEquals(e.getValue().getusername(), parsedreaderlist1.get(e.getKey()).getusername());
                assertEquals(e.getValue().getNotifications().size(), parsedreaderlist1.get(e.getKey()).getNotifications().size());
            }


        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        }
    }

    @Test
    void testBooklistParserAndPersistence() {
        try {
            p.readerlistPersistence("src/JSON files/ReadersTest");
            ReaderList parsedreaderlist = ParseFromFile.readerlistParser("src/JSON files/ReadersTest");
            Map<String, Reader> readerlist1 = readerlist.getReaderList();
            Map<String, Reader> parsedreaderlist1 = parsedreaderlist.getReaderList();
            p.booklistPersistence("src/JSON files/BooksInLibraryTest");
            BookList parsedbooklist = ParseFromFile.booklistParser("src/JSON files/BooksInLibraryTest", parsedreaderlist);

            Map<String, Book> booklist1 = booklist.getBookList();
            Map<String, List<String>> booknamelist1 = booklist.getBookNameList();

            Map<String, Book> parsedbooklist1 = parsedbooklist.getBookList();
            Map<String, List<String>> parsedbooknamelist1 = parsedbooklist.getBookNameList();

            for (Map.Entry<String, Book> e : booklist1.entrySet()) {
                assertEquals(e.getValue(), parsedbooklist1.get(e.getKey()));
                assertEquals(e.getValue().getInfo().getTitle(), parsedbooklist1.get(e.getKey()).getInfo().getTitle());
                assertEquals(e.getValue().getInfo().getDescription(), parsedbooklist1.get(e.getKey()).getInfo().getDescription());
                assertEquals(e.getValue().getInfo().getPublishDate(), parsedbooklist1.get(e.getKey()).getInfo().getPublishDate());
                assertEquals(e.getValue().getInfo().getPrice(), parsedbooklist1.get(e.getKey()).getInfo().getPrice());
                assertEquals(e.getValue().getInfo().getGenre(), parsedbooklist1.get(e.getKey()).getInfo().getGenre());
                assertEquals(e.getValue().getInfo().getAuthor(), parsedbooklist1.get(e.getKey()).getInfo().getAuthor());
                assertEquals(e.getValue().getId(), parsedbooklist1.get(e.getKey()).getId());
                assertEquals(e.getValue().getStatus().getBorrowed(), parsedbooklist1.get(e.getKey()).getStatus().getBorrowed());
                assertEquals(e.getValue().getStatus().getreturnday(), parsedbooklist1.get(e.getKey()).getStatus().getreturnday());
                assertEquals(e.getValue().getStatus().getborrowday(), parsedbooklist1.get(e.getKey()).getStatus().getborrowday());
                assertEquals(e.getValue().getStatus().getUsername(), parsedbooklist1.get(e.getKey()).getStatus().getUsername());
                assertEquals(e.getValue().getFollowers().size(), parsedbooklist1.get(e.getKey()).getFollowers().size());

            }

            for (Map.Entry<String, Reader> e : readerlist1.entrySet()) {
                assertEquals(e.getValue().getSubscribeBooks().size(), parsedreaderlist1.get(e.getKey()).getSubscribeBooks().size());
            }


            for (Map.Entry<String, List<String>> e : booknamelist1.entrySet()) {
                assertEquals(e.getValue().size(), parsedbooknamelist1.get(e.getKey()).size());
            }

        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        }
    }


    @Test
    void testLibrarianlistParserAndPersistence() {
        try {
            p.librarylistPersistence("src/JSON files/LibrariansTest");
            LibrarianList parsedlibrarianrlist = ParseFromFile.librarianListParser("src/JSON files/LibrariansTest");

            List<Librarian> librarianlist1 = librarianlist.getLibrarianlist();

            List<Librarian> parsedlibrarianlist1 = parsedlibrarianrlist.getLibrarianlist();


            for (Librarian l: librarianlist1) {
                assertTrue(parsedlibrarianlist1.contains(l));
                for(Librarian parsedl: parsedlibrarianlist1) {
                    if (l.equals(parsedl)) {
                        assertEquals(l.getusername(), parsedl.getusername());
                        assertEquals(l.getposition(), parsedl.getposition());
                        assertEquals(l.getpassowrd(), parsedl.getpassowrd());
                    }
                }
            }


        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        }
    }


}
