package com.project.library;


import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library myLibrary = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding books to the library
        myLibrary.addBook(new Book("Harry Potter", "J.K. Rowling"));
        myLibrary.addBook(new Book("The Hobbit", "J.R.R. Tolkien"));
        myLibrary.addBook(new Book("1984", "George Orwell"));
        myLibrary.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));

        while (true) {
            System.out.println("\n📚 Library Management System Menu:");
            System.out.println("1. Display all books");
            System.out.println("2. Search book by title");
            System.out.println("3. Search books by author");
            System.out.println("4. Borrow a book");
            System.out.println("5. Return a book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    myLibrary.displayBooksSorted();
                    break;
                case 2:
                    System.out.print("Enter book title : ");
                    String title = scanner.nextLine();
                    myLibrary.searchBookByTitle(title);
                    break;
                case 3:
                    System.out.print("Enter the author name to search: ");
                    String author = scanner.nextLine();
                    myLibrary.searchBooksByAuthor(author);
                    break;
                case 4:
                    System.out.print("Enter the book title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    myLibrary.borrowBook(borrowTitle);
                    break;
                case 5:
                    System.out.print("Enter the book title to return: ");
                    String returnTitle = scanner.nextLine();
                    myLibrary.returnBook(returnTitle);
                    break;
                case 6:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }
}


