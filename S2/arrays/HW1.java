import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter 1000 to exit program \n\n");

        Book[] books = new Book[5];

        books[0] = new Book("Lord of the Flies", 15, 5);
        books[1] = new Book("War and Peace", 30, 3);
        books[2] = new Book("Water", 10, 10);
        books[3] = new Book("White nights", 5, 7);
        books[4] = new Book("Crime and Punishment", 20, 7); // sorry

        Scanner scanner = new Scanner(System.in);

        while(true){
            for(int i = 0; i < books.length; i++){
                System.out.println(books[i].displayProduct());;
            }

            System.out.print("Which book do you want to find: ");

            String choice = scanner.nextLine();

            if(choice.equals("1000")) System.exit(0);

            System.out.println();

            for(Book book : books) {
                if (book.title.equalsIgnoreCase(choice)) {
                    System.out.println(book.displayProduct() + "\nIs this the book you wanted to find: [1/0]");
                    int yno = scanner.nextInt();
                    scanner.nextLine();
                    if (yno == 1) {
                        System.out.print("Deposit money: ");
                        double depositMoney = scanner.nextDouble();
                        scanner.nextLine();
                        if ((int) depositMoney == 1000) System.exit(0);
                        book.tryToBuy(depositMoney);
                        break;
                    } else if (yno == 1000) System.exit(0);
                    else continue;

                }else if(book == books[books.length-1]) System.out.println("\nSorry, but we didn't find the book you were looking for... Maybe try another one");
            }



            System.out.println();System.out.println();
        }
    }

}

class Book{
    public String title;
    public double price;
    public int quantity;

    public Book(String name, double price, int quantity) {
        this.title = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String displayProduct(){
        return "Title: " + title + " | Price: " + price + "$ | Quantity: " + quantity;
    }

    public String whenSelected(){
        return "The product costs: " + this.price + "\nDeposit money: ";
    }

    public void tryToBuy(double dmoney){
        if(dmoney >= this.price && quantity > 0){
            this.quantity --;
            System.out.println("Successfully purchased! \n" + "Change: " + (dmoney - this.price));
        }else if(quantity == 0){
            System.out.println("The product has ran out!\n" + "Change: " + dmoney);
        }
        else{
            System.out.println("Not enough money! \n Change: " + dmoney);
        }
    }
}
