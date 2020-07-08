package ca.ubc.cs.cpsc210.model.book;

import ca.ubc.cs.cpsc210.model.exception.EmptyStringException;

public class BookInfo {
    private String author;
    private String title;
    private String genre;
    private String price;
    private String publishDate;
    private String description;

    // MODIFIES: this
    // EFFECTS: create a BookInfo with the given information about the book. The status is set
    // as 'not has been borrowed' by default
    public BookInfo(String author, String title, String genre,
                    String price, String publishDate, String description) {
        if (author == null) {
            throw new EmptyStringException("author should not be null");
        } else if (title == null || title.equals("")) {
            throw new EmptyStringException("Book title should be provided");
        } else if (genre == null) {
            throw new EmptyStringException("the genre should not be null");
        } else if (price == null) {
            throw new EmptyStringException("the price should not be null");
        } else if (publishDate == null) {
            throw new EmptyStringException("the publishDate should not be null");
        } else if (description == null) {
            throw new EmptyStringException("the description should not be null");
        }
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.publishDate = publishDate;
        this.description = description;
    }

    // EFFECTS: get the author of the book
    public String getAuthor() {
        return author;
    }

    // EFFECTS: get the title of the book
    public String getTitle() {
        return title;
    }

    // EFFECTS: get the genre of the book
    public String getGenre() {
        return genre;
    }

    // EFFECTS: get the price of the book
    public String getPrice() {
        return price;
    }

    // EFFECTS: get the publishDate of the book
    public String getPublishDate() {
        return publishDate;
    }

    // EFFECTS: get the description of the book
    public String getDescription() {
        return description;
    }

    // EFFECTS: return a string representation of the BookInfo
    public String toString() {
        return ("Title: " + title + " \n" + "Author:" + author + " \n" + "Genre:" + genre + " " + ",Price:" + price
                + " " + ",PublishDate:" + publishDate + " \n" + "Description:" + description);
    }


}
