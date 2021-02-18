package com.company;


import java.io.Serializable;

public abstract class Animal implements Serializable {
    private String name;
    private String gender;
    private int health = 100;
    private boolean isSick = false;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

    public String getGender(){
        return this.gender;
    }
    public String getName(){
        return this.name;
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

    public boolean isAnimalSick(){
        double sickness = Math.random();
        if(sickness <= 0.2){
            isSick = true;
        }
        return isSick;
    }

    public void removeSickness(){
        this.isSick = false;
    }

    public boolean getSick(){ return this.isSick; }

    public int reduceHealth(){
        int reducer = (int)(Math.random()*((30-10)+1))+10;
        health = health - reducer;
        return reducer;
    }

    public boolean feedAnimal(Food food){
        double hpIncrease = health*0.1;
        System.out.println(hpIncrease);
        setHealth((int)hpIncrease);
        return true;
    }

}
