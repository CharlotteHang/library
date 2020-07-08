package ca.ubc.cs.cpsc210.persistence;

import ca.ubc.cs.cpsc210.model.book.Book;
import ca.ubc.cs.cpsc210.model.book.BookList;
import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;
import ca.ubc.cs.cpsc210.model.librarian.Librarian;
import ca.ubc.cs.cpsc210.model.librarian.LibrarianList;
import ca.ubc.cs.cpsc210.model.reader.Reader;
import ca.ubc.cs.cpsc210.model.reader.ReaderList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Persistence {
    BookList booklist;
    ReaderList readerlist;
    LibrarianList librarianlist;


    // EFFECTS: create a new parser
    public Persistence(BookList booklist, ReaderList readerlist, LibrarianList librarianlist) {
        this.booklist = booklist;
        this.readerlist = readerlist;
        this.librarianlist = librarianlist;
    }


    // EFFECTS: write the content of  BookList object into a JSON file
    public void booklistPersistence(String bookfile) {
        if (bookfile == null) {
            throw new EmptyStringException("please provide the name of the file storing data of books");
        }
        JSONObject all = new JSONObject();
        Set<Map.Entry<String, Book>> entrySet = booklist.getBookList().entrySet();
        JSONArray array  = Jsonifier.booklistJsonifier(entrySet);
        all.put("Books", array);
        try {
            FileWriter file = new FileWriter(bookfile);
            // FileOutputStream file = new FileOutputStream(bookfile);
            file.write(all.toString(4));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // EFFECTS: write the content of  ReaderList object into a JSON file
    public void readerlistPersistence(String readerfile) {
        if (readerfile == null) {
            throw new EmptyStringException("please provide the filename containing data of readers");
        }
        JSONObject all = new JSONObject();
        Set<Map.Entry<String, Reader>> entrySet = readerlist.getReaderList().entrySet();
        JSONArray array = Jsonifier.readerlistJsonifier(entrySet);
        all.put("Readers", array);
        try {
            FileWriter file = new FileWriter(readerfile);
            file.write(all.toString(4));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // EFFECTS: write the content of  LibrarianList object into a JSON file
    public void librarylistPersistence(String librianfile) {
        if (librianfile == null) {
            throw new EmptyStringException("please provide the filename containing data of librarians");
        }
        JSONObject all = new JSONObject();
        List<Librarian> l = librarianlist.getLibrarianlist();
        JSONArray array = Jsonifier.librarylistJsonifier(l);
        all.put("Librarians", array);
        try {
            FileWriter file = new FileWriter(librianfile);
            file.write(all.toString(4));
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
