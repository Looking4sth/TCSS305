/*
 * TCSS 305 - Fall 2019
 * Assignment 4 - Road Rage
 * This class creates an abstract vehicle.
 */
package model;

import java.util.Random;
/**
 * This is a abstract class for all vehicles, which provides
 * their common properties.
 * @author Hung Vu
 * @version 25 11 2019
 */
abstract class AbstractVehicle implements Vehicle {
    /**
     * Instance field myX is X coordinate of vehicle.
     */
    private int myX;
    /**
     * Instance field myY is Y coordinate of vehicle.
     */
    private int myY;
    /**
     * Instance field myCounter is counter for number of death-turn.
     */
    private int myCounter;
    /**
     * Instance field myDeathTime provides number of death-turn.
     */
    private final int myDeathTime;
    /**
     * Instance field myDefaultX provides initial X-coordinate of vehicle.
     */
    private final int myDefaultX;
    /**
     * Instance field myDefaultY provides initial Y-coordinate of vehicle.
     */
    private final int myDefaultY;
    /**
     * Instance field myDefaultDirection is initial direction of vehicle.
     */
    private final Direction myDefaultDirection;
    /**
     * Instance field myAlive indicate whether the vehicle is dead or not.
     * True is alive.
     * False is dead.
     */
    private boolean myAlive;
    /**
     * Instance field myDirection provides direction the vehicle is heading toward.
     */
    private Direction myDirection;
    /**
     * Instance field myPathChoice create a random object to choose available direction.
     */
    private Random myPathChoice = new Random();
    /**
     * Super constructor for vehicle-type object.
     * @param theX X-coordinate of vehicle.
     * @param theY Y-coordinate of vehicle.
     * @param theDir Direction of vehicle
     * @param theDeathTime number of vehicle's death-turn.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir, 
                              final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDirection = theDir;
        myAlive = true;
        myCounter = 0;
        myDefaultX = theX;
        myDefaultY = theY;
        myDefaultDirection = theDir;
        myDeathTime = theDeathTime;
    }

    /**
     * Return the direction of vehicle.
     * @return Direction of vehicle.
     */
    public Direction getDirection() {
        return myDirection;
    }
    /**
     * This method return X coordinate of vehicle.
     * @return X coordinate of vehicle.
     */
    public int getX() {
        return myX;
    }
    /**
     * This method return Y coordinate of vehicle.
     * @return Y coordinate of vehicle.
     */
    public int getY() {
        return myY;
    }
    /**
     * This method return dead-or-alive status of vehicle.
     * @return dead-or-alive status of vehicle.
     */
    public boolean isAlive() {
        return myAlive;
    }
    /**
     * This method return death time of vehicle.
     * @return Death time of vehicle.
     */
    public int getDeathTime() {
        return myDeathTime;
    }
    /**
     * Return image file name corresponding to vehicle's alive status.
     * @return Image file name corresponding to vehicle's alive status.
     */
    public String getImageFileName() {
        if (isAlive()) {
            return getClass().getSimpleName().toLowerCase() + ".gif";
        } else {
            return getClass().getSimpleName().toLowerCase() + "_dead.gif";
        }
    }
    /**
     * This method return random object to randomize available path of vehicle.
     * @return random object to randomize available path of vehicle.
     */
    protected Random getRandom() {
        return myPathChoice;
    }
    /**
     * This method set the direction of vehicle.
     * @param theDir new Direction
     */
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }
    /**
     * This method will set new X coordinate to vehicle.
     * @param theX new X-coordinate
     */
    public void setX(final int theX) {
        myX = theX;
    }
    /**
     * This method will set new Y coordinate to vehicle.
     * @param theY new Y-coordinate
     */
    public void setY(final int theY) {
        myY = theY;
    }
    /**
     * This method revives a vehicle after a certain of turn.
     */
    public void poke() {
        if (!isAlive()) {
            myCounter++;
        }
        if (myCounter == getDeathTime()) {
            myCounter = 0;
            myAlive = true;
        }
    }
    /**
     * This method set everything on the GUI to the initial state.
     */
    public void reset() {
        setX(myDefaultX);
        setY(myDefaultY);
        setDirection(myDefaultDirection);
        myAlive = true;      
    }
    /**
     * This method provides vehicle's respective behavior at the time
     * of collision.
     * @param theOther Other vehicle in the collision
     */
    public void collide(final Vehicle theOther) {
        if (this.isAlive() && theOther.isAlive()) {
            if (getDeathTime() > theOther.getDeathTime()) {
                myAlive = false;
            } else if (getDeathTime() == theOther.getDeathTime()) {
                myAlive = true;
            } else if (getDeathTime() < theOther.getDeathTime()) {
                myAlive = true;
            }
        }
    }
    /**
     * This method returns state of vehicle at a certain time.
     * @return state of vehicle at a certain time.
     */
    public String toString() {
        if (isAlive()) {
            return getClass().getSimpleName() + " is alive at (" 
                            + String.valueOf(myX) + ", " + String.valueOf(myY) + "), moving " 
                            + getDirection().toString() + ".";
        } else {
            return getClass().getSimpleName() + " is dead and will be revived after " 
                            + String.valueOf(getDeathTime()) + " steps.";
        }
    }
}
