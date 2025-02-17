import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter 1000 to exit program \n\n\n");

        Product[] products = new Product[5];

        products[0] = new Product("Coke", 1.5, 5);
        products[1] = new Product("Chips", 2, 3);
        products[2] = new Product("Water", 1, 10);
        products[3] = new Product("Chocolate", 3, 7);
        products[4] = new Product("Prime", 0.01, 7); // sorry

        Scanner scanner = new Scanner(System.in);

        while(true){
            for(int i = 0; i < products.length; i++){
                System.out.println("Code: " + i + " | " + products[i].displayProduct());;
            }

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if(choice == 1000)System.exit(0);

            System.out.println();

            System.out.print(products[choice].whenSelected());
            double depositMoney = scanner.nextDouble();
            if((int)depositMoney == 1000)System.exit(0);

            System.out.println();

            products[choice].tryToBuy(depositMoney);

            System.out.println();System.out.println();

        }
    }

}

class Product{
    public String name;
    public double price;
    public int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String displayProduct(){
        return "Name: " + name + " | Price: " + price + "$ | Quantity: " + quantity;
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
