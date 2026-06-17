import java.util.ArrayList;
import java.util.List;

/**
 * Central class that manages the collection of books and members.
 *
 * Relationship justification:
 *  - Library <>--> Book   : COMPOSITION  – books are created and owned by the
 *                           library; they do not exist independently of it.
 *  - Library <>--> Member : COMPOSITION  – member registrations belong to this
 *                           library; they cease to exist when the library closes.
 *  - Loan  -->  Book/Member : ASSOCIATION – a loan merely references existing
 *                           objects; destroying a loan does not destroy the book
 *                           or member.
 *
 * @author Mukasa
 */
public class Library {

    private List<Book>   books;      // composition: Library owns its books
    private List<Member> members;    // composition: Library owns its members
    private List<Loan>   activeLoans;

    // ------------------------------------------------------------------ //
    //  Constructor
    // ------------------------------------------------------------------ //

    public Library() {
        books       = new ArrayList<>();
        members     = new ArrayList<>();
        activeLoans = new ArrayList<>();
    }

    // ------------------------------------------------------------------ //
    //  Add operations
    // ------------------------------------------------------------------ //

    /** Adds a new book to the library's collection. */
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    /** Registers a new member with the library. */
    public void registerMember(Member member) {
        members.add(member);
        System.out.println("Member registered: " + member.getName());
    }

    // ------------------------------------------------------------------ //
    //  Lending & returning
    // ------------------------------------------------------------------ //

    /**
     * Lends a book to a member.
     * Business rule: a book may be on at most one active loan at a time.
     *
     * @param memberId the ID of the borrowing member
     * @param isbn     the ISBN of the book to lend
     */
    public void lendBook(String memberId, String isbn) {
        Book   book   = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null) {
            System.out.println("ERROR: Book with ISBN '" + isbn + "' not found.");
            return;
        }
        if (member == null) {
            System.out.println("ERROR: Member with ID '" + memberId + "' not found.");
            return;
        }
        if (!book.isAvailable()) {
            // Graceful rejection – business rule enforced here
            System.out.println("REJECTED: '" + book.getTitle()
                    + "' is already on loan. Cannot lend again until returned.");
            return;
        }

        // Create the loan, update availability, link to member
        Loan loan = new Loan(member, book);
        book.setAvailable(false);
        member.addLoan(loan);
        activeLoans.add(loan);

        System.out.println("SUCCESS: '" + book.getTitle()
                + "' lent to " + member.getName()
                + ". Due: " + loan.getDueDate());
    }

    /**
     * Returns a book to the library.
     *
     * @param isbn the ISBN of the book being returned
     */
    public void returnBook(String isbn) {
        Loan loanToRemove = null;

        for (Loan loan : activeLoans) {
            if (loan.getBook().getIsbn().equals(isbn)) {
                loanToRemove = loan;
                break;
            }
        }

        if (loanToRemove == null) {
            System.out.println("ERROR: No active loan found for ISBN '" + isbn + "'.");
            return;
        }

        loanToRemove.getBook().setAvailable(true);
        loanToRemove.getMember().removeLoan(loanToRemove);
        activeLoans.remove(loanToRemove);

        System.out.println("SUCCESS: '" + loanToRemove.getBook().getTitle()
                + "' returned by " + loanToRemove.getMember().getName() + ".");
    }

    // ------------------------------------------------------------------ //
    //  Search
    // ------------------------------------------------------------------ //

    /**
     * Searches for books whose title contains the given keyword (case-insensitive).
     *
     * @param keyword partial or full title to search for
     */
    public void searchByTitle(String keyword) {
        System.out.println("\n--- Search results for \"" + keyword + "\" ---");
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  " + b);
                found = true;
            }
        }
        if (!found) System.out.println("  No matching books found.");
    }

    // ------------------------------------------------------------------ //
    //  State display
    // ------------------------------------------------------------------ //

    /** Prints the full state of the library (all books and members). */
    public void printState() {
        System.out.println("\n========== LIBRARY STATE ==========");
        System.out.println("--- Books (" + books.size() + ") ---");
        for (Book b : books)      System.out.println("  " + b);

        System.out.println("--- Members (" + members.size() + ") ---");
        for (Member m : members)  System.out.println("  " + m);

        System.out.println("--- Active Loans (" + activeLoans.size() + ") ---");
        for (Loan l : activeLoans) System.out.println("  " + l);
        System.out.println("====================================\n");
    }

    // ------------------------------------------------------------------ //
    //  Private helpers
    // ------------------------------------------------------------------ //

    private Book findBookByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    private Member findMemberById(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) return m;
        }
        return null;
    }

    // ------------------------------------------------------------------ //
    //  Getters
    // ------------------------------------------------------------------ //

    public List<Book>   getBooks()       { return books; }
    public List<Member> getMembers()     { return members; }
    public List<Loan>   getActiveLoans() { return activeLoans; }

    @Override
    public String toString() {
        return "Library{Books=" + books.size()
                + ", Members=" + members.size()
                + ", ActiveLoans=" + activeLoans.size() + "}";
    }
}
