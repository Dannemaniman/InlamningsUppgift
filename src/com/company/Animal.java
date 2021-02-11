package com.company;

/*enum Gender{
    MALE,
    FEMALE
}*/

public abstract class Animal {
    private String name;
    private String gender; //Temporary String, make into enum?
    private int health = 100;

    public Animal(String name, String gender){
        this.name = name;
        this.gender = gender;
    }

    public int getHealth(){
        return this.health;
    }

    public String getName(){
        return this.name;
    }

    public void reduceHealth(){
        int reducer = (int)(Math.random()*((30-10)+1))+10;
        health = health - reducer;
        System.out.println(health);
    }

}
