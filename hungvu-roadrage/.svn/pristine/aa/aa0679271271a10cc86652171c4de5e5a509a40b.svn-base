/*
 * TCSS 305 - Fall 2019
 * Assignment 4 - Road Rage
 * This class creates a Car object for simulator.
 */
package model;

import java.util.Map;
/**
 * This class creates properties of Car.
 * @author Hung Vu
 * @version 25 11 2019
 */
public class Car extends AbstractVehicle {
    /**
     * This is Death time of Car vehicle.
     */
    private static final int MY_DEATH_TIME = 15;
    /**
     * Constructor for Car object.
     * @param theX X-coordinate of vehicle
     * @param theY Y-coordinate of vehicle
     * @param theDir Direction of vehicle
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }

    /**
     * This method let the program know whether Car can pass 
     * a certain terrain or not (with condition).
     * @param theTerrain The terrain in Car direction.
     * @param theLight The light color in Car direction
     * @return True if a Car can pass, false otherwise.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET
                        || (theTerrain == Terrain.LIGHT && theLight != Light.RED)
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN);
    }
    /**
     * This method return the next direction for a Car vehicle at 
     * a specific point based on given rules.
     * @param theNeighbors a map contains neighbors terrain with their
     * relative direction to a vehicle.
     * @return new Direction for vehicle based on given rules.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final char direction;
        if (theNeighbors.get(getDirection()) == Terrain.STREET 
                        || theNeighbors.get(getDirection()) == Terrain.LIGHT 
                        || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            direction = getDirection().letter();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
                            || theNeighbors.get(getDirection().left()) == Terrain.LIGHT 
                            || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            direction =  getDirection().left().letter();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().right()) == Terrain.LIGHT 
                        || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            direction = getDirection().right().letter();
        } else {
            direction = getDirection().reverse().letter();
        }
        return Direction.valueOf(direction);
        
    }

}
