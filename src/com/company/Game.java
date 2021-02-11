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
                case 0 -> System.out.println("\nWhat is the first Players Name?");
                case 1 -> System.out.println("\nWhat is the second Players Name?");
                case 2 -> System.out.println("\nWhat is the third Players Name?");
                case 3 -> System.out.println("\nWhat is the fourth Players Name?");
                default -> System.out.println(" - Error!"); //Tänk om här vad default ska vara.. !

            }
            players.add(new Player(scanner.nextLine()));
        }
        System.out.println("\n    The Game Begins...\n");
        optionScreen();
    }

    void optionScreen(){
        while(rounds != 0){
            System.out.println("- What would " + players.get(counter).getName() + " like to do? - \t\t" + "Rounds Left: " + rounds);
            System.out.println("    - 1. Buy Animal"); //det står köpa max så många djur som hen har pengar till (varje typ av djur har ett fast ursprungspris oavsett kön)
            System.out.println("    - 2. Buy Food");  //Köpa max så mycket mat som hen har pengar till (mat köps i kg och har kilopris)
            System.out.println("    - 3. Feed Animal"); //Mata sina djur (vilken slags mat måste anges för varje djur man vill mata)
            System.out.println("    - 4. Mate Animals"); //d) Försöka få ett par djur att para sig, då skapas i 50% av fallen nya djur man äger (om djuren är av samma slag och olika kön, olka slags djur kan inte para sig). Om parningen lyckas kan spelaren döpa det/de nya djuret/djuren (olika slags djur kan ha olika många ungar/parning). Könet på djuren som skapas vid parning slumpas (50% hona, 50% hane).
            System.out.println("    - 5. Sell Animal"); //Sälja ett-flera djur (priset är ursprungspriset gånger hälsovärdet)

            int choice = Utility.convertAndTestInput(scanner.nextLine());
            if(choice == -1){
                System.out.println("    - Error! - Wrong Choice! Must be number between 1 - 5");
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
                case 3 -> store.feedAnimal();
                case 4 -> store.mateAnimal();
                case 5 -> store.sellAnimal();
                default -> System.out.println("    -  Error - Wrong choice! ");
            }
            showSummary();
            reduceHealth();
            incrementTurn();
            decrementRounds();
        }
    }

    private void reduceHealth(){
        players.get(counter).getAnimals();
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
        } else {
            gameOver();
        }
    }

    void showSummary(){
        players.get(counter).getAnimals();
    }

    void gameOver(){
        System.out.println("GameOver!");
    }




}
