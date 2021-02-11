package com.company;
import java.util.Scanner;

public class Store {
    final int animalPriceList[] = {50, 20, 100, 70, 90, 120}; //FRÅGA OM JAG SKA SLÄNGA IN DETTA I SJÄLVA CLASSERNA ISTÄLLET.. !!
    final int foodPriceList[] = {20, 30, 10}; //SAMMA HÄR

    Player browseAnimals(Player player){
        Scanner scanner = new Scanner(System.in);
        int listSelection = 0;
        while(true){  // while(listSelection <= 0 || listSelection > 6){
            System.out.println("- What animal do you want to buy? -\t\t " + player.getMoney());
            System.out.println("    - 1. Bird (50)");
            System.out.println("    - 2. Cockroach (20)");
            System.out.println("    - 3. Cow (100)");
            System.out.println("    - 4. Dog (70)");
            System.out.println("    - 5. Goat (90)");
            System.out.println("    - 6. Horse (120)");
            System.out.println("    - 7. Done");

            listSelection = Utility.convertAndTestInput(scanner.nextLine());

            if(listSelection == 7){
                break;
            }

            if(listSelection == -1 || listSelection > 7 || listSelection < 1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - 6");
                continue;
            }

            if(!player.isPurchasePossible(animalPriceList[listSelection-1])){
                System.out.println("You cant afford it.. you only have " + player.getMoney() + " and it costs " + animalPriceList[listSelection-1]);
                continue;
            }


        System.out.println("- What is the animals name? -");
        final String animalName = scanner.nextLine();  //gör en ArrayList istället!!

        int animalGender = 0;
        while(animalGender <= 0 || animalGender > 3){ //kanske do while istället
            System.out.println("- What gender is it? -");
            System.out.println("    - 1. Male");
            System.out.println("    - 2. Female");
            System.out.println("    - 3. Other");

            animalGender = Utility.convertAndTestInput(scanner.nextLine());
            if(animalGender == -1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - 3");
                continue;
            }
        }

        Animal animalBought =  purchaseAnimals(listSelection, animalName, animalGender);
        player.addAnimal(animalBought);
        player.setMoney(animalPriceList[listSelection-1]);
        player.getAnimals();
        }
        return player;
    }

    private Animal purchaseAnimals(int animals, String name, int gender){
        String genderConverted;
        switch(gender){
            case 1 -> genderConverted = "Male";
            case 2 -> genderConverted = "Female";
            case 3 -> genderConverted = "Other";
            default -> genderConverted = "Male";
        }
        Animal animal;
        Bird dogglas = new Bird("Gregor", "Male");
        switch(animals){
            case 1 -> animal = new Bird(name, genderConverted);
            case 2 -> animal = new Cockroach(name, genderConverted);
            case 3 -> animal = new Cow(name, genderConverted);
            case 4 -> animal = new Dog(name, genderConverted);
            case 5 -> animal = new Goat(name, genderConverted);
            case 6 -> animal = new Horse(name, genderConverted);
            default -> animal = dogglas; //returna player istället så behöver jag inte alltid returnera ett djur..
        }
        return animal;
    }

    Player buyFood(Player player){
        Scanner scanner = new Scanner(System.in);
        int listSelection = 0;
        int foodAmount;
        while(true){
            System.out.println("- What food do you want to buy? -\t\t " + player.getMoney());
            System.out.println("    - 1. Grain (20 per kg)");
            System.out.println("    - 2. Grass (30 per kg)");
            System.out.println("    - 3. Seed (10 per kg)");
            System.out.println("    - 4. Done");

            listSelection = Utility.convertAndTestInput(scanner.nextLine());

            if(listSelection == -1 || listSelection > 4 || listSelection < 1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - 4");
                continue;
            }

            if(listSelection == 4){
                break;
            }

            System.out.println("- How many kg? -");
            foodAmount = Utility.convertAndTestInput(scanner.nextLine());
            int price = calculatePrice(foodAmount, foodPriceList[listSelection-1]);

            if(!player.isPurchasePossible(price)){
                System.out.println("You cant afford it.. you only have " + player.getMoney() + " and it costs " + price);
                continue;
            }



            switch(listSelection){
                case 1 -> player.setFood(new Grain(foodAmount));
                case 2 -> player.setFood(new Grass(foodAmount));
                case 3 -> player.setFood(new Seed(foodAmount));
            }

            System.out.println(price);
            player.setMoney(price);

        }

        return player;
    }

    int calculatePrice(int foodAmount, int foodPrice){

        return foodAmount*foodPrice;
    }

    void feedAnimal(){

    }

    void mateAnimal(){

    }

    void sellAnimal(){

    }


}
