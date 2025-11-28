import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSampleData();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    handleAddBook();
                    break;
                case 2:
                    handleRegisterUser();
                    break;
                case 3:
                    handleSearchByTitle();
                    break;
                case 4:
                    handleSearchByAuthor();
                    break;
                case 5:
                    handleSearchByIsbn();
                    break;
                case 6:
                    handleCheckoutBook();
                    break;
                case 7:
                    handleReturnBook();
                    break;
                case 8:
                    handleListAvailableBooks();
                    break;
                case 9:
                    handleListUserBorrowedBooks();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("=========================");
        System.out.println(" Online Library System");
        System.out.println("=========================");
        System.out.println("1. Add Book");
        System.out.println("2. Register User");
        System.out.println("3. Search Books by Title");
        System.out.println("4. Search Books by Author");
        System.out.println("5. Search Books by ISBN");
        System.out.println("6. Check Out Book");
        System.out.println("7. Return Book");
        System.out.println("8. List All Available Books");
        System.out.println("9. List Books Borrowed by User");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next(); // discard invalid input
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static void handleAddBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        Book book = library.addBook(title, author, isbn);
        System.out.println("Book added: " + book);
    }

    private static void handleRegisterUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        User user = library.registerUser(name);
        System.out.println("User registered: " + user);
    }

    private static void handleSearchByTitle() {
        System.out.print("Enter title to search: ");
        String title = scanner.nextLine();
        List<Book> books = library.searchBooksByTitle(title);

        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Books found:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void handleSearchByAuthor() {
        System.out.print("Enter author to search: ");
        String author = scanner.nextLine();
        List<Book> books = library.searchBooksByAuthor(author);

        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Books found:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void handleSearchByIsbn() {
        System.out.print("Enter ISBN to search: ");
        String isbn = scanner.nextLine();
        Book book = library.searchBookByIsbn(isbn);

        if (book == null) {
            System.out.println("No book found with this ISBN.");
        } else {
            System.out.println("Book found: " + book);
        }
    }

    private static void handleCheckoutBook() {
        System.out.print("Enter user ID: ");
        int userId = readInt();
        System.out.print("Enter book ID: ");
        int bookId = readInt();

        boolean success = library.checkoutBook(bookId, userId);
        if (success) {
            System.out.println("Book checked out successfully.");
        } else {
            System.out.println("Failed to check out book. Check IDs or availability.");
        }
    }

    private static void handleReturnBook() {
        System.out.print("Enter user ID: ");
        int userId = readInt();
        System.out.print("Enter book ID: ");
        int bookId = readInt();

        boolean success = library.returnBook(bookId, userId);
        if (success) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Failed to return book. Check IDs or whether user has this book.");
        }
    }

    private static void handleListAvailableBooks() {
        List<Book> books = library.getAllAvailableBooks();
        if (books.isEmpty()) {
            System.out.println("No available books in the library.");
        } else {
            System.out.println("Available books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void handleListUserBorrowedBooks() {
        System.out.print("Enter user ID: ");
        int userId = readInt();
        List<Book> books = library.getBorrowedBooksByUser(userId);

        if (books.isEmpty()) {
            System.out.println("This user has not borrowed any books or user does not exist.");
        } else {
            System.out.println("Books borrowed by user " + userId + ":");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void seedSampleData() {
        // Add some sample books
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565");
        library.addBook("To Kill a Mockingbird", "Harper Lee", "9780061120084");
        library.addBook("1984", "George Orwell", "9780451524935");

        // Add some sample users
        library.registerUser("Alice");
        library.registerUser("Bob");
    }
}
