/**
 * 
 */

/**
 * @author ashah
 *
 */
public class Game {
    private char[][] grid;
    private int gridSize;
    private int winningRowLength;
    private char currentPlayer;

    /**
     * Constructor to initialise a new game.
     * 
     * @param gridSize        The size of the grid.
     * @param winningRowLength The length of the winning row.
     */
    public Game(int gridSize, int winningRowLength) {
        this.gridSize = gridSize;
        this.winningRowLength = winningRowLength;
        initializeGrid();
        currentPlayer = 'R'; // Red player starts
    }

    // Method to initialize a new game grid
    private void initializeGrid() {
        this.grid = new char[this.gridSize][this.gridSize];
        // Initialize grid with empty cells
        for (int i = 0; i < this.gridSize; i++) {
            for (int j = 0; j < this.gridSize; j++) {
                this.grid[i][j] = '.';
            }
        }
        // printGrid();
    }
    
    // Method to print the current grid
    public void printGrid()
    {
    	for (int i = 0; i < this.gridSize; i++) {
    		System.out.println();
            for (int j = 0; j < this.gridSize; j++) {
                System.out.print(this.grid[i][j]);
            }
        }
    }
    
    // Method to get gridSize
    public int getGridSize()
    {
    	return this.gridSize;
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
        // TODO: Implement logic to add a token to the specified column
        return false; // Placeholder return value
    }

    /**
     * Method to show the game grid state/progress.
     */
    public void showGridState() {
        // TODO: Implement logic to display the game grid state
    }

    /**
     * Method to get the next player to play.
     * 
     * @return The next player (either 'R' or 'Y').
     */
    public char getNextPlayer() {
        return currentPlayer;
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
