import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library member.
 * Holds a member ID, a name, and the list of loans currently held.
 *
 * @author Mukasa
 */
public class Member {

    private String     memberId;
    private String     name;
    private List<Loan> loans;        // all active loans for this member

    // ------------------------------------------------------------------ //
    //  Constructors
    // ------------------------------------------------------------------ //

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name     = name;
        this.loans    = new ArrayList<>();
    }

    // ------------------------------------------------------------------ //
    //  Getters
    // ------------------------------------------------------------------ //

    public String     getMemberId() { return memberId; }
    public String     getName()     { return name; }
    public List<Loan> getLoans()    { return loans; }

    // ------------------------------------------------------------------ //
    //  Setters
    // ------------------------------------------------------------------ //

    public void setMemberId(String memberId) { this.memberId = memberId; }
    public void setName(String name)         { this.name     = name; }

    // ------------------------------------------------------------------ //
    //  Loan management helpers
    // ------------------------------------------------------------------ //

    /** Adds a loan to this member's active loan list. */
    public void addLoan(Loan loan) {
        loans.add(loan);
    }

    /** Removes a loan from this member's active loan list when a book is returned. */
    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }

    // ------------------------------------------------------------------ //
    //  toString
    // ------------------------------------------------------------------ //

    @Override
    public String toString() {
        return "Member{ID='" + memberId + "', Name='" + name
                + "', ActiveLoans=" + loans.size() + "}";
    }
}