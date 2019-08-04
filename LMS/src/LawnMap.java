//LawnMap.java

/**
 *  Basic design for the lawnmap, contains the 2D array along
 *  with start and end positions and number of grass
 *
 *  @author  Michael Mao
 *  @version May 25, 2018
 *  @author  Period: 5
 *  @author  Assignment: LMS
 *
 *  @author  Sources: None
 */
public class LawnMap {
    
    private char[][] lawn;
    private int numGrass;       //there should be no grass on start pos
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    
    /**
     * Default no args constructor
     */
    public LawnMap()
    {
        lawn = new char[10][10];
        startX = 0;
        startY = 0;
        //no ending position basically
        endX = 101;
        endY = 101;
        numGrass = 98;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                lawn[i][j] = 'G';
            }
        }
        
        lawn[0][0] = 'M';
    }
    
    /**
     * @param l is 2D character grid
     * @param sX is start x coord
     * @param sY is start y coord
     * @param eX is end x coord
     * @param eY is end y coord
     * @param nGrass is number of grass patches
     */
    public LawnMap( char[][] l, int sX, int sY, int eX, int eY, int nGrass )
    {
        lawn = l;
        startX = sX;
        startY = sY;
        endX = eX;
        endY = eY;
        numGrass = nGrass;
    }
    
    /**
     * the following are just getter methods
     */
    
    public char[][] getLawn()
    {
        return lawn;
    }
    
    public int getStartX()
    {
        return startX;
    }
    
    public int getStartY()
    {
        return startY;
    }
    
    public int getEndX()
    {
        return endX;
    }
    
    public int getEndY()
    {
        return endY;
    }
    
    public int getRows()
    {
        return lawn.length;
    }
    
    public int getCols()
    {
        return lawn[0].length;
    }
    
    public int getNumGrass()
    {
        return numGrass;
    }

}
