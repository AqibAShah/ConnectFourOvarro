import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	private static Game board;
	@BeforeEach
	void createBoard() 
	{
		board = new Game(7, 6, 4);
	}

	@Test
	void testValidBoard() {
		Game game = new Game(8,8,4);
		int[] gridSize = game.getGridSize();
		assertEquals(gridSize[0], 8);
		assertEquals(gridSize[1], 8);
		assert(gridSize[0] > 4 && gridSize[1] > 4);
	}
	@Test
	void testBoardLessthanwinningLength() {
		Game game = new Game(3,4,4);
		int[] gridSize = game.getGridSize();
		assert(gridSize[0] >= 4 && gridSize[1] >= 4);
	}
	
	@Test
	void testAddTokentoEmptyBoard() {
		char[][] grid = {
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'R', '.', '.', '.', '.', '.', '.'},
			};
		board.addToken(0);
		assertArrayEquals(board.getGrid(), grid);
		char currentPlayer = board.getCurrentPlayer();
		assertEquals(currentPlayer, 'Y');
		
	}
	
	@Test
	void testAddTokentoBoard() {
		char[][] grid = {
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'R', '.', '.', '.', '.', '.', '.'},
				{'Y', '.', '.', '.', '.', '.', '.'},
				{'R', '.', '.', '.', '.', '.', '.'},
			};
		for (int i=0; i < 3; i++)
		{
			board.addToken(0);
		}
		assertArrayEquals(board.getGrid(), grid);
		char currentPlayer = board.getCurrentPlayer();
		assertEquals(currentPlayer, 'Y');
		
	}
	
	@Test
	void testInvalidTokentoBoard() {
		char[][] grid = {
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'R', '.', '.', '.', '.', '.', '.'},
				{'Y', '.', '.', '.', '.', '.', '.'},
				{'R', '.', '.', '.', '.', '.', '.'},
			};
		assertFalse(board.addToken(7));
		assertFalse(board.addToken(-1));
		
	}
	
	@Test 
	void testWinnerSimpleColumn()
	{
		char[][] grid = {
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'R', '.', '.', '.', '.', '.', '.'},
				{'R', 'Y', '.', '.', '.', '.', '.'},
				{'R', 'Y', '.', '.', '.', '.', '.'},
				{'R', 'Y', '.', '.', '.', '.', '.'},
			};
		char winner = board.checkWinner(grid);
		assertEquals(winner, 'R');
	}
	
	@Test 
	void testWinnerComplexColumn()
	{
		char[][] grid = {
				{'Y', '.', '.', 'Y', '.', '.', '.'},
				{'Y', 'Y', 'R', 'Y', '.', '.', '.'},
				{'Y', 'R', 'R', 'Y', '.', '.', '.'},
				{'R', 'Y', 'R', 'R', '.', '.', '.'},
				{'R', 'Y', 'R', 'R', '.', '.', '.'},
				{'R', 'Y', 'Y', 'R', '.', '.', '.'},
			};
		char winner = board.checkWinner(grid);
		assertEquals(winner, 'R');
	}
	
	@Test 
	void testWinnerSimpleRow()
	{
		char[][] grid = {
				{'Y', 'Y', '.', 'Y', '.', '.', '.'},
				{'Y', 'R', 'Y', 'Y', '.', '.', '.'},
				{'R', 'R', 'R', 'R', '.', '.', '.'},
				{'Y', 'Y', 'R', 'Y', '.', '.', '.'},
				{'R', 'Y', 'R', 'R', '.', '.', '.'},
				{'R', 'Y', 'Y', 'R', '.', '.', '.'},
			};
		char winner = board.checkWinner(grid);
		assertEquals(winner, 'R');
	}
	
	@Test
	void testWinnerComplexRow()
	{
		char[][] grid = {
				{'Y', 'Y', 'Y', 'Y', '.', '.', '.'},
				{'Y', 'R', 'Y', 'Y', '.', '.', '.'},
				{'Y', 'R', 'Y', 'R', '.', '.', '.'},
				{'R', 'Y', 'R', 'Y', 'R', '.', '.'},
				{'R', 'Y', 'R', 'R', 'Y', 'R', 'R'},
				{'R', 'Y', 'Y', 'R', 'Y', 'R', 'R'},
			};
		char winner = board.checkWinner(grid);
		assertEquals(winner, 'Y');
	}
	@Test
	void testWinnerLeftToRightDiagonal()
	{
		char[][] grid = {
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.'},
				{'Y', '.', '.', '.', '.', '.', '.'},
				{'R', 'Y', '.', '.', '.', '.', '.'},
				{'Y', 'R', 'Y', '.', '.', '.', '.'},
				{'R', 'R', 'R', 'Y', '.', '.', '.'},
			};
		char winner = board.checkWinner(grid);
		assertEquals(winner, 'Y');
	}
	
	@Test
	void testWinnerRightToLeftDiagonal()
	{
		char[][] grid = {
				{'.', '.', '.', '.', '.', '.', '.'},
				{'.', '.', '.', 'Y', '.', '.', '.'},
				{'R', '.', 'Y', 'R', '.', '.', '.'},
				{'R', 'Y', 'R', 'Y', '.', '.', '.'},
				{'Y', 'R', 'Y', 'R', '.', '.', '.'},
				{'R', 'R', 'R', 'Y', '.', '.', '.'},
			};
		char winner = board.checkWinner(grid);
		assertEquals(winner, 'Y');
	}

}
