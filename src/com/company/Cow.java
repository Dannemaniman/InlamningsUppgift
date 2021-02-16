package com.company;

public class Cow extends Animal{
    final int purchasePrice = 100;
    public Cow(String name, String gender) {
        super(name, gender);
    }

    public boolean feedAnimal(Food food){
        if(food.getType() == "Seed"){
            System.out.println("\nSorry, I dont eat Seeds.\n");
            return false;
        }
        else{
            double hpIncrease = getHealth()*0.1;
            setHealth((int)hpIncrease);
            return true;
        }
    }

}
