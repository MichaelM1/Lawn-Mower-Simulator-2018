//GameWindow.java

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

/**
 *  GameWindow is the GUI class for the game itself.
 *  Displays a grid as the lawn and current game status
 *
 *  @author  Stanley Wang
 *  @version May 25, 2018
 *  @author  Period: 5
 *  @author  Assignment: LMS
 *
 *  @author  Sources: None
 */
public class GameWindow extends JFrame
{
    private int level;
    private JLabel[][] labelLawn;
    private JLabel scoreLabel;
    private JLabel numMoveLabel;
    private JLabel gameStatusLabel;
    private MowerGame mg;
    private JPanel pnl;
    private JPanel gamePnl;
    private JPanel infoPnl;
    private JButton nextLvlButton;
    
    /**
     * GameWindow constructor
     * @param lvlNum the level number
     * @param lm the lawn map
     * @param mg the mower game
     */
    public GameWindow(int lvlNum, LawnMap lm, MowerGame mg)
    {
        super("Lawn Mower Simulator Level " + lvlNum);
        this.mg = mg;
        
        level = lvlNum;
        
        pnl = new JPanel();
        gamePnl = new JPanel();
        infoPnl = new JPanel();
        
        //we use a 2D array of labels for the GUI
        scoreLabel = new JLabel("Score: 0");
        numMoveLabel = new JLabel("Moves: 0");
        gameStatusLabel = new JLabel("Game Status: In progress");
        nextLvlButton = new JButton("Next Level");
        nextLvlButton.addActionListener( new NextLvlButtonListener() );
        labelLawn = new JLabel[lm.getRows()][lm.getCols()];
        for (int i = 0; i < labelLawn.length; i++)
        {
            for (int j = 0; j < labelLawn[0].length; j++)
            {
                labelLawn[i][j] = new JLabel();
            }
        }
    
        pnl.setLayout( new BoxLayout(pnl, BoxLayout.Y_AXIS) );
        gamePnl.setLayout( new GridLayout(labelLawn.length, labelLawn[0].length) );
        infoPnl.setLayout( new FlowLayout(FlowLayout.CENTER, 100, 5) );
        
        infoPnl.add(scoreLabel);
        infoPnl.add(gameStatusLabel);
        infoPnl.add(numMoveLabel);
        
        pnl.add(gamePnl);
        pnl.add(infoPnl);
        
        update(lm.getLawn());
        
        for (int i = 0; i < labelLawn.length; i++)
        {
            for (int j = 0; j < labelLawn[0].length; j++)
            {
                gamePnl.add(labelLawn[i][j]);
            }
        }
        
        KeyListener KL = new MowerListener();
        pnl.addKeyListener(KL);
        pnl.setFocusable(true);
        
        this.add( pnl );
        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setBounds( 0, 0, 1800, 700 );
        this.setVisible( true );
    }
    
    /**
     * Changes the character at position (x, y) to c
     * @param x is x coord of position to change
     * @param y is y coord of position to change
     * @param c is the character to change to
     */
    public void update(int x, int y, char c)
    {
        if (x >= 0 && x < labelLawn.length && y >= 0 && y < labelLawn[0].length)
        {
            if (c == 'G')
            {
                labelLawn[x][y].setIcon(new ImageIcon(this.getClass().getResource("/images/smallgrass.png")));
            }
            else if (c == 'B')
            {
                labelLawn[x][y].setIcon(new ImageIcon(this.getClass().getResource("/images/smallboulder.png")));
            }
            else if (c == 'M')
            {
                labelLawn[x][y].setIcon(new ImageIcon(this.getClass().getResource("/images/smallmower.png")));
            }
            else if (c == 'O')
            {
                labelLawn[x][y].setIcon(new ImageIcon(this.getClass().getResource("/images/smalldirt.png")));
            }
            else if (c == 'F')
            {
                labelLawn[x][y].setIcon(new ImageIcon(this.getClass().getResource("/images/smallflag.png")));
            }
        }
    }
    
    /**
     * update the whole grid
     * @param charLawn is the 2D character array to update
     */
    public void update(char[][] charLawn)
    {
        for (int i = 0; i < labelLawn.length; i++)
        {
            for (int j = 0; j < labelLawn[0].length; j++)
            {
                if (charLawn[i][j] == 'G')
                {
                    update(i, j, 'G');
                }
                else if (charLawn[i][j] == 'B')
                {
                    update(i, j, 'B');
                }
                else if (charLawn[i][j] == 'M')
                {
                    update(i, j, 'M');
                }
                else if (charLawn[i][j] == 'O')
                {
                    update(i, j, 'O');
                }
                else if (charLawn[i][j] == 'F')
                {
                    update(i, j, 'F');
                }
            }
        }
    }
    
    /**
     * update the number of moves
     * @param x is number of moves
     */
    public void updateNumMove(int x)
    {
        numMoveLabel.setText("Moves: " + x);
    }
    
    /**
     * update the score
     * @param x is the score
     */
    public void updateScore(int x)
    {
        scoreLabel.setText("Score: " + x);
    }
    
    /**
     * To end the game
     */
    public void stopGame(double rank)
    {
        String s = "Game Status: Over, Rank: ";
        if (rank > 0.9)
        {
            s += "A";
        }
        else if (rank > 0.8)
        {
            s += "B";
        }
        else if (rank > 0.7)
        {
            s += "C";
        }
        else if (rank > 0.6)
        {
            s += "D";
        }
        else
        {
            s += "F";
        }
        gameStatusLabel.setText(s);
        pnl.setFocusable(false);
        if (level < 4)
        {
            pnl.add(nextLvlButton);
        }
    }
    
    //private Listener class that makes the mower move
    private class MowerListener implements KeyListener
    {

        @Override
        public void keyPressed(KeyEvent arg0) 
        {
            if (arg0.getKeyCode() == KeyEvent.VK_DOWN )
            {
                mg.move(1,0);
            }
            else if (arg0.getKeyCode() == KeyEvent.VK_UP )
            {
                mg.move(-1,0);
            }
            else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT )
            {
                mg.move(0,1);
            }
            else if (arg0.getKeyCode() == KeyEvent.VK_LEFT )
            {
                mg.move(0,-1);
            }
            
        }

        @Override
        public void keyReleased(KeyEvent arg0) {}

        @Override
        public void keyTyped(KeyEvent arg0) {}
        
    }
    
    //private class for the button that proceeds to next level
    private class NextLvlButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            MowerGame mg = new MowerGame(level + 1, MowerStart.ll.get(level + 1));
        }
    }

}
