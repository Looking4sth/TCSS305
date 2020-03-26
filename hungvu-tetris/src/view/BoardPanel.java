/*
 * TCSS 305 - Fall 2019
 * Assignment 5 - Tetris
 * This class creates GUI for Tetris game.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.Block;
/**
 * This class creates 10 x 20 board game base on given model.
 * @author Hung Vu
 * @version 09 12 2019
 * 
 */
public class BoardPanel extends JPanel {   
    /**
     * This is a generated serial version UID of class.
     */
    private static final long serialVersionUID = -5722621419669101079L;
    /**
     * Default background color of board.
     */
    private static final Color MY_BOARD_BACKGROUND = new Color(255, 219, 172);
    /**
     * Board height.
     */
    private static final int BOARD_HEIGHT = 20;
    /**
     * Board width.
     */
    private static final int BOARD_WIDTH = 10;
    /**
     * Width of grid cell.
     */
    private static final int GRID_WIDTH = 30;
    /**
     * Height of grid cell.
     */
    private static final int GRID_HEIGHT = 40;
    /**
     * Panel layout for board.
     */
    private static final GridLayout BOARD_LAYOUT = new GridLayout(BOARD_HEIGHT, BOARD_WIDTH);
    /**
     * This contains X coordinate that is used, or has been was used on the Board.
     * This is used to re-color the board after the piece/block move to another position.
     * E.g:
     * Block appears at (1, 1) -> x = 1 goes into array list.
     * Block then move down to (2,1) x = 1 is still in array list, 
     * x = 2 goes into array list and so on.
     */
    private static final ArrayList<Integer> X_BOARD = new ArrayList<Integer>();
    /**
     * This contains Y coordinate that is used, or has been was used on the Board.
     * This is used to re-color the board after the piece/block move to another position.
     * E.g:
     * Block appears at (1, 1) -> y = 1 goes into array list.
     * Block then move down to (1, 2) y = 1 is still in array list, 
     * y = 2 goes into array list and so on.
     */
    private static final ArrayList<Integer> Y_BOARD = new ArrayList<Integer>();
    /**
     * 2 Dimensional contains sub panel for the board. Each grid cell is a panel.
     */
    private static final JPanel[][] BOARD_GRID = new JPanel[BOARD_HEIGHT][BOARD_WIDTH];
    /**
     * 2 Dimensional array contains position of current piece, taken from Board.
     */
    private Block[][] myCurrentBlock;
    /**
     * Default constructor for Board Panel.
     */
    public BoardPanel() {
        setLayout(BOARD_LAYOUT);                            
        for (int row = BOARD_HEIGHT - 1; row >= 0; row--) {
            for (int col = 0; col < BOARD_WIDTH; col++) {                               
                BOARD_GRID[row][col] = new JPanel() {
                    /**
                     * This is a generated serial version UID.
                     */
                    private static final long serialVersionUID = -2437053780728777771L;
                    public void paintComponent(final Graphics theG) {
                        super.paintComponent(theG);
                        final Graphics2D g2 = (Graphics2D) theG;
                        g2.setColor(Color.BLACK);
                        g2.drawRect(0, 0, GRID_WIDTH, GRID_HEIGHT);                        
                    }
                };
                BOARD_GRID[row][col].setBackground(MY_BOARD_BACKGROUND); 
                add(BOARD_GRID[row][col]);
            }
        }

    }
    /**
     * This method is used to update the board whenever 
     * Board is changed (Block 2D array is changed).
     * It is called by "update" method (Observer) in Tetris Gui.
     */
    public void updateBoard() {
        if (X_BOARD != null && Y_BOARD != null) {
            for (int i = 0; i < X_BOARD.size(); i++) {
                BOARD_GRID[X_BOARD.get(i)][Y_BOARD.get(i)].
                            setBackground(MY_BOARD_BACKGROUND);

            }
            X_BOARD.clear();
            Y_BOARD.clear();
        }

        for (int i = 0; i < myCurrentBlock.length; i++) {
            for (int j = 0; j < myCurrentBlock[0].length; j++) {
                if (myCurrentBlock[i][j] != null && i < BOARD_HEIGHT) {
                    if (myCurrentBlock[i][j] == Block.I) {
                        BOARD_GRID[i][j].setBackground(Color.CYAN);
                    } else if (myCurrentBlock[i][j] == Block.J) {
                        BOARD_GRID[i][j].setBackground(Color.BLUE);
                    } else if (myCurrentBlock[i][j] == Block.L) {
                        BOARD_GRID[i][j].setBackground(Color.ORANGE);
                    } else if (myCurrentBlock[i][j] == Block.O) {
                        BOARD_GRID[i][j].setBackground(Color.YELLOW);
                    } else if (myCurrentBlock[i][j] == Block.S) {
                        BOARD_GRID[i][j].setBackground(Color.GREEN);
                    } else if (myCurrentBlock[i][j] == Block.T) {
                        BOARD_GRID[i][j].setBackground(Color.PINK);
                    } else if (myCurrentBlock[i][j] == Block.Z) {
                        BOARD_GRID[i][j].setBackground(Color.RED);
                    }
                    
                    X_BOARD.add(Integer.valueOf(i));
                    Y_BOARD.add(Integer.valueOf(j));
                }
            }
        }
    }
    /**
     * Setter for Block 2D array.
     * @param theBlock The new 2D Block array.
     */
    public void setCurrentBlock(final Block[][] theBlock) {
        myCurrentBlock = theBlock;
    }
}
