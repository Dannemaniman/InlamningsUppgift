package com.company;

/*enum Gender{
    MALE,
    FEMALE
}*/

public abstract class Animal {
    private String name;
    private String gender; //Temporary String, make into enum?
    private int health;

    public Animal(String name, int health, String gender){
        this.name = name;
        this.health = health;
        this.gender = gender;
    }

}
