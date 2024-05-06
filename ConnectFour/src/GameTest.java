import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTest {

	@Test
	void testConstructor() {
		Game game = new Game(8, 4);
		int gridSize = game.getGridSize();
		assertEquals(gridSize, 8);
		assert(gridSize > 4);
	}

}
