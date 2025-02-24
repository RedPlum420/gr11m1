import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Car[] cars = {
                new Car("Opel", "Astra", 2000, 300, false),
                new Car("Benz", "Brazil", 2000, 300, false),
                new Car("Skoda", "Ocatavia", 2014, 20000, false),
                new Car("Skoda", "Rapid", 2016, 500, false)
        };

        System.out.println("These are the cars in our gallery: \n");

        for(int i = 0; i < cars.length; i++) {
            System.out.println("index " + i + ": " + cars[i].displayCar());
        }

        System.out.println("\n");

        System.out.println("The most expensive car is: " + returnMostExpensiveCar(cars));
        System.out.println("The average price of a car in the gallery is: " + (int)AveragePrice(cars));

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the index of the car you would like to lease: ");
        int index = scanner.nextInt();

        cars[index].LeaseCar();
    }

    public static String returnMostExpensiveCar(Car[] cars){
        int index = 0;
        double price = cars[0].getPrice();
        for(int i =0; i<cars.length; i++)
        {
            if(cars[i].getPrice() > price){
                price = cars[i].getPrice();
                index = i;
            }
        }
        return cars[index].displayCar();
    }

    public static double AveragePrice(Car[] cars){
        double sum = 0;

        for(int i = 0; i < cars.length; i++){
            sum += cars[i].getPrice();
        }
        return sum/cars.length;
    }
}

class Car{
    private String make;
    private String model;
    private int year;
    private double price;
    private boolean isLeased;
    public Car(String make, String model, int year, double price, boolean isLeased) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isLeased = isLeased;
    }

    public double getPrice() {
        return price;
    }

    public String displayCar(){
        return make + " " + model + " " + year + " " + price;
    }

    public void LeaseCar(){
        isLeased = true;
        System.out.println("You will need to pay: " + price/12 + " per month for a year.");
    }
}
