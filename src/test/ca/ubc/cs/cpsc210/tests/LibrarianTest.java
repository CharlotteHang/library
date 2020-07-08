package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {
    private Librarian librarian1;


    @BeforeEach
    void setup() {
        try {
            librarian1 = new Librarian("username", "password", "position");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testConstructorThrowsEmptyStringExceptionUsername1() {
        try {
            Librarian librarian2 = new Librarian("", "password", "position");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsEmptyStringExceptionUsername2() {
        try {
            Librarian librarian2 = new Librarian(null, "password", "position");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsEmptyStringExceptionPassword1() {
        try {
            Librarian librarian2 = new Librarian("username", "", "position");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsEmptyStringExceptionPassword2() {
        try {
            Librarian librarian2 = new Librarian("username", null, "position");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    void testConstructorThrowsEmptyStringExceptionPosition1() {
        try {
            Librarian librarian2 = new Librarian("username", "password", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsEmptyStringExceptionPosition2() {
        try {
            Librarian librarian2 = new Librarian("username", "password", null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void getUsername() {
        assertEquals(librarian1.getusername(), "username");
    }

    @Test
    void getPassowrd() {
        assertEquals(librarian1.getpassowrd(), "password");
    }

    @Test
    void getPosition() {
        assertEquals(librarian1.getposition(), "position");
    }

    @Test
    public void testEquals(){
        Librarian librarian2 = new Librarian("username", "pass", "p");
        assertFalse(librarian1.equals(null));
        assertFalse(librarian1.equals(new Reader("name", "password")));
        assertFalse(librarian1.equals(new Librarian("user", "pass", "p")));
        assertTrue(librarian2.equals(librarian2));
        assertTrue(librarian2.equals(librarian1));
        assertTrue(librarian2.equals(new Librarian("username", "pass2", "p2")));

    }

    @Test
    public void testHashCode(){
        assertTrue(librarian1.hashCode() == Objects.hash(librarian1.getusername()));
    }
}
