//MenuWindow.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *  MenuWindow is the basic GUI class that appears when the menu screen is opened
 *
 *  @author  Stanley Wang
 *  @version May 25, 2018
 *  @author  Period: 5
 *  @author  Assignment: LMS
 *
 *  @author  Sources: None
 */
public class MenuWindow extends JFrame 
{
    private static JFrame thisWindow;
    private Button freePlayButton = new Button("Free Play");
    private Button levelSelectButton = new Button("Level Select");
    private Button levelCreatorButton = new Button("Level Creator");

    /**
     * no args constructor, initializes buttons
     */
    public MenuWindow() 
    {
        super("Lawn Mower Simulator Menu");
        thisWindow = this;
        JPanel pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));

        pnl.add(freePlayButton);
        pnl.add(levelSelectButton);
        pnl.add(levelCreatorButton);
        freePlayButton.addActionListener(new FPListener());
        levelSelectButton.addActionListener(new LSListener());
        levelCreatorButton.addActionListener(new LCListener());

        this.add(pnl);
    }

	// free play listener constructs default lawn map
    private class FPListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            MowerGame mg = new MowerGame();
        }
    }

    // level select listener prompts the user for the level
    private class LSListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            while (!checked());
        }

        private boolean checked() 
        {
            JLabel lvlSlctLabel = new JLabel("Select Level (1-4): ", JLabel.RIGHT);
            JTextField lvlSlctField = new JTextField(20);

            JPanel fieldsPanel = new JPanel();
            fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
            fieldsPanel.add(lvlSlctLabel);
            fieldsPanel.add(lvlSlctField);

            final String optionNames[] = { "Go and Mow", "Cancel" };

            if (JOptionPane.showOptionDialog(thisWindow, fieldsPanel, "New user registration",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, optionNames, optionNames[0]) != 0)
                return true; // User pressed "Cancel"
            
            //the level must be an integer in the range 0 to 5, 0 opens free play

            String errorMsg = "";
            boolean result = false;
            int level;
            
            try 
            {
                level = Integer.parseInt(lvlSlctField.getText());
            }

            catch (NumberFormatException e) 
            {
                errorMsg = "Enter an integer level";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                lvlSlctField.setText("");
                return false;
            }
            
            if (level >= 0 && level < 5) 
            {
                result = true;
            }

            if (!result) 
            {
                errorMsg = "Enter level in the range 1-4";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Registration failed", JOptionPane.ERROR_MESSAGE);
                lvlSlctField.setText("");
                return false;
            } 
            else 
            {
                MowerGame mg = new MowerGame(level, MowerStart.ll.get(level));
                return true;
            }
        }

    }
    
    //Level Creator Listener class
    private class LCListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            while (!checked());
        }

        private boolean checked() 
        {
            //intialize GUI
            JLabel numRowsLabel = new JLabel("Enter number of rows (1-10): ", JLabel.RIGHT);
            JTextField numRowsField = new JTextField(20);
            JLabel numColsLabel = new JLabel("Enter number of cols (1-10): ", JLabel.RIGHT);
            JTextField numColsField = new JTextField(20);
            JLabel startLabel = new JLabel("Enter starting coordinates: ", JLabel.RIGHT);
            JTextField startXField = new JTextField(20);
            JTextField startYField = new JTextField(20);
            JLabel endLabel = new JLabel("Enter ending coordinates: ", JLabel.RIGHT);
            JTextField endXField = new JTextField(20);
            JTextField endYField = new JTextField(20);
            JLabel boulder1Label = new JLabel("Enter boulder 1 coordinates: ", JLabel.RIGHT);
            JTextField boulder1XField = new JTextField(20);
            JTextField boulder1YField = new JTextField(20);
            JLabel boulder2Label = new JLabel("Enter boulder 2 coordinates: ", JLabel.RIGHT);
            JTextField boulder2XField = new JTextField(20);
            JTextField boulder2YField = new JTextField(20);
            JLabel boulder3Label = new JLabel("Enter boulder 3 coordinates: ", JLabel.RIGHT);
            JTextField boulder3XField = new JTextField(20);
            JTextField boulder3YField = new JTextField(20);

            JPanel fieldsPanel = new JPanel();
            fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
            fieldsPanel.add(numRowsLabel);
            fieldsPanel.add(numRowsField);
            fieldsPanel.add(numColsLabel);
            fieldsPanel.add(numColsField);
            fieldsPanel.add(startLabel);
            fieldsPanel.add(startXField);
            fieldsPanel.add(startYField);
            fieldsPanel.add(endLabel);
            fieldsPanel.add(endXField);
            fieldsPanel.add(endYField);
            fieldsPanel.add(boulder1Label);
            fieldsPanel.add(boulder1XField);
            fieldsPanel.add(boulder1YField);
            fieldsPanel.add(boulder2Label);
            fieldsPanel.add(boulder2XField);
            fieldsPanel.add(boulder2YField);
            fieldsPanel.add(boulder3Label);
            fieldsPanel.add(boulder3XField);
            fieldsPanel.add(boulder3YField);

            final String optionNames[] = { "Go and Mow", "Cancel" };

            if (JOptionPane.showOptionDialog(thisWindow, fieldsPanel, "Level Creator", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, optionNames, optionNames[0]) != 0)
                return true; // User pressed "Cancel"
            
            //data to be read in
            int level = 5;
            boolean result = false;
            String errorMsg = "";
            LawnMap lm;
            char[][] charLawn;
            int row;
            int col;
            int numGrass;
            int startX;
            int startY;
            int endX;
            int endY;
            int b1X;
            int b1Y;
            int b2X;
            int b2Y;
            int b3X;
            int b3Y;
            
            //check that the information entered are integers and not out of bounds
            //number of rows and cols must be > 0 and <= 10
            try {
                row = Integer.parseInt(numRowsField.getText());
            }

            catch (NumberFormatException e) {
                errorMsg = "Enter an integer number of rows";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                numRowsField.setText("");
                return false;
            }

            if (row <= 0 || row > 10) {
                errorMsg = "Number of rows out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                numRowsField.setText("");
                return false;
            }

            try {
                col = Integer.parseInt(numColsField.getText());
            }

            catch (NumberFormatException e) {
                errorMsg = "Enter an integer number of cols";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                numColsField.setText("");
                return false;
            }

            if (col <= 0 || col > 10) {
                errorMsg = "Number of cols out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                numColsField.setText("");
                return false;
            }

            charLawn = new char[row][col];
            
            //starting and ending coordinates must be in range of rows and cols
            try {
                startX = Integer.parseInt(startXField.getText());
            }

            catch (NumberFormatException e) {
                errorMsg = "Enter an integer starting x coordinate";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                startXField.setText("");
                return false;
            }

            if (startX < 0 || startX >= row) {
                errorMsg = "Starting x coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                startXField.setText("");
                return false;
            }

            try {
                startY = Integer.parseInt(startYField.getText());
            }

            catch (NumberFormatException e) {
                errorMsg = "Enter an integer starting y coordinate";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                startYField.setText("");
                return false;
            }

            if (startY < 0 || startY >= col) {
                errorMsg = "Starting y coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                startYField.setText("");
                return false;
            }

            try {
                endX = Integer.parseInt(endXField.getText());
            }

            catch (NumberFormatException e) {
                errorMsg = "Enter an integer ending x coordinate";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                endXField.setText("");
                return false;
            }

            if (endX < 0 || endX >= row) {
                errorMsg = "Ending x coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                endXField.setText("");
                return false;
            }

            try {
                endY = Integer.parseInt(endYField.getText());
            }

            catch (NumberFormatException e) {
                errorMsg = "Enter an integer ending y coordinate";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                endYField.setText("");
                return false;
            }

            if (endY < 0 || endY >= col) {
                errorMsg = "Ending y coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                endYField.setText("");
                return false;
            }
            
            //start and end must be different
            if (startX == endX && startY == endY) {
                errorMsg = "Starting position and ending position the same";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            //a boulder with coordinates out of bounds will not prompt an
            //error message, the boulder will just not be added
            //boulders cannot be in start/end positions
            try {
                b1X = Integer.parseInt(boulder1XField.getText());
            }

            catch (NumberFormatException e) {
                b1X = -1;
            }

            if (b1X < 0 || b1X >= row) {
                errorMsg = "Boulder 1 x coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                boulder1XField.setText("");
                return false;
            }

            try {
                b1Y = Integer.parseInt(boulder1YField.getText());
            }

            catch (NumberFormatException e) {
                b1Y = -1;
            }

            if (b1Y < 0 || b1Y >= col) {
                errorMsg = "Boulder 1 y coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                boulder1YField.setText("");
                return false;
            }
            
            if (b1X == startX && b1Y == startY)
            {
                errorMsg = "Boulder 1 in start position";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (b1X == endX && b1Y == endY)
            {
                errorMsg = "Boulder 1 in end position";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try {
                b2X = Integer.parseInt(boulder2XField.getText());
            }

            catch (NumberFormatException e) {
                b2X = -1;
            }

            if (b2X < 0 || b2X >= row) {
                errorMsg = "Boulder 2 x coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                boulder2XField.setText("");
                return false;
            }

            try {
                b2Y = Integer.parseInt(boulder2YField.getText());
            }

            catch (NumberFormatException e) {
                b2Y = -1;
            }

            if (b2Y < 0 || b2Y >= col) {
                errorMsg = "Boulder 2 y coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                boulder2YField.setText("");
                return false;
            }
            
            if (b2X == startX && b2Y == startY)
            {
                errorMsg = "Boulder 2 in start position";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (b2X == endX && b2Y == endY)
            {
                errorMsg = "Boulder 2 in end position";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            try {
                b3X = Integer.parseInt(boulder3XField.getText());
            }

            catch (NumberFormatException e) {
                b3X = -1;
            }

            if (b3X < 0 || b3X >= row) {
                errorMsg = "Boulder 3 x coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                boulder3XField.setText("");
                return false;
            }

            try {
                b3Y = Integer.parseInt(boulder3YField.getText());
            }

            catch (NumberFormatException e) {
                b3Y = -1;
            }

            if (b3Y < 0 || b3Y >= col) {
                errorMsg = "Boulder 3 y coordinate out of bounds";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                boulder3YField.setText("");
                return false;
            }
            
            if (b3X == startX && b3Y == startY)
            {
                errorMsg = "Boulder 3 in start position";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (b3X == endX && b3Y == endY)
            {
                errorMsg = "Boulder 3 in end position";
                JOptionPane.showMessageDialog(thisWindow, errorMsg, "Level Creator failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    charLawn[i][j] = ' ';
                }
            }

            if (b1X != -1 && b1Y != -1) {
                charLawn[b1X][b1Y] = 'B';
            }
            if (b2X != -1 && b2Y != -1) {
                charLawn[b2X][b2Y] = 'B';
            }
            if (b3X != -1 && b3Y != -1) {
                charLawn[b3X][b3Y] = 'B';
            }

            charLawn[startX][startY] = 'M';
            charLawn[endX][endY] = 'F';
            numGrass = 0;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (charLawn[i][j] == ' ') {
                        charLawn[i][j] = 'G';
                        numGrass++;
                    }
                }
            }

            MowerGame mg = new MowerGame(level, new LawnMap(charLawn, startX, startY, endX, endY, numGrass));
            return true;
        }

    }
    
    /**
	 * @return the freePlayButton
	 */
	public Button getFreePlayButton() {
		return freePlayButton;
	}

	/**
	 * @return the levelSelectButton
	 */
	public Button getLevelSelectButton() {
		return levelSelectButton;
	}

	/**
	 * @return the levelCreatorButton
	 */
	public Button getLevelCreatorButton() {
		return levelCreatorButton;
	}

}
