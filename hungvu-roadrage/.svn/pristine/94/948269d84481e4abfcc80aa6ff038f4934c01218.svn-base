/*
 * TCSS 305 - Fall 2019
 * Assignment 4 - Road Rage
 * This class creates a Truck object for simulator.
 */
package model;

import java.util.ArrayList;
import java.util.Map;
/**
 * This class creates properties of Truck.
 * @author Hung Vu
 * @version 25 11 2019
 */
public class Truck extends AbstractVehicle {
    /**
     * This is Death time of Truck vehicle.
     */
    private static final int MY_DEATH_TIME = 0;
    /**
     * Constructor for Truck object.
     * @param theX X-coordinate of vehicle
     * @param theY Y-coordinate of vehicle
     * @param theDir Direction of vehicle
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
    }
    /**
     * This method let the program know whether Truck can pass 
     * a certain terrain or not (with condition).
     * @param theTerrain The terrain in Truck direction.
     * @param theLight The light color in Truck direction
     * @return True if a Truck can pass, false otherwise.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET
                        || theTerrain == Terrain.LIGHT 
                        || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED);
    }
    /**
     * This method return the next direction for a Truck vehicle at 
     * a specific point based on given rules.
     * @param theNeighbors a map contains neighbors terrain with their
     * relative direction to a vehicle.
     * @return new Direction for vehicle based on given rules.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final ArrayList<Direction> availablePath = new ArrayList<Direction>();
        if (theNeighbors.get(getDirection()) == Terrain.STREET 
                        || theNeighbors.get(getDirection()) == Terrain.LIGHT 
                        || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            availablePath.add(getDirection());
        }
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().left()) == Terrain.LIGHT 
                        || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            availablePath.add(getDirection().left());
        } 
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().right()) == Terrain.LIGHT 
                        || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            availablePath.add(getDirection().right());
        }
        if (availablePath.isEmpty()) {
            return getDirection().reverse();
        } else {
            return availablePath.get(getRandom().nextInt(availablePath.size()));
        }
                        
    }

}
