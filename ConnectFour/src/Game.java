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
    	logger.info("Initialising grid");
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
    	logger.info("Game > addToken(int column)");
    	if (column > this.width)
    	{
    		logger.warning("Column is not on grid");
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
        return false; // Placeholder return value
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
    public char checkWinner() {
        // TODO: Implement logic to check if there is a winner
        return '.'; // Placeholder return value
    }
}
