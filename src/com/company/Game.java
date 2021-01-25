package com.company;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
       newGame();
    }

    public static void newGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Welcome to the Game! ---\n");
        System.out.println(" - Hur många rundor vill du köra? - \n");
        int rundor = Integer.parseInt(scanner.nextLine());
    }
}
