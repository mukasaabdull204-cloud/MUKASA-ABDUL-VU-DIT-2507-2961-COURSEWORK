import java.time.LocalDate;

/**
 * Connects exactly one Member to exactly one Book.
 * Records the borrow date and the due date (14 days by default).
 *
 * @author Mukasa
 */
public class Loan {

    private Member member;
    private Book   book;
    private LocalDate borrowDate;
    private LocalDate dueDate;

    /**
     * Creates a loan with today as the borrow date and a 14-day loan period.
     *
     * @param member  the member borrowing the book
     * @param book    the book being borrowed
     */
    public Loan(Member member, Book book) {
        this.member     = member;
        this.book       = book;
        this.borrowDate = LocalDate.now();
        this.dueDate    = borrowDate.plusDays(14);
    }

    /**
     * Overloaded constructor – caller supplies explicit dates.
     */
    public Loan(Member member, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.member     = member;
        this.book       = book;
        this.borrowDate = borrowDate;
        this.dueDate    = dueDate;
    }

    // ------------------------------------------------------------------ //
    //  Getters
    // ------------------------------------------------------------------ //

    public Member    getMember()     { return member; }
    public Book      getBook()       { return book; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate()    { return dueDate; }

    // ------------------------------------------------------------------ //
    //  toString
    // ------------------------------------------------------------------ //

    @Override
    public String toString() {
        return "Loan{Member='" + member.getName()
                + "', Book='" + book.getTitle()
                + "', Borrowed=" + borrowDate
                + ", Due=" + dueDate + "}";
    }
}
