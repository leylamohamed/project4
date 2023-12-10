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
        boolean debug;
        Scanner s = new Scanner(System.in);
        System.out.println("What difficulty level would you like to play?\na: Easy\nb: Medium\nc: Hard");
        String mode = s.nextLine();
        System.out.println("Would you like to play in debug mode? Type: Y or N");
        String answer = s.nextLine();

        if (answer.equals("Y")) {
            debug = true;
        }
        else {debug = false;}

        Minefield minefield;
        if (mode.equals("a")) {
            minefield = new Minefield(5, 5, 5);
        }
        else if (mode.equals("b")) {
            minefield = new Minefield(9, 9, 12);
        }
        else if (mode.equals("c")) {
            minefield = new Minefield(20, 20, 40);
        } else {
            System.out.println("Default: Easy mode");
            minefield = new Minefield();
        }
        //print out minefield

        System.out.println(minefield);

        //get user's starting position
        System.out.println("Where would you like to start? Format: [row] [column], separated by a space");
        String line = s.nextLine();
        String[] startPosition = line.split(" ");
        int startX = 0;
        int startY = 0;
        if(startPosition.length == 2) { //checks if user entered 2 numbers
            startX = Integer.parseInt(startPosition[0]);
            startY = Integer.parseInt(startPosition[1]);

        }
        else {
            System.out.println("Wrong format. Enter two integers separated by a space.");
            minefield.game = false;
        }
        if (minefield.verifyInBounds(startX, startY)) {
            minefield.createMines(startX, startY, minefield.flags);
            minefield.evaluateField();
            minefield.revealStartingArea(startX, startY);
        }
        else {
            System.out.println("Out of bounds");
            minefield.game = false;
        }
        int currFlags = minefield.flags;
        if(debug) {
            minefield.debug();
        }
        else {
            System.out.println(minefield);
        }

        while (!minefield.gameOver()) {
            //ask user to guess cells to place flags, call guess()
            //handle debug()
            //loop until no flags left
            //check if game is over 


            }


        }
    }
}

