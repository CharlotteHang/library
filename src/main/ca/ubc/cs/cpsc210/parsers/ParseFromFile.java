package ca.ubc.cs.cpsc210.parsers;


import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ParseFromFile {

    // just for the code coverage of ParseFromFile
    public ParseFromFile() {
    }


    // EFFECTS: parse the data from JSON file to get ReaderList object
    public static ReaderList readerlistParser(String readerfile) {
        if (readerfile == null) {
            throw new EmptyStringException("please provide the filename containing data of readers");
        }
        JSONArray readerArray;
        ReaderList readerlist = new ReaderList();
        try {
            Scanner scanner = new Scanner(new File(readerfile));
            //Constructs a new <code>Scanner</code> that produces values scanned
            //from the specified file.
            String jsonString = scanner.useDelimiter("\\A").next();
            //\A The beginning of the input
            scanner.close();

            //jsonString = new String(Files.readerAllBytes(Paths.get(readerfile)))

            JSONObject jsonObject = new JSONObject(jsonString);
            readerArray = jsonObject.getJSONArray("Readers");
            readerlist = DataParser.readerdataParser(readerArray);

        } catch (FileNotFoundException e) {
            System.out.println("this bookfile name does not exist");
        }
        return readerlist;
    }




    // EFFECTS: parse the data from JSON file to get BookList object
    public static BookList booklistParser(String bookfile, ReaderList readerlist) {
        if (bookfile == null) {
            throw new EmptyStringException("please provide the name of the file containing data of books");
        }
        JSONArray bookArray;
        BookList booklist = new BookList();
        try {
            Scanner scanner = new Scanner(new File(bookfile));
            String jsonString = scanner.useDelimiter("\\A").next();
            scanner.close();

            JSONObject jsonObject = new JSONObject(jsonString);
            bookArray = jsonObject.getJSONArray("Books");
            booklist = DataParser.bookdataParser(bookArray, readerlist);

        } catch (FileNotFoundException e) {
            System.out.println("this reader file name does not exist");
        }
        return booklist;
    }




    // EFFECTS: parse the data from JSON file to get LibrarianList object
    public static LibrarianList librarianListParser(String librianfile) {
        if (librianfile == null) {
            throw new EmptyStringException("please provide the filename containing data of librarians");
        }
        JSONArray librarianArray;
        LibrarianList librarianList = new LibrarianList();
        try {
            Scanner scanner = new Scanner(new File(librianfile));
            String jsonString = scanner.useDelimiter("\\A").next();
            scanner.close();

            JSONObject jsonObject = new JSONObject(jsonString);
            librarianArray = jsonObject.getJSONArray("Librarians");
            librarianList = DataParser.librariandataParser(librarianArray);

        } catch (FileNotFoundException e) {
            System.out.println("this librarian file name does not exist");
        }
        return librarianList;
    }


}
