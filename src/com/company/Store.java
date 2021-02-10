package com.company;

import java.util.Scanner;

public class Store {

    void purchaseAnimal(int money){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while(option <= 0 || option > 6){
            System.out.println("- What animal do you want to buy? -");
            System.out.println("    - 1. Bird ");
            System.out.println("    - 2. Cockroach");
            System.out.println("    - 3. Cow");
            System.out.println("    - 4. Dog");
            System.out.println("    - 5. Goat");
            System.out.println("    - 6. Horse");

            try {
                option = Integer.parseInt(scanner.nextLine());
                if (option <= 0 && option > 6){
                    System.out.println("   - Error! -  Have to be number between 1 - 6");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();

            }; //lägg till try catch här ifall man skriver in t ex "aiwd"


        }

        System.out.println("- What is the animals name? -");
        final String animalName = scanner.nextLine();  //gör en ArrayList istället!!

        int animalGender = 0;
        while(animalGender <= 0 || option > 3){
            System.out.println("- What gender is it? -");
            System.out.println("    - 1. Man");
            System.out.println("    - 2. Woman");
            System.out.println("    - 3. Other");
            animalGender = Integer.parseInt(scanner.nextLine());

            if(animalGender > 3 || animalGender <= 0 ){
                System.out.println("    - Error! - Wrong Choice!");
            }
        }


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
