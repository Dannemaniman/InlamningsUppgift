package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    int rounds;
    int players;
    ArrayList<Player> player;

    public static void main(String[] args) {

    }

    public void newGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to the Game! ---\n");
        System.out.println(" - How many rounds? (5-30) - \n");
        rounds = Integer.parseInt(scanner.nextLine());
        System.out.println(" - How many players? (1-4) - \n");
        players = Integer.parseInt(scanner.nextLine());

        for(int i = 0; i <= players; i++){
            System.out.println("What is the Players Name?"); //Leta efter ett sätt att skriva "first, second, third" etc..
            player.add(new Player(scanner.next()));
        }

        System.out.println("    The Game Begins...");
        //purchaseAnimal();
        newRound();
    }

    static void newRound(){
        System.out.println("- What would you like to do? - ");
        System.out.println("    - 1. Buy Animal"); //det står köpa max så många djur som hen har pengar till (varje typ av djur har ett fast ursprungspris oavsett kön)
        System.out.println("    - 2. Buy Food");  //Köpa max så mycket mat som hen har pengar till (mat köps i kg och har kilopris)
        System.out.println("    - 3. Feed Animal"); //Mata sina djur (vilken slags mat måste anges för varje djur man vill mata)
        System.out.println("    - 4. Mate Animals"); //d) Försöka få ett par djur att para sig, då skapas i 50% av fallen nya djur man äger (om djuren är av samma slag och olika kön, olka slags djur kan inte para sig). Om parningen lyckas kan spelaren döpa det/de nya djuret/djuren (olika slags djur kan ha olika många ungar/parning). Könet på djuren som skapas vid parning slumpas (50% hona, 50% hane).
        System.out.println("    - 5. Sell Animal"); //Sälja ett-flera djur (priset är ursprungspriset gånger hälsovärdet)
    }

    static void purchaseAnimal(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("- What animal do you want to buy? -");
        System.out.println("    - 1. Bird ");
        System.out.println("    - 2. Cockroach");
        System.out.println("    - 3. Cow");
        System.out.println("    - 4. Dog");
        System.out.println("    - 5. Goat");
        System.out.println("    - 6. Horse");
        final int animal = Integer.parseInt(scanner.nextLine());
        System.out.println("- What is the animals name? -");
        final String animalName = scanner.next();  //gör en ArrayList istället!!
        System.out.println("- What gender is it? -");
        System.out.println("    - 1. Man");
        System.out.println("    - 2. Woman");
        System.out.println("    - 3. Other");
        final int animalGender = Integer.parseInt(scanner.nextLine());

    }
}
