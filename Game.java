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
        System.out.println("Would you like to set a range? Y / N:");
        System.out.print("> ");
        String setRange = input.nextLine();

        int attempts = 0;
        String again;
        int min, max;

        while (!setRange.equals("Y") & !setRange.equals("N")) {
            System.out.println("Please type Y or N!");
            System.out.print("> ");
            setRange = input.nextLine();
        }
        
        // setting up the target number
        while (true) {
            if (setRange.equals("Y")){
                System.out.println("Choose the minimum: ");
                System.out.print("> ");
                try {
                    min = input.nextInt();
                } catch (InputMismatchException e) {
                    String badInput = input.nextLine();
                    System.out.println("Not a number!");
                    continue;
                }
                System.out.println("Choose the maximum: ");
                System.out.print("> ");
                try {
                    max = input.nextInt();
                } catch (InputMismatchException e) {
                    String badInput = input.nextLine();
                    System.out.println("Not a number!");
                    continue;
                }
                break;
            } else {
                min = 0;
                max = 100;
                break;
            }
        }
        Random rand = new Random();
        //https://javabeginnerstutorial.com/code-base/generate-random-number-java-between-two-numbers/ method 4
        int target = rand.ints(min, max).findFirst().getAsInt(); 
        System.out.println(rand.ints(min, max));
        
        // answer provided for debugging :D 
        System.out.println("Please guess a number between " + min + " and " + max + " (hint: it's " + target +")");
        
        int guess;
        int best = -1; //set to an int that it couldn't be, for simplicity
        int maxAttempts = 15;

        // finally, the gameplay loop
        while (true) {

            attempts++;
            System.out.print("> ");
            try { 
                // this is pretty cool: the thing that might fail is assigning 
                // to a variable declared as int. so that's the thing that's `tried`.
                guess = input.nextInt();
            } catch (InputMismatchException e){ //I got the name of the error by causing it
                String badInput = input.nextLine(); //skip the input that threw the error
                System.out.println("Not a number!");
                continue;
            }

            if (guess != target & attempts < maxAttempts) {
                System.out.println("Not quite!");
                // handle out of bounds
                if (guess > max | guess < min){
                    System.out.println(guess + " is not between " + min + " and " + max + "!");
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
                // regardless of win/loss, you can play again
                System.out.println("Would you like to play again? Y / N");
                System.out.print("> ");
                again = input.nextLine();
                while (!again.equals("Y") & !again.equals("N")) {
                    System.out.println("Please type Y or N!");
                    System.out.print("> ");
                    again = input.nextLine();
                }
                if (again.equals("Y")) {
                    target = rand.nextInt(100);
                    attempts = 0;
                    // too lazy for fixing this replay option to accept a range
                    System.out.println("Please guess a number between 0 and 100 (hint: it's " + target +")");
                    // setting these manually so that the print messages in the loop make sense
                    min = 0
                    max = 100
                } else {
                    System.out.println("Thank you for playing!");
                    break;
                }
            }
        }
    }
}
