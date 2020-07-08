package ca.ubc.cs.cpsc210.model.librarian;

import ca.ubc.cs.cpsc210.model.exception.InvalidLibrarianException;
import ca.ubc.cs.cpsc210.model.exception.NullArgumentException;

import java.util.ArrayList;
import java.util.List;

public class LibrarianList {
    private List<Librarian> librarianlist;

    // MODIFIES: this
    // EFFECTS: creates a new LibrarianList
    public LibrarianList() {
        librarianlist = new ArrayList<>();
    }

    // EFFECTS: return the librarian if this username and password can log in, otherwise return null
    public Librarian logIn(String username, String password) {
        if (username == null || username == "" || password == null || password == "") {
            return null;
        }
        for (Librarian l : librarianlist) {
            if (l.getusername().equals(username) && l.getpassowrd().equals(password)) {
                return l;
            }
        }
        return null;
    }


    // MODIFIES: this
    // EFFECTS: add one librarian to the list of librarians (used for parsing)
    public void addonelibrarian(Librarian l) {
        if (l == null) {
            throw new NullArgumentException("please provide a librarian");
        } else if (librarianlist.contains(l)) {
            throw new InvalidLibrarianException("this username has already been used");
        }
        librarianlist.add(l);
    }

    // EFFECTS: return the list of Librarians
    public List<Librarian> getLibrarianlist() {
        return librarianlist;
    }
}
