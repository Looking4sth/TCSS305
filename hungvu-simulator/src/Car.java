/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class run a traffic simulation game. 
 */
/**
 * This class creates properties of car.
 * @author Hung Vu
 * @version 13 11 2019
 */
public class Car extends Vehicles{
	/**
	 * Instance field myDefaultX is an initial X coordinate of vehicle.
	 */
	private final int myDefaultX;
	/**
	 * Instance field myDefaultY is an initial Y coordinate of vehicle.
	 */
	private final int myDefaultY;
	/**
	 * This method is a constructor for car.
	 * @param theX X coordinate of car
	 * @param theY Y coordinate of car
	 */
	public Car(int theX, int theY) {
		super(theX, theY);
		myDefaultX = theX;
		myDefaultY = theY;
	}
	/**
	 * This method will reset car to their initial position.
	 */
	public void reset() {
		super.setX(myDefaultX);
		super.setY(myDefaultY);
		super.reset();
	}
	/**
	 * This method will print car object depended on
	 * whether it is alive or not.
	 */
	public String toString() {
		if (isAlive() == false) {
			return "The Car is dead";
		}
		else {
			return "The Car is alive " + super.toString();
		}
	}
}