package Hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        String currentWord = "";
        String currentHint;
        String output = "";
        String userGuess;
        List<String> userHasGuessed = new ArrayList<String>();
        int livesLeft = 5;

        // Set words and hints to guess
        String words[] = new String[4];
        String hints[] = new String[4];
        words[0] = "Auto";
        hints[0] = "Auto vihje";
        words[1] = "Jalgratas";
        hints[1] = "Jalgratta vihje";
        words[2] = "Buss";
        hints[2] = "Bussi vihje";
        words[3] = "Traktor";
        hints[3] = "Traktori vihje";


        // Choose word and it hint for guessing randomly
        Random chooseWord = new Random();
        int number = chooseWord.nextInt(words.length);
        currentWord = (String) words[number].toUpperCase();
        currentHint = (String) hints[number];
        System.out.println("Tere tulemast mängima Hangman mängu.");
        System.out.println("=========================");
        System.out.println("Reeglid: Pead ära arvama sõna 5 katsega.");
        System.out.println();
        System.out.println("Valitud sõna: " + currentWord);
        System.out.println("Valitud sõna vihje: " + currentHint);
        System.out.println("Valitud sõna pikkus: " + currentWord.length());


        // System.out.println("Valesid arvamisi: " + wrongGuess);
        // System.out.println(rightGuess);
        System.out.println();
        System.out.println();

        // Display word to guess to user
        for (int i = 0; i < currentWord.length(); i++) {
            output += "*";
        }
        System.out.println(output);

        while (output.contains("*") && livesLeft > 0) {
            Scanner userInput = new Scanner(System.in);
            if (userHasGuessed.isEmpty()) {

            } else {
                System.out.println("\nElusid järgi: " + livesLeft);
                System.out.println("Pakutud tähed: " + userHasGuessed);
            }
            System.out.println("Sinu pakkumine?");
            userGuess = userInput.nextLine().toUpperCase();
            if (userGuess.equals("EXIT")) {
                System.exit(0);
            } else {
                if (userHasGuessed.contains(userGuess)) {
                    System.out.println("\nOled seda tähte juba pakkunud!");
                } else {
                    if (currentWord.contains(userGuess)) {
                        userHasGuessed.add(userGuess);
                        System.out.print("\nTubli! Pakutud täht oli õige!\n");
                    } else {
                        livesLeft = livesLeft - 1;
                        userHasGuessed.add(userGuess);
                        System.out.print("\nProovi uuesti!\n");
                    }
                }
                System.out.println("-------------------");
            }
        }
    }
}