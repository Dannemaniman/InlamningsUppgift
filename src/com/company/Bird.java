package com.company;

public class Bird extends Animal{
    final int purchasePrice = 50;
    public Bird(String name, String gender) {
        super(name, gender);
    }


     public boolean feedAnimal(Food food){
         if(food.getType() == "Grass"){
             System.out.println("\nSorry, I dont eat grass.\n");
             return false;
         }
         else{
             double hpIncrease = getHealth()*0.1;
             setHealth((int)hpIncrease);
             return true;
         }
     }




/*
        switch(food.getType()){
            case "Grain" -> {
                setHealth(food.getHealingAmount());
                System.out.println("Grain healed Bird for" + food.getHealingAmount());
            }
            case "Grass" -> {
                System.out.println("Grass");
            }
            case "Seed" -> {
                System.out.println("Seed");
            }
            default -> System.out.println("Nothing");
        }
    }

     */
}
