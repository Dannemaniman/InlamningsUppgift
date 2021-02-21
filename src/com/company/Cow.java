package com.company;

public class Cow extends Animal{
    public Cow(String name, String gender) {
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
