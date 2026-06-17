/**
 * Represents a book in the community library system.
 * Encapsulates ISBN, title, author, and availability status.
 *
 * @author Mukasa
 */
public class Book {

    private String isbn;
    private String title;
    private String author;
    private boolean available;

    /**
     * Constructor 1 – minimum required fields; author defaults to "Unknown".
     */
    public Book(String isbn, String title) {
        this.isbn      = isbn;
        this.title     = title;
        this.author    = "Unknown";
        this.available = true;        // new books are available by default
    }

    /**
     * Constructor 2 (overload) – full details supplied at creation.
     */
    public Book(String isbn, String title, String author) {
        this.isbn      = isbn;
        this.title     = title;
        this.author    = author;
        this.available = true;
    }

    // ------------------------------------------------------------------ //
    //  Getters
    // ------------------------------------------------------------------ //

    public String getIsbn()      { return isbn; }
    public String getTitle()     { return title; }
    public String getAuthor()    { return author; }
    public boolean isAvailable() { return available; }

    // ------------------------------------------------------------------ //
    //  Setters
    // ------------------------------------------------------------------ //

    public void setIsbn(String isbn)        { this.isbn   = isbn; }
    public void setTitle(String title)      { this.title  = title; }
    public void setAuthor(String author)    { this.author = author; }
    public void setAvailable(boolean available) { this.available = available; }

    // ------------------------------------------------------------------ //
    //  toString
    // ------------------------------------------------------------------ //

    @Override
    public String toString() {
        return "Book{ISBN='" + isbn + "', Title='" + title
                + "', Author='" + author + "', Status="
                + (available ? "Available" : "On Loan") + "}";
    }
}