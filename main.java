//Import Section
import java.util.Random;
import java.util.Scanner;

/*
 * Provided in this class is the neccessary code to get started with your game's implementation
 * You will find a while loop that should take your minefield's gameOver() method as its conditional
 * Then you will prompt the user with input and manipulate the data as before in project 2
 * 
 * Things to Note:
 * 1. Think back to project 1 when we asked our user to give a shape. In this project we will be asking the user to provide a mode. Then create a minefield accordingly
 * 2. You must implement a way to check if we are playing in debug mode or not.
 * 3. When working inside your while loop think about what happens each turn. We get input, user our methods, check their return values. repeat.
 * 4. Once while loop is complete figure out how to determine if the user won or lost. Print appropriate statement.
 */

public class main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("What difficulty level would you like to play?\na: Easy\nb: Medium\nc: Hard");
        String mode = s.nextLine();
        System.out.println("Would you like to play in debug mode? Type: Y or N");
        String debug = s.nextLine();
        Minefield minefield;
        if (mode.equals("a")) {
            minefield = new Minefield(5, 5, 5);
        }
        if (mode.equals("b")) {
            minefield = new Minefield(9, 9, 12);
        }
        if (mode.equals("c")) {
            minefield = new Minefield(20, 20, 40);
        } else {
            System.out.println("Default: Easy mode");
            minefield = new Minefield();
        }
        while (!minefield.gameOver()) {
            //finish game loop
        }
    }
}
