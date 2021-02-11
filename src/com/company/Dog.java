package com.company;

public class Dog extends Animal{
    final int purchasePrice = 70; //KOLLA SÅ ATT DET ÄR OKEJ ATT LOGIKEN LIGGER HÄR MED PURCHASE PRICE... KANSKE LÄGGA I STORE?
    public Dog(String name, String gender) {
        super(name, gender);
    }
}
