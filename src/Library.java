import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Map<Integer, Book> books;
    private Map<Integer, User> users;
    private int nextBookId;
    private int nextUserId;

    public Library() {
        this.books = new HashMap<>();
        this.users = new HashMap<>();
        this.nextBookId = 1;
        this.nextUserId = 1;
    }

    // ----- Book related methods -----

    public Book addBook(String title, String author, String isbn) {
        Book book = new Book(nextBookId++, title, author, isbn);
        books.put(book.getId(), book);
        return book;
    }

    public List<Book> searchBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        String query = title.toLowerCase();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(query)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        String query = author.toLowerCase();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(query)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book searchBookByIsbn(String isbn) {
        for (Book book : books.values()) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getAllAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    public Book getBookById(int id) {
        return books.get(id);
    }

    // ----- User related methods -----

    public User registerUser(String name) {
        User user = new User(nextUserId++, name);
        users.put(user.getId(), user);
        return user;
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    // ----- Checkout / Return methods -----

    public boolean checkoutBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null || user == null) {
            return false;
        }

        if (!book.isAvailable()) {
            return false;
        }

        book.setAvailable(false);
        user.borrowBook(book);
        return true;
    }

    public boolean returnBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null || user == null) {
            return false;
        }

        if (!user.getBorrowedBooks().contains(book)) {
            return false;
        }

        user.returnBook(book);
        book.setAvailable(true);
        return true;
    }

    public List<Book> getBorrowedBooksByUser(int userId) {
        User user = users.get(userId);
        if (user != null) {
            return user.getBorrowedBooks();
        }
        return new ArrayList<>();
    }
}
