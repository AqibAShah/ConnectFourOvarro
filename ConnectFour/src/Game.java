/**
 * 
 */

/**
 * @author ashah
 *
 */
import java.util.logging.Logger;
import java.util.Scanner;

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
     */
    public Game(int width, int height, int winningRowLength) {
        if (winningRowLength <= 1)
        {
        	logger.severe("winning row length must be greater than 1");
        	logger.info("Setting winning row length to 4");
        	winningRowLength = 4;
        	
        }
        if (width < winningRowLength || height < winningRowLength) {
        	logger.severe("Grid size must be bigger than the winning row length");
        	// standard connect four grid
        	logger.info("Setting 7x6 grid");
        	width = 7;
        	height = 6;
        }
        this.width = width;
        this.height = height;
        this.gridSize = new int[2];
        this.gridSize[0] = width;
        this.gridSize[1] = height;
        this.winningRowLength = winningRowLength;
        initializeGrid();
        currentPlayer = 'R'; // Red player starts
    }

    /**
     * Method to initialise the grid that will be used for the game.
     * 
     */
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
    
    
    /**
     * Method to get the size of the grid
     * 
     * @return gridSize         The grid size is an array containing the width and height
     */
    public int[] getGridSize()
    {
    	return this.gridSize;
    }
    
    /**
     * Method to get the grid
     * 
     * @return grid         The grid is a 2d array on which the game is being played
    */
	public char[][] getGrid()
	{
		return this.grid;
	}
    
	/**
     * Method to get the length of a winning combination
     * 
     * @return winningRowLength         The length of a winning combination
    */
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
	 * @param board The board that is being checked
	 * @winningRowLength The winning row length associated with the board
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
    
    /**
     * Method containing the logic to play the game
     *
     * @param game An instance of the game that is being played
    */
    public static void playGame(Game game)
    {
    	game.showGridState();
    }

    // Main method
    public static void main(String[] args) {
    	// variable to check if parameters have been set correctly
    	boolean set = false;
    	while(!set)
    	{
    		try
        	{
        		Scanner scan = new Scanner(System.in);
            	System.out.println("Please enter number of columns");
            	int cols = scan.nextInt();
            	System.out.println("Please enter number of rows");
            	int rows = scan.nextInt();
            	System.out.println("Please enter length of a winning row");
            	int length = scan.nextInt();
            	set = true;
            	Game game = new Game(cols, rows, length);
            	playGame(game);
        	}
        	catch(Exception e) 
        	{
        		System.out.println("Invalid combination, please try again");
        	}
    	}

    }

}
