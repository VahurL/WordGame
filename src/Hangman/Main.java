package Hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        playGame(args);
    }

    /*
    public static final String dictionary = "src/words.txt";

    public static void main(String[] args) throws FileNotFoundException {
        // open the dictionary file and read dictionary into an ArrayList
        Scanner input = new Scanner(new File(dictionary));
        List<String> dictionaryArray = new ArrayList<String>();
        while (input.hasNext()) {
            dictionaryArray.add(input.next().toUpperCase());
        }

        Random randomWord = new Random();
        String currentWord = "";
        currentWord = dictionaryArray.get(randomWord.nextInt(dictionaryArray.size()));
        System.out.println(currentWord);

        if (dictionaryArray.isEmpty()) {
            System.out.println("Kahjuks pole failis sobivaid sõnu!");
        } else {
            playGame(args);
        }
    }
*/

    public static void playGame(String args[]) {
        String currentWord = "";
        String currentHint;
        String wordOutputToUser = "";
        String userGuess;
        String output;
        List<String> userHasGuessed = new ArrayList<String>();
        int livesLeft = 5;
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

        System.out.println("Tere tulemast mängima Hangman mängu.");
        System.out.println("=========================");
        System.out.println("Reeglid: Pead ära arvama sõna 5 katsega.");
        System.out.println();
        System.out.println("Valitud sõna: " + currentWord);
        System.out.println("Valitud sõna vihje: " + currentHint);
        System.out.println("Valitud sõna pikkus: " + currentWord.length());

        System.out.println();
        System.out.println();

        // Change all characters to something else
        wordOutputToUser = currentWord.replaceAll("[a-z]", "*");

        StringBuilder guessedWord = new StringBuilder(wordOutputToUser);
        StringBuilder guessedWord2 = new StringBuilder(currentWord);

        output = guessedWord.toString();

        System.out.println("Sinu äraarvatav sõna: " + wordOutputToUser);

        while (output.contains("*") && livesLeft > 0) {
            Scanner userInput = new Scanner(System.in);
            if (!userHasGuessed.isEmpty()) {
                System.out.println("\n" + guessedWord.toString().toUpperCase());
                System.out.println("\nElusid järgi: " + livesLeft);
                System.out.println("Pakutud tähed: " + userHasGuessed.toString().toUpperCase());
            }
            System.out.println("Sinu pakkumine?");
            userGuess = userInput.nextLine().toLowerCase();
            // If user enters X word then exit game
            if (userGuess.equals("exit")) {
                System.out.println("\nLõpetame mängu!");
                System.exit(0);
            } else {
                if (userHasGuessed.contains(userGuess)) {
                    System.out.println("\nOled seda tähte juba pakkunud!");
                } else {
                    if (currentWord.contains(userGuess)) {
                        userHasGuessed.add(userGuess);
                        System.out.print("\nHästi pakutud!\n");

                        char guessedChar = userGuess.charAt(0);
                        charLocation = guessedWord2.indexOf(userGuess);
//                        System.out.println(charLocation);
                        guessedWord.setCharAt(charLocation, guessedChar);
                        guessedWord2.setCharAt(charLocation, '_');
//                        System.out.println(guessedWord2);

                        while (charLocation > -1) {
                            guessedChar = userGuess.charAt(0);
                            charLocation = guessedWord2.indexOf(userGuess);
//                            System.out.println(charLocation);
                            if (charLocation > -1) {
                                guessedWord.setCharAt(charLocation, guessedChar);
                                guessedWord2.setCharAt(charLocation, '_');
//                                System.out.println(guessedWord2);
                            }
                        }
                    } else {
                        livesLeft = livesLeft - 1;
                        userHasGuessed.add(userGuess);
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
        if (livesLeft == 0) {
            System.out.println("Kahjuks kaotasid mängu!");
            System.out.println("Äraarvatav sõna oli " + currentWord.toUpperCase() + ".");
        }
    }
}