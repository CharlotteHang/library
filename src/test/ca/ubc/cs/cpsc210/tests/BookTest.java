package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookInfo;
import ca.ubc.cs.cpsc210.model.book.Status;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    private Book book1;

    @BeforeEach
    void setup() {
        try {
            book1 = new Book("12345", new BookInfo("", "title",
                    "", "", "", ""));
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testConstructorThrowsEmptyStringException() {
        try {
            book1 = new Book("", new BookInfo("", "title",
                    "", "", "", ""));
            fail("fail to throw EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorBookIDThrowsNullArgumentException() {
        try {
            book1 = new Book(null, new BookInfo("", "title",
                    "", "", "", ""));
            fail("fail to throw EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorBookinfoThrowsNullArgumentException() {
        try {
            book1 = new Book("abc", null);
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        }
    }

    @Test
    void testSetStatusThrowsNullArgumentException() {
        try {
            book1 = new Book("abc", new BookInfo("", "title",
                    "", "", "", ""));
            book1.setStatusInDaraParsing(null);
            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        }
    }

    @Test
    void testSetStatusThrowsEmptyStringException() {
        try {
            book1.setStatus(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testSetStatus() {
        try {
            assertFalse(book1.getStatus().getBorrowed());
            Status s2 = new Status("20190101", "20190101", "abc");
            book1.setStatus(s2);
            assertTrue(book1.getStatus().getBorrowed());
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void testGetID() {
        assertEquals(book1.getId(), "12345");
    }

    @Test
    public void testGetBookinfo() {
        assertEquals(book1.getInfo().getTitle(), "title");
    }

    @Test
    public void testEquals(){
        assertFalse(book1.equals(null));
        assertFalse(book1.equals(new Reader("name", "password")));
        assertFalse(book1.equals(new Book("1234", new BookInfo("", "title",
                "", "", "", ""))));
        assertTrue(book1.equals(book1));
        assertTrue(book1.equals(new Book("12345", new BookInfo("", "title",
                "", "", "", ""))));
        assertTrue(book1.equals(new Book("12345", new BookInfo("123", "title1",
                "123", "123", "123", "123"))));

    }

    @Test
    public void testHashCode(){
        assertTrue(book1.hashCode() == Objects.hash(book1.getId()));
    }

    @Test
    void testSubscribeThrowsNullArgumentException() {
        try {
            book1.subscribe(null);
            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        }
    }

    @Test
    void testSubscribe() {
        try {
            Reader r = new Reader("reader","password");
            assertEquals(book1.getFollowers().size(), 0);
            assertEquals(r.getSubscribeBooks().size(), 0);
            book1.subscribe(r);
            assertEquals(book1.getFollowers().size(), 1);
            assertEquals(r.getSubscribeBooks().size(), 1);
            book1.subscribe(r);
            assertEquals(book1.getFollowers().size(), 1);
            assertEquals(r.getSubscribeBooks().size(), 1);
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void testSubscribe2ThrowsEmptyStringException1() {
        try {
            Reader r = new Reader("reader","password");;
            ReaderList rl = new ReaderList();
            rl.addReader(r);
            book1.subscribe("", rl);

            fail("fail to throw EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testSubscribe2ThrowsEmptyStringException2() {
        try {
            Reader r = new Reader("reader","password");;
            ReaderList rl = new ReaderList();
            rl.addReader(r);
            book1.subscribe(null, rl);

            fail("fail to throw EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void ttestSubscribe2ThrowsNullArgumentException() {
        try {
            Reader r = new Reader("reader","password");;
            ReaderList rl = new ReaderList();
            rl.addReader(r);
            book1.subscribe("reader", null);

            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        }
    }

    @Test
    void testSubscribe2() {
        try {
            Reader r = new Reader("reader","password");;
            ReaderList rl = new ReaderList();
            rl.addReader(r);
            assertEquals(book1.getFollowers().size(), 0);
            assertEquals(r.getSubscribeBooks().size(), 0);

            book1.subscribe("reader", rl);

            assertEquals(book1.getFollowers().size(), 1);
            assertEquals(r.getSubscribeBooks().size(), 1);
            book1.subscribe("reader", rl);
            assertEquals(book1.getFollowers().size(), 1);
            assertEquals(r.getSubscribeBooks().size(), 1);
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void testUnSubscribeThrowsNullArgumentException() {
        try {
            Reader r = new Reader("reader","password");
            book1.subscribe(r);
            book1.unsubscribe(null);
            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        }
    }

    @Test
    void testUnSubscribe() {
        try {
            Reader r = new Reader("reader","password");
            Reader r2 = new Reader("reader2","password");
            Book book2 = new Book("12345678", new BookInfo("", "title",
                    "", "", "", ""));
            assertEquals(book1.getFollowers().size(), 0);
            assertEquals(r.getSubscribeBooks().size(), 0);
            book1.subscribe(r);
            book1.subscribe(r2);
            assertEquals(book1.getFollowers().get(0), ("reader"));
            assertEquals(book1.getFollowers().get(1), ("reader2"));
            assertEquals(book1.getFollowers().size(), 2);
            assertEquals(r.getSubscribeBooks().size(), 1);
            book1.unsubscribe(r);
            assertEquals(book1.getFollowers().size(), 1);
            assertEquals(r.getSubscribeBooks().size(), 0);
            book1.unsubscribe(r);
            assertEquals(book1.getFollowers().size(), 1);
            assertEquals(r2.getSubscribeBooks().size(), 1);
            book1.unsubscribe(r2);
            assertEquals(book1.getFollowers().size(), 0);
            assertEquals(r.getSubscribeBooks().size(), 0);
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void teattoString() {
        assertEquals(book1.toString(), ("ISBN number: 12345, Status: available\n" + "Title: title \n" +
                "Author: \n" + "Genre: ,Price: ,PublishDate: \n" + "Description:"));
    }


}