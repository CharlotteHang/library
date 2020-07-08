package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.InvalidLibrarianException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianListTest {
    private LibrarianList librarianList1;

    @BeforeEach
    void setup() {
        try {
            librarianList1 = new LibrarianList();
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }


    @Test
    void testCanLogin() {
        try {
            librarianList1.addonelibrarian(new Librarian("username", "password", "position"));
            assertEquals(librarianList1.logIn("username", "password").getusername(), "username");
            assertEquals(librarianList1.logIn("username", "passwor"), null);
            assertEquals(librarianList1.logIn("usernam", "password"), null);
            assertEquals(librarianList1.logIn("", "passwor"), null);
            assertEquals(librarianList1.logIn("username", ""), null);
            assertEquals(librarianList1.logIn(null, "password"), null);
            assertEquals(librarianList1.logIn("username", null), null);

        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        }
    }

    @Test
    void testAddOneLibrarianThrowsNullArgumentException() {
        try {
            librarianList1.addonelibrarian(null);
            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            System.out.println("NullArgumentException");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }

    }

    @Test
    void testAddOneLibrarianThrowsInvalidLibrarianException() {
        try {
            librarianList1.addonelibrarian(new Librarian("username", "password", "position"));
            librarianList1.addonelibrarian(new Librarian("username", "password", "position"));
            fail("fail to throw NullArgumentException");
        } catch (NullArgumentException e) {
            fail("Caught unexpected NullArgumentException");
        } catch (InvalidLibrarianException e) {
            System.out.println("InvalidLibrarianException");
        }

    }

    @Test
    void testGetLibrarianList() {
        librarianList1.addonelibrarian(new Librarian("username", "password", "position"));
        librarianList1.addonelibrarian(new Librarian("user", "pass", "position"));
        assertTrue(librarianList1.getLibrarianlist().contains
                (new Librarian("username", "password", "position")));
        assertTrue(librarianList1.getLibrarianlist().contains
                (new Librarian("user", "password", "position")));
    }

}