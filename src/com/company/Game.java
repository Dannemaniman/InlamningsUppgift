package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    int rounds;
    int playersSum;
    ArrayList<Player> players = new ArrayList<>(); //detta borde nog inte vara public... fråga om detta..
    Scanner scanner = new Scanner(System.in);
    Store store = new Store();
    int counter = 0;
    EventLog logData = new EventLog();

    public static void main(String[] args) {

    }

    public void newGame(){
      //  Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to the Game! ---\n");

        while(true){
                System.out.println(" - How many rounds? (5 - 30) - \n");

                rounds = Utility.convertAndTestInput(scanner.nextLine());
                if(rounds == -1){ //Kolla så detta verkligen funkar... !
                    System.out.println("    - Error! - Must be number between 5 - 30");
                    continue;
                }


                if(rounds < 31 && rounds > 4){
                    System.out.println("\n - How many players? (1-4) - \n");
                    playersSum = Utility.convertAndTestInput(scanner.nextLine());
                    if(playersSum == -1){
                        System.out.println("    - Error! - Must be number between 5 - 30");
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
                default -> System.out.println(" - Error!"); //Tänk om här vad default ska vara.. !

            }
            players.add(new Player(scanner.nextLine()));
        }
        System.out.println("\n    The Game Begins... ... ... ... ... ...\n");
        optionScreen();
    }

    void optionScreen(){

        while(rounds != 0){
            if(!logData.name.isEmpty()){ logData.showEventLog(players.get(counter).getName()); }
            //System.out.println("\n*** New Turn!! ***");
            System.out.println("\n- What would *|* " + players.get(counter).getName() + " *|* like to do? - \t\t" + "Rounds Left: " + rounds + "\t\tPlayer Money: " + players.get(counter).getMoney());
            System.out.println("    - 1. Buy Animal");
            System.out.println("    - 2. Buy Food");  //Köpa max så mycket mat som hen har pengar till (mat köps i kg och har kilopris)
            System.out.println("    - 3. Feed Animal"); //Mata sina djur (vilken slags mat måste anges för varje djur man vill mata)
            System.out.println("    - 4. Mate Animals"); //d) Försöka få ett par djur att para sig, då skapas i 50% av fallen nya djur man äger (om djuren är av samma slag och olika kön, olka slags djur kan inte para sig). Om parningen lyckas kan spelaren döpa det/de nya djuret/djuren (olika slags djur kan ha olika många ungar/parning). Könet på djuren som skapas vid parning slumpas (50% hona, 50% hane).
            System.out.println("    - 5. Sell Animal"); //Sälja ett-flera djur (priset är ursprungspriset gånger hälsovärdet)
            System.out.println("    - 6. Show Inventory");
            System.out.println("    - 7. New Turn");
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
                    showInventory();
                    continue;
                }
                case 7 -> System.out.println("");
                default -> System.out.println("    -  Error - Wrong choice! ");
            }
            newTurn();
        }
    }


    private void newTurn(){

        if(players.get(counter) == players.get(players.size()-1)){
            decrementRounds();

        }
        reduceHealth(); //detta ska göras för alla då... VÄLDIGT VIKTIG GLÖM INTE DENNAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
        incrementTurn();
        checkIfPlayerLost();

        if(rounds != 0){
            showInventory();
        }
    }

    private void checkIfPlayerLost(){
        if(players.get(counter).getAnimals().isEmpty() && players.get(counter).getMoney() == 0){
            players.remove(counter);
            logData.addToEventLog(players.get(counter).getName(), 2000, players.get(counter));
        }
    }

    private void reduceHealth(){
        ArrayList<Animal> playerAnimals = players.get(counter).getAnimals();
        String animalToRemove = "";

        for(Animal animal: playerAnimals){
            int healthLost = animal.reduceHealth();
            logData.addToEventLog(animal.getName(), healthLost, players.get(counter));
            if(animal.getHealth() <= 0){
                logData.addToEventLog(animal.getName(), 1000, players.get(counter)); //Glöm inte detta
                animalToRemove = animal.getName();

             //   players.get(counter).removeAnimal(animalToRemove.getName());
            }
        }
        if(!animalToRemove.equals("")){
            //System.out.println("GAME CLASS - DETTA DJURET SKA TAS BORT" + animalToRemove);
            players.get(counter).removeAnimal(animalToRemove);
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

    void gameOver(){
        System.out.println("\n \t \t    GameOver! \n");
        System.out.println("\t\t---- Stats ----");
       // String winner = "";

        //HashMap<String, Integer> participants = new HashMap<>();

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
        System.out.println("\n \tThe Winner is " + winner + " with " + leadingScore);

    }




}
