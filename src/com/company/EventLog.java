package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class EventLog {
    ArrayList<String> name = new ArrayList<>();
    ArrayList<Integer> healthLost = new ArrayList<>();
    LinkedHashMap<String, LinkedHashMap<String, Integer>> users = new LinkedHashMap<>();

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

    public void removeFromEventLog(String key){

    }

    public void showEventLog(String playerName){ //Är det okej att jag skickar Player player överallt såhär?..


/*
        System.out.println(users.get(playerName).keySet());
        System.out.println(users.get(playerName).values());


 */
        if(!users.get(playerName).isEmpty()){
            System.out.println("\n---- EVENT LOG ---- ");
            for(String key: users.get(playerName).keySet()){

                if(users.get(playerName).get(key) == 1000){
                    System.out.println("\t" + key + " has died!");
                } else if(users.get(playerName).get(key) == 2000){
                    System.out.println("\t" + key + " had no money or animals and has lost the game.");

                } else {
                    System.out.println("\t" + key + " has lost " + users.get(playerName).get(key) + " HP!");
                }

            }
            System.out.println("------------------- ");
        }
        users.get(playerName).clear();


       // for(int i = 0; i < name.size(); i++){
           // System.out.println("\t  -  " + name.get(i) + " lost: " + healthLost.get(i) + " HP\n");
     //   }
     /*   name.clear();
        healthLost.clear();


         */
    }

}
