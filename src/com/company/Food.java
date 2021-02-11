package com.company;

public abstract class Food {
    private String type;
    private int quantity;

    public Food(String type, int amount){
        this.type = type;
        this.quantity = amount;
    }

    public String getType(){
        return this.type;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = this.quantity + quantity;
    }
}
