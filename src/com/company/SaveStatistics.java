package com.company;

import java.io.Serializable;
import java.util.ArrayList;

public class SaveStatistics implements Serializable {
   private int rounds;
   private ArrayList<Player> players;
   private int counter;
   private EventLog logData;

    public SaveStatistics(int rounds, ArrayList<Player> players, int counter, EventLog logData){
        this.rounds = rounds;
        this.players = players;
        this.counter = counter;
        this.logData = logData;
    }

    public int getRounds(){
        return this.rounds;
    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public int getCounter(){
        return this.counter;
    }

    public EventLog getLogData(){
        return this.logData;
    }


}
