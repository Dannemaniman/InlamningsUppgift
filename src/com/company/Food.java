package com.company;

public abstract class Food {
    private String type;
    private int quantity;
    private int healingAmount;

    public Food(String type, int amount, int healingAmount){
        this.type = type;
        this.quantity = amount;
        this.healingAmount = healingAmount;
    }

    public int getHealingAmount(){
        return this.healingAmount;
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
