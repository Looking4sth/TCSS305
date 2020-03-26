/*
 * TCSS 305 - Fall 2019
 * Assignment 4 - Road Rage
 * This class creates a Taxi object for simulator.
 */
package model;

import java.util.Map;
/**
 * This class creates properties of Taxi.
 * @author Hung Vu
 * @version 25 11 2019
 */
public class Taxi extends AbstractVehicle {
    /**
     * This is Death time of Taxi vehicle.
     */
    private static final int MY_DEATH_TIME = 15;
    /**
     * This is Taxi wait time for crosswalk light.
     */
    private static final int MY_CROSSWALK_WAIT_TIME = 3;
    /**
     * This is a default value for Taxi crosswalk light counter.
     */
    private static final int MY_DEFAULT_COUNTER = 0;
    /**
     * This is a counter for step a Taxi have to wait for crosswalk light.
     */
    private int myStepCounter = MY_DEFAULT_COUNTER;
    /**
     * This is a default pass value for a Taxi, before considering conditions.
     */
    private boolean myPass = true;
    /**
     * Constructor for Taxi object.
     * @param theX X-coordinate of vehicle
     * @param theY Y-coordinate of vehicle
     * @param theDir Direction of vehicle
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }
    /**
     * This method let the program know whether Taxi can pass 
     * a certain terrain or not (with condition).
     * @param theTerrain The terrain in Taxi direction.
     * @param theLight The light color in Taxi direction
     * @return True if a Taxi can pass, false otherwise.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        if (!myPass && (theTerrain == Terrain.CROSSWALK && theLight == Light.RED)) {
            myStepCounter++;
            if (myStepCounter ==  MY_CROSSWALK_WAIT_TIME) {
                myStepCounter = 0;
                return true;
            }
        }
        if (theTerrain == Terrain.STREET
                        || ((theTerrain == Terrain.LIGHT || theTerrain == Terrain.CROSSWALK)
                                        && theLight != Light.RED)) {
            myPass = true;
        } else {
            myPass = false;
        }
        return myPass;
    }
    /**
     * This method return the next direction for a Taxi vehicle at 
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
            direction = getDirection().left().letter();
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
