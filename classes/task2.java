public class Main {
    public static void main(String[] args) {
        Book redRisng = new Book("Red Rising", "Pierce Brown", 300);
        Book warAndPeace = new Book("War and Peace", "Leo Tolstoy", 1000);
        // Task 4: Create two or more Book objects with different attributes.
        // Task 5: Call the displayDetails method for each object.
        redRisng.displayDetails();
        System.out.println("\n\n");
        warAndPeace.displayDetails();
    }
}

// Define the Book class
class Book {
    // Task 1: Add three attributes: title, author, and numberOfPages.
    String title;
    String author;
    int numberOfPages;


    // Constructor
    public Book(String title, String author, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    // Task 3: Add a method displayDetails() to print the book's details (title, author, and numberOfPages)

    public void displayDetails(){
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Number of pages: " + numberOfPages);
    }
}
