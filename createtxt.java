import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scan.nextLine();
        String file ="";
        try(FileWriter fw = new FileWriter(s +".txt", true)){
            while(!file.equals("done")){
                fw.write(file + "\n");
                file=scan.nextLine();
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
