import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Wizard wizard = new Wizard("Merlin",100, 10,5);
        Warrior warrior = new Warrior("Ragnar",100,7,10);
        Archer archer = new Archer("Elfias",80,30,50);
        // Task 5: Create an object/instance of your new character's class.

        System.out.println(wizard.getName() + "'s health: " + wizard.getHealth());
        System.out.println(warrior.getName()+"'s health: " + warrior.getHealth());
        System.out.println(archer.getName()+"'s health: " + archer.getHealth());

        System.out.println("Game is starting...");

        wizard.setHealth(warrior, null);
        System.out.println( warrior.getName()+" attacks " + wizard.getName() + ". Health updated: " + wizard.getHealth());

        archer.setHealth(null, wizard);
        System.out.println(wizard.getName() + " attacks " + archer.getName() + ". Health updated: " + archer.getHealth());


        warrior.setHealth(wizard, null);
        System.out.println(wizard.getName() + " attacks" + warrior.getName() + ". Health updated: " + warrior.getHealth());

        // CHALLENGE: Update your program for all the characters to be able to attack each other.
        //DONE
    }
}


class Wizard {
    private String name;
    private int health;
    private int darkMagic;
    private int wizardArmor;

    public Wizard(String name, int health, int darkMagic, int wizardArmor){
        this.name = name;
        this.health = health;
        this.darkMagic = darkMagic;
        this.wizardArmor = wizardArmor;
    }

    public String getName() {
        return name;
    }

    public void setHealth(Warrior attack, Archer archer) {
        if(attack != null)
        health = health - (attack.getSword() - wizardArmor);
        else if(archer != null)
            health = health - (archer.getBow() - wizardArmor);
    }

    public int getDarkMagic(){
        return darkMagic;
    }

    public int getHealth() {
        return health;
    }

}

class Warrior {
    private String name;
    private int health;
    private int sword;
    private int metalArmor;

    public Warrior(String name, int health, int sword, int metalArmor){
        this.name = name;
        this.health = health;
        this.sword = sword;
        this.metalArmor = metalArmor;
    }

    public String getName() {
        return name;
    }

    public int getSword() {
        return sword;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(Wizard attack, Archer archer) {
        if(attack != null)
            health = health - (attack.getDarkMagic() - metalArmor);
        else if(archer != null)
            health = health - (archer.getBow() - metalArmor);
    }

}

class Archer {
    private String name;
    private int health;
    private int bow;
    private int quiver;

    public Archer(String name, int health, int bow, int quiver){
        this.name = name;
        this.health = health;
        this.bow = bow;
        this.quiver = quiver;
    }

    public String getName() {
        return name;
    }

    public int getBow() {
        quiver--;
        return bow;
    }

    public int getQuiver() {
        return quiver;
    }

    public int getHealth() {
        return health;
    }


    public void setHealth(Warrior warrior, Wizard wizard){
        if (warrior != null) {
            health = health - warrior.getSword();
        }else if(wizard != null){
            health = health - wizard.getDarkMagic();
        }
    }
}

// Task 1: Write another class here for a different type of character (e.g. archer)

// Task 2: Write instance variables

// Task 3: Write the constructor

// Task 4: Write the necessary getter(accessor) and setter(modifier) methods.
