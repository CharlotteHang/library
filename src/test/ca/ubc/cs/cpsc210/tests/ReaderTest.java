package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookInfo;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.InvalidBorrowBookException;
import ca.ubc.cs.cpsc210.model.exception.InvalidReturnBookException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderTest {
    Reader reader1;
    Reader reader2;

    @BeforeEach
    public void setup() {
        try {
            reader1 = new Reader("username1", "password1");
            List booklist = new ArrayList<>();
            booklist.add("book1");
            booklist.add("book2");
            booklist.add("book3");
            reader2 = new Reader("username2", "password2", booklist);
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }

    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionUsername1() {
        try {
            Reader reader3 = new Reader("", "password");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionUsername2() {
        try {
            Reader reader3 = new Reader(null, "password");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionPassword1() {
        try {
            Reader reader3 = new Reader("username", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionPassword2() {
        try {
            Reader reader3 = new Reader("username", null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor2ThrowsEmptyStringExceptionUsername1() {
        try {
            Reader reader3 = new Reader("", "password2", new ArrayList<String>());
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void testConstructor2ThrowsEmptyStringExceptionUsername2() {
        try {
            Reader reader3 = new Reader(null, "password2", new ArrayList<String>());
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void testConstructor2ThrowsEmptyStringExceptionPassword1() {
        try {
            Reader reader3 = new Reader("username", "", new ArrayList<String>());
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void testConstructor2ThrowsEmptyStringExceptionPassword2() {
        try {
            Reader reader3 = new Reader("username", null, new ArrayList<String>());
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        }
    }

    @Test
    void testConstructor2ThrowsNullArgumentExceptionBookIds() {
        try {
            reader2 = new Reader("username2", "password2", null);
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        }
    }

    @Test
    void testGetUsername() {
        assertEquals(reader1.getusername(), "username1");
        assertEquals(reader2.getusername(), "username2");
    }

    @Test
    void testGetPassword() {
        assertEquals(reader1.getpassowrd(), "password1");
        assertEquals(reader2.getpassowrd(), "password2");
    }

    @Test
    void testGetBooks() {
        assertEquals(reader1.getBorrowedBooksByName().size(), 0);
        assertEquals(reader2.getBorrowedBooksByName().size(), 3);
    }

    @Test
    void testIsBorrowedThrowsEmptyStringException1() {
        try {
            reader2.isBorrowed("");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testIsBorrowedThrowsEmptyStringException2() {
        try {
            reader2.isBorrowed(null);
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testIsBorrowed() {
        try {
            assertFalse(reader1.isBorrowed("book1"));
            assertTrue(reader2.isBorrowed("book1"));
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        }
    }

    @Test
    void testAddBorrow() {
        try {
            reader2.borrowBook("book3");
            assertTrue(reader2.isBorrowed("book3"));
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidBorrowBookException e) {
            fail("Caught unexpected InvalidBorrowBookException");
        }
    }

    @Test
    void testAddBorrowNumber() {
        try {
            for (int i = 0; i < reader1.maxBookNumber; i++) {
                reader1.borrowBook("book3");
            }
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidBorrowBookException e) {
            fail("Caught unexpected InvalidBorrowBookException");
        }
    }

    @Test
    void testAddBorrowNumber2() {
        try {
            for (int i = 0; i <= reader1.maxBookNumber; i++) {
                reader1.borrowBook("book3");
            }
            fail("fail to throw InvalidBorrowBookException");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidBorrowBookException e) {
            System.out.println("InvalidBorrowBookException");
        }
    }


    @Test
    void testAddBorrowThrowsEmptyStringException1() {
        try {
            reader2.borrowBook("");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidBorrowBookException e) {
            fail("Caught unexpected InvalidBorrowBookException");
        }
    }

    @Test
    void testAddBorrowThrowsEmptyStringException2() {
        try {
            reader2.borrowBook(null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidBorrowBookException e) {
            fail("Caught unexpected InvalidBorrowBookException");
        }
    }

    @Test
    void testReturnbook1() {
        try {
            assertTrue(reader2.isBorrowed("book1"));
            assertTrue(reader2.isBorrowed("book2"));
            reader2.returnBook("book2");
            assertTrue(reader2.isBorrowed("book1"));
            assertFalse(reader2.isBorrowed("book2"));
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReturnBookException e) {
            fail("Caught unexpected InvalidReturnBookException");
        }
    }

    @Test
    void testReturnbook2() {
        try {
            assertTrue(reader2.isBorrowed("book1"));
            assertTrue(reader2.isBorrowed("book2"));
            assertTrue(reader2.isBorrowed("book3"));
            reader2.returnBook("book3");
            assertTrue(reader2.isBorrowed("book2"));
            assertFalse(reader2.isBorrowed("book3"));
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReturnBookException e) {
            fail("Caught unexpected InvalidReturnBookException");
        }
    }

    @Test
    void testReturnbookThrowsEmptyStringException() {
        try {
            reader2.returnBook("");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReturnBookException e) {
            fail("Caught unexpected InvalidReturnBookException");
        }
    }

    @Test
    void testReturnbookThrowsEmptyStringException2() {
        try {
            reader2.returnBook(null);
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidReturnBookException e) {
            fail("Caught unexpected InvalidReturnBookException");
        }
    }


    @Test
    void testReturnbookThrowsInvalidReturnBookException() {
        try {
            reader2.returnBook("book4");
            fail("fail to throw NullArgumentException");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        } catch (InvalidReturnBookException e) {
            System.out.println("InvalidReturnBookException");
        }
    }



    @Test
    public void testEquals(){
        assertFalse(reader1.equals(null));
        assertFalse(reader1.equals(new Reader("username", "pass")));
        assertTrue(reader1.equals(new Reader("username1", "pass")));
        assertTrue(reader1.equals(reader1));
        assertFalse(reader1.equals(new Librarian("username1", "password1", "position")));

    }

    @Test
    public void testHashCode(){
        assertTrue(reader1.hashCode() == Objects.hash(reader1.getusername()));
    }

    @Test
    public void testNotifications() {
        for (int i = 0; i < Reader.maxBookNumber ; i++) {
            reader1.update(null,Integer.toString(i));
            assertTrue(reader1.getNotifications().get(i).equals(Integer.toString(i)));
        }
        reader1.update(null,Integer.toString(Reader.maxBookNumber));
        assertTrue(reader1.getNotifications().get(Reader.maxBookNumber - 1).equals(Integer.toString(Reader.maxBookNumber)));

        reader2.setNotifications(reader1.getNotifications());
        System.out.println(reader2.getNotifications().size());
        for (int i = 0; i < Reader.maxBookNumber - 1 ; i++) {
            //System.out.println(i);
            assertTrue(reader2.getNotifications().get(i).equals(Integer.toString(i + 1)));
            //System.out.println(reader2.getNotifications().get(i));

        }
        assertTrue(reader2.getNotifications().get(Reader.maxBookNumber - 1).equals(Integer.toString(Reader.maxBookNumber)));
    }

    @Test
    public void testNotificationsToString(){
        for (int i = 0; i < 3 ; i++) {
            reader1.update(null,Integer.toString(i));
        }
        System.out.println(reader1.notificationsToString());
        assertEquals(reader1.notificationsToString(), "0\n" +
                "1\n" +
                "2\n");
    }

    @Test
    public void testdeleteNotifications() {
        for (int i = 0; i < Reader.maxBookNumber; i++) {
            reader1.update(null, Integer.toString(i));
        }
        assertEquals(reader1.getNotifications().size(), Reader.maxBookNumber);
        reader1.deleteNotifications();
        assertEquals(reader1.getNotifications().size(), 0);
    }

    @Test
    public void testGetBorrowedBooks(){
        BookInfo bookinfo1 = new BookInfo("", "title1", "",
                "", "", "");
        BookInfo bookinfo2 = new BookInfo("", "title2", "",
                "", "", "");
        Book book1 = new Book("booka",bookinfo1);
        Book book2 = new Book("bookb",bookinfo1);
        Book book3 = new Book("bookc",bookinfo2);
        Book book4 = new Book("bookd",bookinfo2);
        Book book5 = new Book("booke",bookinfo2);

        BookList booklist1 = new BookList();
        booklist1.addBook(book1);
        booklist1.addBook(book2);
        booklist1.addBook(book3);
        booklist1.addBook(book4);
        booklist1.addBook(book5);

        reader1.borrowBook("booka");
        reader1.borrowBook("bookb");
        reader1.borrowBook("bookc");

        assertEquals(reader1.getBorrowedBooks(booklist1).size(),3);
        assertEquals(reader1.getBorrowedBooks(booklist1).get(2).getId(),"bookc");

    }

}
