
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.Scanner;
/**
 * This class is a model of the game ConnectFour.
 * 
 * This is my submission for the Ovarro Software Engineer 
 * Technical Test for the 2024 Graduate Software Engineer
 * position.
 * 
 * 
 * @author ashah
 * @version 1.0
 * @since 2024-05-06
 *
 */
public class Game {
    private char[][] grid;
    private int width;
    private int height;
    private int[] gridSize;
    private int winningRowLength;
    private char currentPlayer;
    private static final Logger logger = Logger.getLogger(Game.class.getName());
    static {
        try {
            // Remove the default console handler
            Logger logger = Logger.getLogger("");
            Handler[] handlers = logger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    logger.removeHandler(handler);
                }
            }

            // Create a FileHandler that writes log records to a file called "game_logs.txt"
            Handler fileHandler = new FileHandler("GameLog.txt");
            fileHandler.setFormatter(new SimpleFormatter()); // You can set a custom formatter if needed
            logger.addHandler(fileHandler);
        } catch (Exception e) {
            logger.severe("Failed to set up logging");
        }
    }
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
        	logger.warning("winning row length must be greater than 1" + System.lineSeparator());
        	logger.info("Setting winning row length to 4" + System.lineSeparator());
        	System.out.println("Using default winning row length (4)");
        	winningRowLength = 4;
        	
        }
        if (width < winningRowLength || height < winningRowLength) {
        	logger.warning("Grid size must be bigger than the winning row length" + System.lineSeparator());
        	// standard connect four grid
        	logger.info("Setting 7x6 grid" + System.lineSeparator());
        	System.out.println("Using default grid size (7x6)");
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
        currentPlayer = 'R';
        // Red player starts
    }

    /**
     * Method to initialise the game grid
    */
    private void initializeGrid() {
    	logger.info("Initialising grid" + System.lineSeparator());
        this.grid = new char[this.height][this.width];
        // Initialise grid with empty cells
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.grid[i][j] = '.';
            }
        }
    }
    
    
    /**
     * Method to get the size of the grid
     * 
     * @return gridSize         The grid size is an array containing the width and height
    */
    public int[] getGridSize()
    {
    	logger.info("Getting grid size " + System.lineSeparator());
    	return this.gridSize;
    }
    
    /**
     * Method to get the grid
     * 
     * @return grid         The grid is a 2d array on which the game is being played
    */
	public char[][] getGrid()
	{
		logger.info("Getting grid " + System.lineSeparator());
		return this.grid;
	}
    
	/**
     * Method to get the length of a winning combination
     * 
     * @return winningRowLength         The length of a winning combination
    */
    public int getWinningRowLength()
    {
    	logger.info("Getting length of a winning row" + System.lineSeparator());
    	return this.winningRowLength;
    }
    
    /**
     * Method to add a token to the game grid.
     * 
     * @param column The column where the token should be added.
     * @return true if the token was successfully added, false otherwise.
     */
    public boolean addToken(int column) {
    	logger.info("Attempting to insert token" + System.lineSeparator());
    	if (column > this.width - 1 || column < 0)
    	{
    		// Logging to file
    		logger.warning("Column is not on grid" + System.lineSeparator());
    		// Logging to console with System.out.println
    		System.out.println("Column cannot be found on the grid");
    		return false;
    	}
    	for (int i = this.height - 1; i >= 0; i--) {
            if (this.grid[i][column] == '.')
            {
            	logger.info("Inserting token " + System.lineSeparator());
            	this.grid[i][column] = this.currentPlayer;
            	this.currentPlayer = this.getNextPlayer();
            	return true;
            }
        }
    	logger.info("Selected column is full" + System.lineSeparator());
    	System.out.println("Selected column is full, please try another column");
        return false;
    }

    /**
     * Method to show the game grid state/progress.
    */
    public void showGridState() {
    	logger.info("Printing grid" + System.lineSeparator());
    	System.out.println();
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
    }

    /**
     * Method to get the next player to play.
     * 
     * @return The next player (either 'R' or 'Y').
     */
    public char getNextPlayer() {
    	logger.info("Retrieving next player" + System.lineSeparator());
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
    	logger.info("Retrieving current player" + System.lineSeparator());
    	return this.currentPlayer;
    }

	/**
	 * Method to check if there is a winner.
	 * 
	 * @param board The board that is being checked
	 * @param winningRowLength The winning row length associated with the board
	 * @return The winner player ('R' or 'Y') if there is a winner, '.' if no winner yet.
	*/
    public char checkWinner(char[][] board, int winningRowLength) {
    	logger.info("Checking winner" + System.lineSeparator());
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
                if (rowWin)
                {
                	logger.info("Winner found in rows" + System.lineSeparator());
                	return token;
                	
                }
                	
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
                if (colWin)
                {
                	logger.info("Winner found in columns" + System.lineSeparator());
                	return token;
                }
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
                if (diagonalWin)
                {
                	logger.info("Winner found from top left to bottom right" + System.lineSeparator());
                	return token;
                }
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
                if (diagonalWin)
                {
                	logger.info("Winner found from bottom right to top left" + System.lineSeparator());
                	return token;
                	
                }
                
            }
        }

        logger.info("No winner found" + System.lineSeparator());
        return '.';
    }
    
    /**
     * Method containing the logic to play the game
     *
     * @param game An instance of the game that is being played
    */
    public static void playGame(Game game)
    {
    	boolean finished = false;
    	while (!finished)
    	{
    		game.showGridState();
    		boolean valid = false;
    		while (!valid)
    		{
    			try
            	{
            		Scanner scan = new Scanner(System.in);
            		int[] gridSize = game.getGridSize();
            		System.out.println("Player to move: " + game.getCurrentPlayer());
            		System.out.println("Please enter column number.");
                	int col = scan.nextInt();
                	// checking input here as well, so function returns 
                	// false in a draw
                	if (col > gridSize[0] || col < 0)
                	{
                		logger.warning("Column is not on grid." + System.lineSeparator());
                		System.out.println("Column cannot be found on the grid.");
                		continue;
                	}
                	boolean move = game.addToken(col);
                	if (move) 
                	{
                		valid = true;
                	}
                	char result = game.checkWinner(game.getGrid(), game.getWinningRowLength());
                	if (result != '.')
                	{
                		logger.info("Winner: " + String.valueOf(result) + System.lineSeparator());
                		System.out.println("Winner: " + String.valueOf(result));
                		finished = true;
                	}
            	}
            	catch (Exception e)
            	{
            		System.out.println("Invalid input, please enter a valid column number");
            	}
    			
    		}	
    	}
    	game.showGridState();
    }
    
    /**
     * Main method to accept user input for the parameters required
     * to run the game. It attempts to start the game after verifying 
     * the inputs are correct.
    */
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
        		System.out.println("Please enter columns, rows and winning row length as numbers");
        	}
    	}

    }

}
