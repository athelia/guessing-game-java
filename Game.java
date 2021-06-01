/*

A number-guessing game.

*/

import java.util.Scanner; //to accept command line input
import java.util.Random; //to generate a random number
import java.util.InputMismatchException; //to use the try/catch if a string

public class Game {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Hello there!");
        System.out.println("What's your name?");
        System.out.print("> ");
        String name = input.nextLine();
        System.out.println("Nice to meet you, " + name + "!");

        Random rand = new Random();
        int target = rand.nextInt(100);
        int attempts = 0;
        String again;

        System.out.println("Please guess a number between 1 and 100 (hint: it's " + target +")");
        
        int guess;
        int best = -1;
        int maxAttempts = 15;
        // int guess, best;

        while (true) {
            attempts++;
            System.out.print("> ");
            try {
                guess = input.nextInt();
            } catch (InputMismatchException e){
                String badInput = input.next();
                System.out.println("Not a number!");
                continue;
            }

            if (guess != target & attempts < maxAttempts) {
                System.out.println("Not quite!");
                // handle out of bounds
                if (guess > 100 | guess < 1){
                    System.out.println(guess + " is not between 1 and 100!");
                } else if (guess < target) {
                    System.out.println(guess + " is too low!");
                } else {
                    System.out.println(guess + " is too high!");
                }
            // game is over. process win/loss and start loop over
            } else {
                if (guess != target & attempts == maxAttempts) {
                    System.out.println("Too bad, you ran out of guesses! My number was " + target);
                } else { //in all other cases, we got here because the person won the game
                    // handle updating best score
                    if (best == -1) {
                        best = attempts;
                    } else if (best > attempts) {
                        best = attempts;
                    }
                    System.out.println("Great job, " + name + "! That was my number.");
                    System.out.println("You succeeded in " + attempts + " guesses.");
                    System.out.println("Your best score is " + best + " guesses.");
                }
                System.out.println("Would you like to play again? Y / N");
                System.out.print("> ");
                again = input.next();
                while (!again.equals("Y") & !again.equals("N")) {
                    System.out.println("Please type Y or N!");
                    System.out.print("> ");
                    again = input.next();
                }
                if (again.equals("Y")) {
                    target = rand.nextInt(100);
                    attempts = 0;
                    System.out.println("Please guess a number between 1 and 100 (hint: it's " + target +")");
                } else {
                    System.out.println("Thank you for playing!");
                    break;
                }
            }
        }
    }
}
