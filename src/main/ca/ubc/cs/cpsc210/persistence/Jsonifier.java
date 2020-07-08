package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Jsonifier {

    // just for the code coverage of Jsonifier
    public Jsonifier() {

    }

    // EFFECTS: convert the Set of books into JSON data
    public static JSONArray booklistJsonifier(Set<Map.Entry<String, Book>> entrySet) {
        JSONArray array = new JSONArray();
        for (Map.Entry<String, Book> entry : entrySet) {
            JSONObject element = new JSONObject();

            element = addStatusJsonifier(entry, element);
            element = addInfoJsonifier(entry, element);
            List<String> listoffollowers = entry.getValue().getFollowers();
            JSONObject followers = listOfStringToJsonObject("follower", listoffollowers);
            element.put("Followers", followers);
            array.put(element);
        }
        return array;
    }

    // EFFECTS: convert the information of books into JSON data
    public static JSONObject addInfoJsonifier(Map.Entry<String, Book> entry, JSONObject element) {
        String id = entry.getValue().getId();
        element.put("ISBN", id);
        String author = entry.getValue().getInfo().getAuthor();
        element.put("Author", author);
        String title = entry.getValue().getInfo().getTitle();
        element.put("Title", title);
        String genre = entry.getValue().getInfo().getGenre();
        element.put("Genre", genre);
        String price = entry.getValue().getInfo().getPrice();
        element.put("Price", price);
        String publishDate = entry.getValue().getInfo().getPublishDate();
        element.put("PublishDate", publishDate);
        String description = entry.getValue().getInfo().getDescription();
        element.put("Description", description);
        return element;
    }

    // EFFECTS: convert the status of books into JSON data
    public static JSONObject addStatusJsonifier(Map.Entry<String, Book> entry, JSONObject element) {
        String borrowday = "";
        String returnday = "";
        String username = "";
        if (entry.getValue().getStatus().getBorrowed()) {
            element.put("Borrow", "true");
            borrowday = entry.getValue().getStatus().getborrowday();
            returnday = entry.getValue().getStatus().getreturnday();
            username = entry.getValue().getStatus().getUsername();
        } else {
            element.put("Borrow", "false");
        }
        element.put("BorrowDay", borrowday);
        element.put("ReturnDay", returnday);
        element.put("Username", username);
        return element;
    }


    // EFFECTS: convert the Set of readers into JSON data
    public static JSONArray readerlistJsonifier(Set<Map.Entry<String, Reader>> entrySet) {
        JSONArray array = new JSONArray();
        for (Map.Entry<String, Reader> entry : entrySet) {
            JSONObject element = new JSONObject();

            String username = entry.getValue().getusername();
            element.put("Username", username);
            String password = entry.getValue().getpassowrd();
            element.put("Password", password);
            List<String> bookIds = entry.getValue().getBorrowedBooksByName();
            JSONObject books = listOfStringToJsonObject("book", bookIds);
            element.put("Books", books);
            List<String> listofnotifications = entry.getValue().getNotifications();
            JSONObject notifications = listOfStringToJsonObject("Notification", listofnotifications);
            element.put("Notifications", notifications);
            array.put(element);
        }
        return array;
    }

    // EFFECTS: convert a list of String into a JSON Object
    public static JSONObject listOfStringToJsonObject(String elementname, List<String> listofstring) {
        JSONObject object = new JSONObject();
        for (int i = 0; i < listofstring.size(); i++) {
            object.put((elementname + Integer.toString(i + 1)), listofstring.get(i));
        }

        return object;
    }


    // EFFECTS: convert the list of Librarians into JSON data
    public static JSONArray librarylistJsonifier(List<Librarian> l) {
        JSONArray array = new JSONArray();
        for (Librarian librarian : l) {
            JSONObject element = new JSONObject();

            String username = librarian.getusername();
            element.put("Username", username);
            String password = librarian.getpassowrd();
            element.put("Password", password);
            String positionInfo = librarian.getposition();
            element.put("PositionInfo", positionInfo);

            array.put(element);
        }
        return array;
    }
}
