package com.company;

public class Bird extends Animal{
    public Bird(String name, String gender) {
        super(name, gender);
    }

    public boolean feedAnimal(Food food){
         if(food.getType() == "Grass"){
             System.out.println("\nSorry, I dont eat grass.\n");
             return false;
         }
         else{
             double hpIncrease = 10;
             setHealth((int)hpIncrease);
             return true;
         }
     }
}
