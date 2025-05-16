import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Employee pesho = new Employee(102, 23, "Pesho");
        pesho.writeToFile("employee.txt");
        pesho.readFromFile("employee.txt");
    }
}

class Employee implements Serializable {
    String name;
    int id;
    int age;

    public Employee(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public void writeToFile(String path){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))){
            oos.writeObject(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readFromFile(String path){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))){
            try{
                while(true){
                    Employee employed = (Employee) ois.readObject();
                    System.out.println(employed.id + " " + employed.age + " " + employed.name);
                }
            }catch(EOFException e){}
        }catch(IOException | ClassNotFoundException e){}
    }
}
