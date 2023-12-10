import java.util.Queue;
import java.util.Random;

public class Minefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW_BRIGHT = "\u001B[33;1m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_PURPLE = "\u001b[35m";
    public static final String ANSI_CYAN = "\u001b[36m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001b[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001b[45m";
    public static final String ANSI_GREY_BACKGROUND = "\u001b[0m";

    /* 
     * Class Variable Section
     * 
    */
    public int rows;
    public int columns;
    public int flags;
    public Cell[][] field;
    public boolean game;

    /*Things to Note:
     * Please review ALL files given before attempting to write these functions.
     * Understand the Cell.java class to know what object our array contains and what methods you can utilize
     * Understand the StackGen.java class to know what type of stack you will be working with and methods you can utilize
     * Understand the QGen.java class to know what type of queue you will be working with and methods you can utilize
     */
    

    public Minefield() { //default constructor, sets to Easy mode
        this.rows = 5;
        this.columns = 5;
        this.flags = 5;
        this.field = new Cell[rows][columns];
        this.game = true;
        for (int i = 0; i < field.length; i++) { //make all cells blank
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new Cell(false, "-");
            }
        }
    }

    public Minefield(int rows, int columns, int flags) {
        this.rows = rows;
        this.columns = columns;
        this.flags = flags;
        this.field = new Cell[rows][columns];
        this.game = true;

        for (int i = 0; i < field.length; i++) { //make all cells blank
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = new Cell(false, "-");
            }
        }
    }

    /**
     * evaluateField
     * 
     *
     * @function:
     * Evaluate entire array.
     * When a mine is found check the surrounding adjacent tiles. If another mine is found during this check, increment adjacent cells status by 1.
     *
     */
    public void evaluateField() {
        for(int i = 0; i < field.length; i++) {
            for(int j = 0; j < field[0].length; j++) {
                if (field[i][j].getStatus().equals("M")) { //mine found
                    incrementAdjacent(i,j);
                }
            }
        }
    }
    public void incrementAdjacent(int x, int y) { //increments the status of adjacent cells if they touch a mine
        if (!verifyInBounds(x, y)) {
            return;
        }

        if (verifyInBounds(x, y-1)) { //left
            if (field[x][y-1].getStatus().equals("-")) { //empty cell
                int newStatus = 1;
                field[x][y-1].setStatus(String.valueOf(newStatus));
            }
            else {
                if(!field[x][y-1].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x][y-1].getStatus());
                    int newStatus = currStatus + 1;
                    field[x][y-1].setStatus(String.valueOf(newStatus));
                }
            }
        }
        if(verifyInBounds(x, y+1)) { //right
            if (field[x][y+1].getStatus().equals("-")) {
                int newStatus = 1;
                field[x][y+1].setStatus(String.valueOf(newStatus));
            }
            else {
                if (!field[x][y+1].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x][y+1].getStatus());
                    int newStatus = currStatus + 1;
                    field[x][y+1].setStatus(String.valueOf(newStatus));
                }
            }
        }
        if(verifyInBounds(x-1, y)) { //top
            if (field[x-1][y].getStatus().equals("-")) {
                int newStatus = 1;
                field[x-1][y].setStatus(String.valueOf(newStatus));
            }
            else {
                if (!field[x-1][y].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x-1][y].getStatus());
                    int newStatus = currStatus + 1;
                    field[x-1][y].setStatus(String.valueOf(newStatus));
                }
            }
        }
        if(verifyInBounds(x+1, y)) { //bottom
            if (field[x+1][y].getStatus().equals("-")) {
                int newStatus = 1;
                field[x+1][y].setStatus(String.valueOf(newStatus));
            }
            else {
                if (!field[x+1][y].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x+1][y].getStatus());
                    int newStatus = currStatus + 1;
                    field[x+1][y].setStatus(String.valueOf(newStatus));
                }
            }
        }
        if(verifyInBounds(x-1, y-1)) { //top left
            if (field[x-1][y-1].getStatus().equals("-")) {
                int newStatus = 1;
                field[x-1][y-1].setStatus(String.valueOf(newStatus));
            }
            else {
                if (!field[x-1][y-1].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x-1][y-1].getStatus());
                    int newStatus = currStatus + 1;
                    field[x-1][y-1].setStatus(String.valueOf(newStatus));
                }
            }
        }
        if(verifyInBounds(x-1, y+1)) { //top right
            if (field[x-1][y+1].getStatus().equals("-")) {
                int newStatus = 1;
                field[x-1][y+1].setStatus(String.valueOf(newStatus));
            }
            else {
                if (!field[x-1][y+1].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x-1][y+1].getStatus());
                    int newStatus = currStatus + 1;
                    field[x-1][y+1].setStatus(String.valueOf(newStatus));
                }
            }
        }
        if(verifyInBounds(x+1, y-1)) { //bottom left
            if (field[x+1][y-1].getStatus().equals("-")) {
                int newStatus = 1;
                field[x+1][y-1].setStatus(String.valueOf(newStatus));
            }
            else {
                if (!field[x+1][y-1].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x+1][y-1].getStatus());
                    int newStatus = currStatus + 1;
                    field[x+1][y-1].setStatus(String.valueOf(newStatus));
                }
            }
        }
        if(verifyInBounds(x+1, y+1)) { //bottom right
            if (field[x+1][y+1].getStatus().equals("-")) {
                int newStatus = 1;
                field[x+1][y+1].setStatus(String.valueOf(newStatus));
            }
            else {
                if (!field[x+1][y+1].getStatus().equals("M")) {
                    int currStatus = Integer.parseInt(field[x+1][y+1].getStatus());
                    int newStatus = currStatus + 1;
                    field[x+1][y+1].setStatus(String.valueOf(newStatus));
                }
            }
        }
    }


    /**
     * createMines
     * 
     * Randomly generate coordinates for possible mine locations.
     * If the coordinate has not already been generated and is not equal to the starting cell set the cell to be a mine.
     * utilize rand.nextInt()
     * 
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    public void createMines(int x, int y, int mines) {
        Random rand = new Random();
        int minesPlaced = 0;
        while (minesPlaced < mines) {
            int randX = rand.nextInt(rows);
            int randY = rand.nextInt(columns);

            // Check if the coordinate is valid and not equal to the starting cell
            if (randX != x && randY != y && !field[randX][randY].getStatus().equals("M")) {
                field[randX][randY].setStatus("M");
                minesPlaced++;
            }
        }

    }

    /**
     * guess
     * 
     * Check if the guessed cell is inbounds (if not done in the Main class). 
     * Either place a flag on the designated cell if the flag boolean is true or clear it.
     * If the cell has a 0 call the revealZeroes() method or if the cell has a mine end the game.
     * At the end reveal the cell to the user.
     * 
     * 
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        boolean answer = false;
        if ((x >= 0 && x <= rows) && (y >= 0 && y <= columns)) {
            if (flag) {
                if(flags > 0) {
                   answer = false;
                   field[x][y].setStatus("F");
                }
                //no flags left
                else {
                }
            }
            else {
                if (field[x][y].getStatus().equals("0")) {
                    revealZeroes(x, y);
                    answer = false;
                }
                else if(field[x][y].getStatus().equals("M")) {
                    game = false;
                    answer = true;
                }
            }
        }
        field[x][y].setRevealed(true);
        return answer;
    }

    /**
     * gameOver
     * 
     * Ways a game of Minesweeper ends:
     * 1. player guesses a cell with a mine: game over -> player loses
     * 2. player has revealed the last cell without revealing any mines -> player wins
     * 
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        if (game) { //checks if game is over
            for (int i = 0; i < rows; i++) {
                for (int j = 0; i < columns; j++) {
                    if (!field[i][j].getRevealed() && !field[i][j].getStatus().equals("M")) {
                        return false;
                    }
                }
            }
            return true; // all non-mine cells are revealed, a win
        } else { // losing condition
            return true; // Game over due to hitting a mine, a loss
        }
    }

    /**
     * Reveal the cells that contain zeroes that surround the inputted cell.
     * Continue revealing 0-cells in every direction until no more 0-cells are found in any direction.
     * Utilize a STACK to accomplish this.
     *
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */
    public void revealZeroes(int x, int y) {
        Stack1Gen<Cell> stack = new Stack1Gen<>();
        Cell startPosition = field[x][y];
        String startStatus = startPosition.getStatus();
        stack.push(startPosition);
        while(!stack.isEmpty()) {
            Cell removed = stack.pop();
            removed.setRevealed(true);
            int currX = getX(removed);
            int currY = getY(removed);
            if(verifyInBounds(currX, currY-1) && !field[currX][currY-1].getRevealed() && field[currX][currY-1].getStatus().equals("0")) { //left
                stack.push(field[currX][currY-1]);
            }
            if(verifyInBounds(currX, currY+1) && !field[currX][currY+1].getRevealed() && field[currX][currY+1].getStatus().equals("0")) { //right
                stack.push(field[currX][currY+1]);
            }
            if(verifyInBounds(currX-1, currY) && !field[currX-1][currY].getRevealed() && field[currX-1][currY].getStatus().equals("0")) { //top
                stack.push(field[currX-1][currY]);
            }
            if(verifyInBounds(currX+1, currY) && !field[currX+1][currY].getRevealed() && field[currX+1][currY].getStatus().equals("0")) { //bottom
                stack.push(field[currX+1][currY]);
            }
        }
    }

    /**
     * revealStartingArea
     *
     * On the starting move only reveal the neighboring cells of the inital cell and continue revealing the surrounding concealed cells until a mine is found.
     * Utilize a QUEUE to accomplish this.
     * 
     * This method should follow the psuedocode given in the lab writeup.
     * Why might a queue be useful for this function?
     *
     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealStartingArea(int x, int y) {
        Q1Gen<Cell> queue = new Q1Gen<>();
        Cell startPosition = field[x][y];
        String startStatus = startPosition.getStatus();
        queue.add(startPosition);
        while(queue.length() > 0) {
           Cell removed = queue.remove();
            if (removed.getStatus().equals("M")) {
                break;
            }
            removed.setRevealed(true);
            int currX = getX(removed);
            int currY = getY(removed);

            //if statements for adjacent cells
            if (verifyInBounds(currX, currY-1) && !field[currX][currY-1].getRevealed()) { //left
                queue.add(field[currX][currY-1]);
            }
            if(verifyInBounds(currX, currY+1) && !field[currX][currY+1].getRevealed()) { //right
                queue.add(field[currX][currY+1]);
            }
            if(verifyInBounds(currX-1, currY) && !field[currX-1][currY].getRevealed()) { //top
                queue.add(field[currX-1][currY]);
            }
            if(verifyInBounds(currX+1, currY) && !field[currX+1][currY].getRevealed()) { //bottom
                queue.add(field[currX+1][currY]);
            }
            if(verifyInBounds(currX-1, currY-1) && !field[currX-1][currY-1].getRevealed()) { //top left
                queue.add(field[currX-1][currY-1]);
            }
            if(verifyInBounds(currX-1, currY+1) && !field[currX-1][currY+1].getRevealed()) { //top right
                queue.add(field[currX-1][currY+1]);
            }
            if(verifyInBounds(currX+1, currY-1) && !field[currX+1][currY-1].getRevealed()) { //bottom left
                queue.add(field[currX+1][currY-1]);
            }
            if(verifyInBounds(currX+1, currY+1) && !field[currX+1][currY+1].getRevealed()) { //bottom right
                queue.add(field[currX+1][currY+1]);
            }
        }
    }
    public int getX(Cell c) { //helper method to get the x index of a cell
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (field[i][j] == c) {
                    return i;
                }
            }
        }
        return -1;
    }
    public int getY(Cell c) { // helper method to get the y index of a cell
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (field[i][j] == c) {
                    return j;
                }
            }
        }
        return -1;
    }
    public boolean verifyInBounds(int x, int y) { //helper method to check if an index i in bounds
        return x >= 0 && x < field.length && y >= 0 && y < field[0].length;
    }


    /**
     * For both printing methods utilize the ANSI colour codes provided! 
     * 
     * 
     * 
     * 
     * 
     * debug
     *
     * @function This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void debug() {
        System.out.print(" ");
        for(int a = 0; a < columns; a++) {
            System.out.print(" " + a);
        }
        System.out.print("\n");

        for(int i = 0; i < rows; i++) {
            System.out.print("\n" + i + " ");
            for (int j = 0; j < columns; j++) {
                String status = field[i][j].getStatus();
                if (!status.equals( "-") ) { //if not empty
                    System.out.print(getColor(field[i][j])+status+"\u001b[0m" + " ");
                }
                else {
                    System.out.print("\u001B[33m" +"0" +"\u001b[0m" + " ");
                }
            }
        }
        System.out.print("\n");
    }

    public String getColor(Cell c) { //helper method to assign colors to Cells
        String color = "";
        if (c.getStatus().equals("M")) {
            color = ANSI_RED;
        }
        if (c.getStatus().equals("0")) {
            color = ANSI_YELLOW;
        }
        if (c.getStatus().equals("1")) {
            color = ANSI_BLUE_BRIGHT;
        }
        if (c.getStatus().equals("2")) {
            color = ANSI_BLUE;
        }
        if (c.getStatus().equals("3")) {
            color = ANSI_RED_BRIGHT;
        }
        if (c.getStatus().equals("4")) {
            color = ANSI_RED;
        }
        if (c.getStatus().equals("5")) {
            color =ANSI_GREEN;
        }
        if (c.getStatus().equals("6")) {
            color = ANSI_PURPLE;
        }
        if (c.getStatus().equals("7")) {
            color = ANSI_CYAN;
        }
        if (c.getStatus().equals("8")) {
            color = ANSI_YELLOW_BRIGHT;
        }
        else if (c.getStatus().equals("9")) {
            color = ANSI_RED;
        }
        return color;
    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(" ");
        for(int a = 0; a < columns; a++) {
            result.append(" " + a);
        }
        result.append("\n");

        for (int i = 0; i < rows; i++) {
            result.append(i + " ");
            for (int j = 0; j < columns; j++) {
                Cell currentCell = field[i][j];
                if (currentCell.getRevealed()) { //if cell is revealed, append its status to the result
                    result.append(currentCell.getStatus()).append(" ");
                } else { //case where cell is not flagged or not revealed
                    result.append("- ");
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
