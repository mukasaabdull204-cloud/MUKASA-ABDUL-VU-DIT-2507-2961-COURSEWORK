/**
 * LibraryDemo – driver class for the Community Library system.
 *
 * Demonstrates:
 *  - Adding books and registering members
 *  - Successful lend and return operations
 *  - A correctly rejected duplicate-loan attempt
 *  - Library state printed before and after operations
 *
 * @author Mukasa
 */
public class LibraryDemo {

    public static void main(String[] args) {

        // ---------------------------------------------------------- //
        //  1. Set up the library
        // ---------------------------------------------------------- //
        Library library = new Library();

        // Register two members
        Member alice = new Member("M001", "Alice Nakato");
        Member bob   = new Member("M002", "Bob Wasswa");
        library.registerMember(alice);
        library.registerMember(bob);

        // Add three books (using both constructors)
        Book b1 = new Book("ISBN-001", "Clean Code");                         // constructor 1
        Book b2 = new Book("ISBN-002", "The Pragmatic Programmer", "Hunt");   // constructor 2
        Book b3 = new Book("ISBN-003", "Design Patterns", "GoF");             // constructor 2
        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        // ---------------------------------------------------------- //
        //  2. State BEFORE any lending
        // ---------------------------------------------------------- //
        System.out.println("\n===== STATE BEFORE LENDING =====");
        library.printState();

        // ---------------------------------------------------------- //
        //  3. Lend operations
        // ---------------------------------------------------------- //
        System.out.println("===== LENDING OPERATIONS =====");
        library.lendBook("M001", "ISBN-001");   // Alice borrows Clean Code
        library.lendBook("M002", "ISBN-002");   // Bob borrows Pragmatic Programmer
        library.lendBook("M001", "ISBN-003");   // Alice borrows Design Patterns

        // Attempt to lend Clean Code again – should be REJECTED
        System.out.println("\n-- Attempting to lend 'Clean Code' again (should be rejected) --");
        library.lendBook("M002", "ISBN-001");   // REJECTION expected here

        // ---------------------------------------------------------- //
        //  4. State AFTER lending (before returns)
        // ---------------------------------------------------------- //
        System.out.println("\n===== STATE AFTER LENDING =====");
        library.printState();

        // ---------------------------------------------------------- //
        //  5. Return a book
        // ---------------------------------------------------------- //
        System.out.println("===== RETURN OPERATION =====");
        library.returnBook("ISBN-001");          // Alice returns Clean Code

        // ---------------------------------------------------------- //
        //  6. Final state AFTER return
        // ---------------------------------------------------------- //
        System.out.println("\n===== FINAL STATE AFTER RETURN =====");
        library.printState();

        // ---------------------------------------------------------- //
        //  7. Search demo
        // ---------------------------------------------------------- //
        library.searchByTitle("code");
        library.searchByTitle("Patterns");
    }
}