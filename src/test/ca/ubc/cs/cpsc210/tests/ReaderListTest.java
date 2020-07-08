package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.InvalidReaderException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderListTest {
    ReaderList readerlist;

    @BeforeEach
    public void setup() {
        try {
            readerlist = new ReaderList();
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }

    }


    @Test
    void testContainReader() {
        try {
            readerlist.addReader(new Reader("reader1", "password"));
            readerlist.addReader(new Reader("reader2", "password"));
            assertFalse(readerlist.containReader("reader"));
            assertEquals(readerlist.getReaderList().size(), 2);
            readerlist.addReader(new Reader("reader", "password"));
            assertTrue(readerlist.containReader("reader"));
            assertEquals(readerlist.getReaderList().size(), 3);
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }


    @Test
    void testContainReaderThrowsEmptyStringException1() {
        try {
            readerlist.containReader("");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testContainReaderThrowsEmptyStringException2() {
        try {
            readerlist.containReader(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testSearchReader() {
        try {
            readerlist.addReader(new Reader("reader1", "password"));
            readerlist.addReader(new Reader("reader2", "password"));
            assertEquals(readerlist.searchReader("reader"), null);
            readerlist.addReader(new Reader("reader", "password"));
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
        assertEquals(readerlist.searchReader("reader").getusername(), "reader");
    }

    @Test
    void testAddReaderThrowsNullArgumentException() {
        try {
            readerlist.addReader(null);
            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testAddReaderThrowsInvalidReaderException() {
        try {
            readerlist.addReader(new Reader("reader", "password"));
            readerlist.addReader(new Reader("reader", "password"));
            fail("fail to throw InvalidReaderException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (InvalidReaderException e) {
            System.out.println("InvalidReaderException");
        }
    }

    @Test
    void testCanLogin() {

        try {
            readerlist.addReader(new Reader("reader", "password"));
            readerlist.addReader(new Reader("reader3", "password3"));
            readerlist.addReader(new Reader("reader4", "password4"));
            assertTrue(readerlist.getReaderList().containsKey("reader"));
            assertTrue(readerlist.getReaderList().get("reader").canborrow());
            assertEquals(readerlist.logIn("reader", "password").getpassowrd(), "password");
            assertEquals(readerlist.logIn("reader", "passwor"), null);
            assertEquals(readerlist.logIn("reade", "passwor"), null);
            assertEquals(readerlist.logIn("", "passwor"), null);
            assertEquals(readerlist.logIn("username", ""), null);
            assertEquals(readerlist.logIn(null, "password"), null);
            assertEquals(readerlist.logIn("username", null), null);
        } catch (InvalidReaderException e) {
            fail("Caught unexpected Exception");
        }

    }

    @Test
    void testDeleteReaderThrowsNullArgumentException() {
        try {
            readerlist.deleteReader(null);
            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testDeleteReaderThrowsInvalidReaderException1() {
        try {
            readerlist.addReader(new Reader("reader1", "password"));
            readerlist.addReader(new Reader("reader2", "password"));
            readerlist.deleteReader(new Reader("reader3", "password"));
            fail("fail to throw InvalidReaderException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (InvalidReaderException e) {
            System.out.println("InvalidReaderException");
        }
    }


    @Test
    void testDeleteReaderThrowsInvalidReaderException2() {
        try {
            readerlist.addReader(new Reader("reader1", "password"));
            Reader reader2 = new Reader("reader2", "password");
            readerlist.addReader(reader2);
            readerlist.searchReader("reader2").borrowBook("book");
            readerlist.deleteReader(reader2);
            fail("fail to throw InvalidReaderException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (InvalidReaderException e) {
            System.out.println("InvalidReaderException");
        }
    }

    @Test
    void testDeleteReader() {
        try {
            readerlist.addReader(new Reader("reader1", "password"));
            readerlist.addReader(new Reader("reader2", "password"));
            readerlist.deleteReader(new Reader("reader2", "password"));
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
        assertFalse(readerlist.containReader("reader2"));
    }

    @Test
    void testBorrowBookThrowsEmptyStringExceptionBookIds1() {
        try {
            readerlist.borrowBook("", "username");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testBorrowBookThrowsEmptyStringExceptionBookIds2() {
        try {
            readerlist.borrowBook(null, "username");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testBorrowBookThrowsEmptyStringExceptionUsername1() {
        try {
            readerlist.borrowBook("bookid", "");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testBorrowBookThrowsEmptyStringExceptionUsername2() {
        try {
            readerlist.borrowBook("bookid", null);
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testBorrowBookThrowsInvalidReaderException() {
        try {
            readerlist.addReader(new Reader("reader", "password"));
            readerlist.addReader(new Reader("reader3", "password3"));
            readerlist.addReader(new Reader("reader4", "password4"));
            readerlist.borrowBook("bookid", "reader5");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReaderException e) {
            System.out.println("InvalidReaderException");
        }
    }

    @Test
    void testBorrowBook() {
        try {
            readerlist.addReader(new Reader("reader", "password"));
            readerlist.addReader(new Reader("reader3", "password3"));
            readerlist.addReader(new Reader("reader4", "password4"));
            readerlist.borrowBook("bookid", "reader4");
            assertTrue(readerlist.searchReader("reader4").isBorrowed("bookid"));
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testBorrowBookCannotBorrowBook() {
        try {
            readerlist.addReader(new Reader("reader4", "password"));
            for (int i = 0; i <= Reader.maxBookNumber; i++) {
                readerlist.borrowBook("book" + i, "reader4");
            }
            assertTrue(readerlist.searchReader("reader4").getBorrowedBooksByName().size() == Reader.maxBookNumber);
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void canBorrowThrowsEmptyStringException1() {
        try {
            readerlist.canborrow(null);
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void canBorrowThrowsEmptyStringException2() {
        try {
            readerlist.canborrow("");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void canBorrowThrowsInvalidReaderException() {
        try {
            readerlist.addReader((new Reader("reader1", "password")));
            readerlist.canborrow("reader100");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReaderException e) {
            System.out.println("InvalidReaderException");
        }
    }

    @Test
    void testReturnBookThrowsEmptyStringExceptionBookIds1() {
        try {
            readerlist.returnBook("", "username");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testReturnBookThrowsEmptyStringExceptionBookIds2() {
        try {
            readerlist.returnBook(null, "username");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testReturnBookThrowsEmptyStringExceptionUsername1() {
        try {
            readerlist.returnBook("bookid", "");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testReturnBookThrowsEmptyStringExceptionUsername2() {
        try {
            readerlist.returnBook("bookid", null);
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testReturnBookThrowsInvalidReaderException() {
        try {
            readerlist.addReader(new Reader("reader", "password"));
            readerlist.addReader(new Reader("reader3", "password3"));
            readerlist.addReader(new Reader("reader4", "password4"));
            readerlist.returnBook("bookid", "reader5");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReaderException e) {
            System.out.println("InvalidReaderException");
        }
    }

    @Test
    void testReturnBook() {
        try {
            readerlist.addReader(new Reader("reader", "password"));
            readerlist.addReader(new Reader("reader3", "password3"));
            readerlist.addReader(new Reader("reader4", "password4"));
            readerlist.borrowBook("bookid", "reader4");
            readerlist.borrowBook("bookid2", "reader4");
            assertTrue(readerlist.searchReader("reader4").isBorrowed("bookid"));
            assertTrue(readerlist.searchReader("reader4").isBorrowed("bookid2"));
            readerlist.borrowBook("bookid3", "reader4");
            readerlist.returnBook("bookid2", "reader4");
            readerlist.returnBook("bookid", "reader4");
            assertFalse(readerlist.searchReader("reader4").isBorrowed("bookid"));
            assertTrue(readerlist.searchReader("reader4").isBorrowed("bookid3"));
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReaderException e) {
            fail("Caught unexpected InvalidReaderException");
        }
    }

    @Test
    void testIterator(){
        readerlist.addReader(new Reader("reader", "password"));
        readerlist.addReader(new Reader("reader3", "password3"));
        readerlist.addReader(new Reader("reader4", "password4"));
        readerlist.addReader(new Reader("reader5", "password5"));
        Iterator<Reader> itr = readerlist.iterator();
        for (int i = 0; i < 4; i++ ){
            assertTrue(itr.hasNext());
            Reader next = itr.next();
        }
        assertFalse(itr.hasNext());
        try {
            itr.next();
            fail("fail to catch Exception");
        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElementException");
        }
    }
}