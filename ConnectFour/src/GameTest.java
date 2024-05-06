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
		board.showGridState();
		assertArrayEquals(board.getGrid(), grid);
		char currentPlayer = board.getCurrentPlayer();
		assertEquals(currentPlayer, 'Y');
		
	}

}
