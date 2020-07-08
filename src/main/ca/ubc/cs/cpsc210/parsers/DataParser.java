package ca.ubc.cs.cpsc210.parsers;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookInfo;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.book.Status;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    // just for the code coverage of DataParser, or the coverage report of DataParser shows that this class
    // has not been called
    public DataParser() {
    }


    // EFFECTS: parses JSONArray to build the ReaderList
    public static ReaderList readerdataParser(JSONArray readerArray) {
        ReaderList readerlist = new ReaderList();
        for (Object object: readerArray) {
            JSONObject jsonReader = (JSONObject) object;
            String username = jsonReader.getString("Username");
            String password = jsonReader.getString("Password");
            JSONObject books = (JSONObject) jsonReader.get("Books");

            //bug: notifications not Notifications
            JSONObject notifications = (JSONObject) jsonReader.get("Notifications");
            List<String> bookIds = jasonObjectParser("book", books);
            List<String> readernotifications = jasonObjectParser("Notification", notifications);

            Reader reader = new Reader(username, password, bookIds);
            reader.setNotifications(readernotifications);
            readerlist.addReader(reader);
        }
        return readerlist;
    }



    // EFFECTS: parses the JSONOBjects whose data includes a list of Strings
    public static List<String> jasonObjectParser(String elementname, JSONObject object) {
        List<String> listofstring = new ArrayList<>();
        for (int i = 0; i < object.length(); i++) {
            listofstring.add(object.getString(elementname + Integer.toString(i + 1)));
        }
        return listofstring;
    }





    // EFFECTS: parses JSONArray to build the BookList, readerlist is used for building the observers
    public static BookList bookdataParser(JSONArray bookArray, ReaderList readerlist) {
        BookList booklist = new BookList();
        for (Object object: bookArray) {
            JSONObject jsonBook = (JSONObject) object;
            String id = jsonBook.getString("ISBN");
            BookInfo bookinfo = bookInfoParser(jsonBook);
            JSONObject followers = (JSONObject) jsonBook.get("Followers");
            List<String> listoffollowers = jasonObjectParser("follower", followers);
            Status status = bookstatusParser(jsonBook);
            Book newbook = new Book(id, bookinfo);
            newbook.setStatusInDaraParsing(status);
            //bug
            addObservers(newbook, listoffollowers, readerlist);
            booklist.addBook(newbook);
        }
        return booklist;
    }


    // EFFECTS: parses JSONObject to build the BookInfo of a book
    public static BookInfo bookInfoParser(JSONObject jsonBook) {
        String author = jsonBook.getString("Author");
        String title = jsonBook.getString("Title");
        String genre = jsonBook.getString("Genre");
        String price = jsonBook.getString("Price");
        String publishDate = jsonBook.getString("PublishDate");
        String description = jsonBook.getString("Description");
        BookInfo bookinfo = new BookInfo(author, title, genre, price, publishDate, description);
        return bookinfo;
    }

    // EFFECTS: parses JSONObject to build the status of a book
    public static Status bookstatusParser(JSONObject jsonBook) {
        String borrow = jsonBook.getString("Borrow");
        Status bookstatus = new Status();
        if (borrow.equals("true")) {
            String borrowday = jsonBook.getString("BorrowDay");
            String returnday = jsonBook.getString("ReturnDay");
            String username = jsonBook.getString("Username");
            bookstatus = new Status(borrowday, returnday, username);
        }
        return bookstatus;
    }


    // EFFECTS: add in all the followers to a book
    public static void addObservers(Book newbook, List<String> listoffollowers, ReaderList readerlist) {
        for (String s: listoffollowers) {
            newbook.subscribe(readerlist.searchReader(s));
        }
    }






    // EFFECTS: parses JSONArray to build the LibrarianList
    public static LibrarianList librariandataParser(JSONArray librarianArray) {
        LibrarianList librarianList = new LibrarianList();
        for (Object object: librarianArray) {
            JSONObject jsonLibrarian = (JSONObject) object;
            String username = jsonLibrarian.getString("Username");
            String password = jsonLibrarian.getString("Password");
            String positionInfo = jsonLibrarian.getString("PositionInfo");
            Librarian newLibrarian = new Librarian(username, password, positionInfo);
            librarianList.addonelibrarian(newLibrarian);
        }
        return librarianList;
    }


}
