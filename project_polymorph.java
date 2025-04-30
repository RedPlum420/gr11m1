import java.util.*;
public class Main {
    public static void main(String[] args) {
        ArrayList<Game> list = new ArrayList<>();
        list.add(new Warcraft(1000));
        list.add(new Minecraft(10000));
        list.add(new Game(1));

        for(Game game : list) {
            game.WhatAmI();
            game.WhatsMyName();
            game.TimeToPlay();
            game.Genre();
            System.out.println("3 play throughs are: " + game.hours*3 + " hours");
            System.out.println("--------------");
        }
    }
}

class Game{
    int hours;

    public Game(int hours){
        this.hours = hours;
    }

    public void WhatAmI(){
        System.out.println("I am a Game");
    }

    public void WhatsMyName(){
        System.out.println("I am a Name");
    }

    public void TimeToPlay(){
        System.out.println("1 hour");
    }
    public void Genre(){
        System.out.println("I am a Genre");
    }
}

class Warcraft extends Game{
    public Warcraft(int hours){
        super(hours);
    }

    public void WhatsMyName(){
        System.out.println("I am Warcraft");
    }
    public void Genre(){
        System.out.println("I am MMORPG");
    }
}

class Minecraft extends Game{
    public Minecraft(int hours){
        super(hours);
    }

    public void WhatsMyName(){
        System.out.println("I am Minecraft");
    }
    public void Genre(){
        System.out.println("I am SandBox");
    }
}
