package com.company;

public class Cockroach extends Animal{
    final int purchasePrice = 20;
    public Cockroach(String name, String gender) {
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

}
