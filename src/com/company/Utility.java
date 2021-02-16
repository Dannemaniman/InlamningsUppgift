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

    public static String getClassName(Class c){
        String newWord = c.getName();
        int position = newWord.lastIndexOf(".");
        newWord = newWord.substring(position+1);

        return newWord;
    }
}
