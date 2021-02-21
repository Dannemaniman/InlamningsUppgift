package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class Player  implements Serializable {
    private final String name;
    private int money = 500;
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Food> foods = new ArrayList<>();

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setMoney(int money){
        this.money = this.money - money;
    }

    public boolean isPurchasePossible(int amount){
        return money >= amount;
    }

    public int getMoney(){
        return this.money;
    }

    public void addAnimal(Animal animal){
           this.animals.add(animal);
    }

    public void removeAnimal(String key){
        for(Animal animal: animals){

        if(animal.getName().equals(key)){
            this.animals.remove(animal);
            break;
        }
        }
    }

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

    public void showInventory(){
        System.out.println(" - FOOD IN POSSESSION - ");
        for(Food food: foods){
            String key = food.getType();
            String anotherKey = Utility.getClassName(food.getClass());
            System.out.println("\t\t" + key + "\t" + anotherKey + "\t Quantity: "  + food.getQuantity());
        }
        System.out.println("\n - ANIMALS IN POSSESSION - ");
        for(Animal animal : animals){
            String key = animal.getName();
            String animalWord = animal.getClass().getName();
            int position = animalWord.lastIndexOf(".");
            animalWord = animalWord.substring(position+1);
            System.out.println("\t\t" + key + "\t" + animalWord + "\t" + animal.getGender() + "\t HP: "  + animal.getHealth());
        }
    }

    public void displayAnimals(){
        System.out.println("\n\n - ANIMALS IN POSSESSION - ");
        for(Animal animal : animals){
            String key = animal.getName();
            String animalWord = animal.getClass().getName();
            int position = animalWord.lastIndexOf(".");
            animalWord = animalWord.substring(position+1);
            System.out.println("\t\t" + key + "\t" + animalWord + "\t" + animal.getGender() + "\t HP: "  + animal.getHealth());
        }
        System.out.println("\n");
    }


    public ArrayList<Food> setFood(Food food){
        for(Food foo: foods){
            if(foo.getClass().getName().equals(food.getClass().getName())){
                foo.setQuantity(food.getQuantity());
                return this.foods;
            }
        }
        foods.add(food);
        return this.foods;
    }

    public ArrayList<Food> getFood(){
        return this.foods;
    }

    public void deleteFood(Food food){
        int index = 0;
        for(Food fod: foods){
            if(fod == food){
                fod.setQuantity(-1);
                if(fod.getQuantity() == 0){
                    foods.remove(index);
                    break;
                }
            }
            index++;
        }
    }

    public void displayFood(){
        System.out.println("\n\n - FOODS IN POSSESSION - ");
        for(Food food : foods){
            String key = food.getClass().getName();
            String foodWord = food.getClass().getName();
            int position = key.lastIndexOf(".");
            foodWord = foodWord.substring(position+1);
            System.out.println("\t " + foodWord + "\t Quantity: "  + food.getQuantity());
        }
        System.out.println("\n");
    }
}
