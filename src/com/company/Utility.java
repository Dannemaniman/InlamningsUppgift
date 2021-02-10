package com.company;

public class Utility {

    public static int convertAndTestInput(String input){
        try {
            int convertedInput = Integer.parseInt(input);
            return convertedInput;
        } catch (NumberFormatException e){
            return -1;
        }
    }
}
