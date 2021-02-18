package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class EventLog  implements Serializable {
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> healthLost = new ArrayList<>();
    LinkedHashMap<String, LinkedHashMap<String, Integer>> users = new LinkedHashMap<>();
    ArrayList<String> sickAnimals = new ArrayList<>();

    public void addToEventLog(String name, int healthLost, Player player){
        this.name.add(name);
        this.healthLost.add(healthLost);

        //player data
        String playerName = player.getName();


        if(users.containsKey(playerName)){
            users.get(playerName).put(name, healthLost);
        } else{
            LinkedHashMap<String, Integer> userData = new LinkedHashMap<String, Integer>();
            userData.put(name, healthLost);
            users.put(playerName, userData);
        }
    }

    public void addToSickAnimals(Animal animal){
        sickAnimals.add(animal.getName());
    }

    public void removeSickAnimal(Animal animal){
        sickAnimals.remove(animal);
    }

    public void showEventLog(String playerName){ //Är det okej att jag skickar Player player överallt såhär?..
        if(!users.get(playerName).isEmpty()){
            System.out.println("\n---- EVENT LOG ---- ");
            for(String key: users.get(playerName).keySet()){

                if(users.get(playerName).get(key) == 1000){
                    System.out.println("\t" + key + " has died!");
                    continue;
                } else if(users.get(playerName).get(key) == 2000){
                    System.out.println("\t" + key + " had no money or animals and has lost the game.");

                }
                    System.out.println("\t" + key + " has lost " + users.get(playerName).get(key) + " HP!");

                if(sickAnimals.contains(key)){ // if(users.get(playerName).get(key) == 3000){
                        System.out.println("\t" + key + " has become sick! You need to take her to a Veterinary!");
                    }
                }
            System.out.println("------------------- ");
        }
        users.get(playerName).clear();
    }


    public void clearEventLog(String playerName){
        users.get(playerName).clear();
    }

}
