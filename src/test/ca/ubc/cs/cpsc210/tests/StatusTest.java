package ca.ubc.cs.cpsc210.tests;

import ca.ubc.cs.cpsc210.model.book.Status;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class StatusTest {
    private Status s1;
    private Status s2;
    private Status s4;

    @BeforeEach
    void setup() {
        s1 = new Status();

        try {
            s2 = new Status("2019-09-01-Mon", "2019-09-02-Tue", "abc");
        } catch (EmptyStringException e) {
            fail("caught unexcepted EmptyStringException");
        }

        try {
            s4 = new Status(55, "def");
        } catch (EmptyStringException e) {
            fail("caught unexcepted Exception");
        }

    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionBorrowday1() {
        try {
            Status s3 = new Status("", "2019-09-01-Mon", "abc");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptiondayBorrowday2() {
        try {
            Status s3 = new Status(null, "2019-09-01-Mon", "abc");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionReturnday1() {
        try {
            Status s3 = new Status("2019-09-01-Mon", "", "abc");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionReturnday2() {
        try {
            Status s3 = new Status("2019-09-01-Mon", null, "abc");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }


    @Test
    void testConstructor1ThrowsEmptyStringExceptionUsername1() {
        try {
            Status s3 = new Status("2019-09-01-Mon", "2019-09-02-Tue", "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }

    @Test
    void testConstructor1ThrowsEmptyStringExceptionUsername2() {
        try {
            Status s3 = new Status("2019-09-01-Mon", "2019-09-02-Tue", null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        }
    }


    @Test
    void testConstructor2ThrowsInvalidNumberExceptionDays1() {
        try {
            s4 = new Status(-1, "def");
            fail("fail to throw InvalidNumberException");
        } catch (EmptyStringException e) {
            fail("caught unexcepted EmptyStringException");
        } catch (InvalidNumberException e) {
            System.out.println("InvalidNumberException");
        }
    }

    @Test
    void testConstructor2ThrowsInvalidNumberExceptionDays2() {
        try {
            s4 = new Status(0, "def");
            fail("fail to throw InvalidNumberException");
        } catch (EmptyStringException e) {
            fail("caught unexcepted EmptyStringException");
        } catch (InvalidNumberException e) {
            System.out.println("InvalidNumberException");
        }
    }

    @Test
    void testConstructor2Days3() {
        try {
            s4 = new Status(1, "def");
        } catch (EmptyStringException e) {
            fail("caught unexcepted EmptyStringException");
        } catch (InvalidNumberException e) {
            fail("caught unexcepted InvalidNumberException");
        }
    }


    @Test
    void testConstructor2ThrowsInvalidNumberExceptionDays4() {
        try {
            s4 = new Status(366, "def");
            fail("fail to throw InvalidNumberException");
        } catch (EmptyStringException e) {
            fail("caught unexcepted EmptyStringException");
        } catch (InvalidNumberException e) {
            System.out.println("InvalidNumberException");
        }
    }

    @Test
    void testConstructor2Days5() {
        try {
            s4 = new Status(365, "def");
        } catch (EmptyStringException e) {
            fail("caught unexcepted EmptyStringException");
        } catch (InvalidNumberException e) {
            fail("caught unexcepted InvalidNumberException");
        }
    }

    @Test
    void testConstructor2ThrowsEmptyStringExceptionUsername1() {
        try {
            s4 = new Status(1, "");
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidNumberException e) {
            fail("caught unexcepted InvalidNumberException");
        }
    }

    @Test
    void testConstructor2ThrowsEmptyStringExceptionUsername2() {
        try {
            s4 = new Status(1, null);
            fail("fail to throw EmptyStringException");
        } catch (EmptyStringException e) {
            System.out.println("EmptyStringException");
        } catch (InvalidNumberException e) {
            fail("caught unexcepted InvalidNumberException");
        }
    }


    @Test
    void testGetBorrowed() {
        assertFalse(s1.getBorrowed());
        assertTrue(s2.getBorrowed());
        assertTrue(s4.getBorrowed());

    }

    @Test
    void testgetBorrowDay() {
        assertEquals(s1.getborrowday(), "");
        assertEquals(s2.getborrowday(), "2019-09-01-Mon");
        Calendar calendar = GregorianCalendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        assertEquals(Integer.parseInt(s4.getborrowday().substring(5, 7)), month + 1);
        int year = calendar.get(Calendar.YEAR);
        assertEquals(Integer.parseInt(s4.getborrowday().substring(0, 4)), year);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        assertEquals(Integer.parseInt(s4.getborrowday().substring(8, 10)), day);
        System.out.println(s4.getborrowday());
    }


    @Test
    void testgetReturnDay() {
        assertEquals(s1.getreturnday(), "");
        assertEquals(s2.getreturnday(), "2019-09-02-Tue");
        System.out.println(s4.getborrowday());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, 55);
        int month = calendar.get(Calendar.MONTH);
        System.out.println(month);
        assertEquals(Integer.parseInt(s4.getreturnday().substring(5, 7)), month + 1);
        int year = calendar.get(Calendar.YEAR);
        assertEquals(Integer.parseInt(s4.getreturnday().substring(0, 4)), year);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        assertEquals(Integer.parseInt(s4.getreturnday().substring(8, 10)), day);
        System.out.println(s4.getreturnday());
    }


    @Test
    void testGetUsername() {
        assertEquals(s1.getUsername(), "");
        assertEquals(s2.getUsername(), "abc");
        assertEquals(s4.getUsername(), "def");
    }

    @Test
    void testToString() {
        assertEquals(s1.toString(), "available");
        assertEquals(s2.toString(), "borrowed (will be returned on 2019-09-02-Tue)");
    }


}
