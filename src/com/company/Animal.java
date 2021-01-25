package com.company;

public abstract class Animal {
    String name;
    //add gender with a enum
    int health;

    public Animal(String name, int health){
        this.name = name;
        this.health = health;
    }

}
