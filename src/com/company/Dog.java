package com.company;

public class Dog extends Animal{
    final int purchasePrice = 70; //KOLLA SÅ ATT DET ÄR OKEJ ATT LOGIKEN LIGGER HÄR MED PURCHASE PRICE... KANSKE LÄGGA I STORE?
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
            double hpIncrease = getHealth()*0.1;
            setHealth((int)hpIncrease);
            return true;
        }
    }
}
