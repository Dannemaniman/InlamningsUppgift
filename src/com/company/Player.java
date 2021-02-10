package com.company;

public class Player {
    private final String name;
    private int money = 500;

    public Player(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void changeMoney(int money){
        this.money =- money;
    }

    public int getMoney(){
        return this.money;
    }

}
