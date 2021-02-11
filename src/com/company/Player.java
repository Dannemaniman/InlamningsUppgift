package com.company;

import java.util.ArrayList;

public class Player {
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
        return money > amount;
    }

    public int getMoney(){
        return this.money;
    }

    public void addAnimal(Animal animal){
           this.animals.add(animal);
    }

    public void removeAnimal(String key){  //Bara skicka in som en string text "Bird" så kommer jag deleta med det från min HashMap
       // if(this.Animals.get(key).get(key).equals(0)){ //om siffran är noll.. ta bort djuret
       //     this.Animals.remove(key);
       // }
    }

    public void getAnimals(){
        System.out.println("\n - ANIMALS IN POSSESSION - ");
        for(Animal animal : animals){
            String key = animal.getName();
            String animalWord = animal.getClass().getName();//kanske gör detta till en till enhet i animal klassen..
            int position = animalWord.lastIndexOf(".");
            animalWord = animalWord.substring(position+1);
            System.out.println("\t " + key + "\t " + animalWord + "\t HP: "  + animal.getHealth() + "\n");
        }
    }

    public ArrayList<Food> setFood(Food food){

        for(Food foo: foods){
            System.out.println(foo.getClass().getName());
            System.out.println(food.getClass().getName());
            System.out.println("hej");
            if(foo.getClass().getName().equals(food.getClass().getName())){
                foo.setQuantity(food.getQuantity());
                getFood();
                return this.foods;
            }
        }
        foods.add(food);
        getFood();
        return this.foods;
    }

    public void getFood(){
        System.out.println("\n - FOODS IN POSSESSION - ");
        for(Food food : foods){
            String key = food.getClass().getName();
            String foodWord = food.getClass().getName();//kanske gör detta till en till enhet i animal klassen..
            int position = key.lastIndexOf(".");
            foodWord = foodWord.substring(position+1);
            System.out.println("\t " + foodWord + "\t Quantity: "  + food.getQuantity() + "\n");
        }
    }

}
