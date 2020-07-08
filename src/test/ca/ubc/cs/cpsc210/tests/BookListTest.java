package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookInfo;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.book.Status;
import ca.ubc.cs.cpsc210.model.exception.*;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class BookListTest {
    private BookList booklist;
    private ReaderList readerList;

    @BeforeEach
    void setup() throws InvalidBookException {

        booklist = new BookList();
        booklist.addNewBook(new BookInfo("", "title", "",
                "", "", ""), "bookid");
        booklist.addNewBook(new BookInfo("", "title5", "",
                "", "", ""), "bookid5");
        readerList = new ReaderList();
        readerList.addReader(new Reader("username", "username"));
    }

    @Test
    void testConstructor() {
        try {
            assertEquals(booklist.getBookList().size(), 2);
            assertEquals(booklist.getBookNameList().size(), 2);
            booklist.addNewBook(new BookInfo("", "title", "",
                    "", "", ""), "bookid2");

        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
        assertEquals(booklist.getBookList().size(), 3);
    }


    @Test
    void testBorrowBook() {
        try {
            booklist.borrowBook("bookid", 55, "username", readerList);
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
        assertTrue(booklist.searchBookid("bookid").getStatus().getBorrowed());
        assertTrue(booklist.extactSearchByBookname("title").get(0).getStatus().getBorrowed());
    }

    @Test
    void testBorrowBookThrowsInvalidBookException() {
        try {
            booklist.borrowBook("bookid2", 55, "username", readerList);
            fail("fail to throw InvalidBookException");
        } catch (InvalidBookException e) {
            System.out.println("InvalidBookException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testBorrowBookThrowsInvalidBorrowBookException1() {
        try {
            booklist.borrowBook("bookid", 55, "username", readerList);
            booklist.borrowBook("bookid", 55, "username", readerList);
            fail("fail to throw InvalidBookException");
        } catch (InvalidBorrowBookException e) {
            System.out.println("InvalidBorrowBookException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testBorrowBookThrowsInvalidBorrowBookException2() {
        try {
            for (int i = 0; i <= Reader.maxBookNumber; i++) {
                booklist.addNewBook(new BookInfo("", "title5", "",
                        "", "", ""), Integer.toString(i));
            }
            for (int i = 0; i <= Reader.maxBookNumber; i++) {
                booklist.borrowBook(Integer.toString(i), 55, "username", readerList);
            }
            fail("fail to throw InvalidBookException");
        } catch (InvalidBorrowBookException e) {
            System.out.println("InvalidBorrowBookException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }


    @Test
    void testBorrowBookThrowsEmptyStringExceptionbookid1() {
        try {
            booklist.borrowBook("", 55, "username", readerList);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testBorrowBookThrowsEmptyStringExceptionbookid2() {
        try {
            booklist.borrowBook(null, 55, "username", readerList);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testBorrowBookThrowsEmptyStringExceptionusername1() {
        try {
            booklist.borrowBook("bookid", 55, "", readerList);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testBorrowBookThrowsEmptyStringExceptionusername2() {
        try {
            booklist.borrowBook("bookid", 55, null, readerList);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }


    @Test
    void testReturnBook() {
        try {
            booklist.borrowBook("bookid", 55, "username", readerList);
            booklist.returnBook("bookid", readerList);
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
        assertFalse(booklist.searchBookid("bookid").getStatus().getBorrowed());
        assertFalse(booklist.extactSearchByBookname("title").get(0).getStatus().getBorrowed());
    }

    @Test
    void testReturnBookThrowsInvalidBookException() {
        try {
            booklist.returnBook("bookid2", readerList);
            fail("fail to throw InvalidBookException");
        } catch (InvalidBookException e) {
            System.out.println("InvalidBookException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testReturnBookThrowsInvalidBorrowBookException() {
        try {
            booklist.borrowBook("bookid", 55, "username", readerList);
            booklist.returnBook("bookid", readerList);
            booklist.returnBook("bookid", readerList);
            fail("fail to throw InvalidBookException");
        } catch (InvalidReturnBookException e) {
            System.out.println("InvalidReturnBookException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testReturnBookThrowsEmptyStringExceptionbookid1() {
        try {
            booklist.returnBook("", readerList);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testReturnBookThrowsEmptyStringExceptionbookid2() {
        try {
            booklist.returnBook(null, readerList);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    public void testGetBookMap() {
        assertEquals(booklist.getBookList().size(), 2);
        assertTrue(booklist.getBookList().keySet().contains("bookid"));
        assertFalse(booklist.getBookList().keySet().contains("bookid2"));
    }

    @Test
    public void testGetBookNameList() {
        assertEquals(booklist.getBookNameList().size(), 2);
        assertTrue(booklist.getBookNameList().keySet().contains("title5"));
        assertFalse(booklist.getBookNameList().keySet().contains("title2"));
    }

    @Test
    void testIsinBookmapThrowsEmptyStringException() {
        try {
            booklist.isInBookMap("");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testIsinBookmapThrowsEmptyStringException2() {
        try {
            booklist.isInBookMap(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testaddNewBookThrowsNullArgumentExceptionBookinfo() {
        try {
            booklist.addNewBook(null, "bookid2");
            fail("fail to throw EmptyStringException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testaddNewBookThrowsEmptyStringExceptionBookid1() {
        try {
            booklist.addNewBook(new BookInfo("", "title", "", "", "", ""), "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testaddNewBookThrowsEmptyStringExceptionBookid2() {
        try {
            booklist.addNewBook(new BookInfo("", "title", "", "", "", ""), null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testaddNewBookThrowsInvalidBookException() {
        try {
            booklist.addNewBook(new BookInfo("", "title", "", "", "", ""), "bookid");
            fail("fail to throw EmptyStringException");
        } catch (InvalidBookException e) {
            System.out.println("InvalidBookException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }


    @Test
    void testaddBookThrowsInvalidBookException() {
        try {
            booklist.addBook(new Book("bookid", new BookInfo("", "title", "", "", "", "")));
            fail("fail to throw EmptyStringException");
        } catch (InvalidBookException e) {
            System.out.println("InvalidBookException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testaddBookThrowsNullArgumentExceptionBook() {
        try {
            booklist.addBook(null);
            fail("fail to throw EmptyStringException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testaddBook() {
        try {
            Status s1 = new Status();
            Status s2 = new Status(50, "username");
            BookInfo bookinfo1 = new BookInfo("", "title1", "",
                    "", "", "");
            BookInfo bookinfo2 = new BookInfo("", "title2", "",
                    "", "", "");
            Book book1 = new Book("booka", bookinfo1);
            Book book2 = new Book("bookb", bookinfo1);
            Book book3 = new Book("bookc", bookinfo2);
            Book book4 = new Book("bookd", bookinfo2);
            Book book5 = new Book("booke", bookinfo2);
            book3.setStatus(s1);
            book4.setStatus(s2);
            booklist.addBook(book1);
            booklist.addBook(book2);
            booklist.addBook(book3);
            booklist.addBook(book4);
            booklist.addBook(book5);
            assertEquals(booklist.extactSearchByBookname("title2").size(), 3);
            assertEquals(booklist.fuzzySearchByBookname("title").size(), 7);
            assertEquals(booklist.fuzzySearchByBookname("title2").size(), 3);
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }


    @Test
    public void testSearchid() {

        assertEquals(booklist.searchBookid(""), null);
        assertEquals(booklist.searchBookid(null), null);
        assertEquals(booklist.searchBookid("bookid3"), null);
        BookInfo bookinfo2 = new BookInfo("", "title2", "",
                "", "", "");
        Book book1 = new Book("bookid", bookinfo2);
        assertEquals(booklist.searchBookid("bookid"), book1);
    }

    @Test
    public void testExactSearchBookname() {
        assertEquals(booklist.extactSearchByBookname("").size(), 0);
        assertEquals(booklist.extactSearchByBookname(null).size(), 0);

        booklist.addNewBook(new BookInfo("", "samename", "",
                "", "", ""), "bookid6");
        booklist.addNewBook(new BookInfo("", "samename", "",
                "", "", ""), "bookid7");
        booklist.addNewBook(new BookInfo("", "samename2", "",
                "", "", ""), "bookid8");
        booklist.addNewBook(new BookInfo("", "samename2", "",
                "", "", ""), "bookid9");
        booklist.addNewBook(new BookInfo("", "differentname", "",
                "", "", ""), "bookid10");
        assertEquals(booklist.extactSearchByBookname("samename").size(), 2);
        assertEquals(booklist.extactSearchByBookname("sa").size(), 0);

    }


    @Test
    public void testFuzzySearchBookname() {
        assertEquals(booklist.fuzzySearchByBookname("").size(), 0);
        assertEquals(booklist.fuzzySearchByBookname(null).size(), 0);

        booklist.addNewBook(new BookInfo("", "samename", "",
                "", "", ""), "bookid11");
        booklist.addNewBook(new BookInfo("", "samename", "",
                "", "", ""), "bookid12");
        booklist.addNewBook(new BookInfo("", "samename2", "",
                "", "", ""), "bookid13");
        booklist.addNewBook(new BookInfo("", "samename3", "",
                "", "", ""), "bookid14");
        booklist.addNewBook(new BookInfo("", "differentname", "",
                "", "", ""), "bookid10");
        assertEquals(booklist.fuzzySearchByBookname("samename").size(), 4);
    }


    @Test
    void testIteratorEmptyBookList() {
        Iterator<Book> itr = (new BookList()).iterator();
        assertFalse(itr.hasNext());
    }

    @Test
    void testIteratorExceptionHandling() {
        Iterator<Book> itr = (new BookList()).iterator();
        try {
            itr.next();
            fail("Exception should have been thrown.");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException");
        }
    }

    @Test
    void testIterator() {
        BookInfo bookinfo1 = new BookInfo("", "title1", "",
                "", "", "");
        BookInfo bookinfo2 = new BookInfo("", "title2", "",
                "", "", "");
        Book book1 = new Book("booka", bookinfo1);
        Book book2 = new Book("bookb", bookinfo1);
        Book book3 = new Book("bookc", bookinfo2);
        Book book4 = new Book("bookd", bookinfo2);
        Book book5 = new Book("booke", bookinfo2);

        BookList booklist1 = new BookList();
        booklist1.addBook(book1);
        booklist1.addBook(book2);
        booklist1.addBook(book3);
        booklist1.addBook(book4);
        booklist1.addBook(book5);

        booklist1.borrowBook("booka", 55, "username", readerList);
        booklist1.borrowBook("bookd", 55, "username", readerList);

        Iterator<Book> itr = booklist1.iterator();
        for (int i = 0; i < 3; i++) {
            assertTrue(itr.hasNext());
            Book next = itr.next();
            assertTrue(!next.getStatus().getBorrowed());
            System.out.println(next.getId());
        }
        for (int i = 0; i < 2; i++) {
            assertTrue(itr.hasNext());

            Book next = itr.next();
            System.out.println(next.getId());
            assertTrue(next.getStatus().getBorrowed());
        }
        assertFalse(itr.hasNext());


    }

}
