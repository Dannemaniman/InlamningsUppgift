package com.company;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Store {
    final int animalPriceList[] = {50, 20, 100, 70, 90, 120}; //FRÅGA OM JAG SKA SLÄNGA IN DETTA I SJÄLVA CLASSERNA ISTÄLLET.. !!
    final HashMap<String, Integer> animalPriceList2 = new HashMap<String, Integer>() {{
        put("Bird", 50);
        put("Cockroach", 20);
        put("Cow", 100);
        put("Dog", 70);
        put("Goat", 90);
        put("Horse", 120);
    }};

    final int foodPriceList[] = {20, 30, 10}; //SAMMA HÄR

    Player browseAnimals(Player player){
        Scanner scanner = new Scanner(System.in);
        int listSelection = 0;
        while(true){  // while(listSelection <= 0 || listSelection > 6){
            player.showAnimals();
            System.out.println(" - AVAILABLE ANIMALS -----\t\t " + " Money: " + player.getMoney());
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

                boolean animalExists = false;

                for(Animal animal: player.getAnimals()){
               /*     System.out.println(animal.getName());
                    System.out.println(animalName);*/
                    if(animal.getName().equals(animalName)){
                        System.out.println("\n\t - You cant name the animals the same name! - \n");
                        animalExists = true;

                    }
                }

                if(animalExists){ continue; }








            int animalGender = 0;
        while(animalGender <= 0 || animalGender > 3){ //kanske do while istället
            System.out.println("- What gender is it? -");
            System.out.println("    - 1. Male");
            System.out.println("    - 2. Female");

            animalGender = Utility.convertAndTestInput(scanner.nextLine());
            if(animalGender == -1){
                System.out.println("    - Error! - Wrong Choice! Must be 1 or 2");
                continue;
            }
        }


        Animal animalBought =  purchaseAnimals(listSelection, animalName, animalGender);
        player.addAnimal(animalBought);
        player.setMoney(animalPriceList[listSelection-1]);
        //player.showAnimals();
        }
        return player;
    }




    private Animal purchaseAnimals(int animals, String name, int gender){ //FIXA DENNA SÅ JAG INTE MÅSTE RETURNA ETT ANIMAL.. KANSKE HELA PLAYERN ISTÄLLET LIKA BRA JAG TAR IN
        String genderConverted;
        switch(gender){
            case 1 -> genderConverted = "Male";
            case 2 -> genderConverted = "Female";
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
            player.showFood();
            System.out.println("- What food do you want to buy? -\t\t " + player.getMoney());
            System.out.println("    - 1. Grain (20 per kg)");
            System.out.println("    - 2. Grass (30 per kg)");
            System.out.println("    - 3. Seed (10 per kg)");
            System.out.println("    - 4. Done");

            listSelection = Utility.convertAndTestInput(scanner.nextLine());

            if(listSelection == -1 || listSelection > 4 || listSelection < 1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - 4\n");
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

            player.setMoney(price);

        }

        return player;
    }

    int calculatePrice(int foodAmount, int foodPrice){

        return foodAmount*foodPrice;
    }

    public Player feedAnimal(Player player){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            int counter = 1;
            System.out.println("- What Animal do you want to feed? -");


            for(Animal animal : player.getAnimals()){
                System.out.println("\t" + counter + ".\t" + animal.getName() + "\t" + Utility.getClassName(animal.getClass()) + "\t HP: " + animal.getHealth());
                counter++;
            }
            System.out.println("\t" + counter + ".\t" +"Done");

            int listSelection = Utility.convertAndTestInput(scanner.nextLine());

            if(listSelection == -1 || listSelection > counter || listSelection < 1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - 3");
                continue;
            }

            if(listSelection == counter){
                break;
            }

            int animalToBeFed = listSelection -1;


            System.out.println("- What Food do you want to feed " + player.getAnimals().get(listSelection-1).getName() + " with? -");


            counter = 1;
            for(Food food : player.getFood()){
                System.out.println("\t" + counter + ".\t" + "\t" + Utility.getClassName(food.getClass()) + "\t" + food.getQuantity() + " kg");
                  counter++;
            }

            if(player.getFood().isEmpty()){
                System.out.println("\n \t Nothing here.. press enter");
                listSelection = Utility.convertAndTestInput(scanner.nextLine());
                continue;
            }

            listSelection = Utility.convertAndTestInput(scanner.nextLine());


            if(listSelection == -1 || listSelection > counter-1 || listSelection < 1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - " + counter);
                continue;
            }

            Food foodToDelete = player.getFood().get(listSelection-1);

            boolean success = player.getAnimals().get(animalToBeFed).feedAnimal(foodToDelete);

            if(success){
                //Sedan ta bort mitt item
                player.deleteFood(foodToDelete);
            }
        }
            return player;

    }

    ArrayList<Animal> mateAnimal(ArrayList<Animal> animals){
        Scanner scanner = new Scanner(System.in);
        System.out.println("- What Animal do you want to mate? -");
        int counter = 1;
        for(Animal animal: animals){
            System.out.println("\t" + counter + ".\t" + animal.getName() + "\t" + Utility.getClassName(animal.getClass()) + "\t Kön: " + animal.getGender());
            counter++;
        }

        int listSelection = Utility.convertAndTestInput(scanner.nextLine());
        Animal firstAnimal = animals.get(listSelection-1);

        while(true){
            System.out.println("- What Animal do you want to mate " + animals.get(listSelection-1).getName() + " with? -");
            counter = 1;
            for(Animal animal: animals){
                if(animal == animals.get(listSelection-1)){
                    System.out.println("\t" + counter + ".\t" + animal.getName() + "\t" + Utility.getClassName(animal.getClass()) + "\t Kön: " + animal.getGender() + "    ***CHOSEN***");
                    counter++;
                    continue;
                }
                System.out.println("\t" + counter + ".\t" + animal.getName() + "\t" + Utility.getClassName(animal.getClass()) + "\t Kön: " + animal.getGender());
                counter++;
            }

            listSelection = Utility.convertAndTestInput(scanner.nextLine());

            if(animals.get(listSelection-1).getName() == firstAnimal.getName()){
                System.out.println("\nYou cant mate the animal with itself.\n Press Enter..");
                scanner.nextLine();
                continue;
            } else{
                break;
            }

        }
        Animal secondAnimal = animals.get(listSelection-1);

        //System.out.println(Utility.getClassName(firstAnimal.getClass()) + " --- " + Utility.getClassName(secondAnimal.getClass()));
        //System.out.println(firstAnimal.getGender() + " ---- " + secondAnimal.getGender());
        //
        //
         if(Utility.getClassName(firstAnimal.getClass()).equals(Utility.getClassName(secondAnimal.getClass())) && !firstAnimal.getGender().equals(secondAnimal.getGender())){
             System.out.println("\n" + firstAnimal.getName() + " and " + secondAnimal.getName() + " can be mated! Press Enter..\n");
             scanner.nextLine();
             tryMating(firstAnimal, animals);


         } else {
             System.out.println(firstAnimal.getName() + " gender " + firstAnimal.getGender());
             System.out.println(secondAnimal.getName() + " gender " + secondAnimal.getGender());
             System.out.println("These 2 animals cant be mated! It has to be the same type and different genders!");
    }
        return animals;
    }

    public ArrayList<Animal> tryMating(Animal firstAnimal, ArrayList<Animal> animals){ //GÖR OM TILL PLAYER OBJEKT SÅ JAG KAN NÅ PENGAR OCH ALLT OCH SÅ..
        Scanner scanner = new Scanner(System.in);
        if(Math.random() > 0.5){ //KOLLA SÅ DETTA VERKLIGEN ÄR 50%.... !!!
            String gender;
            String listSelection;
            if(Math.random() > 0.5){
                System.out.println("Success!! What would you like to name him?");
                listSelection = scanner.nextLine();
                gender = "Male";
                }
             else {
                System.out.println("Success!! What would you like to name her?");
                listSelection = scanner.nextLine();
                gender = "Female";
            }

            switch(Utility.getClassName(firstAnimal.getClass())){
                case "Bird" -> animals.add(new Bird(listSelection, gender));
                case "Cockroach" -> animals.add(new Cockroach(listSelection, gender));
                case "Cow" -> animals.add(new Cow(listSelection, gender));
                case "Dog" -> animals.add(new Dog(listSelection, gender));
                case "Goat" -> animals.add(new Goat(listSelection, gender));
                case "Horse" -> animals.add(new Horse(listSelection, gender));
        }
    } else {
            System.out.println("Sorry.. they did not conceive. Press Enter..");
            scanner.nextLine();
        }
        return animals;
    }

     Player sellAnimal(Player player){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Animal> animals = player.getAnimals();
        while(true){
            int counter = 1;
            System.out.println("\n- What Animal do you want to sell? -");
            for(Animal animal: animals){
                System.out.println("\t" + counter + ".\t" + animal.getName() + "\t" + Utility.getClassName(animal.getClass()) + "\t" + animal.getGender() + "\t HP: " + animal.getHealth());
                counter++;
            }
        System.out.println("\t" + counter + ".  Done");
        int listSelection = Utility.convertAndTestInput(scanner.nextLine());

        if(listSelection == counter){
            return player;
        }

     //   System.out.println(animals.get(listSelection-1).getHealth() + " /100");
       // System.out.println(animalPriceList2.get(Utility.getClassName(animals.get(listSelection-1).getClass())));
        double goldToReceive = ((double)animals.get(listSelection-1).getHealth()/100)*(double)animalPriceList2.get(Utility.getClassName(animals.get(listSelection-1).getClass()));
      //  System.out.println(goldToReceive);

        System.out.println("You will receive " + (int)goldToReceive + " kr for it!");
        System.out.println("Are you sure you want to sell it?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        listSelection = Utility.convertAndTestInput(scanner.nextLine());
        if(listSelection == 1){
            player.removeAnimal(animals.get(listSelection-1).getName());
            player.setMoney(-(int)goldToReceive);
            break;
        } else {
            continue;
        }


        }
        return  player;
    }

    public int sellAllAnimals(ArrayList<Animal> animals){
        double goldToReceive = 0;

        for(Animal animal: animals){
            double gold = animalPriceList2.get(Utility.getClassName(animal.getClass()));
            gold = gold*((double)animal.getHealth()/100);
            goldToReceive = goldToReceive + gold;
        }

        return (int)goldToReceive;
    }


}
