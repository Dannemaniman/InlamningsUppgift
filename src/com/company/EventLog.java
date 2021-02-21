package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class EventLog  implements Serializable {
   private ArrayList<String> name = new ArrayList<>();
   private ArrayList<Integer> healthLost = new ArrayList<>();
   private LinkedHashMap<String, LinkedHashMap<String, Integer>> users = new LinkedHashMap<>();
   private ArrayList<String> sickAnimals = new ArrayList<>();


   public ArrayList<String> getName(){
       return this.name;
   }


    public void addToEventLog(String name, int healthLost, Player player){
        this.name.add(name);
        this.healthLost.add(healthLost);

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

    public void showEventLog(String playerName){
        if(users.get(playerName) != null){
            System.out.println("\n---- EVENT LOG ---- ");
            for(String key: users.get(playerName).keySet()){

                if(users.get(playerName).get(key) == 1000){
                    System.out.println("\t" + key + " has died!");
                    continue;
                } else if(users.get(playerName).get(key) == 2000){
                    System.out.println("\t" + key + " had no money or animals and has lost the game.");

                }
                System.out.println("\t" + key + " has lost " + users.get(playerName).get(key) + " HP!");

                if(sickAnimals.contains(key)){
                        System.out.println("\t" + key + " has become sick! You need to take her to a Veterinary!");
                    }
                }
            System.out.println("------------------- ");
        }
        if(users.get(playerName) != null){
            users.get(playerName).clear();
        }
    }

    public void clearEventLog(String playerName){
        if(users.get(playerName) != null){
            users.get(playerName).clear();
        }
    }

}
