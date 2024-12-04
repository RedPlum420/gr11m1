public class Main {
    public static void main(String[] args) {

        Habitat savana = new Habitat("Savana", 300, 33);
        Habitat taiga = new Habitat("Taiga", 700, 15);
        Habitat plains = new Habitat("Plains", 400, 22);
        Habitat mountains = new Habitat("Mountains", 500, 8);

        Staff pesho = new Staff("Pesho", "Lion tamer", savana); //Association
        Staff lisa = new Staff("Lisa", "Bear feeder", taiga); //Association
        Staff Micheal = new Staff("Micheal", "Bird scientist", plains); //Association
        Staff Gordon = new Staff("Gordon", "Animal psychologist", mountains); //Association

        Zoo myZoo = new Zoo();
        myZoo.addAnimal1("Leon", 15, savana, "Panthera leo"); //Composition
        myZoo.addAnimal2("Freddy Fazbear", 27, taiga, "Ursus arctos"); //Composition
        myZoo.addAnimal3("Paco", 6, plains, "Pavo cristatus"); //Composition
        myZoo.addAnimal4("Maci", 2, mountains, "Lynx lynx"); //Composition

        System.out.println("In the Amazing Zoo of Gumball, work four employees. " + pesho.getName() + " works as a " + pesho.getRole() + " in the " + pesho.getAssignedHabitat().getType() + ". " +
                lisa.getName() + " works as a " + lisa.getRole() + " in the " + lisa.getAssignedHabitat().getType() + ". " +
                Micheal.getName() + " works as a " + Micheal.getRole() + " in the " + Micheal.getAssignedHabitat().getType() + ". " +
                Gordon.getName() + " works as a " + Gordon.getRole() + " in the " + Gordon.getAssignedHabitat().getType() + ". " +
                "With all of these amazing people, we can care for four different habitats: " + savana.getType() + " " + taiga.getType() + " " + plains.getType() + " " + mountains.getType() +
                " In these four habitats we have four amazing animals. We have " + myZoo.getAnimal1().getName() + " the " + myZoo.getAnimal1().getSpecies() + " who lives in the " + myZoo.getAnimal1().getHabitat().getType() + " and is " + myZoo.getAnimal1().getAge() + " years old. " +
                "Also, we have " + myZoo.getAnimal2().getName() + " the " + myZoo.getAnimal2().getSpecies() + " who lives in the " + myZoo.getAnimal2().getHabitat().getType() + " and is " + myZoo.getAnimal2().getAge() + " years old. " +
                "Right by him is, " + myZoo.getAnimal3().getName() + " the " + myZoo.getAnimal3().getSpecies() + " who lives in the " + myZoo.getAnimal3().getHabitat().getType() + " and is " + myZoo.getAnimal3().getAge() + " years old. " +
                "And finally is the childrens favourite " + myZoo.getAnimal4().getName() + " the " + myZoo.getAnimal4().getSpecies() + " who lives in the " + myZoo.getAnimal4().getHabitat().getType() + " and is " + myZoo.getAnimal4().getAge() + " years old. " +
                "All of us strive to provide a happy life for our animals, and we hope we will see you soon");
    }
}
//Composition
class Habitat{
    private String type;
    private int size;
    private double temperature;

    public Habitat(String type, int size, double temperature) {
        this.type = type;
        this.size = size;
        this.temperature = temperature;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getTemperature() {
        return temperature;
    }
}

class Animal{
    private String name;
    private int age;
    private Habitat habitat; //Association
    private String species;

    public Animal(String name, int age, Habitat habitat, String species) {
        this.name = name;
        this.age = age;
        this.habitat = habitat;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    //Association
    public Habitat getHabitat() {
        return habitat;
    }

    public String getSpecies() {
        return species;
    }
}


class Zoo{
    private Animal a1;
    private Animal a2;
    private Animal a3;
    private Animal a4;


    //Composition
    public void addAnimal1(String name, int age, Habitat habitat, String species){
        a1 = new Animal(name, age, habitat, species);
    }
    //Composition
    public void addAnimal2(String name, int age, Habitat habitat, String species){
        a2 = new Animal(name, age, habitat, species);
    }
    //Composition
    public void addAnimal3(String name, int age, Habitat habitat, String species){
        a3 = new Animal(name, age, habitat, species);
    }
    //Composition
    public void addAnimal4(String name, int age, Habitat habitat, String species){
        a4 = new Animal(name, age, habitat, species);
    }

    public Animal getAnimal1(){
        return a1;
    }
    public Animal getAnimal2(){
        return a2;
    }
    public Animal getAnimal3(){
        return a3;
    }
    public Animal getAnimal4(){
        return a4;
    }
}


class Staff{
    private String name;
    private String role;
    private Habitat assignedHabitat; //Association

    public Staff(String name, String role, Habitat assignedHabitat) {
        this.name = name;
        this.role = role;
        this.assignedHabitat = assignedHabitat;
    }

    public void assignStaff(Habitat h){
        assignedHabitat = h;
    }

    public String getName(){
        return name;
    }

    public String getRole(){
        return role;
    }

    public Habitat getAssignedHabitat(){
        return assignedHabitat;
    }
}
