package ca.ubc.cs.cpsc210.tests;


import ca.ubc.cs.cpsc210.model.book.BookInfo;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BookInfoTest {
    private BookInfo bookinfo;

    @BeforeEach
    void setup() {
        try {
            bookinfo = new BookInfo("1", "2",
                    "3", "4", "5", "yu");
        } catch (Exception e) {
            fail("Caught unexpected Exception");
        }
    }

    @Test
    void testConstructor() {
        try {
            bookinfo = new BookInfo("", "title",
                    "", "", "", "");
        } catch (EmptyStringException e) {
            fail("Caught unexpected EmptyStringException");
        }
    }


    @Test
    void testConstructorThrowsNullArgumentExceptionAuthor() {
        try {
            bookinfo = new BookInfo(null, "title",
                    "", "", "", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsNullArgumentExceptionTitle() {
        try {
            bookinfo = new BookInfo("", null,
                    "", "", "", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsNullArgumentExceptionTitle2() {
        try {
            bookinfo = new BookInfo("", "",
                    "", "", "", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsNullArgumentExceptionGenre() {
        try {
            bookinfo = new BookInfo("", "title",
                    null, "", "", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsNullArgumentExceptionPrice() {
        try {
            bookinfo = new BookInfo("", "title",
                    "", null, "", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsNullArgumentExceptionPublishDate() {
        try {
            bookinfo = new BookInfo("", "title",
                    "", "", null, "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructorThrowsNullArgumentExceptionPublishDescription() {
        try {
            bookinfo = new BookInfo("", "title",
                    "", "", "", null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testGet() {
        assertEquals(bookinfo.getAuthor(), "1");
        assertEquals(bookinfo.getTitle(), "2");
        assertEquals(bookinfo.getGenre(), "3");
        assertEquals(bookinfo.getPrice(), "4");
        assertEquals(bookinfo.getPublishDate(), "5");
        assertEquals(bookinfo.getDescription(), "yu");
    }


}
