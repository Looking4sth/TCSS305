/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class run a traffic simulation game. 
 */
import java.util.Random;
import javax.swing.ImageIcon;
/**
 * This is a abstract class for all vehicles, which provides
 * their common properties.
 * @author Hung Vu
 * @version 13 11 2019
 */
abstract class Vehicles implements MovableVehicle {
	/**
	 * Instance field myX is X coordinate of vehicle.
	 */
	private int myX;
	/**
	 * Instance field myY is Y coordinate of vehicle.
	 */
	private int myY;

	/**
	 * Instance field myAlive indicate whether the vehicle is dead or not.
	 * True is alive.
	 * False is dead.
	 */
	private boolean myAlive;
	/**
	 * This method is the constructor for vehicles object.
	 * @param theX x coordinate for vehicle
	 * @param theY y coordinate for vehicle
	 */
	protected Vehicles(int theX, int theY) {
		myX = theX;
		myY = theY;
		myAlive = true;
	}
	/**
	 * This method will reset vehicle to their initial position.
	 */
	public void reset() {
		setX(myX);
		setY(myY);
	}
	/**
	 * This method will set new X coordinate to vehicle.
	 */
	public void setX(int theX) {
		myX = theX;
	}
	/**
	 * This method will set new Y coordinate to vehicle.
	 */
	public void setY(int theY) {
		myY = theY;
	}
	/**
	 * This method will set new dead-or-alive status to vehicle.
	 */
	public void setAlive(boolean theAlive) {
		myAlive = theAlive;
	}
	/**
	 * This method return X coordinate of vehicle.
	 * @return X coordinate of vehicle.
	 */
	public int getX() {
		return myX;
	}
	/**
	 * This method return Y coordinate of vehicle.
	 * @return Y coordinate of vehicle.
	 */
	public int getY() {
		return myY;
	}
	/**
	 * This method return dead-or-alive status of vehicle.
	 * @return dead-or-alive status of vehicle.
	 */
	public boolean isAlive() {
		return myAlive;
	}
	/**
	 * This method return image icon of vehicle.
	 * @param fileName image file name of vehicle.
	 * @return image icon of vehicle.
	 */
	public ImageIcon getImageFileName(String fileName) {
		return new ImageIcon(fileName);
	}
	/**
	 * This method will move vehicle to random location on map
	 * but won't move them to black border.
	 */
	public void move() {
		Random randomMove = new Random();
		int x = getX();
		int y = getY();
		int moveX = randomMove.nextInt(47) - 23;
		int moveY = randomMove.nextInt(25) - 12;
		int newX = x + moveX;
		int newY = y + moveY;
		while(newX > 22 || newX < 1) {
			newX = x + randomMove.nextInt(47) - 23;

		}
		while(newY > 11 || newY < 1) {
			newY = y + randomMove.nextInt(25) - 12;
		}
		setX(newX);
		setY(newY);
		
	}
	/**
	 * This method print common information of vehicle
	 * Form: "at (y, x)."
	 */
	public String toString() {
		return "at (" + myY +", " + myX + ").";
	}
	
}
