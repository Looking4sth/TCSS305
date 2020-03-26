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
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.TetrisPiece;
/**
 * This class creates 4 x 4 piece preview board and player-guide section
 * for GUI.
 * @author Hung Vu
 * @version 09 12 2019
 * 
 */
public class PreviewHelpPanel extends JPanel {
    /**
     * This is a generated serial version UID of class.
     */
    private static final long serialVersionUID = 4810858902762390471L;
    /**
     * Default text area width.
     */
    private static final int TEXT_AREA_WIDTH = 5;
    /**
     * Default text area height.
     */
    private static final int TEXT_AREA_HEIGHT = 10;
    /**
     * Default grid width and height for preview board.
     */
    private static final int GRID_WIDTH_HEIGHT = 4;
    /**
     * Default grid cell width for preview board.
     */
    private static final int GRID_WIDTH = 100;
    /**
     * Default grid hieght width for preview board.
     */
    private static final int GRID_HEIGHT = 80;
    /**
     * Color for preview board background.
     */
    private static final Color MY_PREVIEW_BACKGROUND = new Color(178, 196, 240);
    /**
     * Text area contains player guide.
     */
    private static final JTextArea HELP_TEXT_AREA = new 
                    JTextArea(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
    /**
     * Scroll pane for player guide section.
     */
    private static final JScrollPane SCROLL_HELP = new 
                    JScrollPane(HELP_TEXT_AREA);
    /**
     * Panel contains 4 x 4 preview board.
     */
    private static final JPanel SUB_PREVIEW_PANEL = new 
                    JPanel(new GridLayout(GRID_WIDTH_HEIGHT, GRID_WIDTH_HEIGHT));
    /**
     * 2 Dimensional contains sub panel for the preview board. Each grid cell is a panel.
     */
    private static final JPanel[][] PREVIEW_BOARD = new 
                    JPanel[GRID_WIDTH_HEIGHT][GRID_WIDTH_HEIGHT];
    /**
     * List of next piece's block point on 4 x 4 board.
     */
    private model.Point[] myPreviewPoint;
    /**
     * The next tetris piece taken from Board.
     */
    private TetrisPiece myNextPiece;
    /**
     * Default constructor for PreviewHelpPanel.
     */
    public PreviewHelpPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));     
        for (int row = GRID_WIDTH_HEIGHT - 1; row >= 0; row--) {
            for (int col = 0; col < GRID_WIDTH_HEIGHT; col++) {
                PREVIEW_BOARD[row][col] = new JPanel() {
                    /**
                     * 
                     */
                    private static final long serialVersionUID = -4964029402372090427L;
                    public void paintComponent(final Graphics theG) {
                        super.paintComponent(theG);
                        final Graphics2D g2 = (Graphics2D) theG;
                        g2.setColor(Color.BLACK);
                        g2.drawRect(0, 0, GRID_WIDTH, GRID_HEIGHT);
                    }
                };
                PREVIEW_BOARD[row][col].setBackground(MY_PREVIEW_BACKGROUND);
                SUB_PREVIEW_PANEL.add(PREVIEW_BOARD[row][col]);
            }
        }
        HELP_TEXT_AREA.setEditable(false);
        HELP_TEXT_AREA.setText("Player guide: Control actions \n \n"
                        + "Move Left:  Left arrow and 'a' and 'A' \r\n" 
                        + "Move Right:    Right arrow and 'd' and 'D' \r\n" 
                        + "Rotate Up: arrow and 'w' and 'W' \r\n" 
                        + "Move Down: Down arrow and 's' and 'S' \r\n" 
                        + "Drop:  Space\r\n" + "Pause: 'p' and 'P' ");
        add(SUB_PREVIEW_PANEL);
        add(SCROLL_HELP);       
    }
    /**
     * This method is used to update the board whenever 
     * Board is changed (next piece is changed).
     * It is called by "update" method (Observer) in Tetris Gui.
     */
    public void updatePreview() {
        if (myPreviewPoint != null) {
            for (model.Point p : myPreviewPoint) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(MY_PREVIEW_BACKGROUND);
            }
        }
        myPreviewPoint = myNextPiece.getPoints();        
        final model.Point[] point = myNextPiece.getPoints();
        for (model.Point p : point) {
            if (myNextPiece == TetrisPiece.I) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(Color.CYAN);
            } else if (myNextPiece == TetrisPiece.J) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(Color.BLUE);
            } else if (myNextPiece == TetrisPiece.L) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(Color.ORANGE);
            } else if (myNextPiece == TetrisPiece.O) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(Color.YELLOW);
            } else if (myNextPiece == TetrisPiece.S) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(Color.GREEN);
            } else if (myNextPiece == TetrisPiece.T) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(Color.PINK);
            } else if (myNextPiece == TetrisPiece.Z) {
                PREVIEW_BOARD[p.y()][p.x()].setBackground(Color.RED);
            }
        }
    }
    /**
     * Setter for next tetris piece.
     * @param theNextPiece The next tetris piece.
     */
    public void setNextPiece(final TetrisPiece theNextPiece) {
        myNextPiece = theNextPiece;
    }
}
