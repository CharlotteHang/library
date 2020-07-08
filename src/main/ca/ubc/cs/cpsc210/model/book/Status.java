package ca.ubc.cs.cpsc210.model.book;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.exception.InvalidNumberException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Status {
    private boolean borrowed;
    private String borrowday;
    private String returnday;
    private String username;



    // MODIFIES: this
    // EFFECTS: set the status to 'has not been borrowed' buy default
    // this constructor is used when parsing data from the file containing the information of books in library
    public Status() {
        borrowed = false;
        borrowday = "";
        returnday = "";
        username = "";
    }

    // MODIFIES: this
    // EFFECTS: set the status to 'has been borrowed' with given borrowday, returnday, and username of reader who
    // borrowed it
    // this constructor is used when parsing data from the file containing borrowed books
    public Status(String borrowday, String returnday, String username) {
        if (borrowday == null || borrowday == "") {
            throw new EmptyStringException("please provide the borrow day");
        } else if (returnday == null || returnday == "") {
            throw new EmptyStringException("please provide the return day");
        } else if (username == null || username == "") {
            throw new EmptyStringException("please provide the username of the book");
        }
        //borrowday = Parseday.(borrowday);// exception of invalid date, exception of returnday before borrowday
        //returnday = Parseday.(returnday);
        this.username = username;
        borrowed = true;
        this.borrowday = borrowday;
        this.returnday = returnday;

    }

    // MODIFIES: this
    // EFFECTS: set the status to 'has been borrowed' with given daysofborrowing, and username of the reader
    // this constructor is used when a book is borrowed by a reader, the borrowday is set as today by default
    public Status(int daysofborrowing, String username) {
        if (daysofborrowing <= 0) {
            throw new InvalidNumberException("the number of days should be larger than 0");
        } else if (daysofborrowing > 365) {
            throw new InvalidNumberException("the number of days should be smaller than 365");
        } else if (username == null || username == "") {
            throw new EmptyStringException("please provide the username");
        }

        Calendar calendar = GregorianCalendar.getInstance();
        Date borrowdate = calendar.getTime();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("YYYY-MM-dd-EE"); // the day of the week abbreviated
        borrowday = simpleDateformat.format(borrowdate);
        calendar.add(Calendar.DATE, daysofborrowing);
        Date returndate = calendar.getTime();
        returnday = simpleDateformat.format(returndate);

        this.username = username;
        borrowed = true;

    }

    // EFFECTS: get whether a book has been borrowed
    public boolean getBorrowed() {
        return borrowed;
    }

    // EFFECTS: get the day that the book has been borrowed, if it has not been borrowed, just return empty String
    public String getborrowday() {
        return borrowday;
    }

    // EFFECTS: get the day that the book has been returned, if it has not been borrowed, just return empty String
    public String getreturnday() {
        return returnday;
    }


    // EFFECTS: get the username of the reader who borrowed the book,
    // if it has not been borrowed, just return empty String
    public String getUsername() {
        return username;
    }



    // EFFECTS: return a string representation of the status
    public String toString() {
        return (getBorrowed() ? ("borrowed (will be returned on " + returnday + ")") : "available");
    }



}
