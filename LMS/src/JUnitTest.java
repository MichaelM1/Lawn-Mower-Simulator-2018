import java.awt.Window;
import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/**
 * @author Michael Mao
 *
 */
public class JUnitTest {

	/**
	 * Tests the Lawn Map no args constructor
	 */
	@Test
	public void lawnMapContructorTest() {
		LawnMap map = new LawnMap();
		assertEquals(10, map.getCols());
		assertEquals(10, map.getRows());
		assertEquals(0, map.getStartX());
		assertEquals(0, map.getStartY());
		assertEquals(101, map.getEndX());
		assertEquals(101, map.getEndY());
		assertEquals(98, map.getNumGrass());

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', map.getLawn()[i][j]);
				else
					assertEquals('G', map.getLawn()[i][j]);
			}
		}
	}

	/**
	 * Tests the Lawn Map with args constructor
	 */
	@Test
	public void lawnMapConstructorWithParamsTest() {
		char[][] lawn = new char[20][20];
		LawnMap map = new LawnMap(lawn, 1, 1, 5, 5, 10);

		assertEquals(20, map.getCols());
		assertEquals(20, map.getRows());
		assertEquals(1, map.getStartX());
		assertEquals(1, map.getStartY());
		assertEquals(5, map.getEndX());
		assertEquals(5, map.getEndY());
		assertEquals(10, map.getNumGrass());

		assertSame(lawn, map.getLawn());
	}

	/**
	 * Tests the Game Window constructor
	 */
	@Test
	public void gameWindowConstructorTest() {
		MowerGame mg = new MowerGame();
		LawnMap lm = new LawnMap();
		GameWindow window = new GameWindow(1, lm, mg);
		assertNotNull(lm);
		assertNotNull(window);

		assertEquals(0, mg.getLevel());
		assertEquals(0, mg.getGrassMowed());
		assertEquals(0, mg.getScore());
		assertEquals(0, mg.getNumMoves());

		assertNotNull(mg.getGw());
	}

	/**
	 * Tests the Mower Game constructor with no args
	 */
	@Test
	public void mowerGameConstructorTest() {
		MowerGame mg = new MowerGame(0, new LawnMap());
		assertEquals(0, mg.getLevel());
		assertEquals(0, mg.getGrassMowed());
		assertEquals(0, mg.getNumMoves());
		assertEquals(0, mg.getX());
		assertEquals(0, mg.getY());
		assertNotNull(mg.getCurrent());

		// verify map values
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', mg.getMap()[i][j]);
				else
					assertEquals('G', mg.getMap()[i][j]);
			}
		}

		// verify current
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}
	}

	/**
	 * Tests the Mower Game constructor with args
	 */
	@Test
	public void mowerGameConstructorWithParamsTest() {
		LawnMap lm = new LawnMap();
		MowerGame mg = new MowerGame(1, lm);
		assertEquals(1, mg.getLevel());
		assertNotNull(mg.getCurrent());
		assertEquals(0, mg.getGrassMowed());
		assertEquals(0, mg.getNumMoves());

		// verify map values
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', mg.getMap()[i][j]);
				else
					assertEquals('G', mg.getMap()[i][j]);
			}
		}

		// verify current
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}
	}

	/**
	 * Tests the Mower Game move
	 */
	@Test
	public void mowerGameMoveTest() {
		MowerGame mg = new MowerGame(0, new LawnMap());

		assertEquals(0, mg.getX());
		assertEquals(0, mg.getY());
		assertEquals(0, mg.getScore());

		// verify current
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}

		mg.move(1, 2);
		assertEquals(1, mg.getX());
		assertEquals(2, mg.getY());
		assertEquals(1, mg.getScore());

		// verify current status
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('O', mg.getCurrent().getLawn()[i][j]);
				else if (i == 1 && j == 2)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}

		mg.move(2, 3);
		assertEquals(3, mg.getX());
		assertEquals(5, mg.getY());
		assertEquals(2, mg.getScore());

		// verify current status
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('O', mg.getCurrent().getLawn()[i][j]);
				else if (i == 1 && j == 2)
					assertEquals('O', mg.getCurrent().getLawn()[i][j]);
				else if (i == 3 && j == 5)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}

		// move too much,no change
		mg.move(10, 3);
		assertEquals(3, mg.getX());
		assertEquals(5, mg.getY());
		assertEquals(2, mg.getScore());

		// verify current status
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('O', mg.getCurrent().getLawn()[i][j]);
				else if (i == 1 && j == 2)
					assertEquals('O', mg.getCurrent().getLawn()[i][j]);
				else if (i == 3 && j == 5)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}
	}

	/**
	 * Tests the Mower Game move
	 */
	@Test
	public void mowerGameMove2Test() {
		MowerGame mg = new MowerGame(0, new LawnMap());

		assertEquals(0, mg.getX());
		assertEquals(0, mg.getY());
		assertEquals(0, mg.getScore());

		// verify current
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}

		assertEquals(0, mg.getCurrent().getStartX());
		assertEquals(0, mg.getCurrent().getStartY());
		assertEquals(101, mg.getCurrent().getEndX());
		assertEquals(101, mg.getCurrent().getEndY());

		mg.move(0, 0);
		assertEquals(0, mg.getX());
		assertEquals(0, mg.getY());
		assertEquals(-1, mg.getScore());

		// verify current
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', mg.getCurrent().getLawn()[i][j]);
				else
					assertEquals('G', mg.getCurrent().getLawn()[i][j]);
			}
		}
	}

	/**
	 * Tests the Mower Start Constructor
	 */
	@Test
	public void mowerStartConstructorTest() {
		char[][] m1 = { { 'M', 'G', 'G' }, { 'G', 'G', 'G' }, { 'G', 'G', 'F' } };
		char[][] m2 = { { 'B', 'F', 'G' }, { 'G', 'G', 'G' }, { 'G', 'M', 'B' } };
		char[][] m3 = { { 'G', 'G', 'G', 'F', 'G', 'G', 'G' }, { 'G', 'B', 'G', 'G', 'G', 'B', 'G' },
				{ 'G', 'G', 'G', 'M', 'G', 'G', 'G' } };
		char[][] m4 = { { 'G', 'G', 'B', 'G', 'G', 'F', 'G', 'G', 'G', 'G', 'G' },
				{ 'G', 'G', 'G', 'G', 'G', 'G', 'B', 'G', 'G', 'B', 'G' },
				{ 'G', 'B', 'G', 'G', 'B', 'G', 'G', 'G', 'G', 'G', 'G' },
				{ 'G', 'G', 'G', 'G', 'G', 'M', 'G', 'G', 'B', 'G', 'G' } };
		MowerStart ms = new MowerStart();
		assertNotNull(ms);
		assertNotNull(MowerStart.ll);
		assertEquals(5, MowerStart.ll.size());

		assertEquals(10, MowerStart.ll.get(0).getCols());
		assertEquals(10, MowerStart.ll.get(0).getRows());
		assertEquals(0, MowerStart.ll.get(0).getStartX());
		assertEquals(0, MowerStart.ll.get(0).getStartY());
		assertEquals(101, MowerStart.ll.get(0).getEndX());
		assertEquals(101, MowerStart.ll.get(0).getEndY());
		assertEquals(98, MowerStart.ll.get(0).getNumGrass());

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 0 && j == 0)
					assertEquals('M', MowerStart.ll.get(0).getLawn()[i][j]);
				else
					assertEquals('G', MowerStart.ll.get(0).getLawn()[i][j]);
			}
		}

		assertEquals(3, MowerStart.ll.get(1).getCols());
		assertEquals(3, MowerStart.ll.get(1).getRows());
		assertEquals(0, MowerStart.ll.get(1).getStartX());
		assertEquals(0, MowerStart.ll.get(1).getStartY());
		assertEquals(2, MowerStart.ll.get(1).getEndX());
		assertEquals(2, MowerStart.ll.get(1).getEndY());
		assertEquals(7, MowerStart.ll.get(1).getNumGrass());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(m1[i][j], MowerStart.ll.get(1).getLawn()[i][j]);
			}
		}

		assertEquals(3, MowerStart.ll.get(2).getCols());
		assertEquals(3, MowerStart.ll.get(2).getRows());
		assertEquals(2, MowerStart.ll.get(2).getStartX());
		assertEquals(1, MowerStart.ll.get(2).getStartY());
		assertEquals(0, MowerStart.ll.get(2).getEndX());
		assertEquals(1, MowerStart.ll.get(2).getEndY());
		assertEquals(5, MowerStart.ll.get(2).getNumGrass());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(m2[i][j], MowerStart.ll.get(2).getLawn()[i][j]);
			}
		}

		assertEquals(7, MowerStart.ll.get(3).getCols());
		assertEquals(3, MowerStart.ll.get(3).getRows());
		assertEquals(2, MowerStart.ll.get(3).getStartX());
		assertEquals(3, MowerStart.ll.get(3).getStartY());
		assertEquals(0, MowerStart.ll.get(3).getEndX());
		assertEquals(3, MowerStart.ll.get(3).getEndY());
		assertEquals(17, MowerStart.ll.get(3).getNumGrass());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(m3[i][j], MowerStart.ll.get(3).getLawn()[i][j]);
			}
		}

		assertEquals(11, MowerStart.ll.get(4).getCols());
		assertEquals(4, MowerStart.ll.get(4).getRows());
		assertEquals(3, MowerStart.ll.get(4).getStartX());
		assertEquals(5, MowerStart.ll.get(4).getStartY());
		assertEquals(0, MowerStart.ll.get(4).getEndX());
		assertEquals(5, MowerStart.ll.get(4).getEndY());
		assertEquals(36, MowerStart.ll.get(4).getNumGrass());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(m4[i][j], MowerStart.ll.get(4).getLawn()[i][j]);
			}
		}
	}

	/**
	 * Tests the Menu Window Constructor
	 */
	@Test
	public void menuWindowConstructorTest() {
		MenuWindow mw = new MenuWindow();
		assertNotNull(mw);
		assertEquals("Lawn Mower Simulator Menu", mw.getTitle());
		assertEquals(0, mw.getHeight());
		assertEquals(0, mw.getWidth());
		assertEquals(0.5, mw.getAlignmentX(), 0.00001);
		assertEquals(0.5, mw.getAlignmentY(), 0.00001);
		assertNotNull(mw.getLayout());
		assertEquals(Window.Type.NORMAL, mw.getType());

		assertEquals(1, mw.getFreePlayButton().getActionListeners().length);
		assertEquals("Free Play", mw.getFreePlayButton().getLabel());
		assertEquals(1, mw.getLevelSelectButton().getActionListeners().length);
		assertEquals("Level Select", mw.getLevelSelectButton().getLabel());
		assertEquals(1, mw.getLevelCreatorButton().getActionListeners().length);
		assertEquals("Level Creator", mw.getLevelCreatorButton().getLabel());
	}

	public static junit.framework.Test suite() {
		return new JUnit4TestAdapter(JUnitTest.class);
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("JUTest");
	}

}
