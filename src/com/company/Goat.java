package com.company;

public class Goat extends Animal{
    public Goat(String name, String gender) {
        super(name, gender);
    }

    public boolean feedAnimal(Food food){
        if(food.getType() == "Seed"){
            System.out.println("\nSorry, I dont eat Seeds.\n");
            return false;
        }
        else{
            double hpIncrease = 10;
            setHealth((int)hpIncrease);
            return true;
        }
    }

}
