package com.company;

import java.util.Scanner;

public class Game {
    final int money = 500;
    int rounds;
    int players;

    public static void main(String[] args) {

    }

    public void newGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to the Game! ---\n");
        System.out.println(" - How many rounds? (5-30) - \n");
        rounds = Integer.parseInt(scanner.nextLine());
        System.out.println(" - How many players? (1-4) - \n");
        players = Integer.parseInt(scanner.nextLine());

        System.out.println("    The Game Begins...");
        //purchaseAnimal();
        newRound();
    }

    static void newRound(){
        System.out.println("1. Buy Animal"); //det står köpa max så många djur som hen har pengar till (varje typ av djur har ett fast ursprungspris oavsett kön)
        System.out.println("2. Buy Food");  //Köpa max så mycket mat som hen har pengar till (mat köps i kg och har kilopris)
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
