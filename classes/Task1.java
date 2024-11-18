public class Main {
    public static void main(String[] args) {
        // Task 6: Create more objects and test the methods
        Person p1 = new Person("Eda", "eda@gmail.com", "123-456-7890", 27);
        p1.print();
        Person p2 = new Person("Hannah", "hannah@gmail.com", "404-899-9955", 23);
        p2.print();
        Person p3 = new Person("John Pork", "j.pork@gmail.com", "123-456-7890", 23);
        p3.print();

        System.out.println(p3.getName());
        System.out.println(p1.getEmail());
        System.out.println(p2.getAge());

        // Task 8: Test the updateEmail method
        p3.updateEmail("john.pork@gmail.com");
    }
}

// Define the Person class here
class Person {
    // Instance variables
    private String name;
    private String email;
    private String phoneNumber;

    // Task 1: Add a new attribute (e.g., age)
    private int age;

    // Constructor: Initialize Person data
    public Person(String initName, String initEmail, String initPhone, int initAge) { // Task 2: modify method signature to include your new attribute.
        name = initName;
        email = initEmail;
        phoneNumber = initPhone;
        age = initAge;
        // Task 3: Assign a default value to the new attribute here.
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Age: " + age);

        // Task 4: Print the new attribute here
    }

    // Task 5: Add getter methods to access and return each attribute individually. Here's an example:
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Task 7: Add a method to update the email
    public void updateEmail(String newEmail) {
        email = newEmail;
    }
}
