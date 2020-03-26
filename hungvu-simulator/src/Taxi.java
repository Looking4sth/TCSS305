/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class run a traffic simulation game. 
 */
/**
 * This class creates properties of taxi.
 * @author Hung Vu
 * @version 13 11 2019
 */
public class Taxi extends Vehicles{
	/**
	 * Instance field myDefaultX is an initial X coordinate of vehicle.
	 */
	private final int myDefaultX;
	/**
	 * Instance field myDefaultY is an initial Y coordinate of vehicle.
	 */
	private final int myDefaultY;
	/**
	 * This method is a constructor for taxi.
	 * @param theX X coordinate of taxi
	 * @param theY Y coordinate of taxi
	 */
	public Taxi(int theX, int theY) {
		super(theX, theY);
		myDefaultX = theX;
		myDefaultY = theY;
	}
	/**
	 * This method will reset taxi to their initial position.
	 */
	public void reset() {
		super.setX(myDefaultX);
		super.setY(myDefaultY);
		super.reset();
	}
	/**
	 * This method will print taxi object depended on
	 * whether it is alive or not.
	 */
	public String toString() {
		if (isAlive() == false) {
			return "The Taxi is dead";
		}
		else {
			return "The Taxi is alive " + super.toString();
		}
	}
}
