package com.company;

public class Dog extends Animal{
    public Dog(String name, String gender) {
        super(name, gender);
    }

    public boolean feedAnimal(Food food){
        if(food.getType() == "Grass"){
            System.out.println("\nSorry, I dont eat grass.\n");
            return false;
        } else if(food.getType() == "Grain"){
            System.out.println("\nSorry, I dont eat grain.\n");
            return false;
        }
        else{
            double hpIncrease = 10;
            setHealth((int)hpIncrease);
            return true;
        }
    }
}
