package ca.ubc.cs.cpsc210.model.librarian;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;

import java.util.Objects;


public class Librarian {
    private String username;
    private String password;
    private String positionInfo;


    // MODIFIES: this
    // EFFECTS: creates a Librarian account with the given username, password and position information
    public Librarian(String username, String password, String positioninfo) {
        if (username == null || username == "") {
            throw new EmptyStringException("please provide the username of this librarian");
        } else if (password == null || password == "") {
            throw new EmptyStringException("please provide the password of this librarian");
        } else if (positioninfo == null) {
            throw new EmptyStringException("the position information of this librarian cannot be null");
        }
        this.username = username;
        this.password = password;
        this.positionInfo = positioninfo;
    }

    // EFFECTS: returns the username of librarian
    public String getusername() {
        return username;
    }

    // EFFECTS: returns the password of librarian
    public String getpassowrd() {
        return password;
    }

    // EFFECTS: returns the position of librarian
    public String getposition() {
        return positionInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Librarian librarian = (Librarian) o;
        return Objects.equals(username, librarian.username);
    }
    

    @Override
    public int hashCode() {

        return Objects.hash(username);
    }
}
