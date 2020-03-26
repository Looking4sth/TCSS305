/*
 * TCSS 305 - Fall 2019
 * Assignment 4 - Road Rage
 * This class test Atv vehicle.
 */
package model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
/**
 * This class test Atv vehicle.
 * @author Hung Vu
 * @version 25 11 2019
 */
class AtvTest {
    /**
     * The number of times needed to highly ensure (near 100%) that all
     * random possibility are explored.
     */
    private static final int RANDOMNESS_TRIES = 10000000;
    /**
     * This is a main Atv used in this test.
     */
    private Atv myTestAtv = new Atv(1, 2, Direction.WEST);
    /**
     * This is another Atv used to test main Atv's collision behavior.
     */
    private Atv myOtherAtv = new Atv(1, 2, Direction.WEST);
    /**
     * This is a test truck used to test main Atv's collision behavior and
     * turn Atv in to death-state.
     */
    private Truck myTestTruck = new Truck(1, 2, Direction.EAST);
    /**
     * This is a human used to test main Atv's collision behavior.
     */
    private Human myTestHuman = new Human(1, 2, Direction.EAST);
    
    /**
     * This is a test method for Atv constructor.
     */
    @Test
    void testAtvConstructor() {
        assertEquals(1, myTestAtv.getX());
        assertEquals(2, myTestAtv.getY());
        assertEquals(25, myTestAtv.getDeathTime());
        assertEquals(Direction.WEST, myTestAtv.getDirection());
        assertTrue(myTestAtv.isAlive());
    }
    
    /**
     * This is a test method for Atv setters and reset method.
     */
    @Test
    void testAtvSetterAndReset() {
        myTestAtv.setX(3);
        myTestAtv.setY(4);
        myTestAtv.setDirection(Direction.EAST);
        
        assertEquals(3, myTestAtv.getX());
        assertEquals(4, myTestAtv.getY());
        myTestAtv.setDirection(Direction.EAST);
        
        myTestAtv.reset();
        assertEquals(1, myTestAtv.getX());
        assertEquals(2, myTestAtv.getY());
        assertEquals(Direction.WEST, myTestAtv.getDirection());
    }
    
    /**
     * This is a test method for Atv getImageFileName method.
     */
    @Test
    void testGetImageFileName() {   
        assertEquals("atv.gif", myTestAtv.getImageFileName());
        myTestAtv.collide(myTestTruck);
        assertEquals("atv_dead.gif", myTestAtv.getImageFileName());
    }
    /**
     * This is a test method for Atv Poke method.
     */
    @Test
    void testPoke() {
        myTestAtv.collide(myTestTruck);
        for (int i = 0; i < 25; i++) {
            myTestAtv.poke();
        }
        assertTrue(myTestAtv.isAlive());
    }
    /**
     * This is a test method for Atv collide method.
     */
    @Test
    void testCollide() {
        myTestAtv.collide(myOtherAtv);
        assertTrue(myTestAtv.isAlive());
        myTestAtv.collide(myTestHuman);
        assertTrue(myTestAtv.isAlive());
        myTestAtv.collide(myTestTruck);
        assertFalse(myTestAtv.isAlive());
    }
    /**
     * This is a test method for Atv canPass method.
     */
    @Test
    void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.GRASS);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.TRAIL);
        validTerrain.add(Terrain.WALL);
        final List<Light> validLight = new ArrayList<>();
        validLight.add(Light.GREEN);
        validLight.add(Light.RED);
        validLight.add(Light.YELLOW);
        for (final Terrain t : validTerrain) {
            for (final Light l : validLight) {
                if (t == Terrain.WALL) {
                    assertFalse(myTestAtv.canPass(t, l));
                } else {
                    assertTrue(myTestAtv.canPass(t, l));
                }
            }
        }
    }
    /**
     * This is a test method for Atv chooseDirection method.
     */
    @Test
    void testChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.CROSSWALK);
        neighbors.put(Direction.EAST, Terrain.CROSSWALK);
        neighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        neighbors.put(Direction.SOUTH, Terrain.CROSSWALK);
        for (int i = 0; i < RANDOMNESS_TRIES; i++) {
            assertNotEquals(myTestAtv.getDirection().reverse(), 
                            myTestAtv.chooseDirection(neighbors));
        }
    }
    /**
     * This is a test method for Atv toString method.
     */
    @Test
    void testToString() {
        assertEquals("Atv is alive at (1, 2), moving WEST.", myTestAtv.toString());
        myTestAtv.collide(myTestTruck);
        assertEquals("Atv is dead and will be revived after 25 steps.", myTestAtv.toString());
    }
}
