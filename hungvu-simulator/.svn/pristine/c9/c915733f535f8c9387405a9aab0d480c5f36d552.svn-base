/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class run a traffic simulation game. 
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * This is class will open a game window (class Gui)
 * with resize (disable), minimize and exit buttons on the top right.
 * @author Hung Vu
 * @version 13 11 2019
 */
public class MainGui {
	/**
	 * This is a main method to run a simulator
	 * @param args
	 */
	public static void main(String[] args) {
		Gui window = new Gui();
		window.setTitle("Road Rage");
		window.setSize(1440, 810);
		window.setVisible(true);
		window.setResizable(false);
		window.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		
	}

}
