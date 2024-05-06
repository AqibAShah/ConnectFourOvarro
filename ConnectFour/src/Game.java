/**
 * 
 */

/**
 * @author ashah
 *
 */
import java.util.logging.*;

public class Game {
    private char[][] grid;
    private int width;
    private int height;
    private int[] gridSize;
    private int winningRowLength;
    private char currentPlayer;
    private static final Logger logger = Logger.getLogger(Game.class.getName());


    /**
     * Constructor to initialise a new game with custom grid size and winning row length.
     * 
     * @param width             The width of the grid.
     * @param height            The height of the grid.
     * @param winningRowLength The length of the winning row.
     * @throws IllegalArgumentException if width or height is less than 4.
     */
    public Game(int width, int height, int winningRowLength) {
    	this.width = width;
        this.height = height;
        if (winningRowLength <= 1)
        {
        	logger.severe("winning row length must be greater than 1");
        	logger.info("Setting winning row length to 4");
        	this.winningRowLength = 4;
        	
        }
        if (width < winningRowLength || height < winningRowLength) {
        	logger.severe("Grid size must be bigger than the winning row length");
        	logger.info("Setting 7x6 grid");
        	this.width = 7;
        	this.height = 6;
        }
        this.gridSize = new int[2];
        this.gridSize[0] = this.width;
        this.gridSize[1] = this.height;
        this.winningRowLength = winningRowLength;
        initializeGrid();
        currentPlayer = 'R'; // Red player starts
    }

    // Method to initialise a new game grid
    private void initializeGrid() {
        this.grid = new char[this.height][this.width];
        // Initialise grid with empty cells
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j] = '.';
            }
        }
        //printGrid();
    }
    
    
    // Method to get gridSize
    public int[] getGridSize()
    {
    	return this.gridSize;
    }
    
	// Method to get gridSize
	public char[][] getGrid()
	{
		return this.grid;
	}
    
	// Method to get winningRowLength
    public int getWinningRowLength()
    {
    	return this.winningRowLength;
    }
    
    /**
     * Method to add a token to the game grid.
     * 
     * @param column The column where the token should be added.
     * @return true if the token was successfully added, false otherwise.
     */
    public boolean addToken(int column) {
    	if (column > this.width - 1 || column < 0)
    	{
    		logger.warning("Column is not on grid");
    		return false;
    	}
    	for (int i = this.height - 1; i >= 0; i--) {
            if (this.grid[i][column] == '.')
            {
            	logger.info("Inserting token");
            	this.grid[i][column] = this.currentPlayer;
            	this.currentPlayer = this.getNextPlayer();
            	return true;
            }
        }
    	logger.info("Cannot find space to insert token");
        return false;
    }

    /**
     * Method to show the game grid state/progress.
     */
    public void showGridState() {
    	for (int i = 0; i < this.width; i++)
    	{
    		System.out.print(i);
    		System.out.print(" ");
    	}
    	for (int i = 0; i < this.height; i++) {
    		System.out.println();
            for (int j = 0; j < this.width; j++) {
                System.out.print(this.grid[i][j]);
                System.out.print(" ");
            }
        }
    	System.out.println();
    	System.out.println("Player to move: " + this.getCurrentPlayer());
    }

    /**
     * Method to get the next player to play.
     * 
     * @return The next player (either 'R' or 'Y').
     */
    public char getNextPlayer() {
    	if (this.currentPlayer == 'R')
    	{
    		return 'Y';
    	}
        return 'R';
    }
    
    /**
     * Method to get current player.
     * 
     * @return The current player (either 'R' or 'Y').
     */
    public char getCurrentPlayer() {
    	return this.currentPlayer;
    }

    /**
     * Method to check if there is a winner.
     * 
     * @return The winner player ('R' or 'Y') if there is a winner, '.' if no winner yet.
     */
    public char checkWinner(char[][] board, int winningRowLength) {
        // Check rows
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col <= board[row].length - winningRowLength; col++) {
                char token = board[row][col];
                boolean rowWin = true;
                for (int k = 1; k < winningRowLength; k++) {
                    if (board[row][col + k] != token || token == '.') {
                        rowWin = false;
                        break;
                    }
                }
                if (rowWin) return token;
            }
        }

        // Check columns
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row <= board.length - winningRowLength; row++) {
                char token = board[row][col];
                boolean colWin = true;
                for (int k = 1; k < winningRowLength; k++) {
                    if (board[row + k][col] != token || token == '.') {
                        colWin = false;
                        break;
                    }
                }
                if (colWin) return token;
            }
        }

        // Check diagonals (top-left to bottom-right)
        for (int row = 0; row <= board.length - winningRowLength; row++) {
            for (int col = 0; col <= board[row].length - winningRowLength; col++) {
                char token = board[row][col];
                boolean diagonalWin = true;
                for (int k = 1; k < winningRowLength; k++) {
                    if (board[row + k][col + k] != token || token == '.') {
                        diagonalWin = false;
                        break;
                    }
                }
                if (diagonalWin) return token;
            }
        }

        // Check diagonals (top-right to bottom-left)
        for (int row = 0; row <= board.length - winningRowLength; row++) {
            for (int col = board[row].length - 1; col >= winningRowLength - 1; col--) {
                char token = board[row][col];
                boolean diagonalWin = true;
                for (int k = 1; k < winningRowLength; k++) {
                    if (board[row + k][col - k] != token || token == '.') {
                        diagonalWin = false;
                        break;
                    }
                }
                if (diagonalWin) return token;
            }
        }

        // If no winner is found, return '.'
        return '.';
    }

    
    public static void main(String[] args) {
        // Example usage of the Game class
        Game game = new Game(7, 6, 4); // Create a new game with a 7x6 grid and winning row length of 4
        game.showGridState(); // Show the initial grid state
        game.addToken(0); // Example of adding a token to column 0
        game.showGridState(); // Show the grid state after adding a token
    }

}
