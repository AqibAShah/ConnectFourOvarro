import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

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

}
