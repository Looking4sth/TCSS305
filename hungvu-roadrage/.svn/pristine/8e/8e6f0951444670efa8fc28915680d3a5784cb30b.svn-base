/*
 * TCSS 305 - Fall 2019
 * Assignment 4 - Road Rage
 * This class creates a Human object for simulator.
 */
package model;

import java.util.ArrayList;
import java.util.Map;
/**
 * This class creates properties of Human.
 * @author Hung Vu
 * @version 25 11 2019
 */
public class Human extends AbstractVehicle {
    /**
     * This is Death time of Human vehicle.
     */
    private static final int MY_DEATH_TIME = 45;
    /**
     * Constructor for Human object.
     * @param theX X-coordinate of vehicle
     * @param theY Y-coordinate of vehicle
     * @param theDir Direction of vehicle
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }
    /**
     * This method let the program know whether Human can pass 
     * a certain terrain or not (with condition).
     * @param theTerrain The terrain in Human direction.
     * @param theLight The light color in Human direction
     * @return True if a Human can pass, false otherwise.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.GRASS 
                        || (theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN);
    }
    /**
     * This method return the next direction for a Human vehicle at 
     * a specific point based on given rules.
     * @param theNeighbors a map contains neighbors terrain with their
     * relative direction to a vehicle.
     * @return new Direction for vehicle based on given rules.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final char direction;
        final ArrayList<Direction> availablePath = new ArrayList<Direction>();
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            direction = getDirection().letter();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            direction = getDirection().left().letter();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            direction = getDirection().right().letter();

        } else {
            if (theNeighbors.get(getDirection()) == Terrain.GRASS) {
                availablePath.add(getDirection());
            } 
            if (theNeighbors.get(getDirection().left()) == Terrain.GRASS) {
                availablePath.add(getDirection().left());
            }
            if (theNeighbors.get(getDirection().right()) == Terrain.GRASS) {
                availablePath.add(getDirection().right());
            } 
            if (availablePath.isEmpty()) {
                return getDirection().reverse();
            } else {
                return availablePath.get(getRandom().nextInt(availablePath.size()));
            }
        }
        return Direction.valueOf(direction);
    }

}
