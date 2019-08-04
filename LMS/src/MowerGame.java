//MowerGame.java

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  The class that handles gameflow and mechanics
 *
 *  @author  Jeff Liu, Stanley Wang
 *  @version May 25, 2018
 *  @author  Period: 5
 *  @author  Assignment: LMS
 *
 *  @author  Sources: None
 */
public class MowerGame
{
    private int level;
    private LawnMap current;
    private char[][] map;
    private int grassMowed = 0;
    private int score = 0;
    private int numMoves = 0;
    private int x,y; //current coordinates of mower
    private GameWindow gw;
    
    /**
     * No args constructor uses the default lawn map
     */
    public MowerGame()
    {
        current = new LawnMap();
        level = 0;
        initialize();
    }
    
    /**
     * @param l is the level number
     * @param lm is the lawn map
     */
    public MowerGame(int l, LawnMap lm)
    {
        current = lm;
        level = l;
        initialize();
    }
    
    /**
     * initialize GUI and set variables
     */
    private void initialize()
    {
        gw = new GameWindow(level, current, this);
        map = current.getLawn().clone();
        x = current.getStartX();
        y = current.getStartY();
    }
    
    /**
     * To move the mower
     * @param v is number of steps in vertical direction
     * @param h is number of steps in the horizontal direction
     */
    public void move(int v, int h )
    {
        if (x + v < map.length && x + v >= 0 && y + h < map[0].length 
                && y + h >= 0 && map[x + v][y + h] != 'B')
        {
            if (x == current.getEndX() && y == current.getEndY())
            {
                map[x][y] = 'F';    //when moving off the end cell
            }
            else
            {
                map[x][y] = 'O';    //else replace it with dirt
            }
            if (map[x + v][y + h] == 'G')
            {
                score++;        //increment score and grass mowed when mowing grass
                grassMowed++;
            }
            else if (map[x + v][y + h] != 'F')
            {
                score--;        //else wasting oil, decrease score
            }
            map[x + v][y + h] = 'M';
            gw.update(x, y, map[x][y]);
            gw.update(x + v, y + h, map[x + v][y + h]);
            x += v;
            y += h;
            //when game is over
            if (x == current.getEndX() && y == current.getEndY() && grassMowed == current.getNumGrass())
            {
                gw.stopGame(((double) score) / numMoves);
            }
            numMoves++;
            gw.updateNumMove(numMoves);
            gw.updateScore(score);
        }
    }
    
    /**
     * Testing getter method
     * @return the 2D map
     */
    public char[][] getMap()
    {
        return map;
    }
    
    /**
     * Getter method for testing
     * @return level
     */
    public int getLevel()
    {
    	return level;
    }
    
    /**
     * Getter method for testing
     * @return current
     */
    public LawnMap getCurrent()
    {
    	return current;
    }
    
    /**
     * Getter method for grassMowed
     * @return grassMowed
     */
	public int getGrassMowed() {
		return grassMowed;
	}
    /**
     * Getter method for score
     * @return score
     */
	public int getScore() {
		return score;
	}

	/**
	 * Getter method for testing
	 * @return the numMoves
	 */
	public int getNumMoves() {
		return numMoves;
	}

	/**
	 * Getter method for testing
	 * @return the gw
	 */
	public GameWindow getGw() {
		return gw;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

}
