/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class run a traffic simulation game. 
 */
/**
 * This class creates properties of Human.
 * @author Hung Vu
 * @version 13 11 2019
 */
public class Human extends Vehicles {
	/**
	 * Instance field myDefaultX is an initial X coordinate of vehicle.
	 */
	private final int myDefaultX;
	/**
	 * Instance field myDefaultY is an initial Y coordinate of vehicle.
	 */
	private final int myDefaultY;
	/**
	 * This method is a constructor for human.
	 * @param theX X coordinate of human
	 * @param theY Y coordinate of human
	 */
	public Human(int theX, int theY) {
		super(theX, theY);
		myDefaultX = theX;
		myDefaultY = theY;
	}
	/**
	 * This method will reset human to their initial position.
	 */
	public void reset() {
		super.setX(myDefaultX);
		super.setY(myDefaultY);
		super.reset();
	}
	/**
	 * This method will print human object depended on
	 * whether it is alive or not.
	 */
	public String toString() {
		if (isAlive() == false) {
			return "The Human is dead";
		}
		else {
			return "The Human is alive " + super.toString();
		}
	}
}
