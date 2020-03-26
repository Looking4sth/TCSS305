/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class run a traffic simulation game. 
 */
/**
 * This class creates properties of ATV.
 * @author Hung Vu
 * @version 13 11 2019
 */
public class ATV extends Vehicles{
	/**
	 * Instance field myDefaultX is an initial X coordinate of vehicle.
	 */
	private final int myDefaultX;
	/**
	 * Instance field myDefaultY is an initial Y coordinate of vehicle.
	 */
	private final int myDefaultY;
	/**
	 * This method is a constructor for ATV.
	 * @param theX X coordinate of ATV
	 * @param theY Y coordinate of ATV
	 */
	public ATV(int theX, int theY) {
		super(theX, theY);
		myDefaultX = theX;
		myDefaultY = theY;
	}
	/**
	 * This method will reset atv to their initial position.
	 */
	public void reset() {
		super.setX(myDefaultX);
		super.setY(myDefaultY);
		super.reset();
	}
	/**
	 * This method will print ATV object depended on
	 * whether it is alive or not.
	 */
	public String toString() {
		if (isAlive() == false) {
			return "The ATV is dead";
		}
		else {
			return "The ATV is alive " + super.toString();
		}
	}
}
