package Testing1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library {

    private ArrayList<Book> books;  // List to store books

    public Library() {
        this.books = new ArrayList<>();
    }

    // Method to add a book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " has been added to the Library.");
    }

    // Method to display all books in the Library
    public void displayBooksSorted() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        //Sort books alphabetically by title
        Collections.sort(books,Comparator.comparing(Book::getTitle));
        
        System.out.println("Books available in the Library:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " by " + book.getAuthor() +
                    " (" + (book.isAvailable() ? "Available" : "Not Available") + ")");
        }
    }

    // Method to borrow a book
    public void borrowBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {  // Correct method call
                book.setAvailable(false); // Mark as borrowed
                System.out.println("You have successfully borrowed: " + title);
                return;
            }
        }
        System.out.println("Sorry, the book '" + title + "' is not available.");
    }

    // Method to return a book
    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {  // Correct method call
                book.setAvailable(true); // Mark as available
                System.out.println("Thank you for returning: " + title);
                return;
            }
        }
        System.out.println("Sorry, this book does not belong to our Library.");
    }

    // Method to search for a book by title
    public void searchBookByTitle(String title) {  // Added missing parameter
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book Found: " + book.getTitle() + " by " + book.getAuthor());
                return;
            }
        }
        System.out.println("Sorry, the book '" + title + "' is not available in the library.");
    }

    // Method to search for books by author
    public void searchBooksByAuthor(String author) {  // Fixed method name
        boolean found = false;
        System.out.println("Books by " + author + ":");
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                System.out.println("- " + book.getTitle());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found by " + author);
        }
    }
}
