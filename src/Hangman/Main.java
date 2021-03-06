package Hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Start the game
        playGame(args);
    }

    public static void playGame(String args[]) {
        String currentWord = "";
        String currentHint;
        String wordOutputToUser = "";
        String userGuess;
        String output;
        List<String> userHasGuessed = new ArrayList<String>();
        int wrongGuess = 0;
        int charLocation;

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
        currentWord = (String) words[number].toLowerCase();
        currentHint = (String) hints[number];

        String stickman[] = new String[7];
        stickman[0] = " --\n   |\n   |\n   |\n_____\n";
        stickman[1] = " --\n o |\n   |\n   |\n_____\n";
        stickman[2] = " --\n o |\n/  |\n   |\n_____\n";
        stickman[3] = " --\n o |\n/| |\n   |\n_____\n";
        stickman[4] = " --\n o |\n/|\\|\n   |\n_____\n";
        stickman[5] = " --\n o |\n/|\\|\n/  |\n_____\n";
        stickman[6] = " --\n o |\n/|\\|\n/ \\|\n_____\n";

        System.out.println(" ------------------------------------------");
        System.out.println("| Tere tulemast mängima Hangman mängu!     |");
        System.out.println(" ------------------------------------------");
        System.out.println("| Reeglid: Pead ära arvama sõna 6 katsega. |");
        System.out.println("|                                          |");
        System.out.println("| Vihje saamiseks kirjuta 'vihje!'         |");
        System.out.println("| Mängust väljumiseks kirjuta 'exit'.      |");
        System.out.println(" ------------------------------------------");

        // Change all lowercase characters to *
        wordOutputToUser = currentWord.replaceAll("[a-z]", "*");

        // Make to StringBuilders
        StringBuilder guessedWord = new StringBuilder(wordOutputToUser);
        StringBuilder guessedWord2 = new StringBuilder(currentWord);

        output = guessedWord.toString();

        // display first stickman image
        System.out.println("\n" + stickman[0]);

        System.out.println("Äraarvatav sõna: " + wordOutputToUser);

        // Start game loop
        while (output.contains("*") && wrongGuess < 6) { // check if there is any characters to guess or lives left
            Scanner userInput = new Scanner(System.in);
            // If user haven't guessed yet, then show this lines
            if (!userHasGuessed.isEmpty()) {
                System.out.println("\n" + guessedWord.toString().toUpperCase());
                System.out.println("\n" + stickman[(wrongGuess)]);
                System.out.println("\nElusid järgi: " + (6 - wrongGuess)); // show how much lives left
                System.out.println("Pakutud tähed: " + userHasGuessed.toString().toUpperCase()); // show letters what user is guessed
            }
            System.out.println("Sinu pakkumine?");
            userGuess = userInput.nextLine().toLowerCase(); // ask input from user
            // if user don't input anything
            if (userGuess.isEmpty()) {
                System.out.println("Kahjuks ei sisestanud sa ühtegi tähte!");
                System.out.println("-------------------");
                continue; // continue from beginning of loop
            }
            // if user asks for clue
            if (userGuess.equals("vihje!")) {
                System.out.println("Vihje: " + currentHint);
                System.out.println("-------------------");
                continue; // continue from beginning of loop
            }
            // if user wish to exit game
            if (userGuess.equals("exit")) {
                System.out.println("\nLõpetame mängu!");
                System.exit(0); // exit from game
            } else {
                // if user has already guessed entered letter
                if (userHasGuessed.contains(userGuess)) {
                    System.out.println("\nOled seda tähte juba pakkunud!");
                } else {
                    // if user guessed correctly
                    if (currentWord.contains(userGuess)) {
                        userHasGuessed.add(userGuess); // add letter to guessed list
                        System.out.print("\nHästi pakutud!\n");
                        // change user input string to char
                        char guessedChar = userGuess.charAt(0);
                        // search for user guessed letter in word
                        charLocation = guessedWord2.indexOf(userGuess);
                        // set character visible to user
                        guessedWord.setCharAt(charLocation, guessedChar);
                        // replace character with _ in string what user can't see
                        guessedWord2.setCharAt(charLocation, '_');

                        // if there is more than one guessed character then repeat previous
                        while (charLocation > -1) {
                            guessedChar = userGuess.charAt(0);
                            charLocation = guessedWord2.indexOf(userGuess);
                            if (charLocation > -1) {
                                guessedWord.setCharAt(charLocation, guessedChar);
                                guessedWord2.setCharAt(charLocation, '_');
                            }
                        }
                    } else {
                        // if user guess is wrong
                        wrongGuess = wrongGuess + 1;
                        userHasGuessed.add(userGuess); // add letter to guessed list
                        System.out.print("\nProovi uuesti!\n");
                    }
                }
                System.out.println("-------------------");
                output = guessedWord.toString();
            }
        }

        // Text to display when user won
        if (!output.contains("*")) {
            System.out.println("Palju õnne, võitsid mängu!");
            System.out.println("Äraarvatav sõna oli " + currentWord.toUpperCase() + ".");
        }

        // Text to display when user has lost
        if (wrongGuess == 6) {
            System.out.println("\n" + stickman[(wrongGuess)]);
            System.out.println("Kahjuks kaotasid mängu!");
            System.out.println("Äraarvatav sõna oli " + currentWord.toUpperCase() + ".");
        }
    }
}