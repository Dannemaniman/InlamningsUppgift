package com.company;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int rounds;
    private ArrayList<Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Store store = new Store();
    private int counter = 0;
    boolean activeGame = true;
    private EventLog logData = new EventLog();

    public static void main(String[] args) {

    }

    public void newGame(){
        int playersSum;
        System.out.println("\n\n--- Welcome to the Game! ---\n");

        while(true){
                System.out.println("1. New Game");
                System.out.println("2. Load Game");
                int listSelection = Utility.convertAndTestInput(scanner.nextLine());

                if(listSelection != 1 && listSelection != 2){
                    System.out.println("Wrong input! Press 1 or 2");
                    continue;
                }

                if(listSelection == 2){
                    if(Files.exists(Paths.get("saveledger.ser"))){
                        int counter = 1;
                        ArrayList<String> savedGames = (ArrayList<String>) Serializer.deserialize("saveledger.ser");

                        System.out.println("\n  Saved Games: ");
                        for(String save: savedGames){
                             System.out.println("\t" + counter + ". " + save);
                             counter++;
                         }
                        System.out.println("\t" + counter + ". Back");

                        listSelection = Utility.convertAndTestInput(scanner.nextLine());

                        if(listSelection == counter || listSelection == -1){
                            System.out.println("\nWrong Input!\n");
                            continue;
                        }

                        if(listSelection < counter && listSelection > 0){
                            String gameToLoad = savedGames.get(listSelection-1);
                            SaveStatistics loadGame = (SaveStatistics) Serializer.deserialize(gameToLoad);
                            rounds = loadGame.getRounds();
                            players = loadGame.getPlayers();
                            this.counter = loadGame.getCounter();
                            logData = loadGame.getLogData();
                            System.out.println("...Done Loading, press Enter!");
                            scanner.nextLine();
                            players.get(this.counter).showInventory();
                            logData.showEventLog(players.get(this.counter).getName());
                            optionScreen();
                        }
                     } else {
                        System.out.println("No saved files.. press Enter");
                        scanner.nextLine();
                        continue;
                    }
                }

                System.out.println(" - How many rounds? (5 - 30) - \n");

                rounds = Utility.convertAndTestInput(scanner.nextLine());
                if(rounds == -1){ //Kolla så detta verkligen funkar... !
                    System.out.println("    - Error! - Must be number between 5 - 30\n");
                    continue;
                }

                if(rounds < 31 && rounds > 4){
                    System.out.println("\n - How many players? (1-4) - \n");
                    playersSum = Utility.convertAndTestInput(scanner.nextLine());
                    if(playersSum == -1){
                        System.out.println("    - Error! - Must be number between 5 - 30\n");
                        continue;
                    }

                    if(playersSum > 0 && playersSum < 5){
                        break;
                    } else {
                        System.out.println("Wrong amount of players!");
                    }

                } else {
                    System.out.println(" - Error - Not a number between 5 - 30");
                }
            }

        for(int i = 0; i < playersSum; i++){
            switch( i ){
                case 0 -> System.out.println("\nWhat is the first Players Name?\n");
                case 1 -> System.out.println("\nWhat is the second Players Name?\n");
                case 2 -> System.out.println("\nWhat is the third Players Name?\n");
                case 3 -> System.out.println("\nWhat is the fourth Players Name?\n");
            }
            players.add(new Player(scanner.nextLine()));
        }

        System.out.println("\n    The Game Begins... ... ... ... ... ...\n");
        optionScreen();
    }

    void optionScreen(){

        while(rounds != 0 && activeGame){
            if(!logData.getName().isEmpty()){
                logData.showEventLog(players.get(counter).getName());
            //FLYTTAR DENNA LÄNGRE NER I REDUCEHEALTH!!    logData.clearEventLog(players.get(counter).getName()); //den kör denna 2 gånger redan i själva classen när jag visar eventlog??????
            }
            System.out.println("\n- What would *|* " + players.get(counter).getName() + " *|* like to do? - \t\t" + "Rounds Left: " + rounds + "\t\tPlayer Money: " + players.get(counter).getMoney());
            System.out.println("    - 1. Buy Animal");
            System.out.println("    - 2. Buy Food");
            System.out.println("    - 3. Feed Animal");
            System.out.println("    - 4. Mate Animals");
            System.out.println("    - 5. Sell Animal");
            System.out.println("    - 6. Take Animal To Veterinary");
            System.out.println("    - 7. New Turn");
            System.out.println("    - 8. Save Game");

            int choice = Utility.convertAndTestInput(scanner.nextLine());
            if(choice == -1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - 7");
                continue;
            }

            switch (choice){
                case 1 -> {
                    Player updatedPlayer = store.browseAnimals(players.get(counter));
                    players.set(counter, updatedPlayer);

                }
                case 2 -> {
                    Player updatedPlayer = store.buyFood(players.get(counter));
                    players.set(counter, updatedPlayer);
                }
                case 3 -> {
                    Player updatedPlayer = store.feedAnimal(players.get(counter));
                    players.set(counter, updatedPlayer);
                }
                case 4 -> {
                    if (players.get(counter).getAnimals().size() < 2) {
                        System.out.println("Sorry, you need 2 animals to Mate. Press Enter.");
                        scanner.nextLine();
                        continue;
                    }
                    ArrayList<Animal> updatedAnimals = store.mateAnimal(players.get(counter).getAnimals()); //Börjar med att lägga logiken här.. tänk var den bättre borde sitta
                    players.get(counter).showInventory();
                }
                case 5 -> store.sellAnimal(players.get(counter));
                case 6 -> {
                    takeAnimalToVeterinary();
                }
                case 7 -> System.out.println("");
                case 8 -> {
                        System.out.println("Please Write Filename:");
                        String file = scanner.nextLine() + ".ser";

                        if(!Files.exists(Paths.get("saveledger.ser"))){
                            Serializer.serialize("saveledger.ser", new ArrayList<>());
                        }

                        ArrayList<String> fileName = (ArrayList<String>) Serializer.deserialize("saveledger.ser");
                        fileName.add(file);
                        if(Serializer.serialize("saveledger.ser", fileName)){
                            System.out.println("Success!.. Press Enter to go back.");
                            scanner.nextLine();

                            SaveStatistics saveStats = new SaveStatistics(rounds, players, counter, logData);

                            boolean worked = Serializer.serialize(file, saveStats);
                            System.out.println(worked);
                            if(worked){
                                System.out.println("DOUBLE SUCCESS! SAVESTATS SAVED!!");
                                continue;
                            }
                        } else {
                            System.out.println("Save unsuccessful!.. Press Enter to go back.");
                            scanner.nextLine();
                            continue;
                        }
                }
                default -> System.out.println("    -  Error - Wrong choice! ");
            }
            if(activeGame){
                newTurn();
            }
        }
    }

    public void takeAnimalToVeterinary(){
        while(true){
            int counter2 = 1;
            System.out.println("\n- What Animal do you want to try and heal? - \t\t" + players.get(counter).getMoney());
            for(Animal animal: players.get(counter).getAnimals()){
                System.out.println(animal.getSick());
                System.out.println(counter2 + ". " + animal.getName() + " " +  Utility.getClassName(animal.getClass()) + " HP: " + animal.getHealth() + (animal.getSick() ? " --Sick--" : ""));
                counter2++;
        }
            System.out.println(counter2 + ". Done");
            int choice = Utility.convertAndTestInput(scanner.nextLine());

            if(choice == players.get(counter).getAnimals().size()+1){
                System.out.println("You pressed 'Done'");
                break;
            }

            if(players.get(counter).getAnimals().get(choice-1).getSick()){
                int cost = calculateVeterinaryCost(Utility.getClassName(players.get(counter).getAnimals().get(choice-1).getClass()));
                System.out.println("Would you like to try and heal it? It will cost " + cost);
                System.out.println("1. Yes");
                System.out.println("2. No");
                int listSelection = Utility.convertAndTestInput(scanner.nextLine());

                if(listSelection != 1){
                    continue;
                } else{
                    players.get(counter).setMoney(cost);
                    if(Math.random() > 0.5){
                        System.out.println("Success! " + players.get(counter).getAnimals().get(choice-1).getName() + " is no longer sick!");
                        players.get(counter).getAnimals().get(choice-1).removeSickness();
                        logData.removeSickAnimal(players.get(counter).getAnimals().get(choice-1));
                        logData.clearEventLog(players.get(counter).getName());

                    } else {
                        System.out.println("Sorry didnt work... " + players.get(counter).getAnimals().get(choice-1).getName() + " has died.. Press enter.");
                        players.get(counter).removeAnimal(players.get(counter).getAnimals().get(choice-1).getName());
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println(players.get(counter).getAnimals().get(choice-1).getName() + " isn't sick!");
            }
        }
    }

    int calculateVeterinaryCost(String animal){
        int price = 0;

        switch(animal){
            case "Bird" -> price = 20;
            case "Cockroach" -> price = 15;
            case "Cow" -> price = 30;
            case "Dog" -> price = 25;
            case "Goat" -> price = 32;
            case "Horse" -> price = 45;
            default -> price = 0;
        }
        return price;
    }

    private void newTurn(){

        if(players.get(counter) == players.get(players.size()-1)){
            decrementRounds();
        }
        reduceHealth();
        checkIfPlayerLost();
        incrementTurn();

        if(rounds != 0){
            showInventory();
        }
    }

    private void reduceHealth(){
        ArrayList<Animal> playerAnimals = players.get(counter).getAnimals();
        ArrayList<String> animalsToRemove = new ArrayList<>();
        logData.clearEventLog(players.get(counter).getName());

        for(Animal animal: playerAnimals){
            int healthLost = animal.reduceHealth();
            logData.addToEventLog(animal.getName(), healthLost, players.get(counter));

            if(animal.getHealth() <= 0){
                logData.addToEventLog(animal.getName(), 1000, players.get(counter));
                animalsToRemove.add(animal.getName());
            }

            if(animal.isAnimalSick()){
                logData.addToSickAnimals(animal);
            }
        }
        if(!animalsToRemove.isEmpty()){
            for(String removeAnimal: animalsToRemove){
                players.get(counter).removeAnimal(removeAnimal);
            }
        }
    }

    private void incrementTurn(){
        if(counter < players.size()-1){
            counter++;
        } else {
            counter = 0;
        }
    }

    private void decrementRounds(){
        if(rounds != 0){
            rounds--;
            if(rounds == 0){
                gameOver();
            }
        }
    }

    void showInventory(){
        players.get(counter).showInventory();
    }

    private void checkIfPlayerLost(){
        System.out.println("1");
        System.out.println(counter);
        if(players.get(counter).getAnimals().isEmpty() && players.get(counter).getMoney() < 10){
            logData.addToEventLog(players.get(counter).getName(), 2000, players.get(counter));
            System.out.println("2");
            if(players.size() != 1){
                System.out.println("Player " + players.get(counter).getName() + " has lost and left the game... Press Enter.");
                scanner.nextLine();
                players.remove(counter);
            }
        }
        if(players.size() == 1 && players.get(counter).getMoney() < 10 && players.get(counter).getAnimals().isEmpty()){
            gameOver();
        }
    }

    void gameOver(){
        activeGame = false;
        System.out.println("\n \t \t    GameOver! \n");
        System.out.println("\t\t---- Stats ----");
        int leadingScore = 0;
        String winner = "";
        for (Player player: players){
            int money = store.sellAllAnimals(player.getAnimals());
            player.setMoney(-money);
            System.out.println(player.getName() + " ended up with " + (player.getMoney()) + " Money!"); // + money
            if(leadingScore == 0 || leadingScore < player.getMoney()){
             winner = player.getName();
             leadingScore = player.getMoney();
            }
        }
        System.out.println("\t----------------------");
        System.out.println("\n \tThe Winner is " + winner + " with " + leadingScore + " \n");
        System.exit(0);
    }
}
