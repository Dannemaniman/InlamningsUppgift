package com.company;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class EventLog  implements Serializable {
   private ArrayList<String> name = new ArrayList<>();
   private ArrayList<Integer> healthLost = new ArrayList<>();
   private LinkedHashMap<String, LinkedHashMap<String, Integer>> users = new LinkedHashMap<>();
   private LinkedHashMap<String, ArrayList<String>> sickAnimals = new LinkedHashMap<>();


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

    public void addToSickAnimals(Player player, Animal animal){
        ArrayList<String> animals = sickAnimals.get(player.getName());

        if (animals == null) {
            animals = new ArrayList<>();
        }

        if(!animals.contains(animal.getName())){
            animals.add(animal.getName());
        }
        sickAnimals.put(player.getName(), animals);
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

                }

            System.out.println("------------------- ");
            if(sickAnimals.get(playerName) != null){
                for(String animal: sickAnimals.get(playerName)){
                   System.out.println("\t" + animal + " has become sick! You need to take her to a Veterinary!");
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

    public void clearSickAnimals(Animal animal, Player player){
        if(sickAnimals != null){
            for(String anima: sickAnimals.get(player.getName())){
                if(anima == animal.getName()){
                    sickAnimals.get(player.getName()).remove(anima);
                    break;
                }
            }
    }
    }
}
