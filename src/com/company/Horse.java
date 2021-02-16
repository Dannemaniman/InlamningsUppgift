package com.company;

public class Horse extends Animal{
    final int purchasePrice = 120;
    public Horse(String name, String gender) {
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
