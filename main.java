//Written by Leyla Mohamed (moha1594) and Cyrus Vang (vang3339)

//Import Section
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


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
            System.out.println("Wrong format. Enter two integers separated by a space.\nGame Over!");
            minefield.game = false;
        }
        if (minefield.verifyInBounds(startX, startY)) {
            minefield.createMines(startX, startY, minefield.flags);
            minefield.evaluateField();
            minefield.revealStartingArea(startX, startY);
        }
        else {
            System.out.println("Out of bounds.\nGame Over!");
            minefield.game = false;
        }
        int currFlags = minefield.flags;

        while (!minefield.gameOver()) {
            if (debug) {
                minefield.debug();
            } else {
                System.out.println(minefield.toString());
            }

            // Get user input for guess
            System.out.println("Enter row and column for your guess (e.g., 0 1):");
            int guessRow = s.nextInt();
            int guessCol = s.nextInt();

            // Get user input for flag placement
            System.out.println("Do you want to place a flag? (true/false)");
            boolean flag = false;
            boolean wrongFlag = false;
            try {
                flag = s.nextBoolean();
            }
            catch (InputMismatchException e) {
                minefield.game = false;
                wrongFlag = true;

            }

            // Process user guess
            boolean hitMine = minefield.guess(guessRow, guessCol, flag);
            currFlags--;
            if (currFlags == 0) {
                minefield.game = false;
            }

            // Check if the game is over
            if (minefield.gameOver()) {
                if (wrongFlag) {
                    System.out.println("Wrong format. Please enter either 'true' or 'false'\nGame Over!");
                    break;
                }
                if (hitMine) {
                    System.out.println("You hit a mine. You lose!\nGame Over!");
                } else {
                    System.out.println("Congratulations! You won!\nGame Over!");
                }
                break;
            }
        }
        // Close the scanner
        s.close();
    }
}

