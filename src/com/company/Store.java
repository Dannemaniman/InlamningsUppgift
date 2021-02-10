package com.company;

import java.util.Scanner;

public class Store {

    void purchaseAnimal(){
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

    void buyFood(){

    }

    void feedAnimal(){

    }

    void mateAnimal(){

    }

    void sellAnimal(){

    }


}
