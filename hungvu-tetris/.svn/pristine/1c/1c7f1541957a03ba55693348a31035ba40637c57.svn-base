/*
 * TCSS 305 - Fall 2019
 * Assignment 5 - Tetris
 * This class creates GUI for Tetris game.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import model.Block;
import model.Board;
import model.TetrisPiece;
/**
 * This class creates GUI based on given model, which contains
 * logic of Tetris game.
 * SuppressWarnings exit because the model is an Observable object.
 * @author Hung Vu
 * @version 09 12 2019
 * 
 */
@SuppressWarnings("deprecation")
public class TetrisGui extends JFrame implements Observer, ActionListener, KeyListener {
    /**
     * This is a generated serial version UID of class.
     */
    private static final long serialVersionUID = -6222765806425189903L;
    /**
     * This is the string that always appear in an announcement area.
     */
    private static final String DEFAULT_ANNOUNCEMENT = "INFORMATION:\n"
                    + "The game will start right after you press New Game. \n";
    /**
     * This string is to announce player start the game again.
     */
    private static final String START_AGAIN = "Press New Game to play again.";
    /**
     * This string is to announce player not to perform any further action.
     */
    private static final String NO_ACTION = "You will not be able to perform any action. \n";
    /**
     * Default layout for frame.
     */
    private static final GridLayout FRAME_LAYOUT = new GridLayout(1, 2);
    /**
     * Default layout for Buttons and Announcement panel.
     */
    private static final GridLayout BUTTONS_ANNOUNCEMENT_LAYOUT = new GridLayout(4, 1);
    /**
     * Default dimension for Buttons and Announcement panel.
     */
    private static final Dimension BUTTONS_ANNOUNCEMENT_DIMENSION = new Dimension(0, 400);
    /**
     * Default width of frame.
     */
    private static final int FRAME_WIDTH = 615;
    /**
     * Default height of frame.
     */
    private static final int FRAME_HEIGHT = 840;
    /**
     * Default width for text area.
     */
    private static final int TEXT_WIDTH = 15;
    /**
     * Default height for text area.
     */
    private static final int TEXT_HEIGHT = 10;
    /**
     * This is the sub-panel contains "Previewing next piece" and "Help" regions, 
     * located at west side panel of GUI.
     */
    private static final PreviewHelpPanel SUB_PREVIEW_HELP_PANEL = new PreviewHelpPanel();
    /**
     * This is the panel contains "Game Board" region, located at East side of GUI.
     */
    private static final BoardPanel BOARD_PANEL_EAST = new BoardPanel();
    /**
     * This is the panel in the west region of GUI.
     */
    private static final JPanel PANEL_WEST = new JPanel();
    /**
     * This is the sub-panel contains "Buttons" and "Announcement" regions, located
     * at west side panel of GUI.
     */
    private static final JPanel SUB_PANEL_BUTTONS_ANNOUNCEMENT = new JPanel();
    /**
     * Text Area for announcement.
     */
    private static final JTextArea ANNOUNCEMENT_AREA = new 
                    JTextArea(TEXT_WIDTH, TEXT_HEIGHT);
    /**
     * Scrollable text area for announcement.
     */
    private static final JScrollPane SCROLLABLE_ANNOUNCEMENT_AREA = new 
                    JScrollPane(ANNOUNCEMENT_AREA);
    /**
     * Store all the information of current game board (piece position,
     * next piece, etc).
     */
    private static Board myBoard = new Board();
    /**
     * New game button.
     */
    private JButton myNewGame = new JButton("New Game");
    /**
     * Pause/Unpause game button.
     */
    private JButton myPauseGame = new JButton("Pause Game");
    /**
     * End game button.
     */
    private JButton myEndGame = new JButton("End Game");
    /**
     * This field let the program access audio source.
     */
    private AudioInputStream mySoundEffect;
    /**
     * This field allow program to play back sound effect.
     */
    private Clip mySoundPlayer;
    /**
     * Indicate pause/unpause status of the game. Default is false.
     * False means unpausing.
     * True means pausing.
     * 
     * This can also indicate how the timer behaves.
     * True means the timer is canceled.
     * False means the new timer is created.
     */
    private boolean myPauseStatus;
    /**
     * Indicate end (or game over) status of the game
     * False means the game is not not ended yet.
     * True means the game is either not started or already ended.
     * 
     * This can also indicate how the timer behaves.
     * True means the timer is canceled.
     * False means the new timer is created.
     */
    private boolean myEndStatus = true;
    /**
     * Indicate whether the program is allowed to create new timer
     * True means the program is allowed to create new timer.
     * False otherwise.
     */
    private boolean myTimerStatus = true;
    /**
     * Default constructor for Tetris GUI.
     */
    public TetrisGui() {
        buttonsAnnouncementPanel();
        PANEL_WEST.setLayout(new BorderLayout());
        PANEL_WEST.add(SUB_PANEL_BUTTONS_ANNOUNCEMENT, BorderLayout.NORTH);
        PANEL_WEST.add(SUB_PREVIEW_HELP_PANEL, BorderLayout.CENTER);
        getContentPane().setLayout(FRAME_LAYOUT);
        getContentPane().add(PANEL_WEST);
        getContentPane().add(BOARD_PANEL_EAST);
        
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        
    }
    /**
     * This method creates "Buttons" and "Announcement" sub-panel.
     */
    public void buttonsAnnouncementPanel() {
        SUB_PANEL_BUTTONS_ANNOUNCEMENT.setLayout(BUTTONS_ANNOUNCEMENT_LAYOUT);
        SUB_PANEL_BUTTONS_ANNOUNCEMENT.setPreferredSize(BUTTONS_ANNOUNCEMENT_DIMENSION);
        myNewGame.addActionListener(this);
        myPauseGame.addActionListener(this);
        myEndGame.addActionListener(this);
        SUB_PANEL_BUTTONS_ANNOUNCEMENT.add(myNewGame);
        SUB_PANEL_BUTTONS_ANNOUNCEMENT.add(myPauseGame);
        SUB_PANEL_BUTTONS_ANNOUNCEMENT.add(myEndGame);
        ANNOUNCEMENT_AREA.setLineWrap(true);
        ANNOUNCEMENT_AREA.setEditable(false);
        ANNOUNCEMENT_AREA.setText(DEFAULT_ANNOUNCEMENT);
        SUB_PANEL_BUTTONS_ANNOUNCEMENT.add(SCROLLABLE_ANNOUNCEMENT_AREA);
        
    }
    /**
     * This method shows how the GUI behaves if it is ended/game over.
     */
    public void gameOver() {
        if (myEndStatus) {
            ANNOUNCEMENT_AREA.setText(DEFAULT_ANNOUNCEMENT
                                   + "Game is either not started yet or already ended. \n"
                                   + NO_ACTION
                                   + START_AGAIN);
        } else {
            myEndStatus = true;
            myPauseStatus = false;
            myTimerStatus = true;
            myBoard.autostep(myEndStatus);
            ANNOUNCEMENT_AREA.setText(DEFAULT_ANNOUNCEMENT
                                      + "Game Over. \n"
                                      + NO_ACTION
                                      + START_AGAIN);
            playSoundEffect("GameOver.wav");
        }
    }
    /**
     * This method shows how the GUI behaves when paused/unpaused.
     */
    public void pauseGame() {
        if (!myEndStatus) {
            if (!myPauseStatus) {
                myPauseStatus = true;
                myBoard.autostep(myPauseStatus);
                ANNOUNCEMENT_AREA.setText(DEFAULT_ANNOUNCEMENT
                                + "Game is paused. \n"
                                + NO_ACTION);
            } else {
                myPauseStatus = false;
                myBoard.autostep(myPauseStatus);
                ANNOUNCEMENT_AREA.setText(DEFAULT_ANNOUNCEMENT
                                + "Game is unpaused, keep going.");
                setFocusable(true);
                requestFocusInWindow();
            }
        } else {
            ANNOUNCEMENT_AREA.setText(DEFAULT_ANNOUNCEMENT
                            + "Game is ended or not yet started, can not pause/unpause. \n"
                            + START_AGAIN);
        }
        playSoundEffect("PauseUnpause.wav");
    }
    /**
     * This method plays the sound effect respective to given audio file name.
     * Sound files are taken from Morten (Username: LittleRobotSoundFactory - at
     * https://freesound.org/people/LittleRobotSoundFactory/)
     * File name are modified for purpose of this program.
     * @param theFileName Sound effect file name.
     */
    public void playSoundEffect(final String theFileName) {
        try {
            mySoundEffect = AudioSystem.getAudioInputStream(new File(theFileName));
            mySoundPlayer = AudioSystem.getClip();
            mySoundPlayer.open(mySoundEffect);
            mySoundPlayer.start();
        } catch (final UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method shows how the GUI behaves when an observable game board is changed.
     */
    @Override
    public void update(final Observable theObs, final Object theData) {
        if (theData instanceof Block[][]) {
            BOARD_PANEL_EAST.setCurrentBlock((Block[][]) theData);
            BOARD_PANEL_EAST.updateBoard();

        }
        if (theData instanceof TetrisPiece) {
            SUB_PREVIEW_HELP_PANEL.setNextPiece((TetrisPiece) theData);
            SUB_PREVIEW_HELP_PANEL.updatePreview();
            playSoundEffect("NewPiece.wav");
        }
        if (theData.equals(true)) {
            gameOver();
        }
    }
    /**
     * This methods how the GUI behaves when a button is clicked.
     */
    @Override
    public void actionPerformed(final ActionEvent theE) {
        if (theE.getSource() == myNewGame) {
            if (myEndStatus) {
                myEndStatus = false;
                myBoard.newGame();
                if (myTimerStatus) {
                    myBoard.autostep(myEndStatus);
                }
                myTimerStatus = false;
                ANNOUNCEMENT_AREA.setText(DEFAULT_ANNOUNCEMENT
                                + "New game start.");
            } else {
                ANNOUNCEMENT_AREA.setText("INFORMATION:\n"
                                + "The game will start right after you press New Game. \n"
                                + "Cannot start new game. \n"
                                + "Please end the current game first.");
            }
            BOARD_PANEL_EAST.updateBoard();
            SUB_PREVIEW_HELP_PANEL.updatePreview();
            setFocusable(true);
            requestFocusInWindow();
            playSoundEffect("New.wav");
        }
        if (theE.getSource() == myPauseGame) {
            pauseGame();
        }
        if (theE.getSource() == myEndGame) {
            gameOver();
        }
    }
    /**
     * This method shows how the GUI behaves when a character key is typed.
     */
    @Override
    public void keyTyped(final KeyEvent theE) {

        if (theE.getKeyChar() == 'a' || theE.getKeyChar() == 'A') {
            myBoard.left();
            playSoundEffect("Move.wav");
        } else if (theE.getKeyChar() == 'd' || theE.getKeyChar() == 'D') {
            myBoard.right();
            playSoundEffect("Move.wav");
        } else if (theE.getKeyChar() == 's' || theE.getKeyChar() == 'S') {
            myBoard.step();
            playSoundEffect("Move.wav");
        } else if (theE.getKeyChar() == 'w' || theE.getKeyChar() == 'W') {
            myBoard.rotateCW();
            playSoundEffect("Rotate.wav");
        } else if (theE.getKeyChar() == 'p' || theE.getKeyChar() == 'P') {
            pauseGame();
        } 

    }
    /**
     * (Ignore, empty method).
     */
    @Override
    public void keyPressed(final KeyEvent theE) {

    }
    /**
     * This methods shows how the GUI behaves when arrow keys and space key
     * are release.
     * Side note: Duo to how the game works, this can be implemented in either keyPressed
     * or keyReleased.
     */
    @Override
    public void keyReleased(final KeyEvent theE) {

        if (theE.getKeyCode() == KeyEvent.VK_DOWN) {
            myBoard.step();
            playSoundEffect("Move.wav");

        } else if (theE.getKeyCode() == KeyEvent.VK_LEFT) {
            myBoard.left();
            playSoundEffect("Move.wav");

        } else if (theE.getKeyCode() == KeyEvent.VK_RIGHT) {
            myBoard.right();
            playSoundEffect("Move.wav");
        } else if (theE.getKeyCode() == KeyEvent.VK_SPACE) {
            myBoard.drop();
            playSoundEffect("Drop.wav");
        } else if (theE.getKeyCode() == KeyEvent.VK_UP) {
            myBoard.rotateCW();
            playSoundEffect("Rotate.wav");
        }
    }
    /**
     * This is main method of the GUI.
     * @param theArgs Command line argument (ignored, as shown in road rage project).
     */
    public static void main(final String[] theArgs) {
        final TetrisGui window = new TetrisGui();
        myBoard.addObserver(window);
        window.setTitle("Tetris");
        window.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        window.setVisible(true);
        window.setResizable(false);
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent theE) {
                System.exit(0);
            }
        });
    
    }
}
