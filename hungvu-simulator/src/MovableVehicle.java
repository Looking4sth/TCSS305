/*
 * TCSS 305 - Fall 2019
 * Assignment 3 - Traffic Simulator
 * This class run a traffic simulation game. 
 */
/**
 * This interface is used to move a given vehicle to random location on map
 * without reaching black border.
 * @author Hung Vu
 * @version 13 11 2019
 */
public interface MovableVehicle {
	/**
	 * This method will move vehicle to random location on map
	 * but won't move them to black border.
	 */
	void move();
}
