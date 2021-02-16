package com.company;

import java.lang.reflect.Array;

public abstract class Animal {
    private String name;
    private String gender;
    private int health = 100;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

    public String getGender(){
        return this.gender;
    }

    public int getHealth(){
        return this.health;
    }
    public void setHealth(int healingAmount) {
        health = health + healingAmount;
        if(health > 100){
            health = 100;
        }
    }

    public String getName(){
        return this.name;
    }

    public int reduceHealth(){
        int reducer = (int)(Math.random()*((30-10)+1))+10;
        health = health - reducer;
        return reducer;
    }

    public boolean feedAnimal(Food food){
      //  if(food == Grass){

        double hpIncrease = health*0.1;
        System.out.println(hpIncrease);
        setHealth((int)hpIncrease);
       // setHealth(food.getHealingAmount());
        return true;
       // }
    }

}
