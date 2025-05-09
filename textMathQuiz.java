import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("What's your name?");
        String name = scanner.nextLine();
        writeToText("", name + "-answers.txt");
    }
    public static void writeToText(String message, String path){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for(int i = 0; i < 4; i++) {
                Random random = new Random();
                int int1 = 0;
                int int2 = 0;
                try{
                    int1 = random.nextInt(1, 20);
                    int2 = random.nextInt(1, 20);
                }catch(Exception e) {
                    System.out.println("Please enter only real numbers ;p");
                    System.exit(0);
                }


                int operation = random.nextInt(0, 3);

                if (operation == 0) {
                    message += int1 + "+" + int2 + " = ";
                    System.out.println(message);
                    int answer = scanner.nextInt();
                    message += answer;

                    if (answer == int1 + int2) message += " Correct";
                    else message += " Wrong";

                } else if (operation == 1) {
                    message += int1 + "-" + int2 + " = ";
                    System.out.println(message);
                    int answer = scanner.nextInt();
                    message += answer;

                    if (answer == int1 - int2) message += " Correct";
                    else message += " Wrong";
                } else if (operation == 2) {
                    message += int1 + "x" + int2 + " = ";
                    System.out.println(message);
                    int answer = scanner.nextInt();
                    message += answer;

                    if (answer == int1 * int2) message += " Correct";
                    else message += " Wrong";
                }

                writer.write(message);
                message = "";
            }
        } catch (IOException e) {

        }
    }
}
