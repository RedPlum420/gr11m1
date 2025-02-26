import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Book[] displayLibrary = {
                new Book("Ninjago", "Joe", 2013),
                new Book("Ligngang guli guli", "Sleepy Joe", 2024),
                new Book("Wasaaaa", "Steve", 2020),
                new Book("An introduction to data systems", "Rajesh", 2021),
                new Book("Java for Dummies", "That one guy", 2007),
        };

        Scanner scanner = new Scanner(System.in);

        DisplayLibrary(displayLibrary);

        while (true) {
            borrowBook(displayLibrary, scanner);
            System.out.println();
            returnBook(displayLibrary, scanner);

            System.out.println("\nWould you like to exit the program [Y/n]: ");
            String s = scanner.nextLine();

            if(s.equalsIgnoreCase("y")) {
                System.exit(0);
            }
            System.out.println("\n");
        }
    }

    private static void DisplayLibrary(Book[] displayLibrary) {
        for (Book book : displayLibrary) {
            System.out.println(book.getDetails());
            System.out.println();
        }
    }

    private static void borrowBook(Book[] displayLibrary, Scanner scanner) {
        System.out.print("Would you like to borrow a book [Y/n]: ");
        String answer = scanner.nextLine();

        if(answer.equalsIgnoreCase("y")) {
            System.out.print("\nIn that case, which book would you like to borrow: ");
            String bookName = scanner.nextLine();

            int bookIndex = displayLibrary[0].findBookByTitle(displayLibrary, bookName);
            if(bookIndex != -1 && displayLibrary[bookIndex].getBorrowerName() == null) {
                System.out.println("Good, we found it, under what name are you taking it?");
                String borrower = scanner.nextLine();

                displayLibrary[bookIndex].borrowBook(borrower);
                System.out.println("Congratulations you borrowed a book!!!");
            }else if( bookIndex != -1 && displayLibrary[bookIndex].getBorrowerName() != null) {
                System.out.println("The book is already borrowed, sorry.");
            }
            else System.out.println("Sorry, but we were unable to find the book you were looking for.");
        }else {
            System.out.println("That's a shame :(");
            return;
        }
    }

    private static void returnBook(Book[] displayLibrary, Scanner scanner) {
        System.out.print("Would you like to return a book [Y/n]: ");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("y")) {
            System.out.print("\nIn that case, which book would you like to return: ");
            String bookName = scanner.nextLine();

            displayLibrary[0].returnBook(displayLibrary, bookName);
        }else System.out.println("That's a shame :(");
    }

    private static int findBookByTitle(Book[] displayLibrary, String bookTitle) {
        return displayLibrary[0].findBookByTitle(displayLibrary, bookTitle);
    }
}

class Book{
    private String title;
    private String author;
    private int yearPublished;
    private String borrowerName = null;

    public Book(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getDetails(){
        String details = "Title: " + this.title + "\nAuthor: " + this.author;
        details += "\nYear Published: " + this.yearPublished;

        if(borrowerName != null) details += "\nBorrower Name: " + this.borrowerName;

        return details;
    }

    public void borrowBook(String borrowerName){
            this.borrowerName = borrowerName;
    }

    public void returnBook(Book[] books, String title){
        int bookIndex = findBookByTitle(books, title);

        if(bookIndex == -1){
            System.out.println("Sorry, we don't own the book titled: " + title);
        }else{
            if(borrowerName == null){
                System.out.println("The book isn't borrowed.");
                return;
            }

            books[bookIndex].borrowerName = null;
            System.out.println("You successfully returned the book: " + title);
        }
    }

    public int findBookByTitle(Book[] books, String title){
        for(int i = 0; i < books.length; i++){
            if(books[i].title.equalsIgnoreCase(title)){
                return i;
            }
        }
        return -1;
    }

    public String getBorrowerName(){
        return this.borrowerName;
    }

    public void updateBookInfo(String title, String author, int yearPublished){
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

}
