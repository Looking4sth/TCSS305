/*
 * TCSS 305 - Fall 2019
 * Assignment 4 - Road Rage
 * This class creates an Atv object for simulator.
 */
package model;

import java.util.ArrayList;
import java.util.Map;
/**
 * This class creates properties of Atv.
 * @author Hung Vu
 * @version 25 11 2019
 */
public class Atv extends AbstractVehicle {
    /**
     * This is Death time of Atv vehicle.
     */
    private static final int MY_DEATH_TIME = 25;
    /**
     * Constructor for Atv object.
     * @param theX X-coordinate of vehicle
     * @param theY Y-coordinate of vehicle
     * @param theDir Direction of vehicle
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, MY_DEATH_TIME);
       
    }
    /**
     * This method let the program know whether Atv can pass 
     * a certain terrain or not (with condition).
     * @param theTerrain The terrain in Atv direction.
     * @param theLight The light color in Atv direction
     * @return True if a Atv can pass, false otherwise.
     */
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return !(theTerrain == Terrain.WALL);
    }
    /**
     * This method return the next direction for aan Atv vehicle at 
     * a specific point based on given rules.
     * @param theNeighbors a map contains neighbors terrain with their
     * relative direction to a vehicle.
     * @return new Direction for vehicle based on given rules.
     */
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final ArrayList<Direction> availablePath = new ArrayList<Direction>();
        if (theNeighbors.get(getDirection()) != Terrain.WALL) {
            availablePath.add(getDirection());
        }
        if (theNeighbors.get(getDirection().left()) != Terrain.WALL) {
            availablePath.add(getDirection().left());
        }
        if (theNeighbors.get(getDirection().right()) != Terrain.WALL) {
            availablePath.add(getDirection().right());
        }
        return availablePath.get(getRandom().nextInt(availablePath.size()));
    }

}
